package Parcial1;

import java.util.Random;

/**
 *
 * @author Selena Benegas
 */
public class Avion implements Runnable {

    public TorreControl torre;
    public String nombre;
    public char accion;

    public Avion(TorreControl t, String nom) {
        torre = t;
        nombre = nom;
        accion = this.accion();

    }

    @Override
    public void run() {
        //while (true) {
        torre.usarPista(this.accion);
        //}
    }

    private char accion() { //random que determina la accion inicial del avion, a = aterrizar y d =despegar
        char[] chars = {'a', 'd'};
        Random random = new Random();
        int num = random.nextInt(2);
        return chars[num];
    }

}
    /*
    public void run() {
        while (true) {
            if (this.accion == 'a') {
                torre.aterrizar();
                esperaDespegue();

            } else {
                esperaDespegue();
                torre.aterrizar();
            }
        }
    }

    private void esperaDespegue() { //Espera el permiso de despegue
        boolean despegue = false;
        System.out.println(nombre + ": Esperando permiso para despegar.");
        while (!despegue) {
            if (torre.permisoDespegue()) {
                despegue = true;
            }
        }
    }

    private char accion() { //random que determina la accion inicial del avion, a = aterrizar y d =despegar
        char[] chars = {'a', 'd'};
        Random random = new Random();
        int num = random.nextInt(2);
        return chars[num];
    }

}
