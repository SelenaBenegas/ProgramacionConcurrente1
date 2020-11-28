package tp6.blancaNieves;

/**
 *
 * @author Selena Benegas
 */
public class BlancaNieves implements Runnable {

    private Casita casita;
    private String nombre;

    public BlancaNieves(Casita c) {
        nombre = "Blanca Nieves";
        casita = c;
    }

    @Override
    public void run() {
        while (true) {
            casita.cocinar(nombre);
        }
    }
}
