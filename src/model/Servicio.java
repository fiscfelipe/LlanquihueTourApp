package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa un servicio turístico ofrecido por la agencia.
 *
 * Contiene la información común a todos los tipos de servicios.
 */
public abstract class Servicio implements Registrable{

    // Atributos
    private String nombre;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private Duration duracion;
    private int cuposMaximos;
    private int precioPorPersona;

    /**
     * Constructor de la clase Servicio.
     *
     * @param nombre Nombre del servicio.
     * @param fecha Fecha en que se realiza el servicio.
     * @param horaInicio Hora de inicio del servicio.
     * @param duracion Duración del servicio.
     * @param cuposMaximos Cantidad máxima de participantes.
     * @param precioPorPersona Precio por persona.
     */
    public Servicio(String nombre, LocalDate fecha, LocalTime horaInicio, Duration duracion, int cuposMaximos, int precioPorPersona) {

        setNombre(nombre);
        setFecha(fecha);
        setHoraInicio(horaInicio);
        setDuracion(duracion);
        setCuposMaximos(cuposMaximos);
        setPrecioPorPersona(precioPorPersona);
    }

    // Setters

    public void setNombre(String nombre) {
        this.nombre = validarNombre(nombre);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = validarFecha(fecha);
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = validarHoraInicio(horaInicio);
    }

    public void setDuracion(Duration duracion) {
        this.duracion = validarDuracion(duracion);
    }

    public void setCuposMaximos(int cuposMaximos) {
        this.cuposMaximos = validarCuposMaximos(cuposMaximos);
    }

    public void setPrecioPorPersona(int precioPorPersona) {
        this.precioPorPersona = validarPrecioPorPersona(precioPorPersona);
    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public int getCuposMaximos() {
        return cuposMaximos;
    }

    public int getPrecioPorPersona() {
        return precioPorPersona;
    }

    // Métodos públicos

    /**
     * Obtiene el tipo de servicio.
     *
     * @return Nombre del tipo de servicio.
     */
    public abstract String getTipoServicio();

    @Override
    public String toString() {

        return "Tipo de servicio: " + getTipoServicio()
                + "\nNombre: " + nombre
                + "\nFecha: " + fecha
                + "\nHora de inicio: " + horaInicio
                + "\nDuración: " + duracion
                + "\nCupos máximos: " + cuposMaximos
                + "\nPrecio por persona: $" + precioPorPersona;
    }


    /** Descuenta cupos disponibles al crear una reserva. */
    public void reservarCupos(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad de cupos debe ser mayor que cero.");
        }
        if (cantidad > cuposMaximos) {
            throw new IllegalArgumentException("La cantidad de personas supera los cupos disponibles del servicio.");
        }
        cuposMaximos -= cantidad;
    }

    /** Restaura cupos disponibles al eliminar una reserva. */
    public void restaurarCupos(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad de cupos debe ser mayor que cero.");
        }
        cuposMaximos += cantidad;
    }

    // Métodos privados

    private String validarNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        return nombre.trim();
    }

    private LocalDate validarFecha(LocalDate fecha) {

        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }

        return fecha;
    }

    private LocalTime validarHoraInicio(LocalTime horaInicio) {

        if (horaInicio == null) {
            throw new IllegalArgumentException("La hora de inicio no puede ser nula.");
        }

        return horaInicio;
    }

    private Duration validarDuracion(Duration duracion) {

        if (duracion == null || duracion.isZero()
                || duracion.isNegative()) {

            throw new IllegalArgumentException("La duración debe ser mayor a cero.");
        }

        return duracion;
    }

    private int validarCuposMaximos(int cuposMaximos) {

        if (cuposMaximos < 0) {
            throw new IllegalArgumentException("Los cupos máximos deben ser mayores a cero.");
        }

        return cuposMaximos;
    }

    private int validarPrecioPorPersona(int precioPorPersona) {

        if (precioPorPersona < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        return precioPorPersona;
    }
    
    //Métodos interfaz Registrable
    @Override
    public abstract String mostrarDatos();

    @Override
    public abstract String obtenerIdentificador();

}