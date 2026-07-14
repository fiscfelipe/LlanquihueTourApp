package util;

/**
 * Excepción personalizada para manejar errores relacionados con el formato del correo
 */
public class CorreoInvalidoException extends Exception {

    /**
     * Constructor de la excepción
     * 
     * @param mensaje mensaje que explica el error ocurrido 
     */
    public CorreoInvalidoException(String mensaje) {
        super(mensaje);
    }
}