package tp6.pasteleriaBQ;

import tp6.Pasteleria.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Robot implements Runnable {

    private Pasteleria pasteleria;
    private String nombre;

    public Robot(Pasteleria p, String n) {
        pasteleria = p;
        nombre = n;
    }

    @Override
    public void run() {
        while (true) {
            pasteleria.trabajar(nombre);
        }
    }

}
