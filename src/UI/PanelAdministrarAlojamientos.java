package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.EmpresaAlojamiento;

/**
 * Muestra las opciones para administrar los alojamientos de una empresa.
 */
public class PanelAdministrarAlojamientos extends PanelBase {

    /**
     * Construye el menú de administración de alojamientos.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param empresa Empresa de alojamiento seleccionada.
     */
    public PanelAdministrarAlojamientos(VentanaPrincipal ventana, EmpresaAlojamiento empresa) {
        super(ventana, "Administrar Alojamientos");

        JPanel panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(10, 10, 10, 10);

        JButton btnRegistrar = boton("Registrar Alojamiento");
        JButton btnVisualizar = boton("Visualizar Alojamientos");
        JButton btnVolver = boton("Volver");

        btnRegistrar.addActionListener(e -> ventana.mostrarRegistrarAlojamiento(empresa));
        btnVisualizar.addActionListener(e -> ventana.mostrarVisualizarAlojamientos(empresa));
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
