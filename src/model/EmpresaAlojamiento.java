package model;

import java.util.ArrayList;
import util.CorreoInvalidoException;

/**
 * Representa una empresa de alojamiento.
 */
public class EmpresaAlojamiento extends EmpresaExterna {

    // Atributos
    private ArrayList<Alojamiento> alojamientos;

    /**
     * Constructor de la clase EmpresaAlojamiento.
     * @param nombre Nombre de la empresa.
     * @param correo Correo de la empresa.
     * @param proveedor Proveedor responsable.
     * @throws CorreoInvalidoException Si el correo es inválido.
     */
    public EmpresaAlojamiento(String nombre, Correo correo, Proveedor proveedor) throws CorreoInvalidoException {

        super(nombre, correo, proveedor);

        alojamientos = new ArrayList<>();
    }

    // Setters

    /**
     * Establece los tipos de alojamiento de la empresa.
     * @param alojamientos Lista de tipos de alojamiento.
     */
    public void setAlojamientos(ArrayList<Alojamiento> alojamientos) {

        if (alojamientos == null) {
            throw new IllegalArgumentException("La lista de tipos de alojamiento no puede ser nula.");
        }

        this.alojamientos.clear();
        this.alojamientos.addAll(alojamientos);
    }

    // Getters

    /**
     * Obtiene los tipos de alojamiento de la empresa.
     * @return Copia de la lista de tipos de alojamiento.
     */
    public ArrayList<Alojamiento> getAlojamientos() {
        return new ArrayList<>(alojamientos);
    }


    // Métodos públicos

    /**
     * Agrega un tipo de alojamiento a la empresa.
     * @param tipoAlojamiento Tipo de alojamiento que se desea agregar.
     */
    public void agregarTipoAlojamiento(Alojamiento tipoAlojamiento) {

        if (tipoAlojamiento == null) {
            throw new IllegalArgumentException("El tipo de alojamiento no puede ser nulo.");
        }

        alojamientos.add(tipoAlojamiento);
    }

    /**
     * Elimina un tipo de alojamiento de la empresa.
     * @param tipoAlojamiento Tipo de alojamiento que se desea eliminar.
     */
    public void eliminarTipoAlojamiento(Alojamiento tipoAlojamiento) {

        if (tipoAlojamiento == null) {
            throw new IllegalArgumentException("El tipo de alojamiento no puede ser nulo.");
        }

        alojamientos.remove(tipoAlojamiento);
    }

    /**
     * Devuelve una representación en texto de la empresa de alojamiento.
     * @return Información de la empresa.
     */
    @Override
    public String toString() {

        return super.toString()
                + "\nTipos de alojamiento registrados: " + alojamientos.size();
    }

    @Override
    public String obtenerIdentificador() {
        return getNombre();
    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== EMPRESA DE ALOJAMIENTO ==========\n\n");

        sb.append("Nombre: ").append(getNombre()).append("\n");
        sb.append("Correo: ").append(getCorreo()).append("\n");
        sb.append("Proveedor responsable: ")
                .append(getProveedor().getNombre()).append("\n");
        sb.append("Alojamientos registrados: ")
                .append(getAlojamientos().size());

        return sb.toString();
    }

}