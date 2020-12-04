package Parcial2;

public class mainAgua {

    public static void main(String[] args) {
        int k = 4;
        Formacion f = new Formacion(k);
        Generador g = new Generador(f);
        Hlisto h = new Hlisto(f);
        Olisto o = new Olisto(f);
        Thread gen = new Thread(g), hi = new Thread(h), ox = new Thread(o);
        gen.start();
        hi.start();
        ox.start();
    }
}
