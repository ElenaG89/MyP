package shamir.polinomios;

import java.math.BigInteger;
import java.util.Random;
import java.util.Vector;
import java.io.*;

/**
 * Clase que construye un polinomio dada una llave/contraseña y el grado del polinomio.
 * Los coeficientes del poliniomio se construyen aleatoriamente.
 */
public class PolinomioS {
	private BigInteger[] coeficientes;
	private int grado;
	private BigInteger primo = new BigInteger("208351617316091241234326746312124448251235562226470491514186331217050270460481");

	/**
	 * Método que genera el polinomio donde se escondera la llave de cifrado.
	 * @param grado Grado del polinomio.
	 * @param llave de cifrado.
	 */
	public PolinomioS(int grado, String llave) {
		BigInteger k = new BigInteger(llave,16); //
		k = k.mod(primo); 
		this.coeficientes = new BigInteger[grado + 1];
		this.grado = grado;
		this.coeficientes[0] = k;
		for(int i = 1; i <= grado; i++) {
			BigInteger coeficiente = new BigInteger(primo.bitLength(),new Random());
			while(coeficiente.compareTo(primo) >= 0 || coeficiente.compareTo(BigInteger.ZERO)==0) {
				coeficiente = new BigInteger(primo.bitLength(),new Random());
			}
			this.coeficientes[i] = coeficiente;
		}
	}

	/**
	 * Método que evalua el polinomio en x .
	 * @param x Valor que será evaluado en el polinomio.
	 * @return Valor del polinomio luego de evaluar.
	 */
	public BigInteger Evaluar(BigInteger x) {
		BigInteger value = new BigInteger("0");
		for(int i = 0; i <= this.grado; i++) {
			BigInteger coef = this.coeficientes[i];
			value = value.add(coef.multiply(x.modPow(BigInteger.valueOf((long) i), primo)).mod(primo)).mod(primo);
		}
		return value;
	}

	/**
	 * Método que obtiene la pareja (x,P(x)) en forma de cadena.
	 * @param x Valor que se evalua en el polinomio.
	 * @return pareja x,P(x)=evaluación del polinomio
	 */
	public String parejaEval(BigInteger x) {
		return x.toString() + "," + Evaluar(x).toString() + "\n";
	}

	/**
	 * Método que obtiene una lista con las n evaluaciones del polinómio.
	 * @param n número de evaluaciones
	 * @return lista de n parejas con el valor evaluado y su evaluación[P(x)].
	 */
	public String evaluarNValores(int n) {
		String lista = "";
		for(int i = 0; i < n; i++) {
			BigInteger x = new BigInteger(primo.bitLength(),new Random()); 
			while(x.compareTo(primo) >= 0 || x.compareTo(BigInteger.ZERO)==0) {
				x = new BigInteger(primo.bitLength(),new Random());
			}
			lista += parejaEval(x);
		}
		return lista;
	}

	/**
	 * Método que escribe n evaluaciones en un archivo con extensión .frg.
	 * @param n Número de evaluaciones.
	 * @param archivoEval Nombre del archivo donde se guardarán las evaluaciones.
	 */
	public void archivoEvaluaciones(int n, String archivoEval) {
		String evaluaciones = this.evaluarNValores(n);
		Writer escribir;
		try {
			String archivo = archivoEvals(archivoEval);
			escribir = new FileWriter(archivo, false);
			escribir.write(evaluaciones);
			escribir.close();
			System.out.println("Las evaluaciones se guardaron en el archivo: " + archivo);
		} catch(Exception e) {
			System.err.println(e);
	    	System.exit(1);
		}
	}


        /**
	 * Método que agrega al archivo con las n evaluaciones la extensión .frg.
	 * @param archivoEval Archivo con las evaluaciones.
	 */
	public static String archivoEvals(String archivoEval) {
		String archivo_piezas;
		if(archivoEval.lastIndexOf('.') == -1){
			archivo_piezas = archivoEval + ".frg";
		}
	    else {
	    	archivo_piezas = archivoEval.substring(0,archivoEval.lastIndexOf('.'))+".frg";
	    }
	    return archivo_piezas;
	}
}
