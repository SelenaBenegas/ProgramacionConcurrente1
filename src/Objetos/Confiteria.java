package Objetos;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Confiteria {

    public Semaphore semSilla = new Semaphore(1);
    public Semaphore semAtender = new Semaphore(0);
    public Semaphore semPedir = new Semaphore(0);
    public Semaphore semServir = new Semaphore(0);
    public Semaphore semComer = new Semaphore(0);

    public Confiteria() {
        // constructor vacío
    }

    public Boolean entrarAConfiteria() {  //si la silla esta libre se queda
        return semSilla.tryAcquire();
    }

    public String pedido() { //con un random elegimos la opción del menu que completa el pedido
        int num = (int) (Math.random() * (6 - 1)) + 1;
        String pedido = "";
        switch (num) {
            case 1:
                pedido = " una porcion de pollo frito";
                break;
            case 2:
                pedido = " un cafe y tostados";
                break;
            case 3:
                pedido = " un  cafecito";
                break;
            case 4:
                pedido = " alitas de pollo picante";
                break;
            case 5:
                pedido = " una coca y nuggets de pollo";
                break;
        }
        return pedido;
    }

    public void ordenar() {
        System.out.println("- MOZO! Soy " + Thread.currentThread().getName());
        semAtender.release(); //le dice al mozo que lo atienda
        try {
            semPedir.acquire(); // el mozo le da el menu
            System.out.println("- A ver...");
            Thread.sleep(500); //simula el tiempo en elegir el pedido
            System.out.println("- Quiero" + pedido() + ", por favor");
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        semServir.release(); //le dice al mozo que le sirva
        try {
            semComer.acquire(); //espera que el mozo le de la comida
            System.out.println("- Está tremendo eh");
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Thread.sleep(500); //simula el tiempo en comer
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("- Muy rico, muchas gracias! Chau (:"+"\n");
        semSilla.release(); //libera la silla

    }

    public void atenderCliente() {
        try {
            semAtender.acquire();
            System.out.println("+ Buenas, le dejo el menú");
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        semPedir.release();
        try {
            semServir.acquire();
            System.out.println("+ En seguida se lo traigo");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Confiteria.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("+ Aquí tiene, provecho");
        semComer.release();
    }
}
