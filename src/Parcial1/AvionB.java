/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class AvionB implements Runnable {

    public PistaB pista;
    public String nombre;
    public char accion;

    public AvionB(PistaB p, String nom) {
        pista = p;
        nombre = nom;
        accion = this.accion();

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random() + 3) * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
            }
            pista.usarPista(nombre, accion);
        }
    }

    private char accion() { //random que determina la accion inicial del avion, a = aterrizar y d =despegar
        char[] chars = {'a', 'd'};
        Random random = new Random();
        int num = random.nextInt(2);
        return chars[num];
    }

}
