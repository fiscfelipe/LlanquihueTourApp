package model;

import java.util.ArrayList;
import util.CorreoInvalidoException;

/**
 * Representa una empresa de transporte.
 */
public class EmpresaTransporte extends EmpresaExterna {

    // Atributos
    private ArrayList<Vehiculo> vehiculos;

    /**
     * Constructor de la clase EmpresaTransporte.
     * @param nombre Nombre de la empresa.
     * @param correo Correo de la empresa.
     * @param proveedor Proveedor responsable.
     * @throws CorreoInvalidoException Si el correo es inválido.
     */
    public EmpresaTransporte(String nombre, Correo correo, Proveedor proveedor) throws CorreoInvalidoException {

        super(nombre, correo, proveedor);

        vehiculos = new ArrayList<>();
    }

    // Setters

    /**
     * Establece los vehículos de la empresa.
     * @param vehiculos Lista de vehículos.
     */
    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {

        if (vehiculos == null) {
            throw new IllegalArgumentException("La lista de vehículos no puede ser nula.");
        }

        this.vehiculos.clear();
        this.vehiculos.addAll(vehiculos);
    }

    // Getters

    /**
     * Obtiene los vehículos de la empresa.
     * @return Copia de la lista de vehículos.
     */
    public ArrayList<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos);
    }

    // Métodos privados
    
    /**
     * Agrega un vehículo a la empresa.
     * @param vehiculo Vehículo que se desea agregar.
     */
    public void agregarVehiculo(Vehiculo vehiculo) {

        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo.");
        }

        vehiculos.add(vehiculo);
    }

    /**
     * Elimina un vehículo de la empresa.
     * @param vehiculo Vehículo que se desea eliminar.
     */
    public void eliminarVehiculo(Vehiculo vehiculo) {

        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo.");
        }

        vehiculos.remove(vehiculo);
    }

    // Métodos públicos
    
    /**
     * Devuelve una representación en texto de la empresa de transporte.
     * @return Información de la empresa.
     */
    @Override
    public String toString() {

        return super.toString()
                + "\nVehículos registrados: " + vehiculos.size();
    }

    @Override
    public String obtenerIdentificador() {
        return getNombre();
    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== EMPRESA DE TRANSPORTE ==========\n\n");

        sb.append("Nombre: ").append(getNombre()).append("\n");
        sb.append("Correo: ").append(getCorreo()).append("\n");
        sb.append("Proveedor Responsable: ").append(getProveedor().getNombre()).append("\n");
        sb.append("Vehículos Registrados: ").append(getVehiculos().size());

        return sb.toString();
    }
}