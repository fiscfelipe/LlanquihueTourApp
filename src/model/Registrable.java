package model;

/**
 * Define el comportamiento que deben implementar las entidades que pueden registrarse en el sistema.
 */
public interface Registrable {

    /**
     * Devuelve una representación de los datos de la entidad para ser mostrados en la aplicación.
     *
     * @return Información de la entidad.
     */
    String mostrarDatos();

    /**
     * Devuelve el identificador principal de la entidad.
     *
     * @return Identificador de la entidad.
     */
    String obtenerIdentificador();

}