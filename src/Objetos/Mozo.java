package Objetos;

public class Mozo implements Runnable {

    private Confiteria2 confiteria;

    public Mozo(Confiteria2 l) {
        confiteria = l;

    }

    @Override
    public void run() {
        while (true) {
            confiteria.atenderCliente();
        }
    }
}
