/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Impresora {

    private Lock imprimir;
    private Lock memoria1;
    private Lock memoria2;
    private Lock peticiones;
    private Condition datoCargado;
    private Cola cache;
    private ColaDin principal;
    private ColaDin colaP;
    private int hayDato;

    public Impresora(int t) {
        peticiones = new ReentrantLock(); // llave de acceso a la cola de peticiones
        memoria1 = new ReentrantLock(); // llave de acceso a la memoria cache
        memoria2 = new ReentrantLock(); // llave de acceso a la memoria principal
        imprimir = new ReentrantLock();
        datoCargado = imprimir.newCondition();
        cache = new Cola(t); // la cache es una cola estatica
        principal = new ColaDin(); //la memoria principal es una cola dinamica
        colaP = new ColaDin(); //la cola de peticones es una cola dinamica
        hayDato = 0; //al inicio no hay ningun dato cargado a memoria
    }

    public void solicitaEscritura(char p, String nombre) {
        peticiones.lock();
        try {
            System.out.println("\u001B[31m" + nombre + ": solicito escritura de dato: " + p);
            colaP.poner(p); //pongo peticion en la cola de peticiones
        } finally {
            peticiones.unlock();
        }
        cargar(p);
    }

    private void cargar(char p) {
        boolean cargado;
        imprimir.lock();
        try {
            cargado = copiarMemoriaCache(p);
            if (!cargado) { // si no se logro cargar en memoria cache es porque no hay más espacio
                System.out.println("\u001B[35m" + " -- Memoria Caché sin espacio --");
                copiarMemoriaPrincipal(p); //copio en memoria principal
                System.out.println("\u001B[35m" + "El dato '" + p + "' se cargo en Memoria Principal");
            } else { //en caso contrario, el dato ya se cargo en memoria.
                System.out.println("\u001B[35m" + "El dato '" + p + "' se cargo en Memoria Caché");
            }
            hayDato++;
            datoCargado.signalAll();
        } finally {
            imprimir.unlock();
        }
    }

    private boolean copiarMemoriaCache(char p) {
        boolean cargado;
        //cuando se desocupe la memoria cache
        memoria1.lock();
        try {
            cargado = cache.poner(p); // intento copiar el dato
            try {
                Thread.sleep(2000); //simula tiempo en copiar un dato en memoria cache
            } catch (InterruptedException ex) {
                Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            memoria1.unlock();
        }
        return cargado; //retorno true si se logro copiar o false en caso contrario
    }

    private void copiarMemoriaPrincipal(char p) {
        memoria2.lock();
        try {
            try {
                Thread.sleep(5000); //simula tiempo en copiar un dato en memoria principal
            } catch (InterruptedException ex) {
                Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);
            }
            principal.poner(p); // copio el dato en memoria principal 
        } finally {
            memoria2.unlock();
        }
    }

    public void imprimir() {
        boolean encontrado;
        char peticion = ' ';
        imprimir.lock();
        try {
            while (hayDato == 0) {
                datoCargado.await();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);
        }
        peticiones.lock();
        try {
            peticion = (char) colaP.obtenerFrente(); //obtengo la primera peticion
            colaP.sacar(); //saco el dato de la lista de peticiones
        } finally {
            peticiones.unlock();
        }
        System.out.println("\u001B[34m" + "El Servidor intenta imprimir un dato.");
        System.out.println("\u001B[34m" + "El Servidor busca en cache.");
        encontrado = buscarMemoriaCache(peticion); //se busca el dato en la cache
        if (!encontrado) { //si no esta en cache
            System.out.println("\u001B[34m" + "El Servidor busca en memoria principal.");
            buscarMemoriaPrincipal(peticion); //se busca en memoria principal
        }
        System.out.println("--------------- " + peticion + "---------------"); //imprime acá porque si o si lo encuentra
        hayDato--;
        imprimir.unlock();
    }

    public boolean buscarMemoriaCache(char p) {
        boolean impreso = false;
        char frente;
        memoria1.lock();
        try {
            frente = (char) cache.obtenerFrente();
            if (frente == p) { //si el frente de la cola de caché es igual a la peticion
                impreso = true; // aviso que esta en memoria cache
                cache.sacar(); //y lo elimino de la memoria caché
            } // si la cache esta vacia el dato quedo en memoria principal
            try {
                Thread.sleep(2000); //simula tiempo en buscar un dato en memoria cache
            } catch (InterruptedException ex) {
                Logger.getLogger(Impresora.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            memoria1.unlock();
        }
        return impreso;
    }

    public void buscarMemoriaPrincipal(char p) {
        memoria2.lock();
        try {
            principal.sacar(); //elimino la peticion de memoria principal
            try {
                Thread.sleep(5000); //simula tiempo en buscar un dato en memoria principal
            } catch (InterruptedException ex) {
                Logger.getLogger(Impresora.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            memoria2.unlock();
        }
    }
}