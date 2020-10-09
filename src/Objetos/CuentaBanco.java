package Objetos;

public class CuentaBanco {

    private int balance = 50;

    public CuentaBanco() {
    }

    public int getBalance() {
        return balance;
    }

    public void retiroBancario(int retiro) {
        balance = balance - retiro;
    }

    public synchronized void hacerRetiro(int cantidad) throws InterruptedException {
        if (this.getBalance() >= cantidad) {
            System.out.println(Thread.currentThread().getName() + " está realizando un retiro de: " + cantidad + ".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            this.retiroBancario(cantidad);
            System.out.println(Thread.currentThread().getName() + ": Retiro realizado.");
            System.out.println(Thread.currentThread().getName() + ": Losfondos son de: " + this.getBalance());
        } else {
            System.out.println("\u001B[31m"+"No hay suficiente dinero en la cuenta para realizar el retiro Sr." + Thread.currentThread().getName()+"\u001B[0m");
            System.out.println("Su saldo actual es de: " + this.getBalance());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    } // fin de hacer retiro
}
