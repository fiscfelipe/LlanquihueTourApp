package model;

import util.CorreoInvalidoException;
import util.RutInvalidoException;
import util.TelefonoInvalidoException;

/**
 * Representa un guía turístico.
 */
public class GuiaTuristico extends Persona {

    // Atributos
    private String especialidad;
    private String idiomas;
    private int aniosExperiencia;

    /**
     * Constructor de la clase GuiaTuristico.
     * @param nombre Nombre del guía.
     * @param rut Rut del guía.
     * @param correo Correo del guía.
     * @param telefono Teléfono del guía.
     * @param direccion Dirección del guía.
     * @param especialidad Especialidad del guía.
     * @param idiomas Idiomas que domina el guía.
     * @param aniosExperiencia Años de experiencia del guía.
     * @throws RutInvalidoException Si el RUT es inválido.
     * @throws CorreoInvalidoException Si el correo es inválido.
     * @throws TelefonoInvalidoException Si el teléfono es inválido.
     */
    public GuiaTuristico(String nombre, Rut rut, Correo correo, Telefono telefono, Direccion direccion, String especialidad, String idiomas, int aniosExperiencia) throws RutInvalidoException, CorreoInvalidoException, TelefonoInvalidoException {

        super(nombre, rut, correo, telefono, direccion);

        setEspecialidad(especialidad);
        setIdiomas(idiomas);
        setAniosExperiencia(aniosExperiencia);
    }

    // Setters

    /**
     * Establece la especialidad del guía.
     * @param especialidad Especialidad del guía.
     */
    public void setEspecialidad(String especialidad) {

        validarEspecialidad(especialidad);

        this.especialidad = especialidad.trim();
    }

    /**
     * Establece los idiomas del guía.
     * @param idiomas Idiomas del guía.
     */
    public void setIdiomas(String idiomas) {

        validarIdiomas(idiomas);

        this.idiomas = idiomas.trim();
    }

    /**
     * Establece los años de experiencia del guía.
     * @param aniosExperiencia Años de experiencia del guía.
     */
    public void setAniosExperiencia(int aniosExperiencia) {

        validarAniosExperiencia(aniosExperiencia);

        this.aniosExperiencia = aniosExperiencia;
    }

    // Getters

    /**
     * Obtiene la especialidad del guía.
     * @return Especialidad del guía.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Obtiene los idiomas del guía.
     * @return Idiomas del guía.
     */
    public String getIdiomas() {
        return idiomas;
    }

    /**
     * Obtiene los años de experiencia del guía.
     * @return Años de experiencia del guía.
     */
    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    // Métodos privados

    /**
     * Valida la especialidad del guía.
     * @param especialidad Especialidad a validar.
     */
    private void validarEspecialidad(String especialidad) {

        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }

    }

    /**
     * Valida los idiomas del guía.
     * @param idiomas Idiomas a validar.
     */
    private void validarIdiomas(String idiomas) {

        if (idiomas == null || idiomas.trim().isEmpty()) {
            throw new IllegalArgumentException("Los idiomas no pueden estar vacíos.");
        }

    }

    /**
     * Valida los años de experiencia del guía.
     * @param aniosExperiencia Años de experiencia a validar.
     */
    private void validarAniosExperiencia(int aniosExperiencia) {

        if (aniosExperiencia < 0) {
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos.");
        }

    }

    // Métodos públicos

    /**
     * Devuelve una representación en texto del guía turístico.
     * @return Información del guía turístico.
     */
    @Override
    public String toString() {

        return super.toString()
                + "\nEspecialidad: " + especialidad
                + "\nIdiomas: " + idiomas
                + "\nAños de experiencia: " + aniosExperiencia;
    }

    @Override
    public String obtenerIdentificador() {
        return getRut().toString();
    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== GUÍA TURÍSTICO ==========\n\n");

        sb.append("Nombre: ").append(getNombre()).append("\n");
        sb.append("RUT: ").append(getRut()).append("\n");
        sb.append("Correo: ").append(getCorreo()).append("\n");
        sb.append("Teléfono: ").append(getTelefono()).append("\n");
        sb.append("Especialidad: ").append(getEspecialidad()).append("\n");
        sb.append("Idiomas: ").append(getIdiomas()).append("\n");
        sb.append("Años de experiencia: ").append(getAniosExperiencia());

        return sb.toString();
    }

}