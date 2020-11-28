package tp6;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Visitante implements Runnable {

    private String nombre;
    private Observatorio lugar;
    private boolean silla;

    public Visitante(String n, Observatorio obs) {
        nombre = n;
        lugar = obs;
        silla = usaSilla();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random() + 5) * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
            }
            lugar.entrar(nombre, silla);
            try {
                Thread.sleep((long) (Math.random() + 5) * 2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
            }
            lugar.salir(nombre, silla);
        }
    }

    private boolean usaSilla() {
        boolean[] tf = {false, true, false, false}; //intento de que no entren taantos en silla de rueda
        Random random = new Random();
        int num = random.nextInt(4);
        return tf[num];
    }

}
