package Objetos;
public class SurtidorNafta {
    private boolean disponibilidad;
    
    public SurtidorNafta(){
        disponibilidad = true;
    }

    public synchronized boolean tieneDisponibilidad() {
        return disponibilidad;
    }
    
    public synchronized void usarSurtidor(){
        disponibilidad = false;
    }
    
    public synchronized void liberarSurtidor(){
        disponibilidad = true;
    }
    
}
