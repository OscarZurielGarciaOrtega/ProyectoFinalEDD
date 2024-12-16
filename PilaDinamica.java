public class PilaDinamica {
    private NodoPila cima;

    // Nodo interno que representa cada elemento de la pila
    private class NodoPila {
        int valor; // El valor que contiene el nodo (en este caso, una valoración)
        NodoPila siguiente; // Apunta al siguiente nodo de la pila

        public NodoPila(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    // Constructor de la pila, inicialmente vacía
    public PilaDinamica() {
        this.cima = null;
    }

    // Método para agregar un valor (valoración) a la pila
    public void apilar(int valor) {
        NodoPila nuevoNodo = new NodoPila(valor);
        nuevoNodo.siguiente = cima; // El nuevo nodo apunta a la cima actual
        cima = nuevoNodo; // La cima ahora es el nuevo nodo
    }

    // Método para eliminar y devolver el valor de la cima de la pila
    public int desapilar() {
        if (estaVacia()) {
            System.out.println("La pila está vacía.");
            return -1; // Indicamos que no se pudo desapilar
        }
        int valor = cima.valor;
        cima = cima.siguiente; // Movemos la cima al siguiente nodo
        return valor;
    }

    // Método para ver si la pila está vacía
    public boolean estaVacia() {
        return cima == null;
    }

    // Método para mostrar todas las valoraciones en la pila
    public void mostrarValoraciones() {
        NodoPila actual = cima;
        if (actual == null) {
            System.out.println("No hay valoraciones.");
        } else {
            System.out.println("Valoraciones:");
            while (actual != null) {
                System.out.println(actual.valor); // Mostramos el valor (la valoración)
                actual = actual.siguiente; // Nos movemos al siguiente nodo
            }
        }
    }
}
