package TrabajoPracticoSincronizacion;

import Objetos.Curandero;
import Objetos.Orco;
import Objetos.Vida;

public class mainOrcoCurandero {

    public static void main(String[] args) {
        Vida vida = new Vida();
        Orco orco = new Orco(vida);
        Curandero curandero = new Curandero(vida);
        Thread to = new Thread(orco);
        Thread tc = new Thread(curandero);
        to.start();
        tc.start();
    }
}
