/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp6.Obejtos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Museo {

    int cantPersonasAdentro = 0;
    int cantPersonasPermitidas = 15;
    int cantJubiladosEsperando = 0;

    public synchronized void entrarSala() {
        // se invoca cuando una persona quiere entrar en la sala.  
        try {
            while (cantPersonasAdentro >= cantPersonasPermitidas || cantJubiladosEsperando > 0) {
                System.out.println(Thread.currentThread().getName() + ": No soy jubilado y tengo que esperar.");
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Museo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + ": ¡Al fin entre!");
        System.out.println("Personas adentro: " + (cantPersonasAdentro + 1));
        cantPersonasAdentro++;
    }

    public synchronized void entrarSalaJubilado() {
        // se invoca cuando una persona jubilada quiere entrar en la sala. 
        try {
            cantJubiladosEsperando++;
            System.out.println("jubilados esperando: " + (cantJubiladosEsperando));
            while (cantPersonasAdentro >= cantPersonasPermitidas) {
                System.out.println(Thread.currentThread().getName() + ": Tengo que esperar, menos mal que soy jubilado...");
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Museo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + ": Ya adentro.");
        System.out.println("Personas adentro: " + (cantPersonasAdentro + 1));
        cantPersonasAdentro++;
        cantJubiladosEsperando--;
        System.out.println("jubilados esperando: " + (cantJubiladosEsperando));
        this.notifyAll();
    }

    public synchronized void salirSala() {
        // se invoca cuando una persona, jubilada o no, quiere salir de la sala.
        System.out.println(Thread.currentThread().getName() + ": Bueno, me voy.");
        System.out.println("Personas adentro: " + (cantPersonasAdentro - 1));
        cantPersonasAdentro--;
        this.notifyAll();
    }

    public synchronized void notificarTemperatura(int temperatura) {
        // lo invoca la hebra que mide la temperatura de la sala para indicar el último valor medido
        if (temperatura >= 30) {
            cantPersonasPermitidas = 15;
        } else {
            cantPersonasPermitidas = 5;
        }
        System.out.println("Temp: " + temperatura + " - cantPMaxima: " + cantPersonasPermitidas);
        this.notifyAll();
    }
}
