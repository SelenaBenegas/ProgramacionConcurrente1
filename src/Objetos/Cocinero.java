package Objetos;

public class Cocinero implements Runnable {

    private Confiteria2 confiteria;

    public Cocinero(Confiteria2 l) {
        confiteria = l;
    }

    public void run() {
        while (true) {
            confiteria.cocinar();
        }
    }
}
