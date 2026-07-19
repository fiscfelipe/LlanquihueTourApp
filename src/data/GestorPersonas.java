package data;

import java.util.ArrayList;

import model.Cliente;
import model.GuiaTuristico;
import model.Persona;
import model.Proveedor;
import util.RegistroDuplicadoException;

/**
 * Gestiona el registro de personas del sistema.
 *
 * Todas las personas se almacenan en una única colección utilizando
 * polimorfismo. Los métodos específicos filtran la colección mediante
 * instanceof.
 *
 */
public class GestorPersonas {

    // Atributos
    private final ArrayList<Persona> personas;

    /**
     * Constructor de la clase GestorPersonas.
     */
    public GestorPersonas() {

        personas = new ArrayList<>();

    }

    /**
     * Agrega una persona al registro.
     *
     * @param persona Persona que se desea registrar.
     * @throws RegistroDuplicadoException Si ya existe una persona con el mismo
     * identificador.
     */
    public void agregarPersona(Persona persona)
            throws RegistroDuplicadoException {

        validarPersona(persona);

        if (buscarPersona(persona.obtenerIdentificador()) != null) {

            throw new RegistroDuplicadoException(
                    "Ya existe una persona registrada con ese identificador.");

        }

        personas.add(persona);

    }

    /**
     * Busca una persona por su identificador.
     *
     * @param identificador Identificador de la persona.
     * @return Persona encontrada o null si no existe.
     */
    public Persona buscarPersona(String identificador) {

        String identificadorBuscado =
                validarIdentificador(identificador);

        for (Persona persona : personas) {

            if (normalizarIdentificador(persona.obtenerIdentificador())
                    .equals(identificadorBuscado)) {

                return persona;

            }

        }

        return null;

    }

    /**
     * Elimina una persona del registro.
     *
     * @param identificador Identificador de la persona.
     * @return true si fue eliminada; false en caso contrario.
     */
    public boolean eliminarPersona(String identificador) {

        Persona persona = buscarPersona(identificador);

        if (persona == null) {

            return false;

        }

        return personas.remove(persona);

    }

    /**
     * Obtiene todas las personas registradas.
     *
     * @return Lista de personas.
     */
    public ArrayList<Persona> getPersonas() {

        return new ArrayList<>(personas);

    }
        /**
     * Obtiene todos los clientes registrados.
     *
     * @return Lista de clientes.
     */
    public ArrayList<Cliente> getClientes() {

        ArrayList<Cliente> clientes = new ArrayList<>();

        for (Persona persona : personas) {

            if (persona instanceof Cliente cliente) {

                clientes.add(cliente);

            }

        }

        return clientes;

    }

    /**
     * Obtiene todos los proveedores registrados.
     *
     * @return Lista de proveedores.
     */
    public ArrayList<Proveedor> getProveedores() {

        ArrayList<Proveedor> proveedores = new ArrayList<>();

        for (Persona persona : personas) {

            if (persona instanceof Proveedor proveedor) {

                proveedores.add(proveedor);

            }

        }

        return proveedores;

    }

    /**
     * Obtiene todos los guías turísticos registrados.
     *
     * @return Lista de guías turísticos.
     */
    public ArrayList<GuiaTuristico> getGuiasTuristicos() {

        ArrayList<GuiaTuristico> guias = new ArrayList<>();

        for (Persona persona : personas) {

            if (persona instanceof GuiaTuristico guia) {

                guias.add(guia);

            }

        }

        return guias;

    }

    /**
     * Obtiene la cantidad de personas registradas.
     *
     * @return Cantidad de personas.
     */
    public int obtenerCantidadPersonas() {

        return personas.size();

    }

    /**
     * Indica si el registro está vacío.
     *
     * @return true si no existen personas registradas.
     */
    public boolean estaVacio() {

        return personas.isEmpty();

    }

    /**
     * Valida una persona antes de registrarla.
     *
     * @param persona Persona a validar.
     * @throws IllegalArgumentException Si la persona es nula o su
     * identificador es inválido.
     */
    private void validarPersona(Persona persona) {

        if (persona == null) {

            throw new IllegalArgumentException(
                    "La persona no puede ser nula.");

        }

        validarIdentificador(persona.obtenerIdentificador());

    }

    /**
     * Valida y normaliza un identificador.
     *
     * @param identificador Identificador recibido.
     * @return Identificador normalizado.
     * @throws IllegalArgumentException Si es nulo o vacío.
     */
    private String validarIdentificador(String identificador) {

        if (identificador == null) {

            throw new IllegalArgumentException(
                    "El identificador no puede ser nulo.");

        }

        String identificadorNormalizado =
                normalizarIdentificador(identificador);

        if (identificadorNormalizado.isEmpty()) {

            throw new IllegalArgumentException(
                    "El identificador no puede estar vacío.");

        }

        return identificadorNormalizado;

    }

    /**
     * Normaliza un identificador eliminando espacios
     * y convirtiéndolo a mayúsculas.
     *
     * @param identificador Identificador original.
     * @return Identificador normalizado.
     */
    private String normalizarIdentificador(String identificador) {

        return identificador.trim().toUpperCase();

    }

}