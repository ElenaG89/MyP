package src.sudoku;
import src.*;
/**
 *
 * @author 
 */
import java.util.Arrays;
import java.util.Random;

public class Sudoku {

    public static final int[][] tablero_9X9 = {
        {4, 3, 5, 8, 7, 6, 1, 2, 9},
        {8, 7, 6, 2, 1, 9, 3, 4, 5},      
        {2, 1, 9, 4, 3, 5, 7, 8, 6},
        {5, 2, 3, 6, 4, 7, 8, 9, 1},
        {9, 8, 1, 5, 2, 3, 4, 6, 7},
        {6, 4, 7, 9, 8, 1, 2, 5, 3},
        {7, 5, 4, 1, 6, 8, 9, 3, 2},
        {3, 9, 2, 7, 5, 4, 6, 1, 8},
        {1, 6, 8, 3, 9, 2, 5, 7, 4}};
    public static final int modoJuegoMedio = 1;
    public static final String VALORES_9x9 = "123456789";
    private int[][] rc;
    private Random random = new Random();

    // Crear una copia de un arreglo [][]
    private int[][] copyOf(int[][] original) {
        int[][] copia = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copia[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copia;
    }

    // Intercambiar filas de un arreglo [][]
    private int[][] intercambiarFila(int[][] tablero, int fila1, int fila2) {
        for (int j = 0; j < tablero.length; j++) {
            int temp = tablero[fila1][j];
            tablero[fila1][j] = tablero[fila2][j];
            tablero[fila2][j] = temp;
        }
        return tablero;
    }

    // Intercambiar columnas de un arreglo [][]
    private int[][] intercambiarCol(int[][] tablero, int col1, int col2) {
        for (int i = 0; i < tablero.length; i++) {
            int temp = tablero[i][col1];
            tablero[i][col1] = tablero[i][col2];
            tablero[i][col2] = temp;
        }
        return tablero;
    }

    /* Intercambiar las filas y columnas de un tablero. */
    private int[][] interFilasCols(int[][] tablero) {

        int range =7 ;
        int filasTablero = 3 ;
        int colsTablero = 3;

        for (int a = 0; a < range; a += filasTablero) {
            int row[] = getDosRanNum(a, filasTablero);
            intercambiarFila(tablero, row[0], row[1]);
        }

        for (int a = 0; a < range; a += colsTablero) {
            int[] col = getDosRanNum(a, colsTablero);
            intercambiarCol(tablero, col[0], col[1]);
        }
        return tablero;
    }

    // Intercambiar grupos horizontales
    private int[][] interCasillas(int[][] tablero) {
        int firstgrid = 1 + random.nextInt(3);
        int secondgrid = 1 + random.nextInt(3);
        int numFilTab = 3 ;

        if ((firstgrid == 1 && secondgrid == 2) || (firstgrid == 2 && secondgrid == 1)) {
            for (int i = 0; i < numFilTab; i++) {
                intercambiarFila(tablero, i, i + numFilTab);
            }
        } else if ((firstgrid == 2 && secondgrid == 3) || (firstgrid == 3 && secondgrid == 2)) {
            for (int i = numFilTab; i < numFilTab * 2; i++) {
                intercambiarFila(tablero, i, i + numFilTab);
            }
        } else if ((firstgrid == 1 && secondgrid == 3) || (firstgrid == 3 && secondgrid == 1)) {
            for (int i = 0; i < numFilTab; i++) {
                intercambiarFila(tablero, i, i + (numFilTab * 2));
            }
        }
        return tablero;
    }

    // intercambiar los numeros entre filas.
    private int[][] interNums(int[][] tablero) {
        int[] num = getDosRanNum(1, tablero.length);
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] == num[0]) {
                    tablero[i][j] = num[1];
                } else if (tablero[i][j] == num[1]) {
                    tablero[i][j] = num[0];
                }
            }
        }
        return tablero; 
    }

    // generar dos numeros aleatorios como un arreglo de longitud 2.
    private int[] getDosRanNum(int min, int adicional) {
        int a[] = new int[2];
        a[0] = min + random.nextInt(adicional);
        a[1] = min + random.nextInt(adicional);
        return a;
    }

    // Crear un tablero de Sudoku.
    private int[][] creartablero(int[][] tablero) {
        for (int i = 0; i < 10; i++) {
            interFilasCols(tablero);
            interCasillas(tablero);
            interNums(tablero);
        }
        return tablero;
    }

    // Esconder algunos numeros para crear el Sudoku.
    private int[][] crearS(int[][] tablero, int modo) {
        this.rc = copyOf(tablero);
        //El numero de bloques vacios depende del modo de juego: solo se usa intermedio
        int numBloquesVacios = getNumBloquesVacios(tablero, modo);
        for (int i = 0; i < numBloquesVacios; i++) {
            int[] rowcol = getDosRanNum(0, tablero.length);
            this.rc[rowcol[0]][rowcol[1]] = 0;
        }
        return copyOf(this.rc);
    }

    /* Método que define el numero de bloques vacios dependiendo del modo de juego.
       Por default se puso que se juegue en modo medio.
    */
    private int getNumBloquesVacios(int[][] tablero, int modo) {
        int numBloquesVacios = 0;
        //FilasxColumnas en el arreglo tablero_9X9
        int numBloques = tablero.length * tablero[0].length;
            numBloquesVacios = (int) Math.floor((modoJuegoMedio * numBloques) / 100);
        // Se agrega una unidad al numero de bloques vacio para asegurar que sea un
        // entero
        int adicional = (int) Math.floor(((numBloques - numBloquesVacios) * 5) / 100);
        numBloquesVacios += random.nextInt(adicional + 1); 

        return numBloquesVacios;
    }

    /* Checar si la solución es correcta.
    
    */
    public boolean check(int[][] tablero) {
        boolean esCorrecto = true;
        int filasEnCuadricula = 3 ;
        final String setValues =VALORES_9x9 ;
        // revisar las filas
        for (int i = 0; i < tablero.length; i++) {
            String set = setValues;
            for (int j = 0; j < tablero.length; j++) {
                set = set.replace("" + tablero[i][j], "");
            }
            if (!set.isEmpty()) {
                esCorrecto = false;
                return esCorrecto;
            }
        }

        // revisar las columnas
        for (int j = 0; j < tablero.length; j++) {
            String set = setValues;
            for (int i = 0; i < tablero.length; i++) {
                set = set.replace("" + tablero[i][j], "");
            }
            if (!set.isEmpty()) {
                esCorrecto = false;
                return esCorrecto;
            }
        }

        //revisar las cuadriculas horizontal y verticalmente
        for (int hg = 0; hg < tablero.length; hg += filasEnCuadricula) {
            for (int vg = 0; vg < tablero[0].length; vg += 3) {
                String set = setValues;
                for (int i = hg; i < (hg + filasEnCuadricula); i++) {
                    for (int j = vg; j < vg + 3; j++) {
                        set = set.replace("" + tablero[i][j], "");
                    }
                }
                if (!set.isEmpty()) {
                    esCorrecto = false;
                    return esCorrecto;
                }
            }
        }

        return esCorrecto;
    }

    /*Metodo que llama a crear un tablero*/
    public int[][] getNvoRC(int grid, int modoJuego) {
        return crearS(creartablero(tablero_9X9), modoJuego);
    }


    public int[][] resetPuzzle() {
        return rc;
    }
 
     private void printArray(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
 
}
