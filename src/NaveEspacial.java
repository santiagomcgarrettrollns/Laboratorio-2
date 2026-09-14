import java.util.ArrayList;

public class NaveEspacial {
    private String nombre;
    private String codigoIdentificacion;
    private String nombreComandante;
    private Modulo[] modulos;
    private ArrayList<Planeta> planetas;

    public NaveEspacial(String nombre, String codigoIdentificacion, String nombreComandante) {
        this.nombre = nombre;
        this.codigoIdentificacion = codigoIdentificacion;
        this.nombreComandante = nombreComandante;
        this.modulos = new Modulo[5];
        this.planetas = new ArrayList<>();
    }

    // --- MÉTODOS PARA MÓDULOS ---

    public boolean instalarModulo(int posicion, Modulo modulo) {
        if (posicion < 0 || posicion >= modulos.length) {
            throw new IndexOutOfBoundsException("La posición " + posicion + " está fuera de los límites (0-4).");
        }
        if (modulos[posicion] != null) {
            return false; // Posición ocupada
        }
        modulos[posicion] = modulo;
        return true;
    }

    public Modulo obtenerModulo(int posicion) {
        if (posicion < 0 || posicion >= modulos.length) {
            throw new IndexOutOfBoundsException("Posición inválida.");
        }
        return modulos[posicion];
    }

    public boolean modificarModulo(int posicion, double nuevoConsumo, String nuevoEstado) {
        if (posicion < 0 || posicion >= modulos.length) {
            throw new IndexOutOfBoundsException("Posición fuera de rango.");
        }
        if (modulos[posicion] == null) {
            return false;
        }
        modulos[posicion].setConsumoEnergia(nuevoConsumo);
        modulos[posicion].setEstado(nuevoEstado);
        return true;
    }

    public boolean retirarModulo(int posicion) {
        if (posicion < 0 || posicion >= modulos.length) {
            throw new IndexOutOfBoundsException("Posición fuera de rango.");
        }
        if (modulos[posicion] == null) {
            return false;
        }
        modulos[posicion] = null;
        return true;
    }

    public int getCantidadModulosInstalados() {
        int contador = 0;
        for (Modulo m : modulos) {
            if (m != null) {
                contador++;
            }
        }
        return contador;
    }

    public int getEspaciosDisponiblesModulos() {
        return modulos.length - getCantidadModulosInstalados();
    }

    public Modulo getModuloMayorConsumo() {
        Modulo mayor = null;
        for (Modulo m : modulos) {
            if (m != null) {
                if (mayor == null || m.getConsumoEnergia() > mayor.getConsumoEnergia()) {
                    mayor = m;
                }
            }
        }
        return mayor;
    }

    // --- MÉTODOS PARA PLANETAS ---

    public boolean registrarPlaneta(Planeta planeta) {
        if (buscarPlaneta(planeta.getCodigo()) != null) {
            return false; // El código ya existe
        }
        return planetas.add(planeta);
    }

    public Planeta buscarPlaneta(String codigo) {
        for (Planeta p : planetas) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public boolean modificarPlaneta(String codigo, String nuevoNombre, double nuevaDistancia, double nuevaTemp, double nuevaHabitabilidad) {
        Planeta p = buscarPlaneta(codigo);
        if (p == null) {
            return false;
        }
        p.setNombre(nuevoNombre);
        p.setDistancia(nuevaDistancia);
        p.setTemperatura(nuevaTemp);
        p.setNivelHabitabilidad(nuevaHabitabilidad);
        return true;
    }

    public boolean eliminarPlaneta(String codigo) {
        Planeta p = buscarPlaneta(codigo);
        if (p != null) {
            return planetas.remove(p);
        }
        return false;
    }

    public int getCantidadPlanetas() {
        return planetas.size();
    }

    public Planeta getPlanetaMayorHabitabilidad() {
        if (planetas.isEmpty()) return null;
        Planeta mayor = planetas.get(0);
        for (Planeta p : planetas) {
            if (p.getNivelHabitabilidad() > mayor.getNivelHabitabilidad()) {
                mayor = p;
            }
        }
        return mayor;
    }

    public Planeta getPlanetaMenorHabitabilidad() {
        if (planetas.isEmpty()) return null;
        Planeta menor = planetas.get(0);
        for (Planeta p : planetas) {
            if (p.getNivelHabitabilidad() < menor.getNivelHabitabilidad()) {
                menor = p;
            }
        }
        return menor;
    }

    public double getPromedioHabitabilidad() {
        if (planetas.isEmpty()) return 0;
        double suma = 0;
        for (Planeta p : planetas) {
            suma += p.getNivelHabitabilidad();
        }
        return suma / planetas.size();
    }

    // Getters y Setters básicos
    public String getNombre() { return nombre; }
    public String getCodigoIdentificacion() { return codigoIdentificacion; }
    public String getNombreComandante() { return nombreComandante; }
    public Modulo[] getModulos() { return modulos; }
    public ArrayList<Planeta> getPlanetas() { return planetas; }
}