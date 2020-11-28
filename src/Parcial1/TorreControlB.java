/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial1;

/**
 *
 * @author Selena Benegas
 */
public class TorreControlB implements Runnable {

    public String nombre;
    public PistaB pista;

    public TorreControlB(PistaB p) {
        nombre = "\u001B[31m" + "Torre de Control";
        pista = p;
    }

    @Override
    public void run() {
        while (true) {
            pista.darPermiso(nombre);
        }
    }
}
