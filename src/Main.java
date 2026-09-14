import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static NaveEspacial nave = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE EXPLORACIÓN ESPACIAL UVG ===");
        crearNuevaNave();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    crearNuevaNave();
                    break;
                case 2:
                    instalarModulo();
                    break;
                case 3:
                    consultarModulos();
                    break;
                case 4:
                    consultarUnModulo();
                    break;
                case 5:
                    modificarModulo();
                    break;
                case 6:
                    retirarModulo();
                    break;
                case 7:
                    registrarPlaneta();
                    break;
                case 8:
                    consultarPlanetas();
                    break;
                case 9:
                    buscarPlaneta();
                    break;
                case 10:
                    modificarPlaneta();
                    break;
                case 11:
                    eliminarPlaneta();
                    break;
                case 12:
                    mostrarReporteMision();
                    break;
                case 13:
                    salir = true;
                    System.out.println("Finalizando programa de exploración espacial. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("[ERROR] Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n---------------- MENU PRINCIPAL ----------------");
        System.out.println("1. Nueva nave");
        System.out.println("2. Instalar módulo");
        System.out.println("3. Consultar módulos");
        System.out.println("4. Consultar un módulo");
        System.out.println("5. Modificar módulo");
        System.out.println("6. Retirar módulo");
        System.out.println("7. Registrar planeta");
        System.out.println("8. Consultar planetas");
        System.out.println("9. Buscar planeta");
        System.out.println("10. Modificar planeta");
        System.out.println("11. Eliminar planeta");
        System.out.println("12. Mostrar reporte de misión");
        System.out.println("13. Salir");
    }

    private static void crearNuevaNave() {
        System.out.println("\n--- REGISTRO DE NAVE ESPACIAL ---");
        System.out.print("Nombre de la nave: ");
        String nombre = scanner.nextLine();
        System.out.print("Código de identificación: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre del comandante: ");
        String comandante = scanner.nextLine();

        nave = new NaveEspacial(nombre, codigo, comandante);
        System.out.println("[ÉXITO] Nave " + nombre + " inicializada correctamente.");
    }

    private static void instalarModulo() {
        System.out.println("\n--- INSTALAR MÓDULO ---");
        try {
            int pos = leerEntero("Ingrese la posición en la nave (0 a 4): ");
            if (pos < 0 || pos >= 5) {
                System.out.println("[ERROR] Posición inválida. La nave solo soporta índices de 0 a 4.");
                return;
            }
            if (nave.obtenerModulo(pos) != null) {
                System.out.println("[ERROR] La posición " + pos + " ya se encuentra ocupada.");
                return;
            }

            System.out.print("Código del módulo: ");
            String codigo = scanner.nextLine();
            System.out.print("Nombre del módulo: ");
            String nombre = scanner.nextLine();
            System.out.print("Tipo de módulo: ");
            String tipo = scanner.nextLine();
            double consumo = leerDouble("Consumo de energía (kW): ");
            System.out.print("Estado del módulo: ");
            String estado = scanner.nextLine();

            Modulo m = new Modulo(codigo, nombre, tipo, consumo, estado);
            if (nave.instalarModulo(pos, m)) {
                System.out.println("[ÉXITO] Módulo instalado en la posición " + pos + ".");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR DE VALIDACIÓN] " + e.getMessage());
        } finally {
            System.out.println("[FINALLY] Operación de instalación finalizada.");
        }
    }

    private static void consultarModulos() {
        System.out.println("\n--- MÓDULOS INSTALADOS ---");
        Modulo[] modulos = nave.getModulos();
        boolean hayModulos = false;

        for (int i = 0; i < modulos.length; i++) {
            if (modulos[i] != null) {
                System.out.println("Posición [" + i + "]: " + modulos[i]);
                hayModulos = true;
            }
        }

        if (!hayModulos) {
            System.out.println("No hay ningún módulo instalado en la nave.");
        }
    }

    private static void consultarUnModulo() {
        System.out.println("\n--- CONSULTAR MÓDULO ---");
        try {
            int pos = leerEntero("Ingrese la posición a consultar (0 a 4): ");
            Modulo m = nave.obtenerModulo(pos);
            if (m != null) {
                System.out.println("Posición [" + pos + "]: " + m);
            } else {
                System.out.println("La posición [" + pos + "] está vacía (contiene null).");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void modificarModulo() {
        System.out.println("\n--- MODIFICAR MÓDULO ---");
        try {
            int pos = leerEntero("Ingrese la posición del módulo a modificar (0 a 4): ");
            Modulo m = nave.obtenerModulo(pos);

            if (m == null) {
                System.out.println("[ERROR] La posición [" + pos + "] está vacía.");
                return;
            }

            double nuevoConsumo = leerDouble("Nuevo consumo de energía (kW): ");
            System.out.print("Nuevo estado: ");
            String nuevoEstado = scanner.nextLine();

            nave.modificarModulo(pos, nuevoConsumo, nuevoEstado);
            System.out.println("[ÉXITO] Módulo modificado correctamente.");
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void retirarModulo() {
        System.out.println("\n--- RETIRAR MÓDULO ---");
        try {
            int pos = leerEntero("Ingrese la posición del módulo a retirar (0 a 4): ");
            if (nave.retirarModulo(pos)) {
                System.out.println("[ÉXITO] Módulo retirado de la posición " + pos + ".");
            } else {
                System.out.println("[ERROR] La posición " + pos + " ya estaba vacía.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void registrarPlaneta() {
        System.out.println("\n--- REGISTRAR PLANETA ---");
        try {
            System.out.print("Código del planeta: ");
            String codigo = scanner.nextLine();

            if (nave.buscarPlaneta(codigo) != null) {
                System.out.println("[ERROR] Ya existe un planeta registrado con el código: " + codigo);
                return;
            }

            System.out.print("Nombre del planeta: ");
            String nombre = scanner.nextLine();
            double distancia = leerDouble("Distancia desde la nave: ");
            double temperatura = leerDouble("Temperatura (°C): ");
            double habitabilidad = leerDouble("Nivel de habitabilidad (0-100): ");

            Planeta p = new Planeta(codigo, nombre, distancia, temperatura, habitabilidad);
            nave.registrarPlaneta(p);
            System.out.println("[ÉXITO] Planeta " + nombre + " registrado en el sistema.");
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR DE VALIDACIÓN] " + e.getMessage());
        }
    }

    private static void consultarPlanetas() {
        System.out.println("\n--- PLANETAS DESCUBIERTOS ---");
        if (nave.getCantidadPlanetas() == 0) {
            System.out.println("Aún no se han descubierto o registrado planetas en la misión.");
            return;
        }

        for (Planeta p : nave.getPlanetas()) {
            System.out.println(p);
        }
    }

    private static void buscarPlaneta() {
        System.out.println("\n--- BUSCAR PLANETA ---");
        System.out.print("Ingrese el código del planeta: ");
        String codigo = scanner.nextLine();

        Planeta p = nave.buscarPlaneta(codigo);
        if (p != null) {
            System.out.println("Planeta encontrado:");
            System.out.println(p);
        } else {
            System.out.println("[INFO] No se encontró ningún planeta con el código " + codigo + ".");
        }
    }

    private static void modificarPlaneta() {
        System.out.println("\n--- MODIFICAR PLANETA ---");
        System.out.print("Ingrese el código del planeta a modificar: ");
        String codigo = scanner.nextLine();

        Planeta p = nave.buscarPlaneta(codigo);
        if (p == null) {
            System.out.println("[ERROR] No existe ningún planeta registrado con el código " + codigo + ".");
            return;
        }

        try {
            System.out.print("Nuevo nombre: ");
            String nuevoNombre = scanner.nextLine();
            double nuevaDistancia = leerDouble("Nueva distancia desde la nave: ");
            double nuevaTemperatura = leerDouble("Nueva temperatura: ");
            double nuevaHabitabilidad = leerDouble("Nuevo nivel de habitabilidad (0-100): ");

            nave.modificarPlaneta(codigo, nuevoNombre, nuevaDistancia, nuevaTemperatura, nuevaHabitabilidad);
            System.out.println("[ÉXITO] Información del planeta actualizada.");
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR DE VALIDACIÓN] " + e.getMessage());
        }
    }

    private static void eliminarPlaneta() {
        System.out.println("\n--- ELIMINAR PLANETA ---");
        System.out.print("Ingrese el código del planeta a eliminar: ");
        String codigo = scanner.nextLine();

        if (nave.eliminarPlaneta(codigo)) {
            System.out.println("[ÉXITO] Planeta eliminado del registro.");
        } else {
            System.out.println("[ERROR] No se encontró ningún planeta registrado con el código " + codigo + ".");
        }
    }

    private static void mostrarReporteMision() {
        System.out.println("\n========= REPORTE DE MISIÓN DE EXPLORACIÓN =========");
        System.out.println("Nave: " + nave.getNombre() + " | Código: " + nave.getCodigoIdentificacion());
        System.out.println("Comandante a cargo: " + nave.getNombreComandante());
        System.out.println("-----------------------------------------------------");

        // Módulos
        System.out.println("Módulos instalados: " + nave.getCantidadModulosInstalados());
        System.out.println("Espacios disponibles: " + nave.getEspaciosDisponiblesModulos());

        Modulo mayorConsumo = nave.getModuloMayorConsumo();
        if (mayorConsumo != null) {
            System.out.println("Módulo de mayor consumo: " + mayorConsumo.getNombre() + " (" + mayorConsumo.getConsumoEnergia() + " kW)");
        } else {
            System.out.println("Módulo de mayor consumo: N/A (No hay módulos instalados)");
        }

        // Planetas
        System.out.println("-----------------------------------------------------");
        System.out.println("Planetas descubiertos: " + nave.getCantidadPlanetas());

        if (nave.getCantidadPlanetas() > 0) {
            Planeta mayorHab = nave.getPlanetaMayorHabitabilidad();
            Planeta menorHab = nave.getPlanetaMenorHabitabilidad();

            System.out.println("Planeta mayor habitabilidad: " + mayorHab.getNombre() + " (" + mayorHab.getNivelHabitabilidad() + "%)");
            System.out.println("Planeta menor habitabilidad: " + menorHab.getNombre() + " (" + menorHab.getNivelHabitabilidad() + "%)");
            System.out.printf("Promedio de habitabilidad: %.2f%%\n", nave.getPromedioHabitabilidad());
        } else {
            System.out.println("Cálculos de planetas: N/A (ArrayList de planetas vacío)");
        }
        System.out.println("=====================================================");
    }

    // --- MÉTODOS DE LECTURA ROBUSTA CON MANEJO DE InputMismatchException ---

    private static int leerEntero(String mensaje) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                valor = scanner.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("[ERROR DE ENTRADA] Debe ingresar un número entero válido.");
            } finally {
                scanner.nextLine(); // Limpiar el búfer del Scanner
            }
        }
        return valor;
    }

    private static double leerDouble(String mensaje) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                valor = scanner.nextDouble();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("[ERROR DE ENTRADA] Debe ingresar un valor numérico válido.");
            } finally {
                scanner.nextLine(); // Limpiar el búfer del Scanner
            }
        }
        return valor;
    }
}