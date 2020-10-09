
package TrabajoPracticoHilos.Objetos;

public class CajeraRunnable implements Runnable {
    private String nombre;
    private Cliente cliente;
    private long initialTime;
    
    // Constructor, y métodos de acceso

    public CajeraRunnable(String nombre, Cliente cliente, long initialTime) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }

    public void run() {
        System.out.println("La cajera " + this.nombre +
                " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                + this.cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) + " "
                    + "del cliente " + this.cliente.getNombre() + "->Tiempo: " +
                    (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
        }
        System.out.println("La cajera" + this.nombre + "HA TERMINADO DE"
                + "PROCESAR"+ this.cliente.getNombre() + " EN EL TIEMPO: " +
                (System.currentTimeMillis() - this.initialTime) / 1000 +
                "seg");
    }
    public void esperarXsegundos(int tiempo){
        try{
            Thread.sleep(tiempo * 1000);
        }catch(InterruptedException e){
            System.out.println("exepcion");}
    }
}
