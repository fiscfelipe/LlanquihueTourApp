package model;

import util.RutInvalidoException;

/**
 * Representa un RUT validado dentro del sistema.
 *
 * La clase se encarga de normalizar y validar el formato del RUT antes de almacenarlo.
 */
public class Rut {

    // Atributos

    private String rut;

    /**
     * Constructor de la clase Rut.
     *
     * @param rut RUT a almacenar.
     * @throws RutInvalidoException Si el RUT es nulo o no tiene un formato válido.
     */
    public Rut(String rut) throws RutInvalidoException {
        setRut(rut);
    }

    // Setters

    /**
     * Establece el RUT después de validarlo y normalizarlo.
     *
     * @param rut RUT a almacenar.
     * @throws RutInvalidoException Si el RUT es nulo o no tiene un formato válido.
     */
    public void setRut(String rut) throws RutInvalidoException {
        this.rut = validarRut(rut);
    }

    // Getters

    /**
     * Obtiene el RUT almacenado.
     *
     * @return RUT.
     */
    public String getRut() {
        return rut;
    }

    // Métodos públicos

    /**
     * Devuelve el RUT en formato de texto.
     *
     * @return RUT.
     */
    @Override
    public String toString() {
        return rut;
    }

    // Métodos privados

    /**
     * Valida y normaliza un RUT antes de almacenarlo.
     *
     * @param rut RUT a validar.
     * @return RUT validado y normalizado.
     * @throws RutInvalidoException Si el RUT es nulo o no tiene un formato válido.
     */
    private String validarRut(String rut) throws RutInvalidoException {

        if (rut == null) {
            throw new RutInvalidoException("El RUT no puede ser nulo.");
        }

        String rutNormalizado = normalizarRut(rut);

        if (!validarFormato(rutNormalizado)) {
            throw new RutInvalidoException("El RUT ingresado no tiene un formato válido.");
        }

        return rutNormalizado;

    }

    /**
     * Normaliza el RUT eliminando puntos, espacios y convirtiendo la letra verificadora a mayúscula.
     *
     * @param rut RUT ingresado.
     * @return RUT normalizado.
     */
    private String normalizarRut(String rut) {
        return rut.replace(".", "").trim().toUpperCase();
    }

    /**
     * Verifica que el RUT tenga un formato válido.
     *
     * @param rut RUT a validar.
     * @return true si el formato es válido; false en caso
     *         contrario.
     */
    private boolean validarFormato(String rut) {
        return rut.matches("[0-9]{7,8}-[0-9K]");
    }

}