package tp6;

import static java.lang.Math.random;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Selena Benegas
 */
public class Observatorio {

    private Lock entrada;
    private Condition permitido;
    private int cantMax; //cantidad máxima
    private int cantRes; //cantidad restringida
    private int cantSup; // cantidad de veces que se lleno el observatorio
    private int adentro; //cantidad de visitantes adentro
    private int cantSillas; //cantidad de visitantes en silla de ruedas adentro
    private int capacidad; //capacidad actual
    private int limpiando; // cantidad de personal adentro limpiando
    private int observaciones; // libro diario de observaciones de los investigadores
    private int obsMax; //Observaciones maximas por día
    private boolean investigando; // ¿estan investigando?
    //Nos toca a nosotros o no? mmm seguridad haceme entrarr
    private boolean entrarV;
    private boolean entrarM;
    private boolean entrarI;

    public Observatorio(int cMax, int cRes) {
        Random num = new Random();
        int cant = num.nextInt(5 - 3) + 3; //hasta que se hagan 3 a 5 investigaciones por dia
        entrada = new ReentrantLock();
        permitido = entrada.newCondition();
        cantMax = cMax;
        cantRes = cRes;
        capacidad = cMax;
        obsMax = cant;
        cantSup = 0;
        cantSillas = 0;
        adentro = 0;
        limpiando = 0;
        observaciones = 0;
        investigando = false;
        entrarV = true;
        entrarI = false;
        entrarM = false;
    }

    public void entrar(String nombre, boolean silla) {
        entrada.lock();
        try {
            System.out.println(nombre + ": Espero para entrar.");
            while (adentro >= capacidad || investigando || limpiando > 0 || !entrarV) {
                permitido.await();
            }
            System.out.println(nombre + ": Ya entre.");
            if (silla) {
                System.out.println(nombre + ": Ando en sillas de ruedas.");
                cantSillas++;
                capacidad = cantRes;
            }
            adentro++;
            if (adentro == capacidad) {
                cantSup++;
            }
            System.out.println("\u001B[36m" + "--- Capacidad: " + capacidad + ", adentro hay: " + adentro + " ---");
            System.out.println("\u001B[36m" + "--- En sillas de ruedas: " + cantSillas + " ---");
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entrada.unlock();
        }
    }

    public void salir(String nombre, boolean silla) {
        entrada.lock();
        try {
            System.out.println(nombre + ": Voy a salir.");
            adentro--;
            if (silla) {
                cantSillas--;
                if (cantSillas == 0) {
                    System.out.println(nombre + ": Soy el último con sillas de ruedas.");
                    capacidad = cantMax;
                }
            }
            System.out.println("\u001B[36m" + "--- Capacidad: " + capacidad + ", adentro hay: " + adentro + " ---");
            System.out.println("\u001B[36m" + "--- En sillas de ruedas: " + cantSillas + " ---");
            seguridadEntrada();
        } finally {
            entrada.unlock();
        }
    }

    public void investigar(String nombre) {
        entrada.lock();
        try {
            System.out.println(nombre + ": Esperando para investigar.");
            while (adentro > 0 || investigando || limpiando > 0 || !entrarI) {
                permitido.await();
            }
            System.out.println(nombre + ": Comenzando investigacion.");
            investigando = true;
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entrada.unlock();
        }

    }

    public void terminarInvestigacion(String nombre) {
        entrada.lock();
        try {
            System.out.println(nombre + ": Investigacion terminada.");
            investigando = false;
            observaciones++;
            if (observaciones >= obsMax) {
                seguridadSalida();
            }
            System.out.println("\u001B[36m" + "---- Cantidad de observaciones: " + observaciones + " ----");
        } finally {
            entrada.unlock();
        }
    }

    public void limpiar(String nombre) {
        entrada.lock();
        try {
            System.out.println(nombre + ": Esperando para trabajar.");
            while (adentro > 0 || investigando || !entrarM) {
                permitido.await();
            }
            limpiando++;
            System.out.println(nombre + ": Limpiando");
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entrada.unlock();
        }

    }

    public void terminarLimpieza(String nombre) {
        entrada.lock();
        try {
            limpiando--;
            System.out.println(nombre + ": Termine de limpiar.");
            if (limpiando == 0) {
                System.out.println(nombre + ": Mantenimiento finalizado.");
                seguridadSalida();
            }
        } finally {
            entrada.unlock();
        }
    }

    private void seguridadEntrada() {
        Random num = new Random();
        int cant = num.nextInt(4 - 1) + 1; //hasta que se llene 1 a 4 veces
        if (cantSup == cant) {
            entrarV = false;
            cantSup = 0;
            cant = num.nextInt(2); //random de 0 o 1
            if (cant == 1) {
                System.out.println("\u001B[31m" + "Seguridad: cuando se pueda pasa un investigador.");
                entrarI = true;
            } else {
                System.out.println("\u001B[31m" + "Seguridad: cuando se pueda pasan a limpiar.");
                entrarM = true;
            }
        }
        permitido.signalAll();
    }

    private void seguridadSalida() {
        System.out.println("\u001B[31m" + "Seguridad: cuando se pueda pasan los visitantes.");
        observaciones = 0;
        entrarV = true;
        entrarI = false;
        entrarM = false;
        permitido.signalAll();
    }

}
