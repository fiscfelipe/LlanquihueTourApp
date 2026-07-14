package model;

/**
 * Clase que representa una excursión cultural, hereda de ServicioTuristico
 */
public class ExcursionCultural extends ServicioTuristico {

    //Atributos
    private String lugarHistorico;

    /**
     * Constructor de la clase ExcursionCultural.
     * @param nombre            Nombre del servicio turístico.
     * @param duracionHoras     Duración en horas del servicio turístico.
     * @param lugarHistorico    Lugar histórico donde se realiza la excursión cultural.
     */
    public ExcursionCultural(String nombre, int duracionHoras, String lugarHistorico) {
        super(nombre, duracionHoras);
        this.lugarHistorico = lugarHistorico;
    }

    //Setters
    public void setLugarHistorico(String lugarHistorico) {
        this.lugarHistorico = lugarHistorico;
    }

    //Getters
    public String getLugarHistorico() {
        return lugarHistorico;
    }

    //Métodos
    @Override
    public String toString() {
        return super.toString() +
                "\nLugar historico: " + lugarHistorico;
    }

    /**
     * Sobrescribe el método mostrarResumen con la información de ExcursionCultural
     * @return Nombre del servicio, duración y lugar histórico
     */
    @Override
    public String mostrarResumen() {
        return super.mostrarResumen() +
                "\nLugar histórico: " + lugarHistorico;
    }
}