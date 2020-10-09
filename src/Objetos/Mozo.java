package Objetos;

public class Mozo implements Runnable {

    private Confiteria confiteria;

    public Mozo(Confiteria l) {
        confiteria = l;

    }

    @Override
    public void run() {
        while(true){
        confiteria.atenderCliente();
        }
    }
}
