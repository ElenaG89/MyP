package shamir.cifdes;
import shamir.polinomios.*;

import java.io.*;
import java.security.MessageDigest;
import java.util.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * Clase que manipula el password dado por el usuario, genera la llave con
 * SHA+256 y la llave de cifrado para el cifrado AES y genera n evaluaciones
 * del polinomio de Lagrange.
 */
public class LlaveCifrado {

	/**
	 * Método que solicita una contraseña al usuario para encriptar su archivo.
	 * @return password Arreglo de caracteres con la contraseña.
	 */
	public static char[] obtenerContraseña() {
		Console consola = System.console();		
		char[] password =consola.readPassword("Escriba la Contraseña que desea usar para cifrar su archivo: ");
		return password;
	}

	/**
	 * Método que aplica el hashing SHA-256 a una contraseña con SAL.
	 * @param password la contraseña del usuario.
	 * @return cadena de bytes que representan la llave de cifrado.
	 */
	public static String hashPassword(char[] password, String salt) {
		String hashP= null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(salt.getBytes());
			String passwordStr = new String(password);
			hashP=bytesToHex(md.digest(passwordStr.getBytes()));
		} catch(Exception e) {
			System.err.println(e);
	    	System.exit(1);
		}
		return hashP;
	}

        
	/**
	 * Método que recupera las evaluaciones de una archivo con parejas (x,P(X)).
	 * Obtiene un arreglo de vectores {x,P(x)}
	 * @param archivoE cadena que representa el nombre del archivo que contiene las
	 *  evaluaciones.
	 * @return arreglo con todas las parejas x,P(x)
	 */
	public static Vector[] vectorEvaluaciones(String archivoE) {
		Vector[] arreglop = null;
		LinkedList<Vector> lista = new LinkedList<Vector>();
       	try {
            String parEvals;
            FileReader archivoaeval = new FileReader(archivoE);
            BufferedReader br = new BufferedReader(archivoaeval);
            while((parEvals=br.readLine()) != null) {
            	Vector punto = new Vector(2);
            	String[] entradas = parEvals.split(",");
            	punto.add(0,new BigInteger(entradas[0]));
            	punto.add(1,new BigInteger(entradas[1]));
				lista.add(punto);
            }
	    	br.close();
	    	arreglop = listaAVectorArray(lista);
	    	return arreglop;
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
		}
		return null;
    }

    /**
     * Método que tranforma una lista ligada de vectores en un arreglo de vectores.
     * @param lista Lista ligada de vectores.
     * @return Arreglo de vectores.
     */
    public static Vector[] listaAVectorArray(LinkedList<Vector> lista) {
    	Vector[] arreglo = new Vector[lista.size()];
    	for(int i = 0; i < arreglo.length; i++){
			arreglo[i] = (Vector)lista.get(i);
		}
		return arreglo;
    }

    /**
     * Método que recupera la llave de cifrado usando el archivo que contiene los
     * distintos valores del polinomio y evalua en cero.
     * @param archivoE nombre del archivo de evaluaciones.
     * @return arreglo de bytes que representa a la llave de cifrado K.
     */
    public static byte[] getLlave(String archivoE) {
    	Vector[] array = vectorEvaluaciones(archivoE);
    	return Lagrange.interpoLagrange(new BigInteger("0"),array).toByteArray();
    }
	
    /** 
    *Metodo que transforma un arreglo de bytes a cadena.
    *@param bytes Arreglo de bytes.
    *@return String Texto deseado.
    */
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            return result.toString();
    }
    
    
    /** Método que agrega SAL al proceso de hashing SHA-256 */
     public static String getSalt() /*throws NoSuchAlgorithmException*/ {
        try{
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
        }
        catch (Exception e) {
        };
        return "";
    }
    
    

}
