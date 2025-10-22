package biblioteca.simple.modelo;

import biblioteca.simple.contratos.Prestable;

public class Pelicula extends Producto implements Prestable {

    private String director;
    private int duracion;
    private boolean prestado;
    private Usuario prestadoA;

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
    public void prestar (Usuario user){
        if (prestado) throw new IllegalStateException("Ya prestado");
        this.prestado = true;
        this.prestadoA = user;
    }

    @Override
    public void devolver() {
        this.prestado = false;
        this.prestadoA = null;
    }


    @Override
    public boolean estaPrestado(){
        return prestado;
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
