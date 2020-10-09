package Objetos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Taxista implements Runnable {
    private Taxi taxi;

    public Taxista(Taxi t) {
        taxi = t;
    }

    @Override
    public void run() {
        while (true){
        System.out.println("Taxista está durmiendo");
        try {
            taxi.manejar();
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
}
