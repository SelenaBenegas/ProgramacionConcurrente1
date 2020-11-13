/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Termometro implements Runnable {

    Museo sala;

    public Termometro(Museo museo) {
        sala = museo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sala.notificarTemperatura(35);
                Thread.sleep(5000);
                sala.notificarTemperatura(25);
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
            }
        }
    }
}
