package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Permite seleccionar el tipo de empresa externa que será registrada.
 */
public class PanelSeleccionTipoEmpresa extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel de selección del tipo de empresa.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelSeleccionTipoEmpresa(VentanaPrincipal ventana) {

        super(ventana, "Registrar Empresa");

        JPanel panelBotones = new JPanel(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(10, 10, 10, 10);

        JButton btnTransporte = boton("Empresa de Transporte");
        JButton btnAlojamiento = boton("Empresa de Alojamiento");
        JButton btnVolver = boton("Volver");

        btnTransporte.addActionListener(e -> ventana.mostrarRegistrarEmpresaTransporte());
        btnAlojamiento.addActionListener(e -> ventana.mostrarRegistrarEmpresaAlojamiento());
        btnVolver.addActionListener(e -> ventana.mostrarMenuEmpresas());

        restricciones.gridy = 0;
        panelBotones.add(btnTransporte, restricciones);

        restricciones.gridy++;
        panelBotones.add(btnAlojamiento, restricciones);

        restricciones.gridy++;
        panelBotones.add(btnVolver, restricciones);

        add(panelBotones);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}