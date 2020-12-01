package tp6.pasteleriaBQ;

import tp6.Pasteleria.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Brazo implements Runnable {

    private Pasteleria pasteleria;
    private String nombre;

    public Brazo(Pasteleria p, String n) {
        pasteleria = p;
        nombre = n;
    }

    @Override
    public void run() {
        while (true) {
            pasteleria.retirarCaja(nombre);
            try {
                Thread.sleep((long) (Math.random() + 5) * 2000); // tiempo en cambiar de caja
            } catch (InterruptedException ex) {
                Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
            }
            pasteleria.ponerCaja(nombre);
        }
    }

}
