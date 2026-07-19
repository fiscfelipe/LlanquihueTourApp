package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.EmpresaTransporte;

/**
 * Muestra las opciones para administrar los vehículos de una empresa.
 */
public class PanelAdministrarVehiculos extends PanelBase {

    /**
     * Construye el menú de administración de vehículos.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param empresa Empresa de transporte seleccionada.
     */
    public PanelAdministrarVehiculos(
            VentanaPrincipal ventana,
            EmpresaTransporte empresa) {
        super(ventana, "Administrar Vehículos");

        JPanel panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(10, 10, 10, 10);

        JButton btnRegistrar = boton("Registrar Vehículo");
        JButton btnVisualizar = boton("Visualizar Vehículos");
        JButton btnVolver = boton("Volver");

        btnRegistrar.addActionListener(
                e -> ventana.mostrarRegistrarVehiculo(empresa));
        btnVisualizar.addActionListener(
                e -> ventana.mostrarVisualizarVehiculos(empresa));
        btnVolver.addActionListener(e -> ventana.mostrarEmpresa(empresa));

        restricciones.gridy = 0;
        panelBotones.add(btnRegistrar, restricciones);
        restricciones.gridy++;
        panelBotones.add(btnVisualizar, restricciones);
        restricciones.gridy++;
        panelBotones.add(btnVolver, restricciones);

        add(panelBotones);
    }
}
