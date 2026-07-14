package model;

/**
 * Representa un vehículo registrado por un proveedor de transporte.
 *
 * Esta clase utiliza composición, ya que un vehículo posee una patente.
 */
public class Vehiculo {

    // Atributos
    private String tipo;
    private Patente patente;
    private int asientosDisponibles;

    /**
     * Constructor de la clase Vehiculo.
     *
     * @param tipo Tipo de vehículo.
     * @param patente Patente del vehículo.
     * @param asientosDisponibles Cantidad de asientos disponibles.
     */
    public Vehiculo(String tipo, Patente patente, int asientosDisponibles) {

        //Validaciones
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de vehiculo no puede ser un campo vacío.");
        }

        if (patente == null) {
            throw new IllegalArgumentException("La patente no puede ser un campo vacío.");
        }

        if (asientosDisponibles < 0) {
            throw new IllegalArgumentException("Los asientos disponibles deben ser mayores o iguales a cero.");
        }

        this.tipo = tipo;
        this.patente = patente;
        this.asientosDisponibles = asientosDisponibles;
    }

    //Setters
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    //Getters
    public String getTipo() {
        return tipo;
    }

    public Patente getPatente() {
        return patente;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    //Métodos
    @Override
    public String toString() {
        return "Tipo vehiculo: " + tipo +
                "\nPatente vehiculo: " + patente +
                "\nAsientos disponibles: " + asientosDisponibles;
    }
}
