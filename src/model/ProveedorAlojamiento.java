package model;

/**
 * Clase que representa a una Persona que es un Proveedor de Alojamiento.
 *
 * Esta clase hereda de Persona.
 * Esta clase utiliza composición, ya que administra un alojamiento.
 */
public class ProveedorAlojamiento extends Persona {

    private Alojamiento alojamiento;

    /**
     * 
     * @param nombre            Nombre del proveedor de alojamiento
     * @param rut               Rut del proveedor de alojamiento
     * @param correo            Correo electrónico del proveedor de alojamiento
     * @param direccion         Dirección del proveedor de alojamiento
     * @param alojamiento       Alojamiento que administra
     */
    public ProveedorAlojamiento(String nombre, Rut rut, Correo correo, Direccion direccion, Alojamiento alojamiento) {

        super(nombre, rut, correo, direccion);
        this.alojamiento = alojamiento;
    }
    
    //Setters
    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }
    
    //Getters
    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    //Métodos
    @Override
    public String toString() {
        return super.toString() +
                "\n" + alojamiento;
    }
    
    /**
     * Método que sobre escribe mostrarResumen y agrega la informacion pertinente a ProveedorAlojamiento
     * @return datos heredados de persona y los datos de alojamiento
     */
    @Override
    public String mostrarResumen() {

        return super.mostrarResumen() +
                "\n\nAlojamiento:\n" +
                alojamiento;
    }
    
}
