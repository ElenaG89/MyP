package shamir;
import shamir.cifdes.CifradoDescifradoSh;

import java.util.*;
import java.io.*;


/**
 * Clase principal que permite ejecutar el cifrado y descifrado con el
 * esquema secreto de Shamir.
 */
public class Main{
    private static String[] savedArgs;
    
    public static String[] getArgs(){
        return savedArgs;
    }
    
    public static void main(String args[]) throws IOException {
        savedArgs =args;
        if (args[0].equals("c")) {
            if (args.length == 5 ) {
            try{
            int x=Integer.parseInt(args[2]);
            int y=Integer.parseInt(args[3]);
                CifradoDescifradoSh cif= new CifradoDescifradoSh(args[1],args[4]);
                if(Integer.parseInt(args[2])>2 && Integer.parseInt(args[2])>=Integer.parseInt(args[3]))
                cif.cifrarTexto(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
                else    System.out.println("El número de puntos para descifrar la llave no puede ser superior a las evaluaciones en el Polinomio de Lagrange.");
            }
            catch (NumberFormatException e) {
                System.out.println("No se introdujeron números enteros para las evaluaciones y las piezas a utilizar");
}
            } else {
                System.out.println("No se introdujo el número correcto de argumentos (deben ser 4 en total)");
            }
            
        } else if (args[0].equals("d")) {
            if (args.length == 4) {
                CifradoDescifradoSh des = new CifradoDescifradoSh(args[1],args[2]);
                des.descifrarTexto();
            } else {
                System.out.println("Número incorrecto de argumentos. Deben ser tres argumentos.");
            }
        }
        else {
            System.out.println("Opción incorrecta.¿Escogiste \"c\" para cifrar o \"d\" para descifrar ");
        }
    }


}



/*Referencias
https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#
https://www.geeksforgeeks.org/path-touri-method-in-java-with-examples/
https://docs.oracle.com/javase/7/docs/api/javax/crypto/spec/SecretKeySpec.html
https://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html#ENCRYPT_MODE
https://docs.oracle.com/javase/7/docs/api/javax/crypto/spec/GCMParameterSpec.html
https://docs.oracle.com/javase/8/docs/api/javax/crypto/spec/PBEKeySpec.html#PBEKeySpec-char:A-byte:A-int-int-
https://www.w3schools.com/java/ref_string_lastindexof.asp
https://docs.oracle.com/javase/7/docs/api/java/io/FileOutputStream.html
https://stackoverflow.com/questions/25428768/java-eclipse-how-to-call-a-int-or-string-from-another-class-in-same-project
*/
