package model;

/**
 * Clase que representa una Ruta Gastronómica, esta clase hereda de Servicio Turístico.
 */
public class RutaGastronomica extends ServicioTuristico {
    
    //Atributos
    private int numeroDeParadas;

    /**
     * Constructor de la clase RutaGastronomica.
     * @param nombre            Nombre del servicio turístico.
     * @param duracionHoras     Duración en horas del servicio turístico.
     * @param numeroDeParadas   Número de paradas de la ruta gastronómica.
     */
    public RutaGastronomica(String nombre, int duracionHoras, int numeroDeParadas) {
        super(nombre, duracionHoras);
        this.numeroDeParadas = numeroDeParadas;
    }
    
    //Setters
    public void setNumeroDeParadas(int numeroDeParadas) {
        this.numeroDeParadas = numeroDeParadas;
    }

    //Getters
    public int getNumeroDeParadas() {
        return numeroDeParadas;
    }
    
    //Métodos
    @Override
    public String toString() {
        return super.toString() +
                "\nNumero de paradas: " + numeroDeParadas;
    }
    
    /**
     * Sobrescribe el método mostrarResumen con los datos propios de RutaGastronómica
     * @return Nombre del servicio, duración y numero de paradas
     */
    @Override
    public String mostrarResumen() {
        return super.mostrarResumen() +
                "\nNúmero de paradas: " + numeroDeParadas;
    }
}