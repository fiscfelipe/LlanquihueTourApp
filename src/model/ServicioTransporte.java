package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa un servicio de transporte contratado por la agencia.
 *
 * Corresponde a un traslado realizado mediante un vehículo perteneciente a una
 * empresa de transporte externa.
 */
public class ServicioTransporte extends Servicio {

    // Atributos
    private EmpresaTransporte empresa;
    private Vehiculo vehiculo;
    private String lugarOrigen;
    private String lugarDestino;

    /**
     * Constructor de la clase ServicioTransporte.
     *
     * @param nombre Nombre del servicio.
     * @param fecha Fecha del servicio.
     * @param horaInicio Hora de inicio.
     * @param duracion Duración del servicio.
     * @param cuposMaximos Cantidad máxima de pasajeros.
     * @param precioPorPersona Precio por persona.
     * @param empresa Empresa que prestará el servicio.
     * @param vehiculo Vehículo utilizado.
     * @param lugarOrigen Lugar de origen.
     * @param lugarDestino Lugar de destino.
     */
    public ServicioTransporte(
            String nombre,
            LocalDate fecha,
            LocalTime horaInicio,
            Duration duracion,
            int cuposMaximos,
            int precioPorPersona,
            EmpresaTransporte empresa,
            Vehiculo vehiculo,
            String lugarOrigen,
            String lugarDestino) {

        super(nombre, fecha, horaInicio, duracion, cuposMaximos, precioPorPersona);

        setEmpresa(empresa);
        setVehiculo(vehiculo);
        validarCapacidadVehiculo(cuposMaximos, vehiculo);
        setLugarOrigen(lugarOrigen);
        setLugarDestino(lugarDestino);
    }

    // Setters

    public void setEmpresa(EmpresaTransporte empresa) {
        this.empresa = validarEmpresa(empresa);
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = validarVehiculo(vehiculo);
    }

    public void setLugarOrigen(String lugarOrigen) {
        this.lugarOrigen = validarLugar(lugarOrigen);
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = validarLugar(lugarDestino);
    }

    // Getters

    public EmpresaTransporte getEmpresa() {
        return empresa;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    // Métodos públicos

    @Override
    public String getTipoServicio() {
        return "Servicio de transporte";
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
                + "\nVehículo: " + vehiculo.getTipo()
                + "\nLugar de origen: " + lugarOrigen
                + "\nLugar de destino: " + lugarDestino;
    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== SERVICIO DE TRANSPORTE ==========\n\n");
        sb.append("Nombre: ").append(getNombre()).append("\n");
        sb.append("Fecha: ").append(getFecha()).append("\n");
        sb.append("Hora de inicio: ").append(getHoraInicio()).append("\n");
        sb.append("Duración: ").append(getDuracion()).append("\n");
        sb.append("Cupos máximos: ").append(getCuposMaximos()).append("\n");
        sb.append("Precio por persona: $").append(getPrecioPorPersona()).append("\n");
        sb.append("Lugar de origen: ").append(getLugarOrigen()).append("\n");
        sb.append("Lugar de destino: ").append(getLugarDestino()).append("\n");
        sb.append("Empresa: ").append(getEmpresa().getNombre()).append("\n");
        sb.append("Vehículo: ").append(getVehiculo().getPatente());

        return sb.toString();
    }

    // Métodos privados

    private EmpresaTransporte validarEmpresa(EmpresaTransporte empresa) {

        if (empresa == null) {
            throw new IllegalArgumentException("La empresa no puede ser nula.");
        }

        return empresa;
    }

    private Vehiculo validarVehiculo(Vehiculo vehiculo) {

        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo.");
        }

        if (!empresa.getVehiculos().contains(vehiculo)) {
            throw new IllegalArgumentException(
                    "El vehículo no pertenece a la empresa seleccionada.");
        }

        return vehiculo;
    }

    private void validarCapacidadVehiculo(int cuposMaximos, Vehiculo vehiculo) {

        if (cuposMaximos > vehiculo.getCapacidad()) {
            throw new IllegalArgumentException(
                    "Los cupos máximos no pueden superar la capacidad del vehículo.");
        }
    }

    private String validarLugar(String lugar) {

        if (lugar == null || lugar.trim().isEmpty()) {
            throw new IllegalArgumentException("El lugar no puede estar vacío.");
        }

        return lugar.trim();
    }
}
