package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Muestra las opciones para administrar clientes y reservas.
 */
public class PanelMenuClientesReservas extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el menú de clientes y reservas.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelMenuClientesReservas(VentanaPrincipal ventana) {

        super(ventana, "Clientes y Reservas");

        JPanel panelBotones = new JPanel(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(8, 8, 8, 8);

        JButton btnRegistrar = boton("Registrar Cliente");
        JButton btnVisualizar = boton("Visualizar Clientes");
        JButton btnReservas = boton("Administrar Reservas");
        JButton btnEliminar = boton("Eliminar Cliente");
        JButton btnVolver = boton("Volver");

        btnRegistrar.addActionListener(e -> ventana.mostrarRegistrarCliente());
        btnVisualizar.addActionListener(e -> ventana.mostrarVisualizarClientes());
        btnReservas.addActionListener(e -> ventana.mostrarSeleccionClienteReservas());
        btnEliminar.addActionListener(e -> ventana.mostrarEliminarCliente());
        btnVolver.addActionListener(e -> ventana.mostrarMenuPrincipal());

        JButton[] botones = {
            btnRegistrar,
            btnVisualizar,
            btnReservas,
            btnEliminar,
            btnVolver
        };

        for (int i = 0; i < botones.length; i++) {

            restricciones.gridy = i;

            panelBotones.add(botones[i], restricciones);

        }

        add(panelBotones);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}