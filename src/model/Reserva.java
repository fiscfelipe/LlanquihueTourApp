package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa la reserva realizada por un cliente para un servicio turístico.
 */
public class Reserva implements Registrable {

    // Atributos
    private int codigo;
    private Servicio servicio;
    private int cantidadPersonas;
    private int precioTotal;

    /**
     * Constructor de la clase Reserva.
     *
     * @param servicio Servicio reservado.
     * @param cantidadPersonas Cantidad de personas de la reserva.
     */
    public Reserva(Servicio servicio, int cantidadPersonas) {

        setServicio(servicio);
        setCantidadPersonas(cantidadPersonas);
        setPrecioTotal(getServicio().getPrecioPorPersona() * cantidadPersonas);

    }

    // Setters

    /**
     * Establece el código de la reserva.
     *
     * @param codigo Código de la reserva.
     */
    public void setCodigo(int codigo) {

        this.codigo = validarCodigo(codigo);

    }

    /**
     * Establece el servicio reservado.
     *
     * @param servicio Servicio reservado.
     */
    public void setServicio(Servicio servicio) {

        this.servicio = validarServicio(servicio);

    }

    /**
     * Establece la cantidad de personas de la reserva.
     *
     * @param cantidadPersonas Cantidad de personas.
     */
    public void setCantidadPersonas(int cantidadPersonas) {

        this.cantidadPersonas = validarCantidadPersonas(cantidadPersonas);

    }

    /**
     * Establece el precio total de la reserva.
     *
     * @param precioTotal Precio total.
     */
    public void setPrecioTotal(int precioTotal) {

        this.precioTotal = validarPrecioTotal(precioTotal);

    }

    // Getters

    /**
     * Obtiene el código de la reserva.
     *
     * @return Código de la reserva.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el servicio reservado.
     *
     * @return Servicio reservado.
     */
    public Servicio getServicio() {
        return servicio;
    }

    /**
     * Obtiene la cantidad de personas.
     *
     * @return Cantidad de personas.
     */
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    /**
     * Obtiene el precio total.
     *
     * @return Precio total.
     */
    public int getPrecioTotal() {
        return precioTotal;
    }

    // Métodos privados

    /**
     * Valida el código.
     *
     * @param codigo Código a validar.
     * @return Código validado.
     */
    private int validarCodigo(int codigo) {

        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor que cero.");
        }

        return codigo;

    }

    /**
     * Valida el servicio.
     *
     * @param servicio Servicio a validar.
     * @return Servicio validado.
     */
    private Servicio validarServicio(Servicio servicio) {

        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo.");
        }

        return servicio;

    }

    /**
     * Valida la cantidad de personas.
     *
     * @param cantidadPersonas Cantidad de personas.
     * @return Cantidad validada.
     */
    private int validarCantidadPersonas(int cantidadPersonas) {

        if (cantidadPersonas <= 0) {
            throw new IllegalArgumentException("La cantidad de personas debe ser mayor que cero.");
        }

        return cantidadPersonas;

    }

    /**
     * Valida el precio total.
     *
     * @param precioTotal Precio total.
     * @return Precio validado.
     */
    private int validarPrecioTotal(int precioTotal) {

        if (precioTotal < 0) {
            throw new IllegalArgumentException("El precio total no puede ser negativo.");
        }

        return precioTotal;

    }

    // Métodos públicos

    /**
     * Obtiene la fecha del servicio reservado.
     *
     * @return Fecha del servicio.
     */
    public LocalDate getFechaServicio() {
        return servicio.getFecha();
    }

    /**
     * Obtiene la hora de inicio del servicio reservado.
     *
     * @return Hora de inicio del servicio.
     */
    public LocalTime getHoraServicio() {
        return servicio.getHoraInicio();
    }

    /**
     * Devuelve una representación en texto de la reserva.
     *
     * @return Información de la reserva.
     */
    @Override
    public String toString() {

        return "Código: " + codigo
                + "\nServicio: " + servicio.getNombre()
                + "\nFecha: " + getFechaServicio()
                + "\nHora: " + getHoraServicio()
                + "\nCantidad de personas: " + cantidadPersonas
                + "\nPrecio total: $" + precioTotal;

    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== RESERVA ==========\n\n");

        sb.append("Código: ").append(getCodigo()).append("\n");
        sb.append("Servicio: ").append(getServicio().getNombre()).append("\n");
        sb.append("Tipo de servicio: ")
                .append(getServicio().getTipoServicio()).append("\n");
        sb.append("Fecha del servicio: ")
                .append(getServicio().getFecha()).append("\n");
        sb.append("Hora del servicio: ")
                .append(getServicio().getHoraInicio()).append("\n");
        sb.append("Cantidad de personas: ")
                .append(getCantidadPersonas()).append("\n");
        sb.append("Precio total: $")
                .append(getPrecioTotal());

        return sb.toString();

    }

    @Override
    public String obtenerIdentificador() {
        return String.valueOf(getCodigo());
    }

}