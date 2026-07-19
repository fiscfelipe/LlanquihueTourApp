package model;

import util.CorreoInvalidoException;

/**
 * Representa el correo de una persona.
 * 
 * Esta clase valida y normaliza el formato del correo antes de almacenarlo.
 */

public class Correo {

    // Atributos

    private String correo;

    /**
     * Constructor de la clase Correo.
     *
     * @param correo Correo electrónico a almacenar.
     * @throws CorreoInvalidoException Si el correo no tiene un formato válido.
     */
    public Correo(String correo) throws CorreoInvalidoException {
        setCorreo(correo);
    }

    // Setters

    /**
     * Establece el correo electrónico después de normalizarlo y validar su
     * formato.
     *
     * @param correo Correo electrónico a almacenar.
     * @throws CorreoInvalidoException Si el correo es nulo o no tiene un formato válido.
     */
    public void setCorreo(String correo) throws CorreoInvalidoException {

        if (correo == null) {
            throw new CorreoInvalidoException("El correo no puede ser nulo.");
        }

        String correoNormalizado = normalizarCorreo(correo);

        if (!validarFormato(correoNormalizado)) {
            throw new CorreoInvalidoException(
                    "El correo ingresado no tiene un formato válido.");
        }

        this.correo = correoNormalizado;
    }

    // Getters
    
    /**
     * Obtiene el correo electrónico almacenado.
     *
     * @return Correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    // Métodos Públicos
    
    /**
     * Devuelve el correo electrónico en formato de texto.
     *
     * @return Correo electrónico.
     */
    @Override
    public String toString() {
        return correo;
    }

    // Métodos privados

    /**
     * Normaliza el correo eliminando espacios al inicio y al final.
     *
     * @param correo Correo ingresado.
     * @return Correo normalizado.
     */
    private String normalizarCorreo(String correo) {
        return correo.trim().toLowerCase();
    }

    /**
     * Verifica que el correo tenga un formato válido.
     *
     * @param correo Correo a validar.
     * @return true si el formato es válido; false en caso contrario.
     */
    private boolean validarFormato(String correo) {
        return correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

}