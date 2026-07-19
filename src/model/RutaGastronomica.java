package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa una ruta gastronómica ofrecida por la agencia.
 * 
 * Corresponde a un servicio turístico guiado que contempla la visita a distintos puntos gastronómicos.
 *
 */
public class RutaGastronomica extends Servicio {

    // Atributos
    private GuiaTuristico guia;
    private int numeroParadas;

    /**
     * Constructor de la clase RutaGastronomica.
     *
     * @param nombre Nombre de la ruta gastronómica.
     * @param fecha Fecha en que se realiza el servicio.
     * @param horaInicio Hora de inicio del servicio.
     * @param duracion Duración del servicio.
     * @param cuposMaximos Cantidad máxima de participantes.
     * @param precioPorPersona Precio por persona.
     * @param guia Guía turístico encargado del servicio.
     * @param numeroParadas Cantidad de paradas gastronómicas.
     */
    public RutaGastronomica(String nombre, LocalDate fecha, LocalTime horaInicio, Duration duracion, int cuposMaximos, int precioPorPersona, GuiaTuristico guia, int numeroParadas) {

        super(nombre, fecha, horaInicio, duracion, cuposMaximos, precioPorPersona);

        setGuia(guia);
        setNumeroParadas(numeroParadas);
    }

    // Setters

    public void setGuia(GuiaTuristico guia) {
        this.guia = validarGuia(guia);
    }

    public void setNumeroParadas(int numeroParadas) {
        this.numeroParadas = validarNumeroParadas(numeroParadas);
    }

    // Getters

    public GuiaTuristico getGuia() {
        return guia;
    }

    public int getNumeroParadas() {
        return numeroParadas;
    }

    // Métodos públicos

    /**
     * Obtiene el tipo de servicio.
     *
     * @return Tipo de servicio.
     */
    @Override
    public String getTipoServicio() {
        return "Ruta gastronómica";
    }

    @Override
    public String toString() {

        return super.toString()
                + "\nGuía turístico: " + guia.getNombre()
                + "\nNúmero de paradas: " + numeroParadas;
    }

    @Override
    public String obtenerIdentificador() {
        return getNombre();
    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== RUTA GASTRONÓMICA ==========\n\n");

        sb.append("Nombre: ").append(getNombre()).append("\n");
        sb.append("Fecha: ").append(getFecha()).append("\n");
        sb.append("Hora de inicio: ").append(getHoraInicio()).append("\n");
        sb.append("Duración: ").append(getDuracion()).append("\n");
        sb.append("Cupos máximos: ").append(getCuposMaximos()).append("\n");
        sb.append("Precio por persona: $").append(getPrecioPorPersona()).append("\n");
        sb.append("Número de paradas: ")
                .append(getNumeroParadas()).append("\n");
        sb.append("Guía asignado: ").append(getGuia().getNombre());

        return sb.toString();
    }

    // Métodos privados

    private GuiaTuristico validarGuia(GuiaTuristico guia) {

        if (guia == null) {
            throw new IllegalArgumentException("El guía turístico no puede ser nulo.");
        }

        return guia;
    }

    private int validarNumeroParadas(int numeroParadas) {

        if (numeroParadas <= 0) {
            throw new IllegalArgumentException("El número de paradas debe ser mayor a cero.");
        }

        return numeroParadas;
    }


}