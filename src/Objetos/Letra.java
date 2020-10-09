package Objetos;

public class Letra implements Runnable {

    private char letra;
    private int numTurno;
    private int cant;
    private Turno turnos;

    public Letra(char letra, int num, int cantidad, Turno turnos) {
        this.letra = letra;
        this.numTurno = num;
        this.cant = cantidad;
        this.turnos = turnos;
    }

    public int getTurno() {
        return numTurno;
    }

    public int getCantidad() {
        return cant;
    }

    public void run() {
        while (turnos.getRepeticiones() > 0) {
            if (this.numTurno == turnos.getTurno()) { //verifica si es su turno
                for (int i = 1; i <= this.cant; i++) { // imprime la cantidad que le corresponde
                    System.out.print(letra);
                }
                turnos.pasarTurno();
            } else { // si no es su turno se duerme
                try {
                    Thread.sleep(4);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
