package Objetos;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Confiteria2 {

    public Semaphore semAtender = new Semaphore(0, true); //el mozo espera que un empleado lo llame
    public Semaphore semPedir = new Semaphore(0, true); // el empleado espera que el mozo le diga que ordene
    public Semaphore semServir = new Semaphore(0, true);  // el mozo espera a que el empleado ordene
    public Semaphore semBebida = new Semaphore(0, true); // el empleado espera que el mozo de le de su bebida
    public Semaphore semComer = new Semaphore(0, true);  //el empleado es pera que el cocinero le de su comida
    public Semaphore semCocinar = new Semaphore(0, true); //el cocinero espera que el empleado ordene su comida
    public Semaphore semMozoAtendeme = new Semaphore(1, true);
    //semaforo que adquiere cuando entra, y el mozo libera cuando lo termino de atender (otro empleado no me sume permisos en semServir).
    public Semaphore semCocineroAtendeme = new Semaphore(1, true);
    //semaforo que adquiere cuando pide su comida, y el cocinero libera cuando lo termino de atender (otro empleado no me sume permisos en semCocinar).
    public int cantSillas;
    public Semaphore semSilla = new Semaphore(2, true);

    public Confiteria2() {
    }

    //usando semaforos generales en vez de un contador de sillas
    public void entrarAConfiteria(String colorEmp) {
        try {
            //si la silla esta libre se queda
            semSilla.acquire(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ordenar(colorEmp);
    }

    /* public Confiteria2(int sillas) {
        cantSillas = sillas;
    }

    public synchronized  Boolean entrarAConfiteria() {  //si la silla esta libre se queda
        Boolean hayLugar = false;
        if (cantSillas > 0) {
            hayLugar = true;
            lugar(-1);
        }
        return hayLugar;
    }

    public synchronized void lugar(int n) {
        cantSillas += n;
    }
     */
    public void pedido(String colorEmp) { //con un random elegimos la opción del menu que completa el pedido
        int num = (int) (Math.random() * (3 - 1)) + 1;
        switch (num) {
            case 1:
                System.out.println(colorEmp + Thread.currentThread().getName() + ": Quiero un café, por favor");
                pedirBebida(colorEmp);
                break;
            case 2:
                System.out.println(colorEmp + Thread.currentThread().getName() + ": Quiero un cafe y una porcion de pollo frito, por favor");
                pedirBebida(colorEmp);
                pedirComida(colorEmp);
                break;
            case 3:
                System.out.println(colorEmp + Thread.currentThread().getName() + ": Quiero una porcion de pollo frito, por favor");
                pedirComida(colorEmp);
                break;
        }
    }

    public void pedirBebida(String colorEmp) {

        semServir.release(); //le dice al mozo que le sirva
        try {
            semBebida.acquire(); //espera que el mozo le de la bebida
            System.out.println(colorEmp + Thread.currentThread().getName() + ": ayy tenía sed");
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pedirComida(String colorEmp) {
        try {
            semCocineroAtendeme.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(colorEmp + Thread.currentThread().getName() + ": Chef! mi pollo!");
        semCocinar.release(); //le dice al cocinero que le cocine
        try {
            semComer.acquire(); //espera que el cocinero termine su comida
            System.out.println(colorEmp + Thread.currentThread().getName() + ": esto esta re rico! ");
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Thread.sleep(1500); //simula el tiempo en comer
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ordenar(String colorEmp) {
        try {
            semMozoAtendeme.acquire();  //semaforo para que el mozo lo atienda a el y otro empleado no me sume permisos en semServir.
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(colorEmp + Thread.currentThread().getName() + ": MOZO!");
        semAtender.release(); //le dice al mozo que lo atienda
        try {
            semPedir.acquire(); // el mozo le da el menu
            Thread.sleep(1000); //simula el tiempo en elegir el pedido
            pedido(colorEmp);
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(colorEmp + Thread.currentThread().getName() + ": muchas gracias! Chau (:" + "\n");
        //lugar(1);//libera la silla
        semSilla.release();
    }

    public void atenderCliente() {
        try {
            semAtender.acquire();
            System.out.println(Thread.currentThread().getName() + ": Buenas, le dejo el menú");
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        semPedir.release();
        servirBebida();
    }

    public void servirBebida() {
        try {
            semServir.acquire();
            System.out.println(Thread.currentThread().getName() + ": En seguida se lo traigo");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Confiteria2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + ": Aquí tiene su bebida");
        semBebida.release();
        semMozoAtendeme.release();
    }

    public void cocinar() {
        try {
            semCocinar.acquire();
            System.out.println(Thread.currentThread().getName() + ": Ya se lo cocino");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Confiteria2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + ": Aquí tiene su comida, buen provecho");
        semComer.release();
        semCocineroAtendeme.release();
    }
}
