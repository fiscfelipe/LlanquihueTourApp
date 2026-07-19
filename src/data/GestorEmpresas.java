package data;

import java.util.ArrayList;

import model.EmpresaAlojamiento;
import model.EmpresaExterna;
import model.EmpresaTransporte;
import util.RegistroDuplicadoException;

/**
 * Gestiona el registro de empresas externas.
 */
public class GestorEmpresas {

    // Atributos

    private final ArrayList<EmpresaExterna> empresas;

    // Constructor

    /**
     * Construye un gestor de empresas.
     */
    public GestorEmpresas() {

        empresas = new ArrayList<>();

    }

    // Setters

    // Getters

    /**
     * Obtiene la lista de empresas registradas.
     *
     * @return Copia de la lista de empresas.
     */
    public ArrayList<EmpresaExterna> getEmpresas() {

        return new ArrayList<>(empresas);

    }

    /**
     * Obtiene las empresas de transporte registradas.
     *
     * @return Lista de empresas de transporte.
     */
    public ArrayList<EmpresaTransporte> getEmpresasTransporte() {

        ArrayList<EmpresaTransporte> lista = new ArrayList<>();

        for (EmpresaExterna empresa : empresas) {

            if (empresa instanceof EmpresaTransporte transporte) {

                lista.add(transporte);

            }

        }

        return lista;

    }

    /**
     * Obtiene las empresas de alojamiento registradas.
     *
     * @return Lista de empresas de alojamiento.
     */
    public ArrayList<EmpresaAlojamiento> getEmpresasAlojamiento() {

        ArrayList<EmpresaAlojamiento> lista = new ArrayList<>();

        for (EmpresaExterna empresa : empresas) {

            if (empresa instanceof EmpresaAlojamiento alojamiento) {

                lista.add(alojamiento);

            }

        }

        return lista;

    }

    // Métodos públicos

    /**
     * Agrega una empresa al registro.
     *
     * @param empresa Empresa a registrar.
     * @throws RegistroDuplicadoException Si ya existe una empresa con el mismo identificador.
     */
    public void agregarEmpresa(EmpresaExterna empresa)
            throws RegistroDuplicadoException {

        validarEmpresa(empresa);

        if (buscarEmpresa(empresa.obtenerIdentificador()) != null) {

            throw new RegistroDuplicadoException(
                    "Ya existe una empresa registrada con ese identificador.");

        }

        empresas.add(empresa);

    }

    /**
     * Busca una empresa por su identificador.
     *
     * @param identificador Identificador de la empresa.
     * @return Empresa encontrada o {@code null} si no existe.
     */
    public EmpresaExterna buscarEmpresa(String identificador) {

        String id = validarIdentificador(identificador);

        for (EmpresaExterna empresa : empresas) {

            if (normalizarIdentificador(empresa.obtenerIdentificador()).equals(id)) {

                return empresa;

            }

        }

        return null;

    }

    /**
     * Elimina una empresa del registro.
     *
     * @param identificador Identificador de la empresa.
     * @return {@code true} si la empresa fue eliminada; {@code false} en caso contrario.
     */
    public boolean eliminarEmpresa(String identificador) {

        EmpresaExterna empresa = buscarEmpresa(identificador);

        if (empresa == null) {

            return false;

        }

        return empresas.remove(empresa);

    }

    /**
     * Obtiene la cantidad de empresas registradas.
     *
     * @return Cantidad de empresas.
     */
    public int obtenerCantidadEmpresas() {

        return empresas.size();

    }

    /**
     * Indica si el gestor está vacío.
     *
     * @return {@code true} si no existen empresas registradas.
     */
    public boolean estaVacio() {

        return empresas.isEmpty();

    }

    // Métodos privados

    /**
     * Valida una empresa antes de registrarla.
     *
     * @param empresa Empresa a validar.
     */
    private void validarEmpresa(EmpresaExterna empresa) {

        if (empresa == null) {

            throw new IllegalArgumentException(
                    "La empresa no puede ser nula.");

        }

        validarIdentificador(empresa.obtenerIdentificador());

    }

    /**
     * Valida un identificador.
     *
     * @param identificador Identificador a validar.
     * @return Identificador normalizado.
     */
    private String validarIdentificador(String identificador) {

        if (identificador == null) {

            throw new IllegalArgumentException(
                    "El identificador no puede ser nulo.");

        }

        String id = normalizarIdentificador(identificador);

        if (id.isEmpty()) {

            throw new IllegalArgumentException(
                    "El identificador no puede estar vacío.");

        }

        return id;

    }

    /**
     * Normaliza un identificador.
     *
     * @param identificador Identificador original.
     * @return Identificador normalizado.
     */
    private String normalizarIdentificador(String identificador) {

        return identificador.trim().toUpperCase();

    }

}