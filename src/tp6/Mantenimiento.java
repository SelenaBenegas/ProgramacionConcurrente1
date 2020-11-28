/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Mantenimiento implements Runnable {

    private String nombre;
    private Observatorio trabajo;

    public Mantenimiento(String n, Observatorio obs) {
        nombre = n;
        trabajo = obs;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random() + 5) * 2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
            }
            trabajo.limpiar(nombre);
            try {
                Thread.sleep((long) (Math.random() + 5) * 2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            trabajo.terminarLimpieza(nombre);
        }
    }

}
