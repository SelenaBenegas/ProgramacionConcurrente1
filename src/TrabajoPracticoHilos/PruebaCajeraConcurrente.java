package TrabajoPracticoHilos;

import TrabajoPracticoHilos.Objetos.CajeraRunnable;
import TrabajoPracticoHilos.Objetos.CajeraThread;
import TrabajoPracticoHilos.Objetos.Cliente;
import Utiles.TecladoIn;

public class PruebaCajeraConcurrente {

    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Cliente 1", new int[]{2, 2, 1, 5, 2, 3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[]{1, 3, 5, 1, 1});
        System.out.println("Opciones:");
        System.out.println("1. Ejecutar implementacion con herencia de Thread(ej.6b)");
        System.out.println("2. Ejecutar implementacion con interfaz Runnable (ej.7)");
        int opcion = TecladoIn.readLineInt();
        if (opcion == 1) {
            CajeraThread caja1 = new CajeraThread("Rocio", cliente1, System.currentTimeMillis());
            CajeraThread caja2 = new CajeraThread("Selma", cliente2, System.currentTimeMillis());
            caja1.start();
            caja2.start();
        } else {
            CajeraRunnable caja1 = new CajeraRunnable("Rocio", cliente1, System.currentTimeMillis());
            CajeraRunnable caja2 = new CajeraRunnable("Selma", cliente2, System.currentTimeMillis());
            Thread c1 = new Thread(caja1);
            Thread c2 = new Thread(caja2);
            c1.start();
            c2.start();
        }
    }
}
