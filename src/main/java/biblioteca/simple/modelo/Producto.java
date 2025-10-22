package biblioteca.simple.modelo;

public abstract class Producto {

    protected String id;
    protected String titulo;
    protected String anho;
    protected Formato formato;

    //Constructor para recibir objetos de la bbdd
    protected Producto(String titulo, String anho, Formato formato) {
        this.titulo = titulo;
        this.anho = anho;
        this.formato = formato;
    }

    //Constructor para crear nuevos productos desde la app
    protected Producto(String id, String titulo, String anho, Formato formato) {
        this.id = id;
        this.titulo = titulo;
        this.anho = anho;
        this.formato = formato;
    }

    protected String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAnho() {
        return anho;
    }

    protected Formato getFormato() {
        return formato;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", anho='" + anho + '\'' +
                ", formato=" + formato +
                '}';
    }
}
