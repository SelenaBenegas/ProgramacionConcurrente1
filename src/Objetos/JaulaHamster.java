package Objetos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JaulaHamster {

    private final Lock llavePlato = new ReentrantLock();
    private final Lock llaveHamaca = new ReentrantLock();
    private final Lock llaveRueda = new ReentrantLock();

    public JaulaHamster() {
    }

    public void comer() {
        llavePlato.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " está comiendo");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " termino de comer");
        } finally {
            llavePlato.unlock();
        }
    }

    public void usarHamaca() {
        llaveHamaca.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " está descansando en la Hamaca");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " se desperto");
        } finally {
            llaveHamaca.unlock();
        }
    }

    public void usarRueda() {
        llaveRueda.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " está haciendo ejercicio en la Rueda");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " se canso y dejo la Rueda");
        } finally {
            llaveRueda.unlock();
        }
    }
}
