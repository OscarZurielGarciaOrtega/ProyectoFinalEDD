class Nodo {
    int valor;
    Nodo siguiente;

    public Nodo(int valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}

class PilaDinamica {
    private Nodo tope;

    public PilaDinamica() {
        this.tope = null;
    }

    public void push(int valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public Integer pop() {
        if (estaVacia()) {
            return null; // La pila está vacía
        }
        int valor = tope.valor;
        tope = tope.siguiente;
        return valor;
    }

    public Integer verTope() {
        return estaVacia() ? null : tope.valor;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public void imprimirPila() {
        Nodo actual = tope;
        while (actual != null) {
            System.out.println(actual.valor);
            actual = actual.siguiente;
        }
    }
}

