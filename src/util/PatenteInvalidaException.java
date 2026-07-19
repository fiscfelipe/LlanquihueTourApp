package util;

/**
 * Excepción personalizada para manejar errores relacionados con el formato de la patente
 */
public class PatenteInvalidaException extends Exception {

    /**
     * Constructor de la excepción
     * 
     * @param mensaje mensaje que explica el error ocurrido 
     */
    public PatenteInvalidaException(String mensaje) {
        super(mensaje);
    }
}