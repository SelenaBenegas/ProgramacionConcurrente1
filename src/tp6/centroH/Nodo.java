package tp6.centroH;

public class Nodo {
    private Object elemento;
    private Nodo enlace;
    
    public Nodo (Object elemento, Nodo enlace) {
        this.elemento = elemento;
        this.enlace = enlace;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elem) {
        this.elemento = elem;
    }

    public Nodo getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }
    
}
