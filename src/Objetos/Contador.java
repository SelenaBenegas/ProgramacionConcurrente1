package Objetos;

public class Contador {

    int valor;

    public Contador() {
        valor = 0;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int nro) {
        valor = nro;
    }

    public synchronized void incrementar() { //aumenta de a 1
        valor = valor + 1;
    }

    public void incrementar(int incremento) { //aumenta de a n
        synchronized (this) {
            valor = valor + incremento;
        }
    }
}
