/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class PistaB {

    private Semaphore semAterrizar; //el avion que quiera desperar tiene que esperar que le den permiso
    private Semaphore semDespegar; //el avion que quiera desperar tiene que esperar que le den permiso
    private Semaphore semUsarPista; // este semáforo asegura que en la pista solo haya un avion, tiene prioridad quien quiera aterrizar.
    private Semaphore semPermiso; // torre espera a que pidan permiso
    private int esperandoAterrizar; // aviones que esperan confirmacion para aterrizar
    private int esperandoDespegar; //aviones que esperan confirmacion para despegar
    //MODIFICACION
    private int cantAterrizajes;

    public PistaB() {
        semAterrizar = new Semaphore(0);
        semDespegar = new Semaphore(0);
        semUsarPista = new Semaphore(1);
        semPermiso = new Semaphore(1);
        esperandoAterrizar = 0;
        esperandoDespegar = 0;
        //Modificacion
        cantAterrizajes = 0;
    }

    public void usarPista(String nombre, char accion) {
        if (accion == 'a') {
            System.out.println(nombre + ": Esperando permiso para aterrizar...");
            esperandoAterrizar++;
            aterrizar("\u001B[34m" + nombre);
        } else {
            esperandoDespegar++;
            System.out.println(nombre + ": Esperando permiso para despegar...");
            despegar("\u001B[32m" + nombre);
        }
    }

    public void darPermiso(String nombre) {
        try {
            semPermiso.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\u001B[35m" + "Aviones esperando para aterrizar: " + esperandoAterrizar);
        System.out.println("\u001B[35m" + "Aviones esperando para despegar: " + esperandoDespegar);
        if (esperandoDespegar > 0 && cantAterrizajes >= 10) {
            System.out.println(nombre + ": Consedo permiso para DESPEGAR.");
            cantAterrizajes=0;
            semDespegar.release();
        } else {
            System.out.println(nombre + ": Consedo permiso para ATERRIZAR.");
            semAterrizar.release();
        }
    }

    public void aterrizar(String nombre) {
        try {
            semAterrizar.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            semUsarPista.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(nombre + ": Preparando aterrizaje.");
        esperandoAterrizar--;
        try {
            Thread.sleep((long) (Math.random() + 3) * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(nombre + ": Aterrizaje exitoso.");
        //MODIFICACION
        cantAterrizajes++;
        System.out.println("\u001B[35m" + "Cantidad de aterrizajes: "+ cantAterrizajes);
        semUsarPista.release();
        semPermiso.release();
        System.out.println(nombre + ": Nos quedaremos en tierra.");
        try {
            Thread.sleep((long) (Math.random() + 3) * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void despegar(String nombre) {
        try {
            semDespegar.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            semUsarPista.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(nombre + ": Preparando despegue.");
        esperandoDespegar--;
        try {
            Thread.sleep((long) (Math.random() + 3) * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(nombre + ": Despegue exitoso.");
        semUsarPista.release();
        semPermiso.release();
    }
}
