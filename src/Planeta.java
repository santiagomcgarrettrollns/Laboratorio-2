public class Planeta {
    private String codigo;
    private String nombre;
    private double distancia;
    private double temperatura;
    private double nivelHabitabilidad;

    public Planeta(String codigo, String nombre, double distancia, double temperatura, double nivelHabitabilidad) {
        if (distancia <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0.");
        }
        if (nivelHabitabilidad < 0 || nivelHabitabilidad > 100) {
            throw new IllegalArgumentException("El nivel de habitabilidad debe estar entre 0 y 100.");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.distancia = distancia;
        this.temperatura = temperatura;
        this.nivelHabitabilidad = nivelHabitabilidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        if (distancia <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0.");
        }
        this.distancia = distancia;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getNivelHabitabilidad() {
        return nivelHabitabilidad;
    }

    public void setNivelHabitabilidad(double nivelHabitabilidad) {
        if (nivelHabitabilidad < 0 || nivelHabitabilidad > 100) {
            throw new IllegalArgumentException("El nivel de habitabilidad debe estar entre 0 y 100.");
        }
        this.nivelHabitabilidad = nivelHabitabilidad;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + 
               " | Nombre: " + nombre + 
               " | Distancia: " + distancia + 
               " | Temp: " + temperatura + "°C" + 
               " | Habitabilidad: " + nivelHabitabilidad + "%";
    }
}