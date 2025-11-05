package biblioteca.simple.app;

import biblioteca.simple.contratos.Prestable;
import biblioteca.simple.modelo.*;
import biblioteca.simple.servicios.Catalogo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    // Crear Objeto catálogo
    private static final Catalogo catalogo = new Catalogo();
    // Lista usuarios
    private static final List<Usuario> usuario = new ArrayList<>();

    public static void main(String[] args) {
        cargarDatos();
        menu();
    }
    // Carga de 4 libros + 4 peliculas
    // Carga de 4 usuarios
    private static void cargarDatos(){
        catalogo.alta(new Libro("L1", "El Quijote", "1608", Formato.FISICO, "75436536543", "Cervantes"));
        catalogo.alta(new Libro("L2", "El Nombre del Viento", "2007", Formato.FISICO, "08976879", "Patrick Leches"));
        catalogo.alta(new Libro("L3", "1984", "1949", Formato.FISICO, "9780451524935", "George Orwell"));
        catalogo.alta(new Libro("L4", "Cien Años de Soledad", "1967", Formato.FISICO, "9780307474728", "Gabriel García Márquez"));

        catalogo.alta(new Pelicula("P1", "El Padino", "1972", Formato.FISICO, "For Cópola", 175));
        catalogo.alta(new Pelicula("P2", "Parásitos", "2019", Formato.FISICO, "Bon Joon-ho", 132));
        catalogo.alta(new Pelicula("P3", "Inception", "2010", Formato.FISICO, "Christopher Nolan", 148));
        catalogo.alta(new Pelicula("P4", "La Lista de Schindler", "1993", Formato.FISICO, "Steven Spielberg", 195));

        catalogo.alta(new Videojuego("V1", "The Legend of Zelda: Breath of the Wild", "2017", Formato.DIGITAL, "Nintendo Switch", "Aventura"));
        catalogo.alta(new Videojuego("V2", "God of War", "2018", Formato.FISICO, "PlayStation 4", "Acción/Aventura"));
        catalogo.alta(new Videojuego("V3", "Minecraft", "2011", Formato.DIGITAL, "PC", "Sandbox"));
        catalogo.alta(new Videojuego("V4", "Red Dead Redemption 2", "2018", Formato.FISICO, "PlayStation 4", "Mundo Abierto"));

        usuario.add(new Usuario("U1", "Juan"));
        usuario.add(new Usuario("U2", "María"));
        usuario.add(new Usuario("U3", "Carlos"));
        usuario.add(new Usuario("U4", "Lucía"));
        usuario.add(new Usuario("U5", "Pedro"));
        usuario.add(new Usuario("U6", "Ana"));
        usuario.add(new Usuario("U7", "Sofía"));
        usuario.add(new Usuario("U8", "Miguel"));

    }
    // Menu |
    // 1. Listar
    // 2. Buscar por titulo
    // 3. Buscar por año,
    // 4. Prestar ¿Que porducto quieres prestar?, ¿Qué usuario se le va a prestar?
    // 5. Devolver.
    // 6. Salir

    private static void menu(){
        int op;

        do{
            System.out.println("\n=== Menú de Biblioteca ===");
            System.out.println("1. Listar");
            System.out.println("2. Buscar por titulo");
            System.out.println("3. Buscar por año");
            System.out.println("4. Prestar Producto");
            System.out.println("5. Devolver Producto");
            System.out.println("6. Listar Usuarios");
            System.out.println("7. Crear Nuevo Usuario");
            System.out.println("0. Salir");
            // Roll back por si no mete un entero
            while(!sc.hasNextInt()) sc.next();
            op = sc.nextInt();
            sc.nextLine();
            switch (op){
                case 1 -> listar();
                case 2 -> buscarPorTitulo();
                case 3 -> buscarPorAnio();
                case 4 -> prestar();
                case 5 -> devolver();
                case 6 -> listarUsuarios();
                case 7 -> crearUsuario();
                case 0 -> System.out.println("Sayonara!");
                default -> System.out.println("Opción no válida");
            }
        } while(op != 0);
    }

    private static void listar(){
        List<Producto> lista = catalogo.listar();

        if(lista.isEmpty()){
            System.out.println("Catálogo vacío");
            return;
        }

        System.out.println("===Lista de productos===");

        for(Producto p : lista) System.out.println("- " + p);
    }

    private static void buscarPorTitulo(){
        System.out.println("Título (escribe parte del título): ");
        String t = sc.nextLine();
        catalogo.buscar(t).forEach(p -> System.out.println("- " + p));
    }

    private static void buscarPorAnio(){
        System.out.println("Año: ");
        int a = sc.nextInt();
        sc.nextLine();
        catalogo.buscar(a).forEach(p -> System.out.println("- " + p));
    }

    private static void prestar(){
        List<Producto> disponibles = catalogo.listar().stream()
                .filter(p -> p instanceof Prestable pN && !pN.estaPrestado())
                .collect(Collectors.toList());

        if (disponibles.isEmpty()) {
            System.out.println("No hay prodcutos para prestar");
            return;
        }

        System.out.println("-- PRODUCTOS DISPONIBLES --");
        disponibles.forEach(p -> System.out.println(" - ID: " + p.getId() + " | " + p));

        System.out.println("Escribe el ID del producto que quieres: ");
        String id = sc.nextLine().trim().toLowerCase();

        Producto pEncontrado = disponibles.stream()
                .filter(p -> p.getId().toLowerCase().equals(id)).findFirst().orElse(null);

        if (pEncontrado == null){
            System.out.println("El id indicado no exisste");
            return;
        }

        listarUsuarios();
        System.out.println("Ingresa código de usuario " +
                "Si no lo encuentras pon 'NO' para más opciones: ");
        String cUsuario = sc.nextLine();
        Usuario u1 = getUsuarioPorCodigo(cUsuario.toUpperCase());

        if (u1 == null){
            int op;
            do {
                System.out.println("Usuario no encontrado");
                System.out.println("1. ¿Quieres ingresar un nuevo Usuario?");
                System.out.println("0. Salir");
                // Roll back por si no mete un entero
                while(!sc.hasNextInt()) sc.next();
                op = sc.nextInt();
                sc.nextLine();
                switch (op){
                    case 1 -> crearUsuario();
                    case 0 -> System.out.println("Sayonara");
                    default -> System.out.println("Opción no válida");
                }
            } while (op != 0);
        } else {
            Prestable pPrestable = (Prestable) pEncontrado;
            pPrestable.prestar(u1);
        }
    }

    public static void devolver(){
        List<Producto> pPrestados = catalogo.listar().stream()
                .filter(p -> p instanceof Prestable pN && pN.estaPrestado())
                .collect(Collectors.toList());

        if (pPrestados.isEmpty()) {
            System.out.println("No hay prodcutos prestados");
            return;
        }

        System.out.println("-- PRODUCTOS PRESTADOS --");
        pPrestados.forEach(p -> System.out.println(" - ID: " + p.getId() + " | " + p));

        System.out.println("Escribe el ID del producto que quieres: ");
        String id = sc.nextLine().trim().toLowerCase();

        Producto pEncontrado = pPrestados.stream()
                .filter(p -> p.getId().toLowerCase().equals(id)).findFirst().orElse(null);

        if (pEncontrado == null){
            System.out.println("El id indicado no exisste");
        } else {
            Prestable pE = (Prestable) pEncontrado;
            pE.devolver();
            System.out.println("Devuelto correctamente");
        }
    }

    private static void listarUsuarios() {
        if (usuario.isEmpty()) {
            System.out.println("No hay usuarios registrados");
            return;
        }
        System.out.println("-- LISTA USUARIOS --");
        usuario.forEach(u -> System.out.println("- Código : " + u.getId() + "| nombre: " + u.getNombre()));
    }

    private static Usuario getUsuarioPorCodigo(String id) {
        return usuario.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    private static void crearUsuario() {
        System.out.println("Introduce un número como ID de Usuario: ");
        String id = null;
        try {
            int nId = sc.nextInt();
            id = "U" + String.valueOf(nId);
            sc.nextLine();
            if (getUsuarioPorCodigo(id) != null){
                System.out.println("El número de ID introducido ya existe prueba otro.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero válido.");
        }

        System.out.println("Introduce el nombre del nuevo Usuario: ");
        String nombre = sc.nextLine();

        usuario.add(new Usuario(id, nombre));
    }
}
