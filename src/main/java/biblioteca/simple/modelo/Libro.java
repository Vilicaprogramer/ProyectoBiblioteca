package biblioteca.simple.modelo;

import biblioteca.simple.contratos.Prestable;

public class Libro extends Producto implements Prestable {

    private String autor;
    private String ISBN;
    private boolean prestado;
    private Usuario prestadoA;

    public Libro(String titulo, String anho, Formato formato, String autor, String ISBN) {
        super(titulo, anho, formato);
        this.autor = autor;
        this.ISBN = ISBN;
    }

    public Libro(String id, String titulo, String anho, Formato formato, String autor, String ISBN) {
        super(id, titulo, anho, formato);
        this.autor = autor;
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public String getISBN() {
        return ISBN;
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
        return "Libro -> " +
                "autor= '" + autor + '\'' +
                ", ISBN= '" + ISBN + '\'' +
                ", id= '" + id + '\'' +
                ", titulo= '" + titulo + '\'' +
                ", anho= '" + anho + '\'' +
                ", formato= '" + formato + "'";
    }
}
