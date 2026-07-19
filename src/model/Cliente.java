package model;

import java.util.ArrayList;
import util.CorreoInvalidoException;
import util.RutInvalidoException;
import util.TelefonoInvalidoException;

/**
 * Representa un cliente.
 */
public class Cliente extends Persona {

    // Atributos
    private ArrayList<Reserva> reservas;

    /**
     * Constructor de la clase Cliente.
     * @param nombre Nombre del cliente.
     * @param rut Rut del cliente.
     * @param correo Correo del cliente.
     * @param telefono Teléfono del cliente.
     * @param direccion Dirección del cliente.
     * @throws RutInvalidoException Si el RUT es inválido.
     * @throws CorreoInvalidoException Si el correo es inválido.
     * @throws TelefonoInvalidoException Si el teléfono es inválido.
     */
    public Cliente(String nombre, Rut rut, Correo correo, Telefono telefono, Direccion direccion) throws RutInvalidoException, CorreoInvalidoException, TelefonoInvalidoException {

        super(nombre, rut, correo, telefono, direccion);

        reservas = new ArrayList<>();
    }

    // Setters

    /**
     * Establece las reservas del cliente.
     * @param reservas Lista de reservas.
     */
    public void setReservas(ArrayList<Reserva> reservas) {

        if (reservas == null) {
            throw new IllegalArgumentException("La lista de reservas no puede ser nula.");
        }

        this.reservas.clear();
        this.reservas.addAll(reservas);
    }

    // Getters

    /**
     * Obtiene las reservas del cliente.
     * @return Copia de la lista de reservas.
     */
    public ArrayList<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }


    // Métodos públicos
    
    /**
     * Agrega una reserva al cliente.
     * @param reserva Reserva que se desea agregar.
     */
    public void agregarReserva(Reserva reserva) {

        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }

        reservas.add(reserva);
    }

    /**
     * Elimina una reserva del cliente.
     * @param reserva Reserva que se desea eliminar.
     */
    public void eliminarReserva(Reserva reserva) {

        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }

        reservas.remove(reserva);
    }

    /**
     * Devuelve una representación en texto del cliente.
     * @return Información del cliente.
     */
    @Override
    public String toString() {

        return super.toString()
                + "\nReservas registradas: " + reservas.size();
    }

    @Override
    public String obtenerIdentificador() {
        return getRut().toString();
    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== CLIENTE ==========\n\n");

        sb.append("Nombre: ").append(getNombre()).append("\n");
        sb.append("RUT: ").append(getRut()).append("\n");
        sb.append("Correo: ").append(getCorreo()).append("\n");
        sb.append("Teléfono: ").append(getTelefono()).append("\n\n");

        sb.append("Dirección\n");
        sb.append("Calle: ").append(getDireccion().getCalle()).append("\n");
        sb.append("Número: ").append(getDireccion().getNumero()).append("\n");
        sb.append("Ciudad: ").append(getDireccion().getCiudad()).append("\n");
        sb.append("Región: ").append(getDireccion().getRegion());

        return sb.toString();
    }

}