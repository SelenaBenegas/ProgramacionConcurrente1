package tp6.pasteleriaBQ;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import tp6.Pasteleria.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp6.centroH.ColaDin;

/**
 *
 * @author Selena Benegas
 */
public class Pasteleria {

    protected BlockingQueue mostrador;
    private Lock escribir;
    private boolean hayCaja;
    private int hayPastel;
    private int pesoActual;
    private int pesoMax;

    public Pasteleria() {
        // mostrador = new LinkedBlockingQueue();
        mostrador = new ArrayBlockingQueue(5, true);
        escribir = new ReentrantLock();
        hayCaja = true;
        hayPastel = 0;
        pesoActual = 0;
        pesoMax = (int) (Math.random() * 5) + 10;
    }

    public void hornearPastel(int peso) {
        try {
            mostrador.put(peso);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        }
        escribir.lock();
        System.out.println("\u001B[35m" + "**Se agrega pastel de " + peso + "kg al mostrador.**");
        hayPastel++;
        System.out.println("** Pasteles en mostrador: " + hayPastel + " **");
        escribir.unlock();
    }

    public void trabajar(String nombre) {
        int peso = tomarPastel(nombre);
        soltarPastel(peso, nombre);
    }

    private int tomarPastel(String nombre) {
        int peso = 0;
        try {
            peso = (int) mostrador.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        }
        escribir.lock();
        System.out.println("\u001B[32m" + nombre + ": Tomo pastel de " + peso + "kg.");
        hayPastel--;
        System.out.println("** Pasteles en mostrador: " + hayPastel + " **");
        escribir.unlock();
        return peso;
    }

    private synchronized void soltarPastel(int peso, String nombre) {
        if (hayCaja) {
            if (pesoActual + peso > pesoMax) {
                System.out.println("\u001B[31m" + nombre + ": Este pastel supera el peso máximo.");
                hayCaja = false;
                this.notifyAll();
            }
        }
        try {
            while (!hayCaja) {
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(nombre + ": Coloco pastel de " + peso + "kg. ");
        pesoActual += peso;
        System.out.println("Peso maximo: " + pesoMax + "kg. - Peso actual: " + pesoActual + "kg. ");
    }

    public synchronized void retirarCaja(String nombre) {
        try {
            while (hayCaja) {
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\u001B[34m" + nombre + ": Retira caja.");
        pesoActual = 0;
    }

    public synchronized void ponerCaja(String nombre) {
        System.out.println("\u001B[34m" + nombre + ": Pone caja nueva.");
        hayCaja = true;
        pesoMax = (int) (Math.random() * 5) + 10;
        System.out.println("Peso maximo: " + pesoMax + "kg. ");
        this.notifyAll();
    }
}
