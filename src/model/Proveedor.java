package model;

import util.CorreoInvalidoException;
import util.RutInvalidoException;
import util.TelefonoInvalidoException;

/**
 * Representa un proveedor.
 */
public class Proveedor extends Persona {

    // Atributos
    private String cargo;

    /**
     * Constructor de la clase Proveedor.
     * @param nombre Nombre del proveedor.
     * @param rut Rut del proveedor.
     * @param correo Correo del proveedor.
     * @param telefono Teléfono del proveedor.
     * @param direccion Dirección del proveedor.
     * @param cargo Cargo del proveedor.
     * @throws RutInvalidoException Si el RUT es inválido.
     * @throws CorreoInvalidoException Si el correo es inválido.
     * @throws TelefonoInvalidoException Si el teléfono es inválido.
     */
    public Proveedor(String nombre, Rut rut, Correo correo, Telefono telefono, Direccion direccion, String cargo) throws RutInvalidoException, CorreoInvalidoException, TelefonoInvalidoException {

        super(nombre, rut, correo, telefono, direccion);

        setCargo(cargo);
    }

    // Setters

    /**
     * Establece el cargo del proveedor.
     * @param cargo Cargo del proveedor.
     */
    public void setCargo(String cargo) {

        validarCargo(cargo);

        this.cargo = cargo.trim();
    }

    // Getters

    /**
     * Obtiene el cargo del proveedor.
     * @return Cargo del proveedor.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Valida el cargo del proveedor.
     * @param cargo Cargo a validar.
     */
    private void validarCargo(String cargo) {

        if (cargo == null || cargo.trim().isEmpty()) {
            throw new IllegalArgumentException("El cargo no puede estar vacío.");
        }

    }

    // Métodos Públicos
    
    /**
     * Devuelve una representación en texto del proveedor.
     * @return Información del proveedor.
     */
    @Override
    public String toString() {

        return super.toString()
                + "\nCargo: " + cargo;
    }

    @Override
    public String obtenerIdentificador() {
        return getRut().toString();
    }

    @Override
    public String mostrarDatos() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== PROVEEDOR ==========\n\n");

        sb.append("Nombre: ").append(getNombre()).append("\n");
        sb.append("RUT: ").append(getRut()).append("\n");
        sb.append("Correo: ").append(getCorreo()).append("\n");
        sb.append("Teléfono: ").append(getTelefono()).append("\n");
        sb.append("Dirección: ").append(getDireccion()).append("\n");
        sb.append("Cargo: ").append(getCargo());

        return sb.toString();
    }
}