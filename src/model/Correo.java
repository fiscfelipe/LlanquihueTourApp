package model;

import util.CorreoInvalidoException;

/**
     * Representa el correo electrónico de una Persona
     * 
     * Esta clase valida que el correo tenga un formato correcto antes de almacenarlo
     */
public class Correo {

    //Atributos
    private String direccionDeCorreo;

    /**
     * 
     * @param direccionDeCorreo             Direccion de correo ingresada por el usuario
     * @throws CorreoInvalidoException      Si el formato del correo no es válido
     */
    public Correo(String direccionDeCorreo) throws CorreoInvalidoException {
        String correoLimpio = direccionDeCorreo.trim();

        if (!validarFormato(correoLimpio)) {
            throw new CorreoInvalidoException("El correo ingresado no es valido.");
        }

        this.direccionDeCorreo = correoLimpio;
    }

    //Geetters
    public String getDireccionDeCorreo() {
        return direccionDeCorreo;
    }

    //Métodos
    
    /**
     * Valida que el correo tenga el formato correcto
     * Ejemplo valido   micorreo@chilemail.cl
     * @param correo    Correo que se desea validar
     * @return          true si el formato es correcto, false si no lo es
     */
    private boolean validarFormato(String correo) {
        return correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    @Override
    public String toString() {
        return direccionDeCorreo;
    }
}