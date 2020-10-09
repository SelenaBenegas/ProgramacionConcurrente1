
package TrabajoPracticoHilos.Objetos;

import TrabajoPracticoHilos.Objetos.Cliente;

public class Cajera {
    private String nombre;
    // Agregar Constructor, y métodos de acceso

    public Cajera(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
      
    public void procesarCompra(Cliente cliente, long timeStamp) {
        System.out.println ("La cajera " + this.nombre + "COMIENZA A "
                + "PROCESAR LA COMPRA DEL CLIENTE"+ cliente.getNombre() + " EN EL "
                        + "TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) +
            "->Tiempo: " + (System.currentTimeMillis() - timeStamp) /
            1000 + "seg");
        }
        System.out.println("La cajera " + this.nombre +" HA TERMINADO DE "
                + "PROCESAR " + cliente.getNombre() + " EN EL TIEMPO: " +
                (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
    }
    
    public void esperarXsegundos(int tiempo){
        try{
            Thread.sleep(tiempo * 1000);
        }catch(InterruptedException e){
            System.out.println("exepcion");}
    }
}

