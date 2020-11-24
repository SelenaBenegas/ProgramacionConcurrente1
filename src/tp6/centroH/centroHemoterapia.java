package tp6.centroH;

import tp6.centroH.Paciente;
import tp6.centroH.SalaH;
import java.util.Random;

/**
 *
 * @author Selena Benegas
 */
public class centroHemoterapia {

    public static void main(String[] args) {
        Random cant = new Random();
        int cantP = cant.nextInt(30 - 10) + 10;
        SalaH centro = new SalaH();
        Paciente[] pacientes = new Paciente[cantP];
        Thread[] hilos = new Thread[cantP];
        System.out.println("Cantidad de pacientes que vendran hoy: " + cantP);
        System.out.println("Nota: estan en while(true).");
        for (int i = 0; i < cantP; i++) {
            pacientes[i] = new Paciente("Paciente" + i, centro);
        }
        for (int i = 0; i < cantP; i++) {
            hilos[i] = new Thread(pacientes[i]);
        }
        for (int i = 0; i < cantP; i++) {
            hilos[i].start();
        }

    }

}
