package Objetos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Empleado implements Runnable {

    // para el ejercicio 14
    private Confiteria2 confiteria;
    private String nombre;
    private String miColor;

    public Empleado(String nom, Confiteria2 l, String color) {
        nombre = nom;
        confiteria = l;
        miColor = color;
    }

    @Override
    public void run() {
        while(true){
        confiteria.entrarAConfiteria(miColor);
        }
    }
    /*public void run() {
        int i = 0;
        while (i<3) { //hacen solo almuerzo merienda y cena
            if (confiteria.entrarAConfiteria()) { //se fija si está libre la silla
                confiteria.ordenar(miColor); //si está libre llama al mozo y etc.
            } else {
                System.out.println(this.miColor + this.nombre + ": Uuuh todas las sillas ocupadas, me voy al carrito.");
            }
            try {
                Thread.sleep(5000);
                //este sleep logra que no vuelva a sentarse el mismo empleado
            } catch (InterruptedException ex) {
                Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
    }*/

}
