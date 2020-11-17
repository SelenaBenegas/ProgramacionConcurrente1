package Teoria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Escritor implements Runnable {

    private LibroConMonitores libro;
    private String nombre;

    public Escritor(LibroConMonitores l, String n) {
        libro = l;
        nombre = n;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000); // hacen otra cosa
                try {
                    libro.escribir(nombre);
                    Thread.sleep((long) (Math.random() + 3) * 1000);// tiempo escribiendo
                    libro.terminarEscribir(nombre);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
