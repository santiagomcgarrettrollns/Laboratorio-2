public class Modulo {
    private String codigo;
    private String nombre;
    private String tipo;
    private double consumoEnergia;
    private String estado;

    public Modulo(String codigo, String nombre, String tipo, double consumoEnergia, String estado) {
        if (consumoEnergia <= 0) {
            throw new IllegalArgumentException("El consumo de energía debe ser mayor a 0.");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.consumoEnergia = consumoEnergia;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(double consumoEnergia) {
        if (consumoEnergia <= 0) {
            throw new IllegalArgumentException("El consumo de energía debe ser mayor a 0.");
        }
        this.consumoEnergia = consumoEnergia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + 
               " | Nombre: " + nombre + 
               " | Tipo: " + tipo + 
               " | Consumo: " + consumoEnergia + " kW" + 
               " | Estado: " + estado;
    }
}