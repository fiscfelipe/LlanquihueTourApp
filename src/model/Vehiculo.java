package model;

import util.PatenteInvalidaException;

/**
 * Representa un vehículo.
 */
public class Vehiculo implements Registrable {

    // Atributos
    private String tipo;
    private Patente patente;
    private int capacidad;

    /**
     * Constructor de la clase Vehiculo.
     *
     * @param tipo Tipo de vehículo.
     * @param patente Patente del vehículo.
     * @param capacidad Capacidad máxima de pasajeros.
     * @throws PatenteInvalidaException Si la patente es inválida.
     */
    public Vehiculo(String tipo, Patente patente, int capacidad)
            throws PatenteInvalidaException {

        setTipo(tipo);
        setPatente(patente);
        setCapacidad(capacidad);
    }

    // Setters

    /**
     * Establece el tipo de vehículo.
     *
     * @param tipo Tipo de vehículo.
     */
    public void setTipo(String tipo) {

        validarTipo(tipo);

        this.tipo = tipo.trim();
    }

    /**
     * Establece la patente del vehículo.
     *
     * @param patente Patente del vehículo.
     * @throws PatenteInvalidaException Si la patente es inválida.
     */
    public void setPatente(Patente patente)
            throws PatenteInvalidaException {

        if (patente == null) {
            throw new PatenteInvalidaException(
                    "La patente no puede ser nula.");
        }

        this.patente = patente;
    }

    /**
     * Establece la capacidad del vehículo.
     *
     * @param capacidad Capacidad máxima de pasajeros.
     */
    public void setCapacidad(int capacidad) {

        validarCapacidad(capacidad);

        this.capacidad = capacidad;
    }

    // Getters

    /**
     * Obtiene el tipo de vehículo.
     *
     * @return Tipo de vehículo.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene la patente del vehículo.
     *
     * @return Patente del vehículo.
     */
    public Patente getPatente() {
        return patente;
    }

    /**
     * Obtiene la capacidad del vehículo.
     *
     * @return Capacidad máxima de pasajeros.
     */
    public int getCapacidad() {
        return capacidad;
    }

    // Métodos privados

    /**
     * Valida el tipo de vehículo.
     *
     * @param tipo Tipo de vehículo a validar.
     */
    private void validarTipo(String tipo) {

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El tipo de vehículo no puede estar vacío.");
        }

    }

    /**
     * Valida la capacidad del vehículo.
     *
     * @param capacidad Capacidad máxima de pasajeros.
     */
    private void validarCapacidad(int capacidad) {

        if (capacidad < 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor o igual a cero.");
        }

    }

    // Métodos públicos

    /**
     * Obtiene el identificador del vehículo.
     *
     * @return Patente del vehículo.
     */
    @Override
    public String obtenerIdentificador() {

        return patente.toString();
    }

    /**
     * Devuelve la información completa del vehículo.
     *
     * @return Información del vehículo.
     */
    @Override
    public String mostrarDatos() {

        return "Tipo: " + tipo
                + "\nPatente: " + patente
                + "\nCapacidad: " + capacidad;
    }

    /**
     * Devuelve una representación en texto del vehículo.
     *
     * @return Información resumida del vehículo.
     */
    @Override
    public String toString() {

        return obtenerIdentificador();
    }

}