package Objetos;

public class Vida {

    private int puntos;

    public Vida() {
        this.puntos = 10;
    }

    public synchronized int getVida() {
        return puntos;
    }

    public synchronized void hacerDaño(int pDaño) {
        puntos -= pDaño;
    }

    public synchronized void curar(int pVida) {
        puntos += pVida;
    }

    /*public synchronized boolean hacerDaño(int pDaño) {
        boolean daño = false;
        if (puntos > 0) {
            puntos -= pDaño;
            daño = true;
        }
        return daño;
    }

    public synchronized boolean curar(int pVida) {
        boolean cura = false;
        if (puntos < 10) {
            puntos += pVida;
            cura = true;
        }
        return cura;
    }*/
}
