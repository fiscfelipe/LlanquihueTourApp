package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Permite seleccionar el tipo de servicio que será registrado.
 */
public class PanelSeleccionTipoServicio extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel de selección de tipo de servicio.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelSeleccionTipoServicio(VentanaPrincipal ventana) {

        super(ventana, "Registrar Servicio");

        JPanel panelBotones = new JPanel(new GridBagLayout());

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(7, 10, 7, 10);

        String[] nombres = {
            "Ruta Gastronómica",
            "Paseo Lacustre",
            "Excursión Cultural",
            "Servicio de Transporte",
            "Servicio de Alojamiento"
        };

        for (int i = 0; i < nombres.length; i++) {

            int tipo = i;

            JButton botonTipo = boton(nombres[i]);

            botonTipo.addActionListener(
                    e -> ventana.mostrarRegistrarServicio(tipo));

            restricciones.gridy = i;

            panelBotones.add(botonTipo, restricciones);

        }

        JButton btnVolver = boton("Volver");

        btnVolver.addActionListener(e -> ventana.mostrarMenuServicios());

        restricciones.gridy = nombres.length;

        panelBotones.add(btnVolver, restricciones);

        add(panelBotones);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}