import java.util.*;

public class Grafo {
    // Mapa de videojuegos por género
    private Map<String, List<Videojuego>> grafo;

    public Grafo() {
        grafo = new HashMap<>();
    }

    // Agregar un videojuego y conectar con otros del mismo género
    public void agregarVideojuego(Videojuego videojuego) {
        String genero = videojuego.getGenero();
        
        // Si no existe una lista para el género, crea una nueva
        grafo.putIfAbsent(genero, new ArrayList<>());
        
        // Agrega el videojuego al grafo
        grafo.get(genero).add(videojuego);
    }

    // Obtener los videojuegos relacionados por género
    public List<Videojuego> obtenerRelacionadosPorGenero(String genero) {
        return grafo.getOrDefault(genero, new ArrayList<>());
    }

    // Imprimir los videojuegos relacionados por género
    public void imprimirRelacionadosPorGenero(String genero) {
        List<Videojuego> relacionados = obtenerRelacionadosPorGenero(genero);
        if (relacionados.isEmpty()) {
            System.out.println("No se encontraron videojuegos en el género " + genero);
        } else {
            System.out.println("Videojuegos relacionados por género (" + genero + "):");
            for (Videojuego v : relacionados) {
                System.out.println(v);
            }
        }
    }
}

