package data;

import java.util.ArrayList;

import model.Servicio;
import model.ServicioAlojamiento;
import model.ServicioTransporte;
import util.RegistroDuplicadoException;

/**
 * Gestiona el registro de servicios.
 */
public class GestorServicios {

    // Atributos

    private final ArrayList<Servicio> servicios;

    // Constructor

    /**
     * Construye un gestor de servicios.
     */
    public GestorServicios() {

        servicios = new ArrayList<>();

    }

    // Setters

    // Getters

    /**
     * Obtiene los servicios registrados.
     *
     * @return Copia de la lista de servicios.
     */
    public ArrayList<Servicio> getServicios() {

        return new ArrayList<>(servicios);

    }

    /**
     * Obtiene los servicios de transporte.
     *
     * @return Lista de servicios de transporte.
     */
    public ArrayList<ServicioTransporte> getServiciosTransporte() {

        ArrayList<ServicioTransporte> lista = new ArrayList<>();

        for (Servicio servicio : servicios) {

            if (servicio instanceof ServicioTransporte transporte) {

                lista.add(transporte);

            }

        }

        return lista;

    }

    /**
     * Obtiene los servicios de alojamiento.
     *
     * @return Lista de servicios de alojamiento.
     */
    public ArrayList<ServicioAlojamiento> getServiciosAlojamiento() {

        ArrayList<ServicioAlojamiento> lista = new ArrayList<>();

        for (Servicio servicio : servicios) {

            if (servicio instanceof ServicioAlojamiento alojamiento) {

                lista.add(alojamiento);

            }

        }

        return lista;

    }

    // Métodos públicos

    /**
     * Agrega un servicio.
     *
     * @param servicio Servicio a registrar.
     * @throws RegistroDuplicadoException Si el servicio ya existe.
     */
    public void agregarServicio(Servicio servicio) throws RegistroDuplicadoException {

        validarServicio(servicio);

        if (buscarServicio(servicio.obtenerIdentificador()) != null) {

            throw new RegistroDuplicadoException(
                    "Ya existe un servicio registrado con ese identificador.");

        }

        servicios.add(servicio);

    }

    /**
     * Busca un servicio por identificador.
     *
     * @param identificador Identificador.
     * @return Servicio encontrado o {@code null}.
     */
    public Servicio buscarServicio(String identificador) {

        String id = validarIdentificador(identificador);

        for (Servicio servicio : servicios) {

            if (normalizarIdentificador(servicio.obtenerIdentificador()).equals(id)) {

                return servicio;

            }

        }

        return null;

    }

    /**
     * Elimina un servicio.
     *
     * @param identificador Identificador del servicio.
     * @return {@code true} si fue eliminado.
     */
    public boolean eliminarServicio(String identificador) {

        Servicio servicio = buscarServicio(identificador);

        return servicio != null && servicios.remove(servicio);

    }

    /**
     * Obtiene la cantidad de servicios.
     *
     * @return Cantidad de servicios.
     */
    public int obtenerCantidadServicios() {

        return servicios.size();

    }

    /**
     * Indica si el gestor está vacío.
     *
     * @return {@code true} si no existen servicios.
     */
    public boolean estaVacio() {

        return servicios.isEmpty();

    }

    // Métodos privados

    /**
     * Valida un servicio.
     *
     * @param servicio Servicio.
     */
    private void validarServicio(Servicio servicio) {

        if (servicio == null) {

            throw new IllegalArgumentException("El servicio no puede ser nulo.");

        }

        validarIdentificador(servicio.obtenerIdentificador());

    }

    /**
     * Valida un identificador.
     *
     * @param identificador Identificador.
     * @return Identificador normalizado.
     */
    private String validarIdentificador(String identificador) {

        if (identificador == null) {

            throw new IllegalArgumentException("El identificador no puede ser nulo.");

        }

        String id = normalizarIdentificador(identificador);

        if (id.isEmpty()) {

            throw new IllegalArgumentException("El identificador no puede estar vacío.");

        }

        return id;

    }

    /**
     * Normaliza un identificador.
     *
     * @param identificador Identificador.
     * @return Identificador normalizado.
     */
    private String normalizarIdentificador(String identificador) {

        return identificador.trim().toUpperCase();

    }

}
