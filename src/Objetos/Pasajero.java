package Objetos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Pasajero implements Runnable {

    private Taxi taxi;
    private String nombre;

    public Pasajero(String nom, Taxi t) {
        nombre = nom;
        taxi = t;
    }

    public void run() {
        System.out.println(nombre + " necesita un taxi");
        if (taxi.subirTaxi()) {
            try {
                taxi.viajar();
            } catch (InterruptedException ex) {
                Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
