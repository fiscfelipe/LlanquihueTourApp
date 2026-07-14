package model;

/**
 * Clase que representa un paseo lacustre, hereda de ServicioTuristico.
 */
public class PaseoLacustre extends ServicioTuristico {

    //Atributos
    private String tipoEmbarcacion;

    /**
     * Constructor de la clase PaseoLacustre
     * @param nombre            Nombre del servicio turístico.
     * @param duracionHoras     Duración del servicio turístico.
     * @param tipoEmbarcacion   Tipo de embarcación donde se realiza el paseo lacustre.
     */
    public PaseoLacustre(String nombre, int duracionHoras, String tipoEmbarcacion) {
        super(nombre, duracionHoras);
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    //Setters
    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    //Getters
    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    //Métodos
    @Override
    public String toString() {
        return super.toString() +
                "\nTipo de embarcacion: " + tipoEmbarcacion;
    }
    
    /**
     * Sobrescribe el método mostrarResumen con la información de PaseoLacustre
     * @return Nombre del servicio, duración y tipo de embarcación
     */
    @Override
    public String mostrarResumen() {
        return super.mostrarResumen() +
                "\nTipo de embarcación: " + tipoEmbarcacion;
    }
}