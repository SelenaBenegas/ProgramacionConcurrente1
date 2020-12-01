/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp6.pasteleriaBQ;

import tp6.Pasteleria.*;

/**
 *
 * @author Selena Benegas
 */
public class mainPasteleria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //MAIN PARA CASO BASE DEL EJEMPLO, DONDE HAY 3 HORNOS, 2 ROBOTS Y 1 BRAZO.
        int cant = 2;
        Pasteleria p = new Pasteleria();
        Horno a = new Horno(p, 'A', 1), b = new Horno(p, 'B', 2), c = new Horno(p, 'C', 3);
        Brazo br = new Brazo(p, "Brazo");
        Thread brazo = new Thread(br);
        Robot[] robots = new Robot[cant];
        Thread[] hilos = new Thread[cant];
        for (int i = 0; i < cant; i++) {   // bucle donde se crean los robots empaquetadores.
            robots[i] = new Robot(p, "Robot" + i);
        }
        for (int i = 0; i < cant; i++) { // bucle donde se crean los hilos robots.
            hilos[i] = new Thread(robots[i]);
        }
        Thread hornoA = new Thread(a), hornoB = new Thread(b), hornoC = new Thread(c);
        for (int i = 0; i < cant; i++) { //bucle start de los hilos personas.
            hilos[i].start();
        }
        hornoA.start();
        hornoB.start();
        hornoC.start();
        brazo.start();
    }

}
