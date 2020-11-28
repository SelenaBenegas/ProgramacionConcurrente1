package tp6;

//import java.util.Random;
import java.util.Random;

/**
 *
 * @author Selena Benegas
 */
public class mainObservatorio {

    public static void main(String[] args) {
        Random cant = new Random();
        int num = cant.nextInt(25 - 10) + 10;
        int cantV = num;
        num = cant.nextInt(7 - 4) + 4;
        int cantM = num;
        num = cant.nextInt(10 - 5) + 5;
        int cantI = num;
        int cantH = cantV + cantI + cantM;
        Observatorio obs = new Observatorio(15, 10);
        Visitante[] visitantes = new Visitante[cantV];
        Investigador[] investigadores = new Investigador[cantI];
        Mantenimiento[] auxiliares = new Mantenimiento[cantM];
        Thread[] hilos = new Thread[cantH];
        for (int i = 0; i < cantV; i++) {
            visitantes[i] = new Visitante("Visitante" + i, obs);
        }
        for (int i = 0; i < cantI; i++) {
            investigadores[i] = new Investigador("Investigador" + i, obs);
        }
        for (int i = 0; i < cantM; i++) {
            auxiliares[i] = new Mantenimiento("Auxiliar" + i, obs);
        }
        for (int i = 0; i < cantV; i++) {
            hilos[i] = new Thread(visitantes[i]);
        }
        int aux = cantV + cantI;
        int j = 0;
        for (int i = cantV; i < aux; i++) {
            hilos[i] = new Thread(investigadores[j]);
            j++;
        }
        j = 0;
        for (int i = aux; i < cantH; i++) {
            hilos[i] = new Thread(auxiliares[j]);
            j++;
        }
        for (int i = 0; i < cantH; i++) {
            hilos[i].start();
        }
    }

}
