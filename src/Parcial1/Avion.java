package Parcial1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Avion implements Runnable {

    public Pista pista;
    public String nombre;
    public char accion;

    public Avion(Pista p, String nom) {
        pista = p;
        nombre = nom;
        accion = this.accion();

    }

    @Override
    public void run() {
        //No esta en while true para corroborar que deje despergar a los aviones cuando no hay aviones que quieran aterrizar.
        try {
            Thread.sleep((long) (Math.random() + 3) * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
        }
        pista.usarPista(nombre, accion);
    }

    private char accion() { //random que determina la accion inicial del avion, a = aterrizar y d =despegar
        char[] chars = {'a', 'd'};
        Random random = new Random();
        int num = random.nextInt(2);
        return chars[num];
    }

}
