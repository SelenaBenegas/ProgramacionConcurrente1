package Parcial1;

import java.util.concurrent.Semaphore;

public class TorreControl {

    public Semaphore semDespegar = new Semaphore(0); //el avion que quiera desperar tiene que esperar que le den permiso
    public Semaphore semUsarPista = new Semaphore(1); // este semáforo asegura que en la pista solo haya un avion, tiene prioridad quien quiera aterrizar.
    public int contAterrizajes = 0; //modificacion del punto b, agruegé un contador de aterrizajes, cuando llegue a 10 puede despegar un avion

    public void usarPista(char accion) {

    }

}
    /*
    
    public void aterrizar() {
        try {
            semUsarPista.acquire(); // Permiso para usar la pista
            System.out.println(Thread.currentThread().getName() + ": Permiso para aterrizar.");
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Thread.sleep(5000);
            //tiempo de aterrizaje
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        contAterrizajes++; //aumenta la cantidad de aterrisajes
        System.out.println(Thread.currentThread().getName() + ": Aterrizaje exitoso.");
        semUsarPista.release(); //sale de la pista
        try {
            Thread.sleep(8000);
            // tiempo en tierra, fuera de la pista
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized boolean permisoDespegue() {
        boolean permiso = false;
        if (contAterrizajes >= 10) {
            try {
                semUsarPista.acquire(); //Permiso para usar la pista
                System.out.println(Thread.currentThread().getName() + ": Permiso para despegar.");
            } catch (InterruptedException ex) {
                Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            permiso = true;
            contAterrizajes = 0;//reiniciamos contador de aterrizajes
            semDespegar.release(); //puede despegar
            despegar();
        }
        return permiso;
    }
    
    public void despegar() {
        try {
            semDespegar.acquire(); //despega
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + ": Estamos despegando.");
        try {
            Thread.sleep(4000); //tiempo de despegue
            System.out.println(Thread.currentThread().getName() + ": Despegue exitoso.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        semUsarPista.release(); //Desocupa la pista
    }

}
