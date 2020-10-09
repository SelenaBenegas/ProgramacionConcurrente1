package ejemplos;

import Objetos.PingPong;
import Objetos.Dato;

public class EjemploPingPong {

    /* public static void main(String[] args) {
        PingPong t1 = new PingPong("PING", 33);
        PingPong t2 = new PingPong("PONG", 10);
        // Activación
        t1.start();
        t2.start();
        // Espera unos segundos
        try {
            Thread.sleep(5000);
        }catch(InterruptedException e){
            System.out.println("Excepción");}
        // Finaliza la ejecución de los threads
    }*/
    public static void main(String[] args) throws InterruptedException {

        Dato cuenta = new Dato();
        PingPong t1 = new PingPong("PING", (int) (Math.random() * 2300), cuenta);
        PingPong t2 = new PingPong("PONG", (int) (Math.random() * 2000), cuenta);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(Thread.currentThread() + " chau-chau.adios");
        System.out.println(" dato " + cuenta.obtenerValor());
    }
}
