package model;

import util.RutInvalidoException;
/**
 * Representa el RUT de una persona
 * 
 * Esta clase valida que el RUT tenga un formato correcto antes de almacenarlo
 */
public class Rut {

    //Atributos
    private String numero;

    /**
     * Constructor de la clase Rut
     * 
     * @param numero                    Rut ingresado por el usuario
     * @throws RutInvalidoException     Si el formato del RUT no es válido
     */
    public Rut(String numero) throws RutInvalidoException {

        String rutLimpio = numero.replace(".", "").trim().toUpperCase();

        if (!validarFormato(rutLimpio)) {
            throw new RutInvalidoException("El RUT ingresado no tiene un formato valido.");
        }

        this.numero = rutLimpio;
    }

    //Getters
    public String getNumero() {
        return numero;
    }

    //Métodos

    /**
     * Valida que el RUT tenga un formato correcto
     * Ejemplo valido: 12345678-9 o 12345678-K
     * 
     * @param rut       RUT que desea validar
     * @return          true si el formato es correcto, false si no
     */
    private boolean validarFormato(String rut) {
        return rut.matches("[0-9]{7,8}-[0-9Kk]");
    }

    @Override
    public String toString() {
        return numero;
    }
}