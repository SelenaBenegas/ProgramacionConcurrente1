package tp6.centroH;

public class ColaDin {

    private Nodo frente;
    private Nodo fin;

    public ColaDin() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object nuevElto) {
        Nodo nuevo = new Nodo(nuevElto, null);
        if (this.frente == null) { //si el frente es nulo, actualizo el frente
            this.frente = nuevo;
        } else {
            this.fin.setEnlace(nuevo); //tiene al menos un elemento, lo enlazo al ultimo elemento
        }
        this.fin = nuevo; //actualizo fin
        return true;
    }

    public boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {
            // error de cola vacia
            exito = false;
        } else {
            //saca el primer elemento y actualiza frente
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                //si queda vacía, tambien actualiza fin
                this.fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elem = null;
        if (this.frente != null) { //si la cola no esta vacía retorno el elemento del frente
            elem = this.frente.getElemento();
        }
        return elem;
    }

    public boolean esVacia() {
        return (this.frente == null);
    }

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    public ColaDin clone() {
        ColaDin clon = new ColaDin(); //creo una cola clon vacía
        Nodo aux1, aux2; // nodos auxiliares 
        if (this.frente != null) {
            aux1 = this.frente; //aux 1, recorre la cola original
            clon.frente = new Nodo(aux1.getElemento(), null); // creo un nodo con el elemento apuntado por aux1
            aux1 = aux1.getEnlace();
            aux2 = clon.frente; //aux 2, recorre la cola clon
            while (aux1 != null) {
                aux2.setEnlace(new Nodo(aux1.getElemento(), null)); // creo el nodo con el elem de aux1 y lo enlazo a aux2
                aux2 = aux2.getEnlace();
                aux1 = aux1.getEnlace();
            }
            clon.fin = aux2;
        }
        return clon;
    }

    @Override
    public String toString() {
        String cadena;
        if (this.frente == null && this.fin == null) {
            cadena = "Cola vacía.";
        } else {
            Nodo aux = this.frente; // nodo auxiliar que recorre la cola
            cadena = "[";
            while (aux != null) {
                cadena += aux.getElemento().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    cadena += " , ";
                }
            }
            cadena += "]";
        }
        return cadena;
    }
}
