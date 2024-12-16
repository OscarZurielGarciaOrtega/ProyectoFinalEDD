public class HistorialBusquedaListaLigada {
    private NodoHistorial cabeza;  // Primer nodo de la lista

    public HistorialBusquedaListaLigada() {
        cabeza = null;  // Inicialmente la lista está vacía
    }

    // Método para agregar una nueva búsqueda al inicio de la lista
    public void agregarBusqueda(Videojuego videojuego) {
        NodoHistorial nuevoNodo = new NodoHistorial(videojuego);
        nuevoNodo.siguiente = cabeza;  // El nuevo nodo apunta al anterior primer nodo
        cabeza = nuevoNodo;  // El nuevo nodo se convierte en el primer nodo de la lista
    }

    // Método para mostrar todas las búsquedas en orden (de la cabeza a la cola)
    public void mostrarHistorial() {
        if (cabeza == null) {
            System.out.println("El historial está vacío.");
            return;
        }

        NodoHistorial actual = cabeza;
        while (actual != null) {
            System.out.println(actual.videojuego);
            actual = actual.siguiente;  // Avanzamos al siguiente nodo
        }
    }
}
