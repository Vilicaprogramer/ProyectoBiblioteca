package biblioteca.simple.app;

import biblioteca.simple.contratos.Prestable;
import biblioteca.simple.modelo.*;
import biblioteca.simple.servicios.Catalogo;

import java.util.ArrayList;
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
        catalogo.alta(new Pelicula("P1", "El Padino", "1972", Formato.FISICO, "For Cópola", 175));
        catalogo.alta(new Pelicula("P2", "Parásitos", "2019", Formato.FISICO, "Bon Joon-ho", 132));

        usuario.add(new Usuario("U1", "Juan"));
        usuario.add(new Usuario("U2", "María"));
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
            System.out.println("\n===Menú de Biblioteca===");
            System.out.println("1. Listar");
            System.out.println("2. Buscar por titulo");
            System.out.println("3. Buscar por año");
            System.out.println("4. Prestar Producto");
            System.out.println("5. Devolver Producto");
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
    }
}
