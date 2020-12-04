package Parcial2;
/**
 *
 * @author Selena Benegas
 */
public class Hlisto implements Runnable {

    private Formacion formacion;

    public Hlisto(Formacion f) {
        formacion = f;
    }

    @Override
    public void run() {
        while (true) {
            formacion.hListo();
        }
    }
}
