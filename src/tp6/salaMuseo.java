package tp6;

import tp6.Obejtos.Persona;
import tp6.Obejtos.Termometro;
import tp6.Obejtos.Museo;

/**
 *
 * @author Selena Benegas
 */
public class salaMuseo {

    public static void main(String[] args) {
        int cantP = 25;
        Museo museo = new Museo();
        Persona[] personas = new Persona[cantP];
        Termometro termo = new Termometro(museo);
        Thread[] hilos = new Thread[cantP];
        for (int i = 0; i < cantP; i++) {   // bucle donde se crean las personas.
            personas[i] = new Persona(museo);
        }
        for (int i = 0; i < cantP; i++) { // bucle donde se crean los hilos personas.
            hilos[i] = new Thread(personas[i], "Persona" + i);
        }
        Thread termometro = new Thread(termo, "Termometro");
        termometro.start();
        for (int i = 0; i < cantP; i++) { //bucle start de los hilos personas.
            hilos[i].start();
        }
    }

}
