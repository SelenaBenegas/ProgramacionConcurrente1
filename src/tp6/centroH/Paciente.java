package tp6.centroH;

import tp6.centroH.SalaH;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Paciente implements Runnable {

    String nombre;
    SalaH centro;
    boolean sentarse;

    public Paciente(String n, SalaH c) {
        nombre = n;
        centro = c;
        sentarse = seSienta();
    }

    @Override
    public void run() {
        while (true) {
            centro.entrar(nombre, sentarse);
            try {
                Thread.sleep((long) (Math.random() + 5) * 2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            centro.salir(nombre);
        }
    }

    private boolean seSienta() {
        boolean[] tf = {false, true}; //intento de que no entren taantos en silla de rueda
        Random random = new Random();
        int num = random.nextInt(2);
        return tf[num];
    }

}
