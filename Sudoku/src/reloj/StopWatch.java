package src.reloj;
import src.*;
/**
 *
 * @author 
 */

public class StopWatch {

    private long tiempoInicio = 0;
    private long tiempoFin = 0;
    private long pausa = 0;
    private boolean enEjecucion = false;
    
    /*Metodo que inicia el reloj*/
    public void iniciar() {
        this.tiempoInicio = System.currentTimeMillis();
        pausa = 0;
        this.enEjecucion = true;
    }

    /*Metodo que para el reloj*/
    public void stop() {
        this.tiempoFin = System.currentTimeMillis();
        this.enEjecucion = false;
    }

    /*Metodo que pausa el reloj*/
    public void pausar() {
        if(enEjecucion) {
            pausa = System.currentTimeMillis();
            stop();
        }
    }

    /*Metodo que reinicia el reloj*/
    public void continuar() {
        if(!enEjecucion){
            long duracion = System.currentTimeMillis() - pausa;
            tiempoInicio += duracion;
            this.enEjecucion = true;
        }
    }

    /*Metodo que determina el Tiempo transcurrido en milisegundos*/
    public long tiempoTranscu() {
        long transcurrido;
        if (enEjecucion) {
            transcurrido = (System.currentTimeMillis() - tiempoInicio);
        } else {
            transcurrido = (tiempoFin - tiempoInicio);
        }
        return transcurrido;
    }
}
