package Objetos;

import Objetos.Dato;

/*public class PingPong extends Thread {

    private String cadena; // Lo que va a escribir.
    private int delay; // Tiempo entre escritura

    public PingPong(String cartel, int cantMs) {
        cadena = cartel;
        delay = cantMs;
    }

    ;
    public void run() {
        for (int i = 1; i < delay * 10; i++) {
            System.out.print(cadena + "");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Excepción1");
            }
        }
    } //fin método run()
}//fin clase PingPong */
public class PingPong extends Thread {

    private int delay;            //info de las iteraciones
    private Dato miD;          // para mantener el total
    private int miCta = 0;    // para contar mis iteraciones

// constructor 1, que refina al constructor heredado de Thread
    public PingPong(String cartel, int cantMs) {
        super(cartel);
        this.delay = cantMs;
    }

// constructor 2, que utiliza al constructor 1
    public PingPong(String cartel, int cantMs, Dato dd) {
        this(cartel, cantMs);
        this.miD = dd;
    }

    public void run() {

        for (int i = 1; i < delay * 2; i++) {
// System.out.print(this.getName() + " ");
            miCta++;
            this.miD.contar();

        } // del for
        System.out.println(miCta + " veces " + this.getName());

    } // del run
}
    
