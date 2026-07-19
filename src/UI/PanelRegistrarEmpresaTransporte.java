package UI;

import model.Correo;
import model.EmpresaExterna;
import model.EmpresaTransporte;
import model.Proveedor;

/**
 * Permite registrar una empresa externa de transporte.
 */
public class PanelRegistrarEmpresaTransporte extends PanelFormularioEmpresa {

    // Atributos

    // Constructor

    /**
     * Construye el formulario de registro de empresa de transporte.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelRegistrarEmpresaTransporte(VentanaPrincipal ventana) {

        super(ventana, "Registrar Empresa de Transporte");

    }

    // Setters

    // Getters

    // Métodos públicos

    /**
     * Crea una empresa de transporte con los datos validados.
     *
     * @param nombre Nombre de la empresa.
     * @param correo Correo de la empresa.
     * @param proveedor Proveedor responsable.
     * @return Empresa de transporte creada.
     * @throws Exception Si los datos no son válidos.
     */
    @Override
    protected EmpresaExterna crearEmpresa(String nombre, Correo correo, Proveedor proveedor) throws Exception {

        return new EmpresaTransporte(nombre, correo, proveedor);

    }

    // Métodos privados

}