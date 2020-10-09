package TrabajoPracticoSeccionCritica;

import Objetos.Pasajero;
import Objetos.Taxi;
import Objetos.Taxista;

public class mainTaxi {

    public static void main(String[] args) {
        Taxi taxi = new Taxi();
        Taxista taxista = new Taxista(taxi);
        Pasajero pasa = new Pasajero("P1", taxi);
        Thread t1 = new Thread(taxista);
        Thread t2 = new Thread(pasa);
        t1.start();
        t2.start();
    }

}
