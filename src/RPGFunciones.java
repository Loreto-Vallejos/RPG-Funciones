import java.util.Scanner;

public class RPGFunciones{

    // ===== Estado del personaje (sin POO, solo variables globales estáticas) =====
    static String nombre = "";
    static String clase = "";
    static int vida = 0;
    static int fuerza = 0;
    static double oro = 0.0;
    static boolean personajeCreado = false;

    // Inventario (solo para foreach)
    static String[] inventario = {"Poción", "Hierro", "Pergamino", "Llave Antigua"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcionMenu = 0;

        // ===== MENÚ PRINCIPAL (do-while) =====
        do {
            mostrarMenuPrincipal();
            opcionMenu = leerEntero(sc);

            switch (opcionMenu) {
                case 1 -> crearPersonaje(sc);
                case 2 -> entrenar(sc);            // while dentro
                case 3 -> batalla();               // for dentro
                case 4 -> mostrarInventario();     // foreach dentro
                case 5 -> mostrarEstado();         // if/else dentro
                case 6 -> System.out.println("\n👋 Saliendo del juego... ¡hasta la próxima!");
                default -> System.out.println("Opción inválida");
            }

        } while (opcionMenu != 6);

        sc.close();
    }

    // ===================== FUNCIONES (MÉTODOS) =====================

    static void mostrarMenuPrincipal() {
        System.out.println("\n=== RPG SIMULATOR ===");
        System.out.println("1. Crear personaje");
        System.out.println("2. Entrenar");
        System.out.println("3. Batalla");
        System.out.println("4. Inventario");
        System.out.println("5. Estado del personaje");
        System.out.println("6. Salir");
        System.out.print("Elige una opción: ");
    }

    // Lee un entero sin caerse (si no es número, retorna -1)
    static int leerEntero(Scanner sc) {
        if (!sc.hasNextInt()) {
            System.out.println("Opción inválida");
            sc.nextLine(); // limpiar input
            return -1;
        }
        int valor = sc.nextInt();
        sc.nextLine(); // limpiar salto de línea
        return valor;
    }

    static void crearPersonaje(Scanner sc) {
        System.out.println("\n--- Crear personaje ---");

        System.out.print("Nombre del personaje: ");
        String nombreIngresado = sc.nextLine().trim();
        if (nombreIngresado.isEmpty()) {
            System.out.println("Nombre inválido (no puede estar vacío).");
            return;
        }

        System.out.print("Tipo (mago/guerrero/arquero): ");
        String claseIngresada = sc.nextLine().trim().toLowerCase();
        if (!(claseIngresada.equals("mago") || claseIngresada.equals("guerrero") || claseIngresada.equals("arquero"))) {
            System.out.println("Opción inválida (clase no reconocida).");
            return;
        }

        System.out.print("Puntos de vida (>= 0): ");
        int vidaIngresada = leerEntero(sc);
        if (vidaIngresada < 0) {
            System.out.println("Opción inválida (no se permiten negativos).");
            return;
        }

        System.out.print("Fuerza base (>= 0): ");
        int fuerzaIngresada = leerEntero(sc);
        if (fuerzaIngresada < 0) {
            System.out.println("Opción inválida (no se permiten negativos).");
            return;
        }

        // Guardar en “estado global”
        nombre = nombreIngresado;
        clase = claseIngresada;
        vida = vidaIngresada;
        fuerza = fuerzaIngresada;
        oro = 10.0; 
        personajeCreado = true;

        System.out.println("\n✅ Personaje creado:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Clase: " + clase);
        System.out.println("Vida: " + vida);
        System.out.println("Fuerza: " + fuerza);
        System.out.println("Oro: " + oro);
    }

    // ===== Entrenamiento (while) =====
    static void entrenar(Scanner sc) {
        if (!personajeCreado) {
            System.out.println("\n⚠️ Primero debes crear un personaje (opción 1).");
            return;
        }

        int opcionEntrenar = -1;
        System.out.println("\n--- Entrenamiento ---");

        while (opcionEntrenar != 0) {
            System.out.println("\nEntrenar:");
            System.out.println("1. +5 fuerza");
            System.out.println("2. +10 vida");
            System.out.println("0. Terminar entrenamiento");
            System.out.print("Elige: ");

            opcionEntrenar = leerEntero(sc);

            if (opcionEntrenar == 1) {
                fuerza += 5;
                System.out.println("✅ Entrenaste fuerza. Fuerza actual: " + fuerza);
            } else if (opcionEntrenar == 2) {
                vida += 10;
                System.out.println("✅ Entrenaste vida. Vida actual: " + vida);
            } else if (opcionEntrenar == 0) {
                System.out.println("🏁 Entrenamiento terminado.");
            } else {
                System.out.println("Opción inválida");
            }
        }
    }

    // ===== Batalla (for 5 turnos) =====
    static void batalla() {
        if (!personajeCreado) {
            System.out.println("\n⚠️ Primero debes crear un personaje (opción 1).");
            return;
        }

        System.out.println("\n--- Batalla (5 turnos) ---");

        int vidaJugador = vida;  
        int vidaEnemigo = 60;
        int fuerzaEnemigo = 12;

        for (int turno = 1; turno <= 5; turno++) {

            if (vidaJugador <= 0) {
                System.out.println("💀 Has sido derrotado antes del turno " + turno + ".");
                break;
            }
            if (vidaEnemigo <= 0) {
                System.out.println("🏆 ¡Ganaste! El enemigo cayó antes del turno " + turno + ".");
                break;
            }

            int danoJugador = (fuerza / 2) + 5;
            int danoEnemigo = (fuerzaEnemigo / 2) + 4;

            vidaEnemigo -= danoJugador;
            vidaJugador -= danoEnemigo;

            if (vidaEnemigo < 0) vidaEnemigo = 0;
            if (vidaJugador < 0) vidaJugador = 0;

            System.out.println("\nTurno " + turno + ":");
            System.out.println("Tú golpeas por " + danoJugador + " de daño. Vida enemigo: " + vidaEnemigo);
            System.out.println("Enemigo golpea por " + danoEnemigo + " de daño. Tu vida: " + vidaJugador);
        }

        if (vidaJugador > 0 && vidaEnemigo > 0) {
            System.out.println("\n⏳ Fin de 5 turnos. Ambos siguen de pie.");
        }

      
    }

    // ===== Inventario (foreach) =====
    static void mostrarInventario() {
        System.out.println("\n--- Inventario ---");
        for (String item : inventario) {
            System.out.println("- " + item);
        }
    }

    // ===== Estado (if/else + operadores) =====
    static void mostrarEstado() {
        if (!personajeCreado) {
            System.out.println("\n⚠️ Primero debes crear un personaje (opción 1).");
            return;
        }

        System.out.println("\n--- Estado del personaje ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Clase: " + clase);
        System.out.println("Vida: " + vida);
        System.out.println("Fuerza: " + fuerza);
        System.out.println("Oro: " + oro);

        if (vida > 80) {
            System.out.println("✅ Estás en excelente estado");
        } else if (vida > 40) {
            System.out.println("🟨 Estado moderado");
        } else {
            System.out.println("⚠️ Cuidado, estás herido");
        }

        // ejemplo de operadores
        if (fuerza >= 50 && vida >= 50) {
            System.out.println("💪 Bonus: Te ves fuerte y resistente.");
        }
    }
}
