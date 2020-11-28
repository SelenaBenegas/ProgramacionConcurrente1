package Parcial1;

import java.util.concurrent.Semaphore;

public class TorreControl implements Runnable {

    public String nombre;
    public Pista pista;

    public TorreControl(Pista p) {
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
