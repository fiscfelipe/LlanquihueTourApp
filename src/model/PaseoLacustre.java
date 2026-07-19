package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa un paseo lacustre ofrecido por la agencia.
 *
 * Corresponde a un servicio turístico guiado que contempla un paseo utilizando
 * una embarcación.
 *
 */
public class PaseoLacustre extends Servicio {

    // Atributos
    private GuiaTuristico guia;
    private String embarcacion;
    private String lugarDestino;

    /**
     * Constructor de la clase PaseoLacustre.
     *
     * @param nombre Nombre del paseo lacustre.
     * @param fecha Fecha en que se realiza el servicio.
     * @param horaInicio Hora de inicio del servicio.
     * @param duracion Duración del servicio.
     * @param cuposMaximos Cantidad máxima de participantes.
     * @param precioPorPersona Precio por persona.
     * @param guia Guía turístico encargado del servicio.
     * @param embarcacion Nombre de la embarcación utilizada durante el recorrido.
     * @param lugarDestino Lugar de destino del paseo.
     */
    public PaseoLacustre(String nombre, LocalDate fecha, LocalTime horaInicio, Duration duracion, int cuposMaximos, int precioPorPersona, GuiaTuristico guia, String embarcacion, String lugarDestino) {

        super(nombre, fecha, horaInicio, duracion, cuposMaximos, precioPorPersona);

        setGuia(guia);
        setEmbarcacion(embarcacion);
        setLugarDestino(lugarDestino);
    }

    // Setters

    public void setGuia(GuiaTuristico guia) {
        this.guia = validarGuia(guia);
    }

    public void setEmbarcacion(String embarcacion) {
        this.embarcacion = validarEmbarcacion(embarcacion);
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = validarLugarDestino(lugarDestino);
    }

    // Getters

    public GuiaTuristico getGuia() {
        return guia;
    }

    public String getEmbarcacion() {
        return embarcacion;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    // Métodos públicos

    /**
     * Obtiene el tipo de servicio.
     *
     * @return Tipo de servicio.
     */
    @Override
    public String getTipoServicio() {
        return "Paseo lacustre";
    }

    @Override
    public String toString() {

        return super.toString()
                + "\nGuía turístico: " + guia.getNombre()
                + "\nEmbarcación: " + embarcacion
                + "\nLugar de destino: " + lugarDestino;
    }

    @Override
    public String obtenerIdentificador() {
        return getNombre();
    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== PASEO LACUSTRE ==========\n\n");

        sb.append("Nombre: ").append(getNombre()).append("\n");
        sb.append("Fecha: ").append(getFecha()).append("\n");
        sb.append("Hora de inicio: ").append(getHoraInicio()).append("\n");
        sb.append("Duración: ").append(getDuracion()).append("\n");
        sb.append("Cupos máximos: ").append(getCuposMaximos()).append("\n");
        sb.append("Precio por persona: $").append(getPrecioPorPersona()).append("\n");
        sb.append("Embarcación: ").append(getEmbarcacion()).append("\n");
        sb.append("Lugar de destino: ").append(getLugarDestino()).append("\n");
        sb.append("Guía asignado: ").append(getGuia().getNombre());

        return sb.toString();
    }

    // Métodos privados

    private GuiaTuristico validarGuia(GuiaTuristico guia) {

        if (guia == null) {
            throw new IllegalArgumentException(
                    "El guía turístico no puede ser nulo.");
        }

        return guia;
    }

    private String validarEmbarcacion(String embarcacion) {

        if (embarcacion == null || embarcacion.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La embarcación no puede estar vacía.");
        }

        return embarcacion.trim();
    }

    private String validarLugarDestino(String lugarDestino) {

        if (lugarDestino == null || lugarDestino.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El lugar de destino no puede estar vacío.");
        }

        return lugarDestino.trim();
    }

}