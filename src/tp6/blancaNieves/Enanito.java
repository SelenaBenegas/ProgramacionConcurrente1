/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp6.blancaNieves;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Enanito implements Runnable {

    private Casita casita;
    private String nombre;

    public Enanito(Casita c, String n) {
        casita = c;
        nombre = n;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i = 0;
            while (i < 2) {
                System.out.println(nombre + ": Estoy trabajando!");
                try {
                    Thread.sleep((long) (Math.random() + 10) * 2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Enanito.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(nombre+": Voy a comer");
                casita.comer(nombre);
                try {
                    Thread.sleep((long) (Math.random() + 5) * 2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Enanito.class.getName()).log(Level.SEVERE, null, ex);
                }
                casita.terminarComer(nombre);
                i++;
            }
            System.out.println(nombre + ": A DORMIR!");
            try {
                Thread.sleep((long) (Math.random() + 5) * 2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enanito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
