package Objetos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VerificarCuenta implements Runnable {

    private CuentaBanco cb = new CuentaBanco();

    public void run() {
        for (int i = 0; i <= 3; i++) {
            try {
                cb.hacerRetiro(10);
                if (cb.getBalance() < 0) {
                    System.out.println("La cuenta está sobregirada.");
                }
            } catch (Exception ex) {
                Logger.getLogger(VerificarCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
