public class CarritoCompra {
    private ColaDinamica cola;

    public CarritoCompra() {
        cola = new ColaDinamica();
    }

    // Agregar un videojuego al carrito
    public void agregarAlCarrito(Videojuego videojuego) {
        cola.encolar(videojuego);
    }

    // Mostrar los videojuegos en el carrito
    public void verCarrito() {
        if (cola.estaVacia()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("Videojuegos en el carrito:");
            cola.imprimirCola();
        }
    }

    // Eliminar un videojuego del carrito
    public void eliminarDelCarrito(String nombre) {
        ColaDinamica carritoTemporal = new ColaDinamica();
        boolean encontrado = false;

        while (!cola.estaVacia()) {
            Videojuego videojuego = cola.desencolar();
            if (!videojuego.getNombre().equals(nombre)) {
                carritoTemporal.encolar(videojuego);
            } else {
                encontrado = true;
            }
        }

        // Volver a colocar los elementos en la cola original
        while (!carritoTemporal.estaVacia()) {
            cola.encolar(carritoTemporal.desencolar());
        }

        if (encontrado) {
            System.out.println("Videojuego eliminado del carrito.");
        } else {
            System.out.println("El videojuego no está en el carrito.");
        }
    }

    // Vaciar el carrito
    public void vaciarCarrito() {
        while (!cola.estaVacia()) {
            cola.desencolar();
        }
        System.out.println("El carrito ha sido vaciado.");
    }
}



