package shamir.cifdes;
import shamir.polinomios.*;
import shamir.*;

import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * Clase que implementa el cifrado y descifrado de archivos de tipo .txt
 */
public class CifradoDescifradoSh {
	private String nombreArchivoEvals;
	private String nombreArchivoTexto;


	public CifradoDescifradoSh(String nomEvals, String textoClaro) {
		this.nombreArchivoEvals = nomEvals;
		this.nombreArchivoTexto = textoClaro;
	}

         /**
	 * Método que agrega la terminación .aes al archivo que contiene el
	 * texto cifrado
	 * @return archivo Nombre del archivo con datos cifrados.
	 */
	public String archivoAES() {
		String archivo;
		if(this.nombreArchivoTexto.lastIndexOf('.') == -1){
			archivo = this.nombreArchivoTexto + ".aes";
		}
	    else {
	    	archivo = this.nombreArchivoTexto.substring(0,this.nombreArchivoTexto.lastIndexOf('.'))+".aes";
	    }
	    return archivo;
	}

          /**
	 * Método que agrega la terminación .desencriptado.txt al archivo  
	 * con el contenido descifrado.
	 * @return archivo Nombre del archivo con datos descifrados.
	 */        
	public String archivoTexto() {
		String archivo;
		String[] args=Main.getArgs();
		String ter=args[3];
	        //String ter=nomOrig.substring(nomOrig.length()-3);
		if(this.nombreArchivoTexto.lastIndexOf('.') == -1){
			archivo = this.nombreArchivoTexto +"_desencriptado."+ ter;
		}
	    else {
	    	
	    	archivo = this.nombreArchivoTexto.substring(0,this.nombreArchivoTexto.lastIndexOf('.'))+"_desencriptado."+ ter;
	    }
	    return archivo;
	}

	/**
	 * Método que aplica el cifrado AES.
	 * @param llave Llave que resulta de aplicar SHA-256.
	 */
	public void cifradoAES(BigInteger llave) {
		byte[] k = llave.toByteArray();
		String archivo = this.archivoAES();

		try {
			Cipher cifrar = Cipher.getInstance("AES");
			FileOutputStream textoCifrado = new FileOutputStream(archivo,false);
			SecretKeySpec kSec = new SecretKeySpec(k,0,16,"AES");
			cifrar.init(Cipher.ENCRYPT_MODE,kSec);
			CipherInputStream inputCifrado = new CipherInputStream(new FileInputStream(this.nombreArchivoTexto),cifrar);
			int escribir = inputCifrado.read();
			while(escribir != -1) {
				textoCifrado.write(escribir);
				escribir = inputCifrado.read();
			}
			textoCifrado.close();
			inputCifrado.close();
			System.out.println("El texto cifrado se guardo como: " + archivo);
		} catch(Exception e) {
			System.err.println(e);
	    	System.exit(1);
		}
	}

	/**
	 * Método que aplica el cifrado AES.
	 * @param n Evaluaciones que se aplicaran en el polinomio de Lagrange.
	 * @param t Minimo de puntos necesarios para descrifrar.
	 */
	public void cifrarTexto(int n, int t) {
		char[] contraseña = LlaveCifrado.obtenerContraseña(); 
	        String salt=LlaveCifrado.getSalt();
		String k = LlaveCifrado.hashPassword(contraseña,salt);
		PolinomioS polinomio = new PolinomioS(t-1,k);
		polinomio.archivoEvaluaciones(n,this.nombreArchivoEvals);
		this.cifradoAES(new BigInteger(k,16).abs());
	}

	/**
	 * Método que descrifra y guarda el texto descifrado en un txt.
	 * @param llave Valor del Polinomio en 0/K
	 */
	public void descifradoAES(BigInteger llave) {
		byte[] k = llave.toByteArray();
		String archivo = this.archivoTexto();
		try {
			Cipher cifrar = Cipher.getInstance("AES");
			FileOutputStream textoDescifrado = new FileOutputStream(archivo,false);
			SecretKeySpec kSec = new SecretKeySpec(k,0,16,"AES");
			cifrar.init(Cipher.DECRYPT_MODE,kSec);
			CipherInputStream inputCifrado = new CipherInputStream(new FileInputStream(this.nombreArchivoTexto),cifrar);
			int escribir = inputCifrado.read();
			while(escribir != -1) {
				textoDescifrado.write(escribir);
				escribir = inputCifrado.read();
			}
			textoDescifrado.close();
			inputCifrado.close();
			System.out.println("El texto descifrado se guardo en el archivo: " + archivo);
		} catch(Exception e) {
			System.err.println(e);
	    	System.exit(1);
		}
	}

	/**
	 * Método que define K y que manda a descifrar usando el valor K
	 */
	public void descifrarTexto() {
		BigInteger k = new BigInteger(LlaveCifrado.getLlave(this.nombreArchivoEvals));
		this.descifradoAES(k);
	}
	
    
	

}
