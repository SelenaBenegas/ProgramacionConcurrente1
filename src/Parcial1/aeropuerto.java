package Parcial1;

import java.util.Random;

public class aeropuerto {

    public static void main(String[] args) {
        Random num = new Random();
        int cant = num.nextInt(30 - 10) + 10;
        Avion[] aviones = new Avion[cant];
        Pista pista = new Pista();
        TorreControl tr = new TorreControl(pista);
        Thread[] hilos = new Thread[cant];
        Thread torre = new Thread(tr);
        System.out.println("Cantidad de aviones que usarán la pista hoy: " + cant);
        for (int i = 0; i < cant; i++) {   // bucle donde se crean los aviones
            aviones[i] = new Avion(pista, "Avion" + i);
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
