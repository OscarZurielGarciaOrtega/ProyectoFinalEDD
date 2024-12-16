import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArbolAVL arbolVideojuegos = new ArbolAVL();
        HistorialBusquedaListaLigada historial = new HistorialBusquedaListaLigada();  
        CarritoCompra carritoCompra = new CarritoCompra();
        Map<String, PilaDinamica> valoraciones = new HashMap<>();
        Grafo grafoVideojuegos = new Grafo();
        List<Videojuego> videojuegos = leerArchivoCSV("D:\\ArbolAVL\\2milVideojuegosEstructura.csv");
        QuicksortPorSaga.ordenarPorSaga(videojuegos);

        for (Videojuego videojuego : videojuegos) {
            arbolVideojuegos.insertar(videojuego);
            grafoVideojuegos.agregarVideojuego(videojuego);
        }

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            printMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    print("Introduce el nombre del videojuego que deseas buscar: ");
                    String nombreBusqueda = scanner.nextLine();
                    Videojuego videojuego = arbolVideojuegos.buscarEnArbolPorNombre(arbolVideojuegos.getRaiz(), nombreBusqueda);
                    if (videojuego != null) {
                        print("Videojuego encontrado: " + videojuego);
                        historial.agregarBusqueda(videojuego);  // Agregar al historial
                    } else {
                        print("Videojuego no encontrado.");
                    }
                    break;
                case 2:
                    print("Introduce el año de lanzamiento del videojuego que deseas buscar: ");
                    int añoBusqueda = scanner.nextInt();
                    scanner.nextLine();
                    List<Videojuego> videojuegosEncontrados = arbolVideojuegos.buscarPorAnio(añoBusqueda);
                    if (!videojuegosEncontrados.isEmpty()) {
                        print("Videojuegos encontrados en el año " + añoBusqueda + ":");
                        for (Videojuego v : videojuegosEncontrados) {
                            print(v.toString());
                        }
                        // Agregar al historial todos los encontrados
                        for (Videojuego v : videojuegosEncontrados) {
                            historial.agregarBusqueda(v);
                        }
                    } else {
                        print("No se encontraron videojuegos en ese año.");
                    }
                    break;
                case 3:
                    print("Videojuegos ordenados alfabéticamente por nombre:");
                    arbolVideojuegos.imprimirArbolInOrden();
                    break;
                case 4:
                    print("Videojuegos ordenados por año de lanzamiento:");
                    arbolVideojuegos.imprimirPorAnio();
                    break;
                case 5:
                    // Agregar un nuevo videojuego
                    print("Introduce el nombre del videojuego: ");
                    String nombreAgregar = scanner.nextLine();
                    print("Introduce el género del videojuego: ");
                    String generoAgregar = scanner.nextLine();
                    print("Introduce el año de lanzamiento del videojuego: ");
                    int añoAgregar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer del scanner

                    Videojuego videojuegoAgregar = new Videojuego(nombreAgregar, generoAgregar, añoAgregar);
                    arbolVideojuegos.insertar(videojuegoAgregar);
                    print("Videojuego agregado exitosamente.");
                    break;
                case 6:
                    // Eliminar un videojuego
                    print("Introduce el nombre del videojuego que deseas eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    arbolVideojuegos.eliminarVideojuego(nombreEliminar);
                    print("Videojuego eliminado si existía.");
                    break;
                case 7:
                    print("Historial de búsquedas:");
                    historial.mostrarHistorial();  // Mostrar el historial de búsquedas
                    break;
                case 8:
                    // Agregar al carrito
                    print("Introduce el nombre del videojuego que deseas agregar al carrito: ");
                    String nombreCarritoAgregar = scanner.nextLine();
                    Videojuego videojuegoCarritoAgregar = arbolVideojuegos.buscarEnArbolPorNombre(arbolVideojuegos.getRaiz(), nombreCarritoAgregar);
                    if (videojuegoCarritoAgregar != null) {
                        carritoCompra.agregarAlCarrito(videojuegoCarritoAgregar);
                        print("Videojuego agregado al carrito.");
                    } else {
                        print("Videojuego no encontrado.");
                    }
                    break;
                case 9:
                    // Ver carrito
                    carritoCompra.verCarrito();
                    break;
                case 10:
                    // Eliminar del carrito
                    print("Introduce el nombre del videojuego que deseas eliminar del carrito: ");
                    String nombreCarritoEliminar = scanner.nextLine();
                    carritoCompra.eliminarDelCarrito(nombreCarritoEliminar);
                    break;
                case 11:
                    // Vaciar carrito
                    carritoCompra.vaciarCarrito();
                    break;
                case 12:
                    print("Introduce el nombre del videojuego que deseas valorar: ");
                    String nombreValoracion = scanner.nextLine();
                    Videojuego videojuegoValorar = arbolVideojuegos.buscarEnArbolPorNombre(arbolVideojuegos.getRaiz(), nombreValoracion);
                    if (videojuegoValorar != null) {
                        print("Introduce la valoración (1 a 5): ");
                        int valoracion = scanner.nextInt();
                        scanner.nextLine();
                        if (valoracion >= 1 && valoracion <= 5) {
                            valoraciones.putIfAbsent(videojuegoValorar.getNombre(), new PilaDinamica());
                            valoraciones.get(videojuegoValorar.getNombre()).push(valoracion);
                            print("Valoración añadida.");
                        } else {
                            print("Valoración inválida. Debe estar entre 1 y 5.");
                        }
                    } else {
                        print("Videojuego no encontrado.");
                    }
                    break;
                case 13:
                    print("Introduce el nombre del videojuego para ver sus valoraciones: ");
                    String nombreMostrar = scanner.nextLine();
                    if (valoraciones.containsKey(nombreMostrar)) {
                        print("Valoraciones para " + nombreMostrar + ":");
                        valoraciones.get(nombreMostrar).imprimirPila();
                    } else {
                        print("No hay valoraciones para este videojuego.");
                    }
                    break;
                case 14:
                    print("Introduce el nombre de la saga para ver los videojuegos relacionados: ");
                    String sagaBusqueda = scanner.nextLine();
                    List<Videojuego> juegosDeLaSaga = buscarJuegosPorSaga(sagaBusqueda, videojuegos);
                    if (!juegosDeLaSaga.isEmpty()) {
                        print("Videojuegos relacionados con la saga '" + sagaBusqueda + "':");
                        for (Videojuego v : juegosDeLaSaga) {
                            print(v.toString());
                        }
                    } else {
                        print("No se encontraron videojuegos para esa saga.");
                    }
                    break;
                case 15:
                    print("Videojuegos Ordenados por saga:");
                    imprimirVideojuegos(videojuegos);
                    break;
                case 16:
                    // Imprimir videojuegos relacionados por género
                    print("Introduce el género para ver los videojuegos relacionados: ");
                    String generoBusqueda = scanner.nextLine();
                    grafoVideojuegos.imprimirRelacionadosPorGenero(generoBusqueda);
                    break;
                
                case 17:
                    print("Saliendo...");
                    break;
                default:
                    print("Opción no válida.");
            }
        } while (opcion != 17);

        scanner.close();
    }

    // Método para buscar juegos relacionados con una saga
    public static List<Videojuego> buscarJuegosPorSaga(String saga, List<Videojuego> videojuegos) {
        List<Videojuego> juegosRelacionados = new ArrayList<>();
        for (Videojuego videojuego : videojuegos) {
            if (obtenerSaga(videojuego.getNombre()).equalsIgnoreCase(saga)) {
                juegosRelacionados.add(videojuego);
            }
        }
        return juegosRelacionados;
    }

    public static void printMenu() {
        print("*** Menú Principal ***");
        print("1. Buscar videojuego por nombre");
        print("2. Buscar videojuego por año de lanzamiento");
        print("3. Imprimir videojuegos en orden alfabético por nombre");
        print("4. Imprimir videojuegos ordenados por año de lanzamiento");
        print("5. Agregar un nuevo videojuego");
        print("6. Eliminar un videojuego");
        print("7. Ver historial de búsquedas");
        print("8. Agregar al carrito");
        print("9. Ver carrito");
        print("10. Eliminar del carrito");
        print("11. Vaciar carrito");
        print("12. Valorar un videojuego");
        print("13. Ver valoraciones de un videojuego");
        print("14. Ver videojuegos relacionados por saga");
        print("15. Ver videojuegos ordenados por saga");
        print("16. Ver videojuegos ordenados por género");
        print("17. Salir");
        print("Seleccione una opción: ");
    }

    public static void print(String mensaje) {
        System.out.println(mensaje);
    }

    public static List<Videojuego> leerArchivoCSV(String archivo) {
        List<Videojuego> videojuegos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); // Para saltar la cabecera del CSV
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0].trim();
                String genero = datos[1].trim();
                int año = Integer.parseInt(datos[2].trim());
                videojuegos.add(new Videojuego(nombre, genero, año));
            }
        } catch (IOException e) {
            print("Error al leer el archivo: " + e.getMessage());
        }
        return videojuegos;
    }

    public static void imprimirVideojuegos(List<Videojuego> videojuegos) {
        for (Videojuego v : videojuegos) {
            print(v.toString());
        }
    }

    // Método para extraer la saga de un videojuego en función de la similitud en su nombre
    public static String obtenerSaga(String nombre) {
        // Simplemente dividimos el nombre por espacios y tomamos las primeras palabras clave
        String[] partes = nombre.split(" ");
        // Usamos las primeras palabras como criterio para identificar la saga
        if (partes.length > 1) {
            return partes[0] + " " + partes[1];  // Suponemos que las primeras 2 palabras son la saga
        }
        return partes[0];  // Si solo hay una palabra, asumimos que es la saga
    }
}





