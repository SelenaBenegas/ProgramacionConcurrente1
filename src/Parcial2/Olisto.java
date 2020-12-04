
package Parcial2;
/**
 *
 * @author Selena Benegas
 */
public class Olisto implements Runnable {

    private Formacion formacion;

    public Olisto(Formacion f) {
        formacion = f;
    }

    @Override
    public void run() {
        while (true) {
            formacion.oListo();
        }
    }
}
