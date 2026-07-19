package UI;

import java.awt.BorderLayout;
import javax.swing.JButton;
import model.EmpresaTransporte;
import model.Vehiculo;

/**
 * Muestra la información completa de un vehículo.
 */
public class PanelVisualizarVehiculo extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel de visualización de un vehículo.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param empresa Empresa de transporte a la que pertenece el vehículo.
     * @param vehiculo Vehículo que será visualizado.
     */
    public PanelVisualizarVehiculo(VentanaPrincipal ventana, EmpresaTransporte empresa, Vehiculo vehiculo) {

        super(ventana, "Visualizar Vehículo");

        add(areaDatos(vehiculo.mostrarDatos()), BorderLayout.CENTER);

        JButton volver = boton("Volver");

        volver.addActionListener(
                e -> ventana.mostrarVisualizarVehiculos(empresa));

        add(barra(volver), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}
