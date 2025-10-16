package biblioteca.simple.contratos;

import biblioteca.simple.modelo.Usuario;

public interface Prestable {
    void prestar(Usuario user);
    void devolver ();
    boolean estaPrestado();
}
