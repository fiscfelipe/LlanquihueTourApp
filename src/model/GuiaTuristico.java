package model;

/**
 * Clase que representa a un guía turístico de Llanquihue Tour.
 * Hereda de Persona.
 */
public class GuiaTuristico extends Persona {

    // Atributos
    private String especialidad;
    private String idiomas;
    private int aniosExperiencia;

    /**
     * Constructor de la clase GuiaTuristico.
     *
     * @param nombre                Nombre del guía.
     * @param rut                   RUT del guía.
     * @param correo                Correo electrónico del guía.
     * @param direccion             Dirección del guía.
     * @param especialidad          Especialidad del guía.
     * @param idiomas               Idiomas que domina.
     * @param aniosExperiencia      Años de experiencia.
     */
public GuiaTuristico(String nombre, Rut rut, Correo correo, Direccion direccion, String especialidad, String idiomas, int aniosExperiencia) {

    super(nombre, rut, correo, direccion);

    if (especialidad == null || especialidad.trim().isEmpty()) {
        throw new IllegalArgumentException("La especialidad no puede estar vacía.");
    }

    if (idiomas == null || idiomas.trim().isEmpty()) {
        throw new IllegalArgumentException("Los idiomas no pueden estar vacíos.");
    }

    if (aniosExperiencia < 0) {
        throw new IllegalArgumentException("Los años de experiencia deben ser mayores o iguales a cero.");
    }

    this.especialidad = especialidad;
    this.idiomas = idiomas;
    this.aniosExperiencia = aniosExperiencia;
}

    // Setters
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    // Getters
    public String getEspecialidad() {
        return especialidad;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }
    
    //Métodos
    @Override
    public String toString() {
        return "Especialidad: " + especialidad +
                "\nIdiomas: " + idiomas +
                "\nAños de experiencia: " + aniosExperiencia;
    }

    /**
     * Sobrescribe el método mostrarResumen con los datos propios de GuiaTuristico
     * @return Los datos comunes de persona y los atributos de guía turístico
     */
    @Override
    public String mostrarResumen() {
        return super.mostrarResumen() +
                "\nEspecialidad: " + especialidad +
                "\nIdiomas: " + idiomas +
                "\nAños de experiencia: " + aniosExperiencia;
    }
}