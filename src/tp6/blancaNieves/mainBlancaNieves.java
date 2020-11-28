package tp6.blancaNieves;

import java.util.Random;

/**
 *
 * @author Selena Benegas
 */
public class mainBlancaNieves {

    public static void main(String[] args) {
        Casita casa = new Casita();
        BlancaNieves bn = new BlancaNieves(casa);
        Enanito[] enanitos = new Enanito[7];
        Thread[] hilos = new Thread[7];
        Thread blanca = new Thread(bn);
        for (int i = 0; i < 7; i++) {
            enanitos[i] = new Enanito(casa, "Enanito" + i);
        }
        for (int i = 0; i < 7; i++) {
            hilos[i] = new Thread(enanitos[i]);
        }
        blanca.start();
        for (int i = 0; i < 7; i++) {
            hilos[i].start();
        }
    }

}
