package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa un servicio de alojamiento ofrecido por la agencia.
 *
 * Corresponde a una estadía que utiliza un alojamiento perteneciente a una
 * empresa externa.
 */
public class ServicioAlojamiento extends Servicio {

    // Atributos
    private EmpresaAlojamiento empresa;
    private Alojamiento alojamiento;

    /**
     * Constructor de la clase ServicioAlojamiento.
     *
     * @param nombre Nombre del servicio.
     * @param fecha Fecha del servicio.
     * @param horaInicio Hora de inicio.
     * @param duracion Duración de la estadía.
     * @param cuposMaximos Cantidad máxima de huéspedes.
     * @param precioPorPersona Precio por persona.
     * @param empresa Empresa que prestará el servicio.
     * @param alojamiento Alojamiento utilizado.
     */
    public ServicioAlojamiento(
            String nombre,
            LocalDate fecha,
            LocalTime horaInicio,
            Duration duracion,
            int cuposMaximos,
            int precioPorPersona,
            EmpresaAlojamiento empresa,
            Alojamiento alojamiento) {

        super(nombre, fecha, horaInicio, duracion, cuposMaximos, precioPorPersona);

        setEmpresa(empresa);
        setAlojamiento(alojamiento);
        validarCapacidadAlojamiento(cuposMaximos, alojamiento);
    }

    // Setters

    public void setEmpresa(EmpresaAlojamiento empresa) {
        this.empresa = validarEmpresa(empresa);
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = validarAlojamiento(alojamiento);
    }

    // Getters

    public EmpresaAlojamiento getEmpresa() {
        return empresa;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    // Métodos públicos

    @Override
    public String getTipoServicio() {
        return "Servicio de alojamiento";
    }

    @Override
    public String obtenerIdentificador() {
        return getNombre();
    }

    @Override
    public String toString() {

        return super.toString()
                + "\nEmpresa: " + empresa.getNombre()
                + "\nProveedor: " + empresa.getProveedor().getNombre()
                + "\nAlojamiento: " + alojamiento.getTipo();
    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== SERVICIO DE ALOJAMIENTO ==========\n\n");
        sb.append("Nombre: ").append(getNombre()).append("\n");
        sb.append("Fecha: ").append(getFecha()).append("\n");
        sb.append("Hora de check-in: ").append(getHoraInicio()).append("\n");
        sb.append("Duración: ").append(getDuracion()).append("\n");
        sb.append("Cupos máximos: ").append(getCuposMaximos()).append("\n");
        sb.append("Precio por persona: $").append(getPrecioPorPersona()).append("\n");
        sb.append("Tipo de alojamiento: ")
                .append(getAlojamiento().getTipo()).append("\n");
        sb.append("Empresa: ").append(getEmpresa().getNombre());

        return sb.toString();
    }

    // Métodos privados

    private EmpresaAlojamiento validarEmpresa(EmpresaAlojamiento empresa) {

        if (empresa == null) {
            throw new IllegalArgumentException("La empresa no puede ser nula.");
        }

        return empresa;
    }

    private Alojamiento validarAlojamiento(Alojamiento alojamiento) {

        if (alojamiento == null) {
            throw new IllegalArgumentException("El alojamiento no puede ser nulo.");
        }

        if (!empresa.getAlojamientos().contains(alojamiento)) {
            throw new IllegalArgumentException(
                    "El alojamiento no pertenece a la empresa seleccionada.");
        }

        return alojamiento;
    }

    private void validarCapacidadAlojamiento(
            int cuposMaximos, Alojamiento alojamiento) {

        if (cuposMaximos > alojamiento.getCapacidad()) {
            throw new IllegalArgumentException(
                    "Los cupos máximos no pueden superar la capacidad del alojamiento.");
        }
    }
}
