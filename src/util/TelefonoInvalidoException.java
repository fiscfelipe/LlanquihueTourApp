package util;

/**
 * Excepción lanzada cuando un teléfono no cumple con el formato esperado.
 */
public class TelefonoInvalidoException extends Exception {

    /**
     * Constructor de la excepción.
     *
     * @param mensaje Mensaje descriptivo del error.
     */
    public TelefonoInvalidoException(String mensaje) {
        super(mensaje);
    }

}