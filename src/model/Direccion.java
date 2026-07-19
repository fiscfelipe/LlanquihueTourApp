package model;

/**
 * Representa una dirección válida dentro del sistema.
 * 
 * La clase se encarga de validar los datos básicos de una dirección antes de almacenarlos.
 */
public class Direccion {

    // Atributos

    private String calle;
    private int numero;
    private String ciudad;
    private String region;

    /**
     * Constructor de la clase Direccion.
     *
     * @param calle  Nombre de la calle.
     * @param numero Número de la dirección.
     * @param ciudad Ciudad donde se ubica la dirección.
     * @param region Región donde se ubica la dirección.
     * @throws IllegalArgumentException Si alguno de los datos es inválido.
     */
    public Direccion(String calle, int numero, String ciudad, String region) {

        setCalle(calle);
        setNumero(numero);
        setCiudad(ciudad);
        setRegion(region);

    }

    // Setters

    /**
     * Establece el nombre de la calle.
     *
     * @param calle Nombre de la calle.
     * @throws IllegalArgumentException Si la calle es nula o está vacía.
     */
    public void setCalle(String calle) {
        this.calle = validarTexto(calle, "La calle");
    }

    /**
     * Establece el número de la dirección.
     *
     * @param numero Número de la dirección.
     * @throws IllegalArgumentException Si el número es menor o igual a cero.
     */
    public void setNumero(int numero) {
        this.numero = validarNumero(numero);
    }

    /**
     * Establece la ciudad de la dirección.
     *
     * @param ciudad Ciudad de la dirección.
     * @throws IllegalArgumentException Si la ciudad es nula o está vacía.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = validarTexto(ciudad, "La ciudad");
    }

    /**
     * Establece la región de la dirección.
     *
     * @param region Región de la dirección.
     * @throws IllegalArgumentException Si la región es nula o está vacía.
     */
    public void setRegion(String region) {
        this.region = validarTexto(region, "La región");
    }

    // Getters

    /**
     * Obtiene el nombre de la calle.
     *
     * @return Nombre de la calle.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Obtiene el número de la dirección.
     *
     * @return Número de la dirección.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtiene la ciudad de la dirección.
     *
     * @return Ciudad de la dirección.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Obtiene la región de la dirección.
     *
     * @return Región de la dirección.
     */
    public String getRegion() {
        return region;
    }

    // Métodos públicos

    /**
     * Devuelve la dirección en formato de texto.
     *
     * @return Dirección completa.
     */
    @Override
    public String toString() {
        return calle + " " + numero + ", " + ciudad + ", " + region;
    }

    // Métodos privados

    /**
     * Valida que un texto no sea nulo ni esté vacío y devuelve su versión normalizada.
     *
     * @param texto        Texto a validar.
     * @param nombreCampo  Nombre del campo para el mensaje de error.
     * @return Texto normalizado.
     * @throws IllegalArgumentException Si el texto es nulo o está vacío.
     */
    private String validarTexto(String texto, String nombreCampo) {

        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(nombreCampo + " no puede ser nulo ni estar vacío.");
        }

        return texto.trim();

    }
    
     /**
     * Valida que el número de la dirección sea mayor que cero.
     *
     * @param numero Número de la dirección.
     * @return Número validado.
     * @throws IllegalArgumentException Si el número es menor o igual a cero.
     */
    private int validarNumero(int numero) {

        if (numero <= 0) {
            throw new IllegalArgumentException("El número debe ser mayor que cero.");
        }

        return numero;

    }

}