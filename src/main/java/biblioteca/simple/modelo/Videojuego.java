package biblioteca.simple.modelo;

import biblioteca.simple.contratos.Prestable;

public class Videojuego extends Producto implements Prestable {

    private String plataforma;
    private String genero;
    private boolean prestado;
    private Usuario prestadoA;

    public Videojuego(String titulo, String anho, Formato formato, String plataforma, String genero) {
        super(titulo, anho, formato);
        this.plataforma = plataforma;
        this.genero = genero;
    }

    public Videojuego(String id, String titulo, String anho, Formato formato, String plataforma, String genero) {
        super(id, titulo, anho, formato);
        this.plataforma = plataforma;
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getGenero() {
        return genero;
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
        return "Videojuego -> " +
                "plataforma= '" + plataforma + '\'' +
                ", genero= '" + genero + '\'' +
                ", id= '" + id + '\'' +
                ", titulo= '" + titulo + '\'' +
                ", anho= '" + anho + '\'' +
                ", formato= ?" + formato + "'";
    }
}

