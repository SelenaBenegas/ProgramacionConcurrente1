package Teoria;

/**
 *
 * @author Selena Benegas
 */
public class mainLectorEscritorMonitores {

    public static void main(String[] args) {
        int cantE = 5;
        int cantL = 15;
        int cantH = cantE + cantL;
        LibroConMonitores libro = new LibroConMonitores();
        Escritor[] escritores = new Escritor[cantE];
        Lector[] lectores = new Lector[cantL];
        Thread[] hilos = new Thread[cantE + cantL];
        for (int i = 0; i < cantE; i++) {   // bucle donde se crean los escritores.
            escritores[i] = new Escritor(libro, "Escritor" + i);
        }
        for (int i = 0; i < cantL; i++) {   // bucle donde se crean los lectores.
            lectores[i] = new Lector(libro, "Lector" + i);
        }
        for (int i = 0; i < cantL; i++) { // bucle donde se crean los hilos.
            hilos[i] = new Thread(lectores[i]);
        }
        int j = 0;
        for (int i = cantL; i < cantH; i++) { // bucle donde se terminan de crear los hilos.
            hilos[i] = new Thread(escritores[j]);
            j++;
        }
        for (int i = 0; i < cantH; i++) { //bucle start de los hilos.
            hilos[i].start();
        }
    }

}
