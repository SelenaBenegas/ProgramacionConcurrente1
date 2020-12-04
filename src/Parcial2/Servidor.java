package Parcial2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Servidor implements Runnable {

    private Impresora impresora;

    public Servidor(Impresora i) {
        impresora = i;

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            impresora.imprimir();
        }
    }

}
