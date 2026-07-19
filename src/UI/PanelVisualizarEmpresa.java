package UI;

import java.awt.BorderLayout;
import javax.swing.JButton;
import model.EmpresaAlojamiento;
import model.EmpresaExterna;
import model.EmpresaTransporte;

/**
 * Muestra la información completa de una empresa externa.
 */
public class PanelVisualizarEmpresa extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel de visualización de la empresa.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param empresa Empresa que será visualizada.
     */
    public PanelVisualizarEmpresa(
            VentanaPrincipal ventana,
            EmpresaExterna empresa) {

        super(ventana, "Visualizar Empresa");

        add(areaDatos(empresa.mostrarDatos()), BorderLayout.CENTER);

        JButton volver = boton("Volver");
        JButton verProveedor = boton("Ver Proveedor");

        volver.addActionListener(
                e -> ventana.mostrarVisualizarEmpresas());

        verProveedor.addActionListener(
                e -> ventana.mostrarProveedor(empresa));

        JButton administrar;

        if (empresa instanceof EmpresaTransporte transporte) {

            administrar = boton("Administrar Vehículos");

            administrar.addActionListener(
                    e -> ventana.mostrarAdministrarVehiculos(transporte));

        } else {

            EmpresaAlojamiento alojamiento = (EmpresaAlojamiento) empresa;

            administrar = boton("Administrar Alojamientos");

            administrar.addActionListener(
                    e -> ventana.mostrarAdministrarAlojamientos(alojamiento));

        }

        add(barra(volver, verProveedor, administrar), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}