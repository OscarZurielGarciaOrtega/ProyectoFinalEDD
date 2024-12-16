public class Videojuego {
    private String nombre;
    private String genero;
    private int año;
   

    public Videojuego(String nombre, String genero, int año) {
        this.nombre = nombre;
        this.genero = genero;
        this.año = año;
        
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public int getAño() {
        return año;
    }
    

    @Override
    public String toString() {
        return "Videojuego [nombre=" + nombre + ", genero=" + genero + ", año=" + año + "]";
    }
}
