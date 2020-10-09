package Objetos;

public class Orco implements Runnable {

    private String nombre;
    private Vida vida;
    private int dañar = 3;

    public Orco(Vida puntos) {
        nombre = "Orco";
        vida = puntos;
    }

    public void run() {
        System.out.println("Orco, la vida actual es de: " + vida.getVida() + " puntos.");
        vida.hacerDaño(dañar);
        System.out.println(nombre + " daño con " + dañar + " puntos de vida, vida actual: " + vida.getVida());

    }

}
