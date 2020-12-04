package Parcial2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Usuario implements Runnable {

    private Impresora impresora;
    private String nombre;

    public Usuario(Impresora i, String n) {
        impresora = i;
        nombre = n;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random() + 3) * 1000);
                impresora.solicitaEscritura(generarChar(), nombre);
            } catch (InterruptedException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static char generarChar() {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Random random = new Random();
        int n = random.nextInt(7);
        return chars[n];
    }
}
