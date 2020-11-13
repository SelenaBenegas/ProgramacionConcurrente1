package tp6;

import java.util.Random;

/**
 *
 * @author Selena Benegas
 */
public class Persona implements Runnable {

    boolean jubilado;
    Museo sala;

    public Persona(Museo museo) {
        jubilado = esJubilado();
        sala = museo;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 4) {
            try {
                Thread.sleep((long) (Math.random() + 3) * 1000);
            } catch (InterruptedException ex) {
            }
            if (jubilado) {
                sala.entrarSalaJubilado();
            } else {
                sala.entrarSala();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
            sala.salirSala();
            i++;
        }
    }

    private boolean esJubilado() {
        boolean[] tf = {true, false};
        Random random = new Random();
        int num = random.nextInt(2);
        return tf[num];
    }

}
