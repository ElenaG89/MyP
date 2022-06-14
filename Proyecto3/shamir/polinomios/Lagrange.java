package shamir.polinomios;


import java.math.BigInteger;
import java.util.*;

/**
 * Clase que implementa el algoritmo para construir un polinomio mediante
 * el método de interpolación de lagrange.
 * Los coeficientes del polinomio se encuentran en Zp con p primo.
 */
public class Lagrange {

	/**
	 * Número primo usado como el tamaño del módulo en el campo finito que se
	 * usará (el que dió el profe)
	 */
	private static BigInteger primo = new BigInteger("208351617316091241234326746312124448251235562226470491514186331217050270460481");
	
	/**1|
	 * Método para construir el polinomio de interpolación dados t puntos usando el 
	 * algoritmo de interpolación de Lagrange.
	 * @param x Valor que será evaluado en el polinomio.
	 * @param evaluaciones Arreglo de puntos que permiten construir el polinomio.
	 * @return evaluación de x en el polinomio.
	 */
	public static BigInteger interpoLagrange(BigInteger x, Vector[] evaluaciones) {
		BigInteger k = new BigInteger("0");
		BigInteger denominador = new BigInteger("1");
		BigInteger numerador = new BigInteger("1");
		BigInteger x_j = null, x_i = null, cociente = null;
		for(int i = 0; i < evaluaciones.length; i++) {
			numerador = numeradorLagrange(x, evaluaciones,i);
	   		denominador = denominadorLagrange(evaluaciones,i);
			cociente = numerador.multiply(denominador.modInverse(primo)).mod(primo);
			k = k.add(((BigInteger)evaluaciones[i].elementAt(1)).multiply(cociente).mod(primo)).mod(primo);
		}
		return k;
	}

	/**
	 * Método para construir el numerador del termino Li del algoritmo de Lagrange.
	 * @param x Valor que será evaluado en el polinomio construido por interpolación de lagrange.
	 * @param evaluaciones Arreglo de puntos usados para construir el polinomio.
	 * @param i Indice del termino Li en la interpolación de Lagramge.
	 * @return evaluación del valor x en el numerador de Li.
	 */
	public static BigInteger numeradorLagrange(BigInteger x, Vector[] evaluaciones, int i) {
		BigInteger numerador = new BigInteger("1");
		for(int j = 0; j < evaluaciones.length; j++){
			if(j != i) {
				BigInteger x_j = (BigInteger) evaluaciones[j].elementAt(0);
				numerador = numerador.multiply(x.subtract(x_j).mod(primo)).mod(primo);
			}
		}
		return numerador;
	}

	/**
	 * Método para construir el denominador del termino Li del algoritmo de Lagrange.
	 * @param evaluaciones Arreglo de puntos usado para construir el polinomio.
	 * @param i Indice del termino Li en la interpolación de Lagramge.
	 * @return evaluación del valor x en el denominador de Li.
	 */
	public static BigInteger denominadorLagrange(Vector[] evaluaciones, int i) {
		BigInteger denominador = new BigInteger("1");
		for(int j = 0; j < evaluaciones.length; j++){
			if(j != i) {
				BigInteger x_i = (BigInteger) evaluaciones[i].elementAt(0);
				BigInteger x_j = (BigInteger) evaluaciones[j].elementAt(0);
				denominador = denominador.multiply(x_i.subtract(x_j).mod(primo)).mod(primo);
			}
		}
		return denominador;
	}
}
