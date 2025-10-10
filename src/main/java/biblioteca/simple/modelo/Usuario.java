package biblioteca.simple.modelo;

public class Usuario {
    private String id;
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
