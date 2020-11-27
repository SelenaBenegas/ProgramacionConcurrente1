package tp6.Pasteleria;

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

    private Lock lockCola;
    private Semaphore retirar;
    private boolean hayCaja;
    private ColaDin cola;
    private int hayPastel;
    private int pesoActual;
    private int pesoMax;

    public Pasteleria() {
        lockCola = new ReentrantLock();
        retirar = new Semaphore(0);
        cola = new ColaDin();
        hayCaja = true;
        hayPastel = 0;
        pesoActual = 0;
        pesoMax = (int) (Math.random() * 5) + 10;
    }

    public synchronized void hornearPastel(int peso) {
       // lockCola.lock();
        cola.poner(peso);
        //lockCola.unlock();
        hayPastel++;
        System.out.println("** Pasteles en mostrador: " + hayPastel + " **");
        this.notifyAll();
    }

    public void trabajar(String nombre) {
        int peso = tomarPastel(nombre);
        soltarPastel(peso, nombre);
    }

    private synchronized int tomarPastel(String nombre) {
        int peso;
        try {
            while (hayPastel == 0) {
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        }
        //lockCola.lock();
        peso = (int) cola.obtenerFrente();
        System.out.println(nombre + ": Tomo pastel de " + peso + "kg.");
        cola.sacar();
        //lockCola.unlock();
        hayPastel--;
        System.out.println("** Pasteles en mostrador: " + hayPastel + " **");
        return peso;
    }

    private synchronized void soltarPastel(int peso, String nombre) {
        if (hayCaja) {
            if (pesoActual + peso > pesoMax) {
                System.out.println(nombre + "\u001B[31m" + ": Este pastel supera el peso máximo.");
                retirar.release();
                hayCaja = false;
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

    public void retirarCaja(String nombre) {
        try {
            retirar.acquire();
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
