package UI;

import java.awt.BorderLayout;
import javax.swing.JButton;
import model.Cliente;
import model.Reserva;

/**
 * Muestra la información de una reserva.
 */
public class PanelVisualizarReserva extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel para visualizar una reserva.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param cliente Cliente al que pertenece la reserva.
     * @param reserva Reserva que será visualizada.
     */
    public PanelVisualizarReserva(VentanaPrincipal ventana, Cliente cliente, Reserva reserva) {

        super(ventana, "Visualizar Reserva");

        add(areaDatos(reserva.mostrarDatos()), BorderLayout.CENTER);

        JButton volver = boton("Volver");

        volver.addActionListener(
                e -> ventana.mostrarAdministrarReservas(cliente));

        add(barra(volver), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}