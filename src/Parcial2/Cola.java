package Parcial2;

import BenegasFAI2649Parcial2.*;

public class Cola {

    private Object[] arreglo;
    private int frente;
    private int fin;
    private int TAMANO;

    public Cola(int t) {
        this.arreglo = new Object[t];
        TAMANO = t;
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poner(Object nuevElto) {
        boolean exito;
        if (((this.fin + 1) % TAMANO) == this.frente) {
            exito = false; // error de cola llena
        } else {
            this.arreglo[this.fin] = nuevElto; //actualizo fin
            this.fin = (this.fin + 1) % TAMANO; //avanza fin de manera circular
            exito = true;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito = true;
        if (this.frente == this.fin) {
            exito = false; //error de cola vacía
        } else {
            arreglo[frente] = null;
            this.frente = (this.frente + 1) % this.TAMANO; //avanza frente de manera circular
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elem = null;
        if (arreglo[frente] != null) { //si la cola no esta vacía retorno el elemento del frente
            elem = arreglo[frente];
        }
        return elem;
    }

    public boolean esVacia() {
        //la cola esta vacia si frente y fin son iguales
        return (this.frente == this.fin);
    }

    public void vaciar() {
        this.frente = this.fin;
    }

    public Cola clone() {
        Cola clon = new Cola(TAMANO); // creo cola clon
        if (this.frente != this.fin) { //si la cola original no esta vacia
            clon.frente = this.frente;
            clon.fin = this.fin;
            int i = this.frente;
            while (i != fin) {
                clon.arreglo[i] = this.arreglo[i]; //copio los elememntos uno por uno
                i = (i + 1) % TAMANO; //i avanza de manera circular
            }
        }
        return clon;
    }

    @Override
    public String toString() {
        String cadena;
        if (this.frente == this.fin) {
            cadena = "Cola vacia.";
        } else {
            int pos = this.frente;
            cadena = "[";
            while (pos != this.fin) {
                cadena += (arreglo[pos]);
                pos = (pos + 1) % TAMANO; //pos avanza de manera circular
                if (pos != this.fin) {
                    cadena += " , ";
                }
            }
            cadena += "]";
        }
        return cadena;
    }
}
