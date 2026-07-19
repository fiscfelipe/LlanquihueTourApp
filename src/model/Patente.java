package model;

import util.PatenteInvalidaException;

/**
 * Representa la patente de un vehículo.
 */
public class Patente {

    // Atributos
    private String codigo;

    /**
     * Constructor de la clase Patente.
     * @param codigo Patente ingresada por el usuario.
     * @throws PatenteInvalidaException Si el formato de la patente no es válido.
     */
    public Patente(String codigo) throws PatenteInvalidaException {

        String patenteLimpia = codigo.trim().toUpperCase();

        if (!validarFormato(patenteLimpia)) {
            throw new PatenteInvalidaException("La patente ingresada no es válida.");
        }

        this.codigo = patenteLimpia;
    }

    // Getters

    /**
     * Obtiene el código de la patente.
     * @return Código de la patente.
     */
    public String getCodigo() {
        return codigo;
    }

    // Métodos privados

    /**
     * Valida que la patente tenga un formato correcto.
     * @param patente Patente que se desea validar.
     * @return true si el formato es correcto; false en caso contrario.
     */
    private boolean validarFormato(String patente) {
        return patente.matches("([A-Z]{4}\\d{2})|([A-Z]{2}\\d{4})");
    }

    // Métodos públicos

    /**
     * Devuelve una representación en texto de la patente.
     * @return Código de la patente.
     */
    @Override
    public String toString() {
        return codigo;
    }

}