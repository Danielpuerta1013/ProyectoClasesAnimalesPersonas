package org.example;

import org.example.animales.Animal;
import org.example.personas.Persona;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // scanner sc
        boolean ejecutar = true; // bool para while (bandera)

        do {
            mostrarMenu();
            int opcionMenu = sc.nextInt(); // entrada de usuario
            sc.nextLine(); // Consumir el salto de línea evitar errores

            switch (opcionMenu) { // se crea un menu
                case 1: // opcion 1 gestionar animales
                    gestionarAnimales(sc); // se llama la funcion para gestional animales
                    break;
                case 2:
                    gestionarPersonas(sc); // se llama la funcion para gestionar personas
                    break;
                case 3:
                    System.out.println("Hasta luego");
                    ejecutar = false; // se cambia la bandera para terminar el programa
                    break;
                default:
                    System.out.println("Opción incorrecta"); // default para opcion incorrecta
                    break;
            }
        } while (ejecutar);
    }

    public static void mostrarMenu() { // menu principal no retorna nada
        System.out.println("--------------------");
        System.out.println("Bienvenido");
        System.out.println("1. Animales");
        System.out.println("2. Personas");
        System.out.println("3. Salir");
        System.out.print("Escoja una opción: ");
    }

    public static void gestionarAnimales(Scanner sc) {
        Map<String, List<Animal>> clasificaciones = new TreeMap<>(); // crea un TreeMap para clasificar
        // por tipo
        List<Animal> animales = new ArrayList<>(); // se crea una lista para guardar los animales
        boolean agregar = true; // bandera menu interno

        while (agregar) {
            System.out.print("Ingrese 1 para agregar un animal y 0 para salir: "); // menu para agregar
            // o volver al menu anterior
            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea evita errores

            if (opcion == 0) {
                agregar = false; // cambio de bandera para salir
            } else if (opcion == 1) {
                agregarAnimal(sc, animales); // llama a la funcion agregar animales
            } else {
                System.out.println("Opción incorrecta"); // default para validacion de opcion
            }
        }

        clasificarAnimales(animales, clasificaciones); // llama la funcion para clasificar

        imprimirClasificaciones(clasificaciones); // llama la función para imprimir
    }

    public static void agregarAnimal(Scanner sc, List<Animal> animales) { // funcion para agregar animales
        System.out.print("Ingrese el nombre del animal: ");
        String nombreAnimal = sc.nextLine();

        System.out.print("Ingrese el tipo del animal (terrestre, volador o acuático): ");
        String tipoAnimal = sc.nextLine();

        System.out.print("Ingrese el género del animal M o H: ");
        String generoAnimal = sc.nextLine();
        // se piden por consola los datos

        Animal animal = new Animal(); // se crea un nuevo animal
        animal.setNombre(nombreAnimal); // se hace uso de los setters
        animal.setGenero(generoAnimal);
        animal.setTipo(tipoAnimal);
        animales.add(animal); // se agrega al arraylist
    }

    public static void clasificarAnimales(List<Animal> animales, Map<String, List<Animal>> clasificaciones) {
        for (Animal animal : animales) { // funcion para clasificar animales recibe el treeMap y el arrayList
            clasificaciones.computeIfAbsent(animal.getTipo(), k -> new ArrayList<>()).add(animal);
        } // recorre la lista de animales computeIfAbsent mira si la clave asociada al tipo
        // existe, si no existe la crea y agrega el animal correspondiente
    }

    public static void imprimirClasificaciones(Map<String, List<Animal>> clasificaciones) {
        for (Map.Entry<String, List<Animal>> entry : clasificaciones.entrySet()) { //recorre las entradas
            // del treeMap (Clave)
            System.out.println(entry.getKey() + ":");// se imprime la clave actual
            for (Animal animal : entry.getValue()) { // for anidado que extrae el valor de la clave actual
                //dando como resultado un arraylist con los animales clasificados
                System.out.println("\t" + animal.getNombre());// se imprime el arraylist con un t que hace una sangria
            }
        }
    }

    public static void gestionarPersonas(Scanner sc) {
        List<Persona> personas = new ArrayList<>(); // crea un arrayList para almacenar las
        // personas
        boolean agregar = true; // bandera para while interno

        while (agregar) {// ciclo para llenado de datos
            System.out.println("--------");
            System.out.println("Escoja 1 para agregar una nueva persona o 0 para volver al menú anterior");
            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea para evitar errores

            if (opcion == 0) { // si opcion es 0 se sale del ciclo
                agregar = false;
            } else if (opcion == 1) { // si es 1 llama a la función agregar personas
                agregarPersona(sc, personas);
            } else {
                System.out.println("Opción incorrecta");// default para validar errores
            }
        }

        imprimirResultadosPersonas(personas); // una vez acabado el ciclo se imprime resultados
    }

    public static void agregarPersona(Scanner scanner, List<Persona> personas) {
        // pedida de datos por consola
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese la edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese el género M o F: ");
        String genero = scanner.nextLine();

        System.out.print("Ingrese el sueldo por hora: ");
        double sueldoHora = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese el cargo: ");
        String cargo = scanner.nextLine();
        // utilizacion de setters para agregar las variables

        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setCargo(cargo);
        persona.setEdad(edad);
        persona.setSueldoHora(sueldoHora);
        persona.setGenero(genero);
        // se agrega el objeto nuevo al arrayList

        personas.add(persona);
    }

    public static void imprimirResultadosPersonas(List<Persona> personas) {
        int cantidadPersonas = personas.size(); // primera funcion cuenta el tamaño del arraylist
        // segunda función: convierte el array a stream para poder usar map, el maptoint convierte a enteros
        // itera sobre el getEdad y calcula el promedio
        double promedioEdades = personas.stream().mapToInt(Persona::getEdad).average().orElse(0);
        // se usa el filter p(persona) toma el valor de cada persona accede a su edad y devuelve las personas mayores de edad
        // por ultimo count cuenta el numero de personas que cumplen
        long personasMayoresEdad = personas.stream().filter(p -> p.getEdad() >= 18).count();
        // se usa el filter que itera sobre el nombre de cada persona y se usa el startwith para contar cuantas personas
        //su nombre comienza con a, se usa el lower case para asegurar que no sea sensible a mayus y minusculas
        long personasConNombreA = personas.stream().filter(p -> p.getNombre().toLowerCase().startsWith("a")).count();
        // se itera sobre el appellido de cada persona y esta vez se usa contains, para verificar si el appelido
        // contiene una m, por utimo se cuenta
        long personasConApellidoM = personas.stream().filter(p -> p.getApellido().toLowerCase().contains("m")).count();

        //se imprimen los resultados anteriores

        System.out.println("Cantidad de personas: " + cantidadPersonas);
        System.out.println("Promedio de edades: " + promedioEdades);
        System.out.println("Cantidad de personas mayores de edad: " + personasMayoresEdad);
        System.out.println("Personas cuyos nombres empiezan con 'a': " + personasConNombreA);
        System.out.println("Personas cuyos apellidos contienen 'm': " + personasConApellidoM);

        // calcular el sueldo de 8 horas de directores masculinos

        System.out.println("Sueldos de 8 horas de directores masculinos:");
        personas.stream() // se comvierte el arraylist a stream
                // se usa el filter para iterar sobre el cargo y seleccionar las personas
                // que cumplan con que sea director y ademas el genero sea M
                .filter(p -> p.getCargo().equalsIgnoreCase("Director") && p.getGenero().equalsIgnoreCase("M"))
                //por ultimo se itera sobre las personas que cumple y se imprime nombre completo y se calcula sueldo
                .forEach(p -> System.out.println("Nombre: " + p.getNombre() + " " + p.getApellido() + " Sueldo por 8 horas: $" + (p.getSueldoHora() * 8)));

        //desarrollador que mas gana por hora

        Optional<Persona> masGanaDesarrollador = personas.stream() // se convierte a stream y se crea retorno de operacioon max
                .filter(p -> p.getCargo().equalsIgnoreCase("Desarrollador"))// se usa filter para filtrar personas
                // con el cargo desarrollador
                .max(Comparator.comparingDouble(Persona::getSueldoHora));// se usa max con el comparador de los sueldos de las personas
        masGanaDesarrollador.ifPresentOrElse(// variable que contiene el resultado
                // si tiene un valor imprime el resultado, si no devuelve un mensaje de que no hay persona que cumpla
                p -> System.out.println("Persona desarrollador que más gana por hora: " + p.getNombre() + ", Sueldo por hora: $" + p.getSueldoHora()),
                () -> System.out.println("No hay ninguna persona con el cargo de Desarrollador.")
        );
        // personas de genero F ordenadas por nombre

        System.out.println("\nMujeres ordenadas por nombre:");
        personas.stream() // se convierte a stream
                .filter(p -> p.getGenero().equalsIgnoreCase("F"))// se filtran las personas de genero F
                .sorted(Comparator.comparing(Persona::getNombre))// se usa sorted con el comparador en los nombres
                .forEach(p -> System.out.println("Nombre: " + p.getNombre() + ", Apellido: " + p.getApellido()));
        // se usa el for each pera recorrer los resultados del filter
    }
}
