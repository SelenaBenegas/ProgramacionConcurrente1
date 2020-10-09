package TrabajoPracticoSeccionCritica;

import Objetos.Confiteria;
import Objetos.Empleado;
import Objetos.Mozo;

public class mainPollosHermanos {

    public static void main(String[] args) {
        Confiteria confiteria = new Confiteria();    // confiteria
        Mozo mozo1 = new Mozo(confiteria);  //mozo
        Thread mozo = new Thread(mozo1);    // hilo de mozo
        Empleado[] empleados = new Empleado[6]; //array de empleados
        Thread[] hilos = new Thread[6]; //array hilos empleados
        for (int i = 0; i < 6; i++) {   // bucle donde se crean los empleados
            empleados[i] = new Empleado(confiteria);
        }
        for (int i = 0; i < 6; i++) { // bucle donde se crean los hilos empleados
            hilos[i] = new Thread(empleados[i], "Empleado" + i);
        }
        mozo.start(); 
        for (int i = 0; i < 6; i++) {
            hilos[i].start();
        }
    }
}
