package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Muestra las opciones disponibles para administrar servicios turísticos.
 */
public class PanelMenuServicios extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el menú de servicios.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelMenuServicios(VentanaPrincipal ventana) {

        super(ventana, "Servicios");

        JPanel panelBotones = new JPanel(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(10, 10, 10, 10);

        JButton btnRegistrar = boton("Registrar Servicio");
        JButton btnVisualizar = boton("Visualizar Servicios");
        JButton btnGuias = boton("Guías Turísticos");
        JButton btnVolver = boton("Volver");

        btnRegistrar.addActionListener(e -> ventana.mostrarSeleccionTipoServicio());
        btnVisualizar.addActionListener(e -> ventana.mostrarVisualizarServicios());
        btnGuias.addActionListener(e -> ventana.mostrarMenuGuias());
        btnVolver.addActionListener(e -> ventana.mostrarMenuPrincipal());

        restricciones.gridy = 0;
        panelBotones.add(btnRegistrar, restricciones);

        restricciones.gridy++;
        panelBotones.add(btnVisualizar, restricciones);

        restricciones.gridy++;
        panelBotones.add(btnGuias, restricciones);

        restricciones.gridy++;
        panelBotones.add(btnVolver, restricciones);

        add(panelBotones);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}