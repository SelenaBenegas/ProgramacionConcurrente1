package TrabajoPracticoSeccionCritica;

import Objetos.Hamster;
import Objetos.JaulaHamster;

public class mainHamsters {

    public static void main(String[] args) {
        JaulaHamster jaula = new JaulaHamster();
        int cantHamsters = 8;
        Hamster[] hamsters = new Hamster[cantHamsters];
        Thread[] hilos = new Thread[cantHamsters];
        for (int i = 0; i < cantHamsters; i++) {
            hamsters[i] = new Hamster(jaula);
        }
        for (int i = 0; i < cantHamsters; i++) {
            hilos[i] = new Thread(hamsters[i], "Hamster" + i);
        }
        for (int i = 0; i < cantHamsters; i++) {
            hilos[i].start();
        }
    }

}
