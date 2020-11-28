/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp6;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 *
 * @author Selena Benegas
 */
public class Investigador implements Runnable {

    private String nombre;
    private Observatorio trabajo;

    public Investigador(String n, Observatorio obs) {
        nombre = n;
        trabajo = obs;
    }

    @Override
    public void run() {
        while(true){            
            trabajo.investigar(nombre);
            try {
                Thread.sleep((long) (Math.random() + 5) * 2000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Investigador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            trabajo.terminarInvestigacion(nombre);
        }
    }
    
}
