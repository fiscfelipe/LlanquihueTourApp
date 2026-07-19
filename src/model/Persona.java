package model;

import util.CorreoInvalidoException;
import util.RutInvalidoException;
import util.TelefonoInvalidoException;

/**
 * Representa una persona.
 */
public abstract class Persona implements Registrable {

    // Atributos
    private String nombre;
    private Rut rut;
    private Correo correo;
    private Telefono telefono;
    private Direccion direccion;

    /**
     * Constructor de la clase Persona.
     *
     * @param nombre Nombre de la persona.
     * @param rut Rut de la persona.
     * @param correo Correo de la persona.
     * @param telefono Teléfono de la persona.
     * @param direccion Dirección de la persona.
     */
    public Persona(String nombre, Rut rut, Correo correo, Telefono telefono, Direccion direccion) throws RutInvalidoException, CorreoInvalidoException, TelefonoInvalidoException {

        setNombre(nombre);
        setRut(rut);
        setCorreo(correo);
        setTelefono(telefono);
        setDireccion(direccion);
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = validarNombre(nombre);
    }

    public void setRut(Rut rut) {
        this.rut = validarRut(rut);
    }

    public void setCorreo(Correo correo) {
        this.correo = validarCorreo(correo);
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = validarTelefono(telefono);
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = validarDireccion(direccion);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public Rut getRut() {
        return rut;
    }

    public Correo getCorreo() {
        return correo;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    // Métodos publicos
    @Override
    public String toString() {
        return "Nombre: " + nombre
                + "\nRUT: " + rut
                + "\nCorreo: " + correo
                + "\nTeléfono: " + telefono
                + "\nDirección: " + direccion;
    }

    // Métodos privados
    private String validarNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        return nombre.trim();
    }

    private Rut validarRut(Rut rut) {

        if (rut == null) {
            throw new IllegalArgumentException("El RUT no puede ser nulo.");
        }

        return rut;
    }

    private Correo validarCorreo(Correo correo) {

        if (correo == null) {
            throw new IllegalArgumentException("El correo no puede ser nulo.");
        }

        return correo;
    }

    private Telefono validarTelefono(Telefono telefono) {

        if (telefono == null) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo.");
        }

        return telefono;
    }

    private Direccion validarDireccion(Direccion direccion) {

        if (direccion == null) {
            throw new IllegalArgumentException("La dirección no puede ser nula.");
        }

        return direccion;
    }

    
    //Métodos interfaz Registrable
    @Override
    public abstract String mostrarDatos();

    @Override
    public abstract String obtenerIdentificador();
}