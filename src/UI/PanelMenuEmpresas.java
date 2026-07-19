package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Muestra las opciones para administrar empresas externas.
 */
public class PanelMenuEmpresas extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el menú de empresas externas.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelMenuEmpresas(VentanaPrincipal ventana) {

        super(ventana, "Empresas Externas");

        JPanel panelBotones = new JPanel(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(10, 10, 10, 10);

        JButton btnRegistrar = boton("Registrar Empresa");
        JButton btnVisualizar = boton("Visualizar Empresas");
        JButton btnVolver = boton("Volver");

        btnRegistrar.addActionListener(e -> ventana.mostrarRegistrarEmpresa());
        btnVisualizar.addActionListener(e -> ventana.mostrarVisualizarEmpresas());
        btnVolver.addActionListener(e -> ventana.mostrarMenuPrincipal());

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