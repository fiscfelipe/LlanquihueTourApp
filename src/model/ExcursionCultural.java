package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa una excursión cultural ofrecida por la agencia.
 * 
 * Corresponde a un servicio turístico guiado que contempla la visita a un lugar de interés cultural.
 */
public class ExcursionCultural extends Servicio {

    // Atributos
    private GuiaTuristico guia;
    private String lugarDestino;

    /**
     * Constructor de la clase ExcursionCultural.
     *
     * @param nombre Nombre de la excursión cultural.
     * @param fecha Fecha en que se realiza el servicio.
     * @param horaInicio Hora de inicio del servicio.
     * @param duracion Duración del servicio.
     * @param cuposMaximos Cantidad máxima de participantes.
     * @param precioPorPersona Precio por persona.
     * @param guia Guía turístico encargado del servicio.
     * @param lugarDestino Lugar de destino de la excursión.
     */
    public ExcursionCultural(String nombre, LocalDate fecha, LocalTime horaInicio, Duration duracion, int cuposMaximos, int precioPorPersona, GuiaTuristico guia, String lugarDestino) {

        super(nombre, fecha, horaInicio, duracion, cuposMaximos, precioPorPersona);

        setGuia(guia);
        setLugarDestino(lugarDestino);
    }

    // Setters

    public void setGuia(GuiaTuristico guia) {
        this.guia = validarGuia(guia);
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = validarLugarDestino(lugarDestino);
    }

    // Getters

    public GuiaTuristico getGuia() {
        return guia;
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
        return "Excursión cultural";
    }

    @Override
    public String toString() {

        return super.toString()
                + "\nGuía turístico: " + guia.getNombre()
                + "\nLugar de destino: " + lugarDestino;
    }

    @Override
    public String obtenerIdentificador() {
        return getNombre();
    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== EXCURSIÓN CULTURAL ==========\n\n");

        sb.append("Nombre: ").append(getNombre()).append("\n");
        sb.append("Fecha: ").append(getFecha()).append("\n");
        sb.append("Hora de inicio: ").append(getHoraInicio()).append("\n");
        sb.append("Duración: ").append(getDuracion()).append("\n");
        sb.append("Cupos máximos: ").append(getCuposMaximos()).append("\n");
        sb.append("Precio por persona: $").append(getPrecioPorPersona()).append("\n");
        sb.append("Lugar de destino: ").append(getLugarDestino()).append("\n");
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

    private String validarLugarDestino(String lugarDestino) {

        if (lugarDestino == null || lugarDestino.trim().isEmpty()) {
            throw new IllegalArgumentException("El lugar de destino no puede estar vacío.");
        }

        return lugarDestino.trim();
    }

}