package Objetos;

public class Hamster implements Runnable {

    private JaulaHamster jaula;

    public Hamster(JaulaHamster j) {
        jaula = j;
    }

    @Override
    public void run() {
        while (true) {
            jaula.comer();
            jaula.usarHamaca();
            jaula.usarRueda();
        }
    }
}
