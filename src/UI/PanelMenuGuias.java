package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Muestra las opciones disponibles para administrar guías turísticos.
 */
public class PanelMenuGuias extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el menú de guías turísticos.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelMenuGuias(VentanaPrincipal ventana) {

        super(ventana, "Guías Turísticos");

        JPanel panelBotones = new JPanel(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(10, 10, 10, 10);

        JButton btnRegistrar = boton("Registrar Guía Turístico");
        JButton btnVisualizar = boton("Visualizar Guías Turísticos");
        JButton btnVolver = boton("Volver");

        btnRegistrar.addActionListener(e -> ventana.mostrarRegistrarGuia());
        btnVisualizar.addActionListener(e -> ventana.mostrarVisualizarGuias());
        btnVolver.addActionListener(e -> ventana.mostrarMenuServicios());

        restricciones.gridy = 0;
        panelBotones.add(btnRegistrar, restricciones);

        restricciones.gridy++;
        panelBotones.add(btnVisualizar, restricciones);

        restricciones.gridy++;
        panelBotones.add(btnVolver, restricciones);

        add(panelBotones);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}