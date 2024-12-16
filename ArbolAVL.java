import java.util.*;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public NodoAVL getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoAVL raiz) {
        this.raiz = raiz;
    }

    public void insertar(Videojuego valor) {
        this.raiz = insertarNodo(valor, raiz);
    }

    private NodoAVL insertarNodo(Videojuego valor, NodoAVL nodo) {
        if (nodo == null) {
            return new NodoAVL(valor);
        }

        if (valor.getNombre().compareTo(nodo.getValor().getNombre()) < 0) {
            nodo.setIzq(insertarNodo(valor, nodo.getIzq()));
        } else if (valor.getNombre().compareTo(nodo.getValor().getNombre()) > 0) {
            nodo.setDer(insertarNodo(valor, nodo.getDer()));
        } else {
            return nodo;
        }

        nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzq()), obtenerAltura(nodo.getDer())));
        int balance = calcularBalance(nodo);

        if (balance > 1 && valor.getNombre().compareTo(nodo.getDer().getValor().getNombre()) > 0) {
            return rotacionIzq(nodo);
        }

        if (balance < -1 && valor.getNombre().compareTo(nodo.getIzq().getValor().getNombre()) < 0) {
            return rotacionDer(nodo);
        }

        if (balance > 1 && valor.getNombre().compareTo(nodo.getDer().getValor().getNombre()) < 0) {
            nodo.setDer(rotacionDer(nodo.getDer()));
            return rotacionIzq(nodo);
        }

        if (balance < -1 && valor.getNombre().compareTo(nodo.getIzq().getValor().getNombre()) > 0) {
            nodo.setIzq(rotacionIzq(nodo.getIzq()));
            return rotacionDer(nodo);
        }

        return nodo;
    }

    private int obtenerAltura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    private int calcularBalance(NodoAVL nodo) {
        return nodo == null ? 0 : obtenerAltura(nodo.getDer()) - obtenerAltura(nodo.getIzq());
    }

    public NodoAVL rotacionIzq(NodoAVL x) {
        NodoAVL y = x.getDer();
        NodoAVL z = y.getIzq();

        y.setIzq(x);
        x.setDer(z);

        x.setAltura(1 + Math.max(obtenerAltura(x.getIzq()), obtenerAltura(x.getDer())));
        y.setAltura(1 + Math.max(obtenerAltura(y.getIzq()), obtenerAltura(y.getDer())));

        return y;
    }

    public NodoAVL rotacionDer(NodoAVL y) {
        NodoAVL x = y.getIzq();
        NodoAVL z = x.getDer();

        x.setDer(y);
        y.setIzq(z);

        y.setAltura(1 + Math.max(obtenerAltura(y.getIzq()), obtenerAltura(y.getDer())));
        x.setAltura(1 + Math.max(obtenerAltura(x.getIzq()), obtenerAltura(x.getDer())));

        return x;
    }

    public void inOrden(NodoAVL nodo, List<Videojuego> lista) {
        if (nodo != null) {
            inOrden(nodo.getIzq(), lista);
            lista.add(nodo.getValor());
            inOrden(nodo.getDer(), lista);
        }
    }

    public void imprimirArbolInOrden() {
        List<Videojuego> lista = new ArrayList<>();
        inOrden(raiz, lista);

        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    public void imprimirPorAnio() {
        List<Videojuego> lista = new ArrayList<>();
        inOrden(raiz, lista);

        // Ordenar por año de lanzamiento
        Collections.sort(lista, new Comparator<Videojuego>() {
            @Override
            public int compare(Videojuego v1, Videojuego v2) {
                return Integer.compare(v1.getAño(), v2.getAño());
            }
        });

        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    public List<Videojuego> buscarPorAnio(int año) {
        long inicio = System.nanoTime();  // Inicia el contador de tiempo
        int[] nodosVisitados = {0};  // Usamos un arreglo para contar nodos visitados
        List<Videojuego> resultados = new ArrayList<>();
        
        buscarPorAnioRecursivo(raiz, año, resultados, nodosVisitados);

        long fin = System.nanoTime();  // Finaliza el contador de tiempo
        long duracion = fin - inicio;  // Calcula el tiempo transcurrido

        // Imprime los resultados
        System.out.println("Nodos visitados: " + nodosVisitados[0]);
        System.out.println("Tiempo de búsqueda (en nanosegundos): " + duracion);

        return resultados;
    }

    private void buscarPorAnioRecursivo(NodoAVL nodo, int año, List<Videojuego> resultados, int[] nodosVisitados) {
        if (nodo == null) {
            return;
        }

        nodosVisitados[0]++;  // Incrementa el contador de nodos visitados

        if (nodo.getValor().getAño() == año) {
            resultados.add(nodo.getValor());
        }

        buscarPorAnioRecursivo(nodo.getIzq(), año, resultados, nodosVisitados);
        buscarPorAnioRecursivo(nodo.getDer(), año, resultados, nodosVisitados);
    }

    public Videojuego buscarEnArbolPorNombre(NodoAVL nodo, String nombre) {
        long inicio = System.nanoTime();  // Inicia el contador de tiempo
        int[] nodosVisitados = {0};  // Usamos un arreglo para contar nodos visitados

        Videojuego resultado = buscarEnArbolPorNombreRecursivo(nodo, nombre, nodosVisitados);

        long fin = System.nanoTime();  // Finaliza el contador de tiempo
        long duracion = fin - inicio;  // Calcula el tiempo transcurrido

        // Imprime los resultados
        System.out.println("Nodos visitados: " + nodosVisitados[0]);
        System.out.println("Tiempo de búsqueda (en nanosegundos): " + duracion);

        return resultado;
    }

    private Videojuego buscarEnArbolPorNombreRecursivo(NodoAVL nodo, String nombre, int[] nodosVisitados) {
        if (nodo == null) {
            return null;
        }

        nodosVisitados[0]++;  // Incrementa el contador de nodos visitados

        Videojuego videojuego = nodo.getValor();
        if (videojuego.getNombre().equalsIgnoreCase(nombre)) {
            return videojuego;
        }

        if (nombre.compareTo(videojuego.getNombre()) < 0) {
            return buscarEnArbolPorNombreRecursivo(nodo.getIzq(), nombre, nodosVisitados);
        } else {
            return buscarEnArbolPorNombreRecursivo(nodo.getDer(), nombre, nodosVisitados);
        }
    }

    public void eliminarVideojuego(String nombre) {
        raiz = eliminarNodo(raiz, nombre);
    }

    private NodoAVL eliminarNodo(NodoAVL nodo, String nombre) {
        if (nodo == null) {
            return null;
        }

        Videojuego videojuego = nodo.getValor();
        if (nombre.compareTo(videojuego.getNombre()) < 0) {
            nodo.setIzq(eliminarNodo(nodo.getIzq(), nombre));
        } else if (nombre.compareTo(videojuego.getNombre()) > 0) {
            nodo.setDer(eliminarNodo(nodo.getDer(), nombre));
        } else {
            if (nodo.getIzq() == null || nodo.getDer() == null) {
                NodoAVL temp = (nodo.getIzq() != null) ? nodo.getIzq() : nodo.getDer();
                return temp;
            } else {
                NodoAVL temp = obtenerMinimoNodo(nodo.getDer());
                nodo.setValor(temp.getValor());
                nodo.setDer(eliminarNodo(nodo.getDer(), temp.getValor().getNombre()));
            }
        }

        nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzq()), obtenerAltura(nodo.getDer())));
        int balance = calcularBalance(nodo);

        if (balance > 1 && calcularBalance(nodo.getDer()) >= 0) {
            return rotacionIzq(nodo);
        }

        if (balance > 1 && calcularBalance(nodo.getDer()) < 0) {
            nodo.setDer(rotacionDer(nodo.getDer()));
            return rotacionIzq(nodo);
        }

        if (balance < -1 && calcularBalance(nodo.getIzq()) <= 0) {
            return rotacionDer(nodo);
        }

        if (balance < -1 && calcularBalance(nodo.getIzq()) > 0) {
            nodo.setIzq(rotacionIzq(nodo.getIzq()));
            return rotacionDer(nodo);
        }

        return nodo;
    }

    private NodoAVL obtenerMinimoNodo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.getIzq() != null) {
            actual = actual.getIzq();
        }
        return actual;
    }
}






