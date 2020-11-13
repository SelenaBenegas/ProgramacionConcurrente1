package Parcial1;

public class aeropuerto {

    public static void main(String[] args) {
        Avion[] aviones = new Avion[50];
        TorreControl torre = new TorreControl();
        Thread[] hilos = new Thread[20];
        for (int i = 0; i < 20; i++) {   // bucle donde se crean los aviones
            aviones[i] = new Avion(torre, "Avion" + i);
        }
        for (int i = 0; i < 20; i++) { // bucle donde se crean los hilos aviones
            hilos[i] = new Thread(aviones[i], "Avión" + i);
        }
        for (int i = 0; i < 20; i++) { //bucle start de los hilos.
            hilos[i].start();
        }
    }

}
