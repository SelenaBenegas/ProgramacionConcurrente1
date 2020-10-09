package Objetos;

class Dato {

    private int valor;

    void contar() {
        valor = ++valor;
    }

    int obtenerValor() {
        return valor;
    }
}
