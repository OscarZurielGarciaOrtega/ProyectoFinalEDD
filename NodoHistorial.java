public class NodoHistorial {
    Videojuego videojuego;
    NodoHistorial siguiente;

    public NodoHistorial(Videojuego videojuego) {
        this.videojuego = videojuego;
        this.siguiente = null;
    }
}

