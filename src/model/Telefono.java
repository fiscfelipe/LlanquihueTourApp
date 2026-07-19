package model;

import util.TelefonoInvalidoException;

/**
 * Representa un teléfono celular internacional.
 */
public class Telefono {

    // Atributos
    private String telefono;

    /**
     * Constructor de la clase Telefono.
     *
     * @param telefono Número de teléfono en formato internacional.
     * @throws TelefonoInvalidoException Si el teléfono no cumple con el formato esperado.
     */
    public Telefono(String telefono) throws TelefonoInvalidoException {
        setTelefono(telefono);
    }

    // Setter
    public void setTelefono(String telefono) throws TelefonoInvalidoException {
        this.telefono = validarTelefono(telefono);
    }

    // Getter
    public String getTelefono() {
        return telefono;
    }

    //Métodos públicos
    @Override
    public String toString() {
        return telefono;
    }

    // Métodos privados
    private String validarTelefono(String telefono) throws TelefonoInvalidoException {

        if (telefono == null) {
            throw new TelefonoInvalidoException("El teléfono no puede ser nulo.");
        }

        telefono = telefono.trim();

        if (!telefono.matches("^\\+[0-9]{8,15}$")) {
            throw new TelefonoInvalidoException("El teléfono debe comenzar con '+' y contener entre 8 y 15 dígitos.");
        }

        return telefono;
    }

}