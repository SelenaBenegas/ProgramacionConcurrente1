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
        Pasteleria p = new Pasteleria();
        Horno a = new Horno(p, 'A', 1), b = new Horno(p, 'B', 2), c = new Horno(p, 'C', 3);
        Robot r1 = new Robot(p, "Robot1"), r2 = new Robot(p, "Robot2");
        Brazo br = new Brazo(p, "Brazo");
        Thread hornoA = new Thread(a), hornoB = new Thread(b), hornoC = new Thread(c);
        Thread robot1 = new Thread(r1), robot2 = new Thread(r2), brazo = new Thread(br);
        hornoA.start();
        hornoB.start();
        hornoC.start();
        robot1.start();
        robot2.start();
        brazo.start();
    }

}
