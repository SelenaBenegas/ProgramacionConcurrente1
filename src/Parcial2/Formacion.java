package Parcial2;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Formacion {

    private Semaphore oxigeno;
    private Semaphore hidrogeno;
    private Semaphore encuentro;
    private Semaphore somosAgua;
    private int lleno;
    private int veces;
    private int o;
    private int h;

    public Formacion(int k) {
        oxigeno = new Semaphore(0);
        hidrogeno = new Semaphore(0);
        encuentro = new Semaphore(0);
        somosAgua = new Semaphore(0);
        lleno = k; //para que el recipiente se llene se tiene que hacer agua k veces
        veces = 0;
        o = 0;
        h = 0;
    }

    public void generar() {
        Random random = new Random();
        int n = random.nextInt(2);
        if (n == 0) {
            System.out.println("\u001B[31m" + "Se ha creado un atomo de Oxígeno");
            o++;
            oxigeno.release();
        } else {
            System.out.println("\u001B[31m" + "Se ha creado un atomo de Hidrógeno");
            h++;
            hidrogeno.release();
        }
        System.out.println("\u001B[31m" + "Oxigeno: " + o + " - Hidroxeno: " + h);
    }

    public void oListo() {
        try {
            oxigeno.acquire();
            System.out.println("Hay un O listo.");
            o--;
            encuentro.acquire();
            System.out.println("Se encontraron dos H y un O");
            System.out.println("Comienza la formacion");
            hacerAgua();
            somosAgua.release();
            System.out.println("de oxigeno salgo como agua.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Formacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hListo() {
        try {
            hidrogeno.acquire(2);
            System.out.println("Hay dos H listos.");
            h -= 2;
            encuentro.release();
            somosAgua.acquire();
            System.out.println("de hidrogeno salgo como agua.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Formacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hacerAgua() {
        System.out.println("\u001B[34m" + "Formacion de agua.");
        try {
            Thread.sleep((long) (Math.random() + 3) * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Formacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\u001B[34m" + "Se ha finalizado la formacion de agua.");
        veces++;
        System.out.println("\u001B[34m" + " Veces que se ha hecho agua: " + veces + " veces. - Capacidad: " + lleno + " veces.");
        if (veces == lleno) {
            System.out.println("\u001B[34m" + "Se ha llenado el recipiente.");
            System.out.println("\u001B[34m" + "Se ha colocado un recipiente vacío.");
            veces = 0;
        }
    }
}
