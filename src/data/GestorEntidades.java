package data;

/**
 * Coordina el acceso a los distintos gestores del sistema.
 */
public class GestorEntidades {

    // Atributos
    private final GestorPersonas gestorPersonas;
    private final GestorEmpresas gestorEmpresas;
    private final GestorServicios gestorServicios;
    private final GestorReservas gestorReservas;

    /**
     * Constructor de la clase GestorEntidades.
     */
    public GestorEntidades() {

        gestorPersonas = new GestorPersonas();
        gestorEmpresas = new GestorEmpresas();
        gestorServicios = new GestorServicios();
        gestorReservas = new GestorReservas();
    }

    /**
     * Obtiene el gestor de personas.
     *
     * @return Gestor de personas.
     */
    public GestorPersonas getGestorPersonas() {
        return gestorPersonas;
    }

    /**
     * Obtiene el gestor de empresas.
     *
     * @return Gestor de empresas.
     */
    public GestorEmpresas getGestorEmpresas() {
        return gestorEmpresas;
    }

    /**
     * Obtiene el gestor de servicios.
     *
     * @return Gestor de servicios.
     */
    public GestorServicios getGestorServicios() {
        return gestorServicios;
    }

    /**
     * Obtiene el gestor de reservas.
     *
     * @return Gestor de reservas.
     */
    public GestorReservas getGestorReservas() {
        return gestorReservas;
    }

}