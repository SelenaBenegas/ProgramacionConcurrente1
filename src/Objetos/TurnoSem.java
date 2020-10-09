package Objetos;

import java.util.concurrent.Semaphore;

public class TurnoSem {

    private Semaphore semPasar = new Semaphore(1);
    private int turno = 1;
    private int repeticiones; //cantidad de "vueltas"
    private int cantidad; //cantidad de turnos

    public TurnoSem(int rep, int cant) {
        repeticiones = rep;
        cantidad = cant;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public int getTurno() {
        return turno;
    }

    public void pasarTurno() {
        semPasar.acquire();
        turno = turno + 1;
        if (turno > cantidad && repeticiones > 0) {
            turno = 1;
            repeticiones--;
        }
    }
}
