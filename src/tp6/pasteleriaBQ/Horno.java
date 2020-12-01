/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp6.pasteleriaBQ;

import tp6.Pasteleria.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Horno implements Runnable {

    private Pasteleria pasteleria;
    private char tipo;
    private int peso;

    public Horno(Pasteleria pria,char t, int p) {
        pasteleria = pria;
        tipo = t;
        peso = p;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("-- Horno"+tipo+": Horneando pastel de peso: " + peso + "kg. --");
            try {
                Thread.sleep((long) (Math.random() + 2) * 2000); // tiempo en hornear
            } catch (InterruptedException ex) {
                Logger.getLogger(Horno.class.getName()).log(Level.SEVERE, null, ex);
            }
            pasteleria.hornearPastel(peso);
        }
    }

}
