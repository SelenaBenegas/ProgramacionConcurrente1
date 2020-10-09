package TrabajoPracticoSincronizacion;

import Objetos.Letra;
import Objetos.Turno;

public class mainLetras {

    public static void main(String[] args) {
        Letra a, b, c;
        Thread t1, t2, t3;
        Turno turno;
        int i = (int) (Math.random() * 10 + 1);
        turno = new Turno(i, 3);
        a = new Letra('A', 1, 1, turno);
        b = new Letra('B', 2, 2, turno);
        c = new Letra('C', 3, 3, turno);
        t1 = new Thread(a);
        t2 = new Thread(b);
        t3 = new Thread(c);
        t1.start();
        t2.start();
        t3.start();
    }
}
