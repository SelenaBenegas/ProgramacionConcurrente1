
package ejemplos;

import Objetos.Contador;
import Objetos.GeneradorNros;

public class TestGenerador {

    public static void main(String[] args) {
        Contador elContador = new Contador();
        GeneradorNros genera1, genera2;
        Thread hilo1, hilo2;
        genera1 = new GeneradorNros(elContador, "soyGen_1 ", 100);
        hilo1 = new Thread(genera1);
        genera2 = new GeneradorNros(elContador, "soyGen_2 ", 130);
        hilo2 = new Thread(genera2);
        hilo1.start();
        hilo2.start();
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            System.out.println("catch");
        }
        System.out.println("total de numeros generados por los hilos "
                + elContador.getValor());
    }
}
