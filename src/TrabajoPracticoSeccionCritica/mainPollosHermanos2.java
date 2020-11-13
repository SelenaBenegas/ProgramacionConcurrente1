package TrabajoPracticoSeccionCritica;

import Objetos.Cocinero;
import Objetos.Empleado;
import Objetos.Mozo;
import Objetos.Confiteria2;

/**
 *
 * @author Selena Benegas
 */
public class mainPollosHermanos2 {

    public static void main(String[] args) {
        Confiteria2 confiteria = new Confiteria2();
        Mozo mozo1 = new Mozo(confiteria);  //mozo1
        Thread mozo = new Thread(mozo1, "Mozo1");    // hilo de mozo1
        Mozo mozo0 = new Mozo(confiteria);  //mozo2
       Thread mozo2 = new Thread(mozo0, "Mozo2");    // hilo de mozo2
        Cocinero chef = new Cocinero(confiteria);  //chef1
        Thread chef1 = new Thread(chef, "Chef1");    // hilo de chef1
       // Cocinero chef0 = new Cocinero(confiteria);  //chef1
        //Thread chef2 = new Thread(chef0, "Chef2");    // hilo de chef1
        Empleado[] empleados = new Empleado[6]; //array de empleados
        Thread[] hilos = new Thread[6]; //array hilos empleados
        //creo a los empleados
        empleados[0] = new Empleado("Empleado0", confiteria, "\u001B[31m");
        empleados[1] = new Empleado("Empleado1", confiteria, "\u001B[33m");
        empleados[2] = new Empleado("Empleado2", confiteria, "\u001B[34m");
        empleados[3] = new Empleado("Empleado3", confiteria, "\u001B[35m");
        empleados[4] = new Empleado("Empleado4", confiteria, "\u001B[36m");
        empleados[5] = new Empleado("Empleado5", confiteria, "\u001B[32m");

        /*
        // Sin los colorcitos era mas facil porque hacia un for para crearlos jaja
        for (int i = 0; i < 6; i++) {   // bucle donde se crean los empleados
            empleados[i] = new Empleado("Empleado" + i, confiteria);
        }*/
        for (int i = 0; i < 6; i++) { // bucle donde se crean los hilos empleados
            hilos[i] = new Thread(empleados[i], "Empleado" + i);
        }
        mozo.start();
       mozo2.start();
        chef1.start();
        //chef2.start();
        for (int i = 0; i < 6; i++) {
            hilos[i].start();
        }
    }
}
