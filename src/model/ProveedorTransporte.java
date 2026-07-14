package model;

/**
 * Clase que representa una Persona que es Proveedor de Transporte para LlanquihueTour
 *
 * Esta clase hereda de la clase Persona.
 * Esta clase utiliza composición, ya que un proveedor registra un vehículo.
 */
public class ProveedorTransporte extends Persona {
    
    //Atributos propios
    private Vehiculo vehiculo;

    /**
     * 
     * @param nombre        Nombre del proveedor de transporte
     * @param rut           RUT del proveedor de transporte
     * @param correo        Correo del proveedor de transporte
     * @param direccion     Direccion del proveedor de transporte
     * @param vehiculo      Vehiculo registrado a nombre del proveedor de transporte
     */
    public ProveedorTransporte(String nombre, Rut rut, Correo correo, Direccion direccion, Vehiculo vehiculo) {

        super(nombre, rut, correo, direccion);
        this.vehiculo = vehiculo;
    }

    //Setters
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    //Getters
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    //Métodos
    @Override
    public String toString() {

        return super.toString() +
                "\n" + vehiculo;

    }

    /**
     * Método que sobre escribe mostrarResumen y agrega la informacion pertinente a ProveedorTransporte
     * @return datos heredados de persona y los datos del vehículo
     */    
    @Override
    public String mostrarResumen() {

        return super.mostrarResumen() +
                "\n" + vehiculo;
    }
    
}
