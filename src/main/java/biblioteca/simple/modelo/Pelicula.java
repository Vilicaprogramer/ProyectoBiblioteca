package biblioteca.simple.modelo;

public class Pelicula extends Producto{

    private String director;
    private int duracion;

    public Pelicula(String titulo, String anho, Formato formato, String director, int duracion) {
        super(titulo, anho, formato);
        this.director = director;
        this.duracion = duracion;
    }

    public Pelicula(String id, String titulo, String anho, Formato formato, String director, int duracion) {
        super(id, titulo, anho, formato);
        this.director = director;
        this.duracion = duracion;
    }

    public String getDirector() {
        return director;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "director='" + director + '\'' +
                ", duracion=" + duracion +
                ", id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", anho='" + anho + '\'' +
                ", formato=" + formato +
                '}';
    }
}
