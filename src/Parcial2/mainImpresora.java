package Parcial2;

/**
 *
 * @author Selena Benegas
 */
public class mainImpresora {

    public static void main(String[] args) {
        int t = 4; //TAMAÑO DE IMPRESORA LIMITADA
        int cant = 5;
        Impresora imp = new Impresora(t);
        Servidor s = new Servidor(imp);
        Usuario[] usuarios = new Usuario[cant];
        Thread[] hilos = new Thread[cant];
        Thread servidor = new Thread(s);
        for (int i = 0; i < cant; i++) {   // bucle donde se crean
            usuarios[i] = new Usuario(imp, "Usuario" + i);
        }
        for (int i = 0; i < cant; i++) { // bucle donde se crean los hilos 
            hilos[i] = new Thread(usuarios[i]);
        }
        for (int i = 0; i < cant; i++) { //bucle start de los hilos.
            hilos[i].start();
        }
        servidor.start();
    }
}
