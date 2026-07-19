package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import service.RegistroService;

/**
 * Muestra las opciones principales de la aplicación.
 */
public class PanelMenuPrincipal extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el menú principal.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelMenuPrincipal(VentanaPrincipal ventana) {

        super(ventana, "Menú Principal");

        JPanel panelBotones = new JPanel(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(10, 10, 10, 10);

        JButton btnClientes = boton("Clientes y Reservas");
        JButton btnEmpresas = boton("Empresas Externas");
        JButton btnServicios = boton("Servicios");
        JButton btnSalir = boton("Salir");

        btnClientes.addActionListener(e -> ventana.mostrarMenuClientesReservas());
        btnEmpresas.addActionListener(e -> ventana.mostrarMenuEmpresas());
        btnServicios.addActionListener(e -> ventana.mostrarMenuServicios());

        btnSalir.addActionListener(e -> {

            RegistroService registroService = new RegistroService();

            registroService.guardarTodo(ventana.getGestorEntidades());

            System.exit(0);

        });

        restricciones.gridy = 0;
        panelBotones.add(btnClientes, restricciones);

        restricciones.gridy++;
        panelBotones.add(btnEmpresas, restricciones);

        restricciones.gridy++;
        panelBotones.add(btnServicios, restricciones);

        restricciones.gridy++;
        panelBotones.add(btnSalir, restricciones);

        add(panelBotones);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}