package Objetos;

public class Curandero implements Runnable {

    private String nombre;
    private Vida vida;
    private int cura = 3;

    public Curandero(Vida puntos) {
        nombre = "Curandero";
        vida = puntos;
    }

    public void run() {
        System.out.println("Curandero, la vida actual es de: " + vida.getVida()+ " puntos.");
        vida.curar(cura);
        System.out.println(nombre + " curo con "+cura+" puntos de vida, vida actual: "+vida.getVida());
    }

}
