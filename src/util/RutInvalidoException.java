package util;

/**
 * Excepción personalizada para manejar errores relacionados con el formato del RUT
 */
public class RutInvalidoException extends Exception {

    /**
     * Constructor de la excepción
     * 
     * @param mensaje mensaje que explica el error ocurrido 
     */
    public RutInvalidoException(String mensaje) {
        super(mensaje);
    }
}