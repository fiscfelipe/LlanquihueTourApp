package model;

/**
 * Representa un tipo de alojamiento.
 */
public class Alojamiento implements Registrable {

    // Atributos
    private String nombre;
    private String tipo;
    private int capacidad;
    private Direccion direccion;

    /**
     * Constructor de la clase Alojamiento.
     *
     * @param nombre Nombre del alojamiento.
     * @param tipo Tipo de alojamiento.
     * @param capacidad Capacidad máxima de huéspedes.
     * @param direccion Dirección del alojamiento.
     */
    public Alojamiento(String nombre, String tipo, int capacidad,
            Direccion direccion) {

        setNombre(nombre);
        setTipo(tipo);
        setCapacidad(capacidad);
        setDireccion(direccion);
    }

    // Setters

    /**
     * Establece el nombre del alojamiento.
     *
     * @param nombre Nombre del alojamiento.
     */
    public void setNombre(String nombre) {

        validarNombre(nombre);

        this.nombre = nombre.trim();
    }

    /**
     * Establece el tipo de alojamiento.
     *
     * @param tipo Tipo de alojamiento.
     */
    public void setTipo(String tipo) {

        validarTipo(tipo);

        this.tipo = tipo.trim();
    }

    /**
     * Establece la capacidad del alojamiento.
     *
     * @param capacidad Capacidad máxima de huéspedes.
     */
    public void setCapacidad(int capacidad) {

        validarCapacidad(capacidad);

        this.capacidad = capacidad;
    }

    /**
     * Establece la dirección del alojamiento.
     *
     * @param direccion Dirección del alojamiento.
     */
    public void setDireccion(Direccion direccion) {

        if (direccion == null) {
            throw new IllegalArgumentException(
                    "La dirección no puede ser nula.");
        }

        this.direccion = direccion;
    }

    // Getters

    /**
     * Obtiene el nombre del alojamiento.
     *
     * @return Nombre del alojamiento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el tipo de alojamiento.
     *
     * @return Tipo de alojamiento.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene la capacidad del alojamiento.
     *
     * @return Capacidad máxima de huéspedes.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Obtiene la dirección del alojamiento.
     *
     * @return Dirección del alojamiento.
     */
    public Direccion getDireccion() {
        return direccion;
    }

    // Métodos privados

    /**
     * Valida el nombre del alojamiento.
     *
     * @param nombre Nombre a validar.
     */
    private void validarNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre del alojamiento no puede estar vacío.");
        }

    }

    /**
     * Valida el tipo de alojamiento.
     *
     * @param tipo Tipo de alojamiento a validar.
     */
    private void validarTipo(String tipo) {

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El tipo de alojamiento no puede estar vacío.");
        }

    }

    /**
     * Valida la capacidad del alojamiento.
     *
     * @param capacidad Capacidad máxima de huéspedes.
     */
    private void validarCapacidad(int capacidad) {

        if (capacidad < 1) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero.");
        }

    }

    // Métodos públicos

    /**
     * Obtiene el identificador del alojamiento.
     *
     * @return Nombre del alojamiento.
     */
    @Override
    public String obtenerIdentificador() {

        return nombre;
    }

    /**
     * Devuelve la información completa del alojamiento.
     *
     * @return Información del alojamiento.
     */
    @Override
    public String mostrarDatos() {

        return "Nombre: " + nombre
                + "\nTipo: " + tipo
                + "\nCapacidad: " + capacidad
                + "\nDirección:\n" + direccion;
    }

    /**
     * Devuelve una representación en texto del alojamiento.
     *
     * @return Identificador del alojamiento.
     */
    @Override
    public String toString() {

        return obtenerIdentificador();
    }

}