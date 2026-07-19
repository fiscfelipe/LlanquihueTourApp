package UI;

import java.awt.BorderLayout;
import javax.swing.JButton;
import model.EmpresaExterna;

/**
 * Muestra la información completa del proveedor de una empresa externa.
 */
public class PanelVisualizarProveedor extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel de visualización del proveedor.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param empresa Empresa cuyo proveedor será visualizado.
     */
    public PanelVisualizarProveedor(VentanaPrincipal ventana, EmpresaExterna empresa) {

        super(ventana, "Visualizar Proveedor");

        add(areaDatos(empresa.getProveedor().mostrarDatos()), BorderLayout.CENTER);

        JButton volver = boton("Volver");

        volver.addActionListener(
                e -> ventana.mostrarEmpresa(empresa));

        add(barra(volver), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}