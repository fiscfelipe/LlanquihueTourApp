package UI;

import model.Correo;
import model.EmpresaAlojamiento;
import model.EmpresaExterna;
import model.Proveedor;

/**
 * Permite registrar una empresa externa de alojamiento.
 */
public class PanelRegistrarEmpresaAlojamiento extends PanelFormularioEmpresa {

    // Atributos

    // Constructor

    /**
     * Construye el formulario de registro de empresa de alojamiento.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelRegistrarEmpresaAlojamiento(VentanaPrincipal ventana) {

        super(ventana, "Registrar Empresa de Alojamiento");

    }

    // Setters

    // Getters

    // Métodos públicos

    /**
     * Crea una empresa de alojamiento con los datos validados.
     *
     * @param nombre Nombre de la empresa.
     * @param correo Correo de la empresa.
     * @param proveedor Proveedor responsable.
     * @return Empresa de alojamiento creada.
     * @throws Exception Si los datos no son válidos.
     */
    @Override
    protected EmpresaExterna crearEmpresa(String nombre, Correo correo, Proveedor proveedor) throws Exception {

        return new EmpresaAlojamiento(nombre, correo, proveedor);

    }

    // Métodos privados

}