/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial1;

import java.util.Random;

/**
 *
 * @author Selena Benegas
 */
public class mainB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random num = new Random();
        int cant = num.nextInt(30 - 10) + 10;
        AvionB[] aviones = new AvionB[cant];
        PistaB pista = new PistaB();
        TorreControlB tr = new TorreControlB(pista);
        Thread[] hilos = new Thread[cant];
        Thread torre = new Thread(tr);
        System.out.println("Cantidad de aviones que usarán la pista hoy: " + cant);
        for (int i = 0; i < cant; i++) {   // bucle donde se crean los aviones
            aviones[i] = new AvionB(pista, "Avion" + i);
        }
        for (int i = 0; i < cant; i++) { // bucle donde se crean los hilos aviones
            hilos[i] = new Thread(aviones[i], "Avión" + i);
        }
        for (int i = 0; i < cant; i++) { //bucle start de los hilos.
            hilos[i].start();
        }
        torre.start();

    }

}
