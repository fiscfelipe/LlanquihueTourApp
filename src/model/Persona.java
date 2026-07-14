package model;
/**
 * Clase que representa a una persona viculada a LlanquihueTour
 * 
 * esta clase utuliza composición, ya que una Persona tiene un rut, un correo y una dirección
 */
public abstract class Persona implements Registrable {

    // Atributos
    private String nombre;
    private Rut rut;
    private Correo correo;
    private Direccion direccion;
    
    /**
     * Constructor de la clase Persona
     * 
     * @param nombre            Nombre de la Persona
     * @param rut               RUT de la Persona
     * @param correo            Correo electrónico de la Persona
     * @param direccion         Dirección del domicilio de la Persona
     */
    public Persona(String nombre, Rut rut, Correo correo, Direccion direccion) {
        
        //Validación
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }

        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
        this.direccion = direccion;
    }
    
    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(Rut rut) {
        this.rut = rut;
    }
        
    public void setCorreo(Correo correo) {
        this.correo = correo;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public Rut getRut() {
        return rut;
    }

    public Correo getCorreo() {
        return correo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    //Métodos
    /**
     * Sobrescribe el método toString() por defecto
     * @return La información de la Persona
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nRut: " + rut +
                "\nCorreo: " + correo +
                "\nDireccion: " + direccion;
    }
    
    /**
     * Sobrescribe el método mostrarResumen con una base común de todas las subclases
     * @return Nombre, Rut, correo y direccion
     */
    @Override
    public String mostrarResumen() {
        return "Nombre: " + nombre +
               "\nRUT: " + rut +
               "\nCorreo: " + correo +
               "\nDirección: " + direccion;
    }
}