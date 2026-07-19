package model;

import util.CorreoInvalidoException;

/**
 * Representa una empresa externa.
 */
public abstract class EmpresaExterna implements Registrable{

    // Atributos
    private String nombre;
    private Correo correo;
    private Proveedor proveedor;

    /**
     * Constructor de la clase EmpresaExterna.
     * @param nombre Nombre de la empresa.
     * @param correo Correo de la empresa.
     * @param proveedor Proveedor responsable de la empresa.
     * @throws CorreoInvalidoException Si el correo es inválido.
     */
    public EmpresaExterna(String nombre, Correo correo, Proveedor proveedor) throws CorreoInvalidoException {

        setNombre(nombre);
        setCorreo(correo);
        setProveedor(proveedor);
    }

    // Setters

    /**
     * Establece el nombre de la empresa.
     * @param nombre Nombre de la empresa.
     */
    public void setNombre(String nombre) {

        validarNombre(nombre);

        this.nombre = nombre.trim();
    }

    /**
     * Establece el correo de la empresa.
     * @param correo Correo de la empresa.
     * @throws CorreoInvalidoException Si el correo es inválido.
     */
    public void setCorreo(Correo correo)
            throws CorreoInvalidoException {

        if (correo == null) {
            throw new CorreoInvalidoException("El correo no puede ser nulo.");
        }

        this.correo = correo;
    }

    /**
     * Establece el proveedor responsable de la empresa.
     * @param proveedor Proveedor responsable.
     */
    public void setProveedor(Proveedor proveedor) {

        if (proveedor == null) {
            throw new IllegalArgumentException("El proveedor no puede ser nulo.");
        }

        this.proveedor = proveedor;
    }

    // Getters

    /**
     * Obtiene el nombre de la empresa.
     * @return Nombre de la empresa.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el correo de la empresa.
     * @return Correo de la empresa.
     */
    public Correo getCorreo() {
        return correo;
    }

    /**
     * Obtiene el proveedor responsable.
     * @return Proveedor responsable.
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    //Métodos privados
    
    /**
     * Valida el nombre de la empresa.
     * @param nombre Nombre a validar.
     */
    private void validarNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

    }

    // Métodos públicos
    
    /**
     * Devuelve una representación en texto de la empresa.
     * @return Información de la empresa.
     */
    @Override
    public String toString() {

        return "Nombre: " + nombre
                + "\nCorreo: " + correo
                + "\nProveedor:\n" + proveedor;
    }
    
    //Métodos interfaz Registrable
    @Override
    public abstract String mostrarDatos();

    @Override
    public abstract String obtenerIdentificador();

}