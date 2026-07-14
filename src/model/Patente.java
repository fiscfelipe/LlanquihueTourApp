package model;

import util.PatenteInvalidaException;

/**
 * Representa la patente del vehículo del Proveedor de Transporte
 * 
 * Esta clase valida que la patente del vehículo tenga un formato correcto antes de almacenarlo
 */
public class Patente {

    //Atributo que almacena la patente
    private String codigo;

    /**
     * Constructor de la clase Patente
     * 
     * @param codigo                        Patente ingresada por el usuario   
     * @throws PatenteInvalidaException     si el formato de la patente no es válido
     */
    public Patente(String codigo) throws PatenteInvalidaException {

        String patenteLimpia = codigo.trim().toUpperCase();

        if (!validarFormato(patenteLimpia)) {
            throw new PatenteInvalidaException("La patente ingresada no es valida.");
        }

        this.codigo = patenteLimpia;
    }

    //Getters
    public String getCodigo() {
        return codigo;
    }

    //Métodos
    
    /**
     * Valida que la Patente tenga el formato correcto.
     * Ejemplo ABCD12 o AB1234
     * @param patente       Patente que se desea validar
     * @return              true si el formato es correcto, false si no lo es
     */
    private boolean validarFormato(String patente) {
        return patente.matches("([A-Z]{4}\\d{2})|([A-Z]{2}\\d{4})");
    }

    @Override
    public String toString() {
        return codigo;
    }
}