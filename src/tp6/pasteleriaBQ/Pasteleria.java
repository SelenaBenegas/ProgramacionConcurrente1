package tp6.pasteleriaBQ;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Pasteleria {

    protected BlockingQueue mostrador;
    private Lock escribir; //Lock para sincronizar los carteles y la variable contadora hayPastel
    private boolean hayCaja; // condicion de espera para el brazo
    private int hayPastel; // cuenta la cantidad de pasteles en el mostrador
    private int pesoActual; //peso actual de la caja, incrementa cada vez que se agrega un pastel
    private int pesoMax; // peso máximo que soporta la caja

    public Pasteleria() {
        // ** elegir uno y comentar los otros**
        mostrador = new ArrayBlockingQueue(5, true); //En el mostrador entran 5 pasteles
        //mostrador = new LinkedBlockingQueue(5); //En el mostrador entran 5 pasteles
        //mostrador = new LinkedBlockingQueue(); //La cantidad de pasteles en el mostrador es ilimitada
        //** lo que sigue queda igual***
        escribir = new ReentrantLock();
        hayCaja = true; //comienza con una caja 
        pesoMax = (int) (Math.random() * 5) + 10; // el peso máximo de la caja inicial es random
        pesoActual = 0; //la caja inicial está vacía
        hayPastel = 0; //aun no hay pasteles en el mostrador
    }

    //TRABAJO DEL HORNO
    public void hornearPastel(int peso) {
        try {
            mostrador.put(peso); //cuando termina de hornear se pone el pastel en el mostrador, si no hay lugar espera bloqueado
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        }
        escribir.lock();
        System.out.println("\u001B[35m" + "**Se agrega pastel de " + peso + "kg al mostrador.**");
        hayPastel++;
        System.out.println("** Pasteles en mostrador: " + hayPastel + " **");
        escribir.unlock();
    }

    //TRABAJO DEL ROBOT
    public void trabajar(String nombre) {
        int peso = tomarPastel(nombre);
        soltarPastel(peso, nombre);
    }

    private int tomarPastel(String nombre) {
        int peso = 0;
        try {
            peso = (int) mostrador.take(); //Si hay pasteles en el mostrador toma uno,  si no hay espera bloqueado
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        }
        escribir.lock();
        System.out.println("\u001B[32m" + nombre + ": Tomo pastel de " + peso + "kg.");
        hayPastel--;
        System.out.println("** Pasteles en mostrador: " + hayPastel + " **");
        escribir.unlock();
        return peso;
    }

    private synchronized void soltarPastel(int peso, String nombre) {
        if (hayCaja) { //si hay caja
            if (pesoActual + peso > pesoMax) { //verifica que el pastel que va a poner no supere el peso maximo de la caja
                System.out.println("\u001B[31m" + nombre + ": Este pastel supera el peso máximo.");
                hayCaja = false; // si lo supera, setea hayCaja a false, para que los robots no puedan poner mas pasteles y el brazo deba cambiarlo
                this.notifyAll(); // notifica tanto a los robots como al brazo
            }
        }
        try {
            while (!hayCaja) { //mientras no haya una caja disponible  deben esperar
                this.wait();
            }
            //una vez que cambian la caja, pueden colocar un pastel
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        }
        //si es pastel no superaba el peso maximo de la caja, simplemente pone el pastel en la caja.
        System.out.println(nombre + ": Coloco pastel de " + peso + "kg. ");
        pesoActual += peso; //se actualiza el peso
        System.out.println("Peso maximo: " + pesoMax + "kg. - Peso actual: " + pesoActual + "kg. ");
    }

// TRABAJO DEL BRAZO
    public synchronized void retirarCaja(String nombre) {
        try {
            while (hayCaja) { //mientras haya una caja disponible para colocar pasteles, el brazo debe esperar
                this.wait();
            }
            //una vez que notifican que la caja de se lleno
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        }
        //la cambia, seteando el pesoActual a cero
        System.out.println("\u001B[34m" + nombre + ": Retira caja.");
        pesoActual = 0;
    }

    public synchronized void ponerCaja(String nombre) {
        System.out.println("\u001B[34m" + nombre + ": Pone caja nueva.");
        hayCaja = true; //luego no tifica que hay una caja disponible
        pesoMax = (int) (Math.random() * 5) + 10; // el peso maximo de la nueva caja es random
        System.out.println("Peso maximo: " + pesoMax + "kg. ");
        this.notifyAll();
    }
}
