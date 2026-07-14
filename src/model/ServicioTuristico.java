package model;

/**
 * Clase que representa un servicio turistico de Llanquihue Tour
 */
public abstract class ServicioTuristico implements Registrable {

    private String nombre;
    private int duracionHoras;

    /**
     * Constructor de la clase ServicioTuristico
     * @param nombre            Nombre del servicio turístico.
     * @param duracionHoras     Duración del servicio turístico.
     */
    public ServicioTuristico(String nombre, int duracionHoras) {
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    //Métodos
    @Override
    public String toString() {
        return "Nombre del servicio: " + nombre +
                "\nDuracion: " + duracionHoras + " horas";
    }
    
    /**
     * Sobrescribe el método mostrarResumen con una base común de todas las subclases
     * @return Nombre del servicio y duración
     */
    @Override
    public String mostrarResumen() {
        return "Nombre del servicio: " + nombre +
               "\nDuración: " + duracionHoras + " horas";
    }
}