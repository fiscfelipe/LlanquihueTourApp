package data;

import java.util.ArrayList;

import model.Cliente;
import model.Reserva;
import model.Servicio;

/**
 * Gestiona las reservas y centraliza la actualización de cupos.
 */
public class GestorReservas {

    // Atributos

    private final ArrayList<Reserva> reservas = new ArrayList<>();
    private int siguienteCodigo = 1;

    // Constructor

    /**
     * Construye un gestor de reservas.
     */
    public GestorReservas() {

    }

    // Setters

    /**
     * Establece el siguiente código disponible.
     *
     * @param siguienteCodigo Siguiente código.
     */
    public void setSiguienteCodigo(int siguienteCodigo) {

        if (siguienteCodigo > 0) {

            this.siguienteCodigo = siguienteCodigo;

        }

    }

    // Getters

    /**
     * Obtiene las reservas registradas.
     *
     * @return Copia de la lista de reservas.
     */
    public ArrayList<Reserva> getReservas() {

        return new ArrayList<>(reservas);

    }

    // Métodos públicos

    /**
     * Crea una nueva reserva.
     *
     * @param cliente Cliente.
     * @param servicio Servicio.
     * @param cantidadPersonas Cantidad de personas.
     * @return Reserva creada.
     */
    public Reserva crearReserva(Cliente cliente, Servicio servicio, int cantidadPersonas) {

        if (cliente == null) {

            throw new IllegalArgumentException("El cliente no puede ser nulo.");

        }

        if (servicio == null) {

            throw new IllegalArgumentException("El servicio no puede ser nulo.");

        }

        Reserva reserva = new Reserva(servicio, cantidadPersonas);
        servicio.reservarCupos(cantidadPersonas);

        try {

            reserva.setCodigo(siguienteCodigo++);
            reservas.add(reserva);
            cliente.agregarReserva(reserva);

            return reserva;

        } catch (RuntimeException ex) {

            servicio.restaurarCupos(cantidadPersonas);
            throw ex;

        }

    }

    /**
     * Agrega una reserva cargada desde un archivo.
     *
     * @param cliente Cliente propietario.
     * @param reserva Reserva cargada.
     */
    public void agregarReservaCargada(Cliente cliente, Reserva reserva) {

        if (cliente == null) {

            throw new IllegalArgumentException("El cliente no puede ser nulo.");

        }

        validarReserva(reserva);

        cliente.agregarReserva(reserva);
        reservas.add(reserva);

    }

    /**
     * Agrega una reserva.
     *
     * @param reserva Reserva.
     */
    public void agregarReserva(Reserva reserva) {

        validarReserva(reserva);
        reserva.setCodigo(siguienteCodigo++);
        reservas.add(reserva);

    }

    /**
     * Busca una reserva por código.
     *
     * @param codigo Código.
     * @return Reserva encontrada o null.
     */
    public Reserva buscarReserva(int codigo) {

        validarCodigo(codigo);

        for (Reserva reserva : reservas) {

            if (reserva.getCodigo() == codigo) {

                return reserva;

            }

        }

        return null;

    }

    /**
     * Elimina una reserva.
     */
    public boolean eliminarReserva(Cliente cliente, Reserva reserva) {

        if (cliente == null || reserva == null) {

            throw new IllegalArgumentException("El cliente y la reserva son obligatorios.");

        }

        if (!reservas.contains(reserva) || !cliente.getReservas().contains(reserva)) {

            return false;

        }

        reserva.getServicio().restaurarCupos(reserva.getCantidadPersonas());
        cliente.eliminarReserva(reserva);
        reservas.remove(reserva);

        return true;

    }

    /**
     * Elimina todas las reservas de un cliente.
     */
    public void eliminarReservasCliente(Cliente cliente) {

        if (cliente == null) {

            throw new IllegalArgumentException("El cliente no puede ser nulo.");

        }

        for (Reserva reserva : new ArrayList<>(cliente.getReservas())) {

            eliminarReserva(cliente, reserva);

        }

    }

    /**
     * Elimina una reserva por código.
     */
    public boolean eliminarReserva(int codigo) {

        Reserva reserva = buscarReserva(codigo);

        return reserva != null && reservas.remove(reserva);

    }

    /**
     * Obtiene la cantidad de reservas.
     */
    public int obtenerCantidadReservas() {

        return reservas.size();

    }

    /**
     * Indica si el gestor está vacío.
     */
    public boolean estaVacio() {

        return reservas.isEmpty();

    }

    /**
     * Agrega una reserva cargada desde un archivo.
     *
     * @param reserva Reserva cargada.
     */
    public void agregarReservaCargada(Reserva reserva) {

        if (reserva == null) {

            throw new IllegalArgumentException("La reserva no puede ser nula.");

        }

        reservas.add(reserva);

    }

    // Métodos privados

    /**
     * Valida una reserva.
     *
     * @param reserva Reserva.
     */
    private void validarReserva(Reserva reserva) {

        if (reserva == null) {

            throw new IllegalArgumentException("La reserva no puede ser nula.");

        }

    }

    /**
     * Valida un código.
     *
     * @param codigo Código.
     */
    private void validarCodigo(int codigo) {

        if (codigo <= 0) {

            throw new IllegalArgumentException("El código debe ser mayor que cero.");

        }

    }

}
