package tp6.blancaNieves;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Casita {

    private Semaphore silla;
    private Semaphore atender;
    private Semaphore volver;
    private Semaphore comer;
    private Semaphore pedir;
    private Semaphore semAtendeme;
    private boolean paseando;
    private int esperando;

    public Casita() {
        silla = new Semaphore(4, true);
        atender = new Semaphore(0, true);
        volver = new Semaphore(0, true);
        pedir = new Semaphore(0, true);
        comer = new Semaphore(0, true);
        semAtendeme = new Semaphore(1, true);
        paseando = true;
        esperando = 0;
    }

    public void comer(String nombre) {
        try {
            silla.acquire(); //si hay silla se sienta, sino queda esperando
        } catch (InterruptedException ex) {
            Logger.getLogger(Casita.class.getName()).log(Level.SEVERE, null, ex);
        }
        esperando++; //una vez que se sienta, espera la comida
        System.out.println("ESPERANDO = " + esperando);
        try {
            semAtendeme.acquire(); // le dice que lo atienda a él primero, los demas esperan a que le de la comida a el antes de poder pedir
        } catch (InterruptedException ex) {
            Logger.getLogger(Casita.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (paseando) { //Si BN esta paseando
            System.out.println(nombre + ": Podes volver y cocinar algo? toy chikito");
            volver.release(); // la llama para que vuelva a la casa
        }
        try {
            pedir.acquire(); //espera que bn le avise que llego
        } catch (InterruptedException ex) {
            Logger.getLogger(Casita.class.getName()).log(Level.SEVERE, null, ex);
        }
        esperando--; //como ya esta comiendo no tiene que esperar el plato
        System.out.println("ESPERANDO = " + esperando);
        System.out.println(nombre + ": Algo rico.");
        atender.release(); // le avisa a BN que ya puede cocinar
        try {
            comer.acquire(); //espera que blancanieves le de la comida
        } catch (InterruptedException ex) {
            Logger.getLogger(Casita.class.getName()).log(Level.SEVERE, null, ex);
        }
        semAtendeme.release(); //termina de atenderlo, y puede atender a otro enano
        System.out.println(nombre + ": Que rico que esta estoo, muchas gracias");
    }

    public void terminarComer(String nombre) {
        System.out.println(nombre + ": me voy.");
        //cuando termina de comer libera una silla
        silla.release();
    }

    public void cocinar(String nombre) {
        if (esperando == 0) { //si no hay nadie esperando
            paseando = true; // se va a pasear
            System.out.println("\u001B[35m" + nombre + ": Estoy paseando");
            try {
                volver.acquire(); //espera a que llamen para volver
            } catch (InterruptedException ex) {
                Logger.getLogger(Casita.class.getName()).log(Level.SEVERE, null, ex);
            }
            paseando = false;
            System.out.println("\u001B[35m" + nombre + ": Ya estoy volviendo a la casita.");
        } else {
            System.out.println(nombre + ": Que queres comer?");
            pedir.release();
            try {
                atender.acquire(); //espera a que un enanito le diga que le cocine
            } catch (InterruptedException ex) {
                Logger.getLogger(Casita.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(nombre + ": Cocinando...");
            try {
                Thread.sleep(1000); //simula cocinar a los enanitos
            } catch (InterruptedException ex) {
                Logger.getLogger(Casita.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(nombre + ": Acá está tu comida.");
            comer.release(); //le da la comida al enano
        }
    }
}
