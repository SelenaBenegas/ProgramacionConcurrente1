package Parcial2;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Generador implements Runnable {

    private Formacion formacion;

    public Generador(Formacion f) {
        formacion = f;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random() + 3) * 4000);
                formacion.generar();
                Thread.sleep((long) (Math.random() + 3) * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
