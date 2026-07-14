package model;

/**
 * Representa la dirección de un lugar
 * 
 */
public class Direccion {

    //Atributos
    private String calle;      
    private int numero;         
    private String ciudad;      
    private String region;      

    /**
     * Constructor de la clase Dirección
     * @param calle         Calle donde se ubica el lugar
     * @param numero        Número del lugar
     * @param ciudad        Ciudad donde está el lugar
     * @param region        Región en la que se encuentra el lugar
     */
    public Direccion(String calle, int numero, String ciudad, String region) {
        
        //Validaciones
        if (calle == null || calle.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'calle' no puede ser vacío.");
        }

        if (numero <= 0) {
            throw new IllegalArgumentException("El numero de direccion debe ser mayor a cero.");
        }

        if (ciudad == null || ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'ciudad' no puede ser vacío.");
        }

        if (region == null || region.trim().isEmpty()) {
            throw new IllegalArgumentException("La campo 'región' no puede ser vacío.");
        }

        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.region = region;
    }
    //Setters
    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    //Getters
    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getRegion() {
        return region;
    }

    //Métodos
    @Override
    public String toString() {
        return "#" + numero + " " + calle + ", " + ciudad + ", Región de " + region;
    }
}