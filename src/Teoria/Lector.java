package Teoria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Lector implements Runnable {

    private LibroConMonitores libro;
    private String nombre;

    public Lector(LibroConMonitores l, String n) {
        libro = l;
        nombre = n;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random() + 5) * 1500); // hacen otra cosa
                try {
                    libro.leer(nombre);
                    Thread.sleep((long) (Math.random() + 5) * 1500);
                    libro.terminarleer(nombre);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
