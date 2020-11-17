package Teoria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class LibroConMonitores {

    private int cantLeyendo = 0;
    private boolean escribiendo = false;

    public LibroConMonitores() {

    }

    public synchronized void escribir(String nombre) {
        try {
            System.out.println(nombre + ": esperando para escribir...");
            while (escribiendo || cantLeyendo > 0) {
                this.wait(); //esperan si hay un escritor o un lector
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(LibroConMonitores.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(nombre + ": ¡Escribiendo!");
        escribiendo = true; // si no hay nadie, empieza a escribir
    }

    public synchronized void terminarEscribir(String nombre) {
        System.out.println(nombre + ": termine de escribir.");
        escribiendo = false; 
        this.notifyAll(); // termina de escibir y avisa a todos (lectores y escritores)
    }

    public synchronized void leer(String nombre) {
        try {
            System.out.println(nombre + ": esperando para leer...");
            while (escribiendo) {
                this.wait(); //espera si hay un escritor
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(LibroConMonitores.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(nombre + ": Leyendo :) ");
        cantLeyendo++; // Uno más leyendo
    }

    public synchronized void terminarleer(String nombre) {
        System.out.println(nombre + ": termine de leer.");
        cantLeyendo--; //uno menos leyendo
        this.notifyAll(); //aviso a todos

    }
}
