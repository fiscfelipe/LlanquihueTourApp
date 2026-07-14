package model;

/**
 * Representa un alojamiento administrado por un proveedor.
 *
 * Esta clase utiliza composición, ya que un alojamiento posee una dirección.
 */
public class Alojamiento {

    // Atributos
    private String nombre;
    private int habitacionesDisponibles;
    private Direccion direccion;

    /**
     * Constructor de la clase Alojamiento.
     *
     * @param nombre                    Nombre del alojamiento.
     * @param habitacionesDisponibles   Cantidad de habitaciones disponibles.
     * @param direccion                 Dirección del alojamiento.
     */
    public Alojamiento(String nombre, int habitacionesDisponibles, Direccion direccion) {

        //Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del alojamiento no puede estar vacio.");
        }

        if (habitacionesDisponibles < 0) {
            throw new IllegalArgumentException("Las habitaciones disponibles deben ser mayores o iguales a cero.");
        }

        if (direccion == null) {
            throw new IllegalArgumentException("La direccion del alojamiento no puede ser nula.");
        }

        this.nombre = nombre;
        this.habitacionesDisponibles = habitacionesDisponibles;
        this.direccion = direccion;
    }

    //Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHabitacionesDisponibles(int habitacionesDisponibles) {
        this.habitacionesDisponibles = habitacionesDisponibles;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    //Getters

    public String getNombre() {
        return nombre;
    }

    public int getHabitacionesDisponibles() {
        return habitacionesDisponibles;
    }

    public Direccion getDireccion() {
        return direccion;
    }
    
    //Métodos
    
    @Override
    public String toString() {
        return "Nombre alojamiento: " + nombre +
                "\nHabitaciones disponibles: " + habitacionesDisponibles +
                "\nDireccion alojamiento: " + direccion;
    }
}
