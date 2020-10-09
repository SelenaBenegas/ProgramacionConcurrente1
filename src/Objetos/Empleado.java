package Objetos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Empleado implements Runnable {

    private Confiteria confiteria;

    public Empleado(Confiteria l) {
        confiteria = l;

    }

    @Override
    public void run() {
        while (true) {
            if (confiteria.entrarAConfiteria()) { //se fija si está libre la silla
                confiteria.ordenar(); //si está libre llama al mozo y etc.
            }
            try {
                Thread.sleep(1500);
                //este sleep logra que no vuelva a sentarse el mismo empleado
            } catch (InterruptedException ex) {
                Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
