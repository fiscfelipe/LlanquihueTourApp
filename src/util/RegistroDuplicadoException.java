package util;

/**
 * Excepción lanzada cuando se intenta registrar una entidad que ya existe en el sistema.
 */
public class RegistroDuplicadoException extends Exception {

    /**
     * Constructor de la excepción.
     *
     * @param mensaje Mensaje descriptivo del error.
     */
    public RegistroDuplicadoException(String mensaje) {
        super(mensaje);
    }

}