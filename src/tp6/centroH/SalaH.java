/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp6.centroH;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class SalaH {

    private ColaDin cola;
    private Semaphore sentarse;
    private Lock llaveCola;
    private Lock llaveSilla;
    private Lock accesoCamilla;
    private Condition hayCamilla;
    private int cantRevistas;
    private int revistasOcupadas;
    private int cantSillas;
    private int sillasOcupadas;
    private int cantCamillas;
    private int camillasOcupadas;

    public SalaH() {
        cola = new ColaDin();
        sentarse = new Semaphore(12);
        llaveCola = new ReentrantLock(true);
        llaveSilla = new ReentrantLock(true);
        accesoCamilla = new ReentrantLock(true);
        hayCamilla = accesoCamilla.newCondition();
        cantRevistas = 9;
        cantSillas = 12;
        cantCamillas = 4;
        camillasOcupadas = 0;
        revistasOcupadas = 0;
        sillasOcupadas = 0;
    }

    public void entrar(String nombre, boolean sentarse) {
        boolean sentado = false;
        llaveCola.lock();
        System.out.println(nombre + ": voy a donar!");
        cola.poner(nombre);
        llaveCola.unlock();
        if (sentarse) {
            System.out.println(nombre + ": Voy a esperar sentado.");
            if (this.sentarse.tryAcquire()) {
                System.out.println(nombre + ": tengo una silla.");
                this.sillasOcupadas++;
                System.out.println("\u001B[36m" + "Sillas ocupadas: " + sillasOcupadas);
                sentado = true;
            } else {
                System.out.println(nombre + ": no hay sillas libres.");
            }
        }
        esperar(nombre);
        if (sentado) {
            System.out.println(nombre + ": dejo la silla desocupada.");
            this.sentarse.release();
            this.sillasOcupadas--;
            System.out.println("\u001B[36m" + "Sillas ocupadas: " + sillasOcupadas);
        }
    }

    /*este entrar es con la silla con un contador y locks que lo cuiden, y el de arrriba con semaforos.
    public void entrar(String nombre, boolean sentarse) {
        boolean sentado = false;
        llaveCola.lock();
        System.out.println(nombre + ": voy a donar!");
        cola.poner(nombre);
        llaveCola.unlock();
        if (sentarse) {
            llaveSilla.lock();
            System.out.println(nombre + ": Voy a esperar sentado.");
            if (sillasOcupadas < cantSillas) {
                System.out.println(nombre + ": tengo una silla.");
                this.sillasOcupadas++;
                System.out.println("\u001B[36m" + "Sillas ocupadas: " + sillasOcupadas);
                sentado = true;
            } else {
                System.out.println(nombre + ": no hay sillas libres.");
            }
            llaveSilla.unlock();
        }
        esperar(nombre);
        if (sentado) {
            llaveSilla.lock();
            System.out.println(nombre + ": dejo la silla desocupada.");
            this.sillasOcupadas--;
            System.out.println("\u001B[36m" + "Sillas ocupadas: " + sillasOcupadas);
            llaveSilla.unlock();
        }
    }*/
    private void esperar(String nombre) {
        boolean revista;
        try {
            accesoCamilla.lock();
            revista = tomarRevista(nombre);
            System.out.println(nombre + ": Espero a ser atendido...");
            while (camillasOcupadas >= cantCamillas || !cola.obtenerFrente().equals(nombre)) {
                hayCamilla.await();
            }
            if (revista) {
                dejarRevista(nombre);
            }
            camillasOcupadas++;
            System.out.println("\u001B[34m" + "Cantidad de camillas ocupadas: " + camillasOcupadas);
        } catch (InterruptedException ex) {
        } finally {
            accesoCamilla.unlock();
        }
    }

    private boolean tomarRevista(String nombre) {
        boolean revista = false;
        try {
            accesoCamilla.lock();
            while (revistasOcupadas >= cantRevistas && !cola.obtenerFrente().equals(nombre)) {
                System.out.println(nombre + ": Esperando una revista, viendo tv.");
                hayCamilla.await();
            }
            if (revistasOcupadas < cantRevistas) {
                System.out.println(nombre + ": Leyendo :)");
                revistasOcupadas++;
                revista = true;
            }
            System.out.println("\u001B[35m" + "Cantidad de revistas ocupadas:" + revistasOcupadas);
        } catch (InterruptedException ex) {
        } finally {
            accesoCamilla.unlock();
        }
        return revista;
    }

    private void dejarRevista(String nombre) {
        accesoCamilla.lock();
        llaveCola.lock();
        System.out.println(nombre + ": Estoy siendo atendido.");
        cola.sacar();
        llaveCola.unlock();
        System.out.println(nombre + ": Peleense por la revista jaja");
        revistasOcupadas--;
        System.out.println("\u001B[35m" + "Cantidad de revistas ocupadas:" + revistasOcupadas);
        hayCamilla.signalAll();
        accesoCamilla.unlock();
    }

    public void salir(String nombre) {
        accesoCamilla.lock();
        System.out.println("\u001B[31m" + nombre + ": Termine de donar.");
        camillasOcupadas--;
        System.out.println("\u001B[34m" + "Cantidad de camillas ocupadas: " + camillasOcupadas);
        hayCamilla.signalAll();
        accesoCamilla.unlock();

    }

}
