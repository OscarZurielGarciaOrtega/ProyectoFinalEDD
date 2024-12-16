import java.util.*;

public class QuicksortPorSaga {

    // Método público para ordenar una lista de videojuegos
    public static void ordenarPorSaga(List<Videojuego> videojuegos) {
        quicksortPorSaga(videojuegos, 0, videojuegos.size() - 1);
    }

    // Método recursivo para aplicar el algoritmo Quicksort
    private static void quicksortPorSaga(List<Videojuego> videojuegos, int low, int high) {
        if (low < high) {
            int pi = partition(videojuegos, low, high);
            quicksortPorSaga(videojuegos, low, pi - 1);
            quicksortPorSaga(videojuegos, pi + 1, high);
        }
    }

    // Función para dividir la lista y ordenar por saga (similitud en el nombre)
    private static int partition(List<Videojuego> videojuegos, int low, int high) {
        Videojuego pivot = videojuegos.get(high);
        String sagaPivot = obtenerSaga(pivot.getNombre());
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (obtenerSaga(videojuegos.get(j).getNombre()).compareTo(sagaPivot) <= 0) {
                i++;
                Collections.swap(videojuegos, i, j);
            }
        }
        Collections.swap(videojuegos, i + 1, high);
        return i + 1;
    }

    // Función para extraer la saga de un videojuego en función de la similitud en su nombre
    private static String obtenerSaga(String nombre) {
        // Simplemente dividimos el nombre por espacios y tomamos las primeras palabras clave
        String[] partes = nombre.split(" ");
        // Usamos las primeras palabras como criterio para identificar la saga
        // Ejemplo: "Super Mario Odyssey" -> "Super Mario"
        if (partes.length > 1) {
            return partes[0] + " " + partes[1];  // Suponemos que las primeras 2 palabras son la saga
        }
        return partes[0];  // Si solo hay una palabra, asumimos que es la saga
    }
}

