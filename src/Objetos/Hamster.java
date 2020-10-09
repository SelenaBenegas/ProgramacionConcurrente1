package Objetos;

public class Hamster implements Runnable {

    private String nombre;
    private JaulaHamster jaula;

    public Hamster(String nom, JaulaHamster j) {
        nombre = nom;
        jaula = j;
    }

    public String getName() {
        return nombre;
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
