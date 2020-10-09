
package Objetos;

public class GeneradorNros implements Runnable {

    private String nombre;
    private Contador cuenta;
    private int cantidad;

    public GeneradorNros(Contador cont, String cadena, int maximo) {
        cuenta = cont;
        nombre = cadena;
        cantidad = maximo;
    }

    public void run() {
        int totalNros = 0;
        double nroAleatorio;
        for (int i = 1; i < cantidad; i++) {
            nroAleatorio = Math.random() * 10;
            if (nroAleatorio > 5 && nroAleatorio < 6) {
                totalNros++;
            }
        }
        cuenta.incrementar(totalNros);
        System.out.println("hilo " + nombre + " genero " + totalNros);
    }
}
