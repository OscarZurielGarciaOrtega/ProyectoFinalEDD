public class ColaDinamica {
    private Nodo frente;
    private Nodo finalCola;

    private class Nodo {
        Videojuego videojuego;
        Nodo siguiente;

        public Nodo(Videojuego videojuego) {
            this.videojuego = videojuego;
            this.siguiente = null;
        }
    }

    public ColaDinamica() {
        frente = null;
        finalCola = null;
    }

    public void encolar(Videojuego videojuego) {
        Nodo nuevoNodo = new Nodo(videojuego);
        if (estaVacia()) {
            frente = nuevoNodo;
            finalCola = nuevoNodo;
        } else {
            finalCola.siguiente = nuevoNodo;
            finalCola = nuevoNodo;
        }
    }

    public Videojuego desencolar() {
        if (estaVacia()) {
            return null; // O lanza una excepción según tu preferencia
        }
        Videojuego videojuego = frente.videojuego;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null;
        }
        return videojuego;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void imprimirCola() {
        Nodo actual = frente;
        while (actual != null) {
            System.out.println(actual.videojuego);
            actual = actual.siguiente;
        }
    }
}



