package UI;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Reserva;

/**
 * Panel que solicita la confirmación para eliminar una reserva del sistema.
 */
public class PanelConfirmarEliminarReserva extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel de confirmación para eliminar una reserva.
     *
     * @param v Ventana principal de la aplicación.
     * @param cliente Cliente al que pertenece la reserva.
     * @param reserva Reserva que será eliminada.
     */
    public PanelConfirmarEliminarReserva(VentanaPrincipal v, Cliente cliente, Reserva reserva) {

        super(v, "Confirmar Eliminación de Reserva");

        add(areaDatos(reserva.mostrarDatos()), BorderLayout.CENTER);

        JButton volver = boton("Volver");
        JButton elim = boton("Eliminar Reserva");

        volver.addActionListener(e -> v.mostrarAdministrarReservas(cliente));

        elim.addActionListener(e -> {

            int op = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de eliminar esta reserva?\n\n"
                    + "Los cupos del servicio asociado serán restaurados "
                    + "automáticamente.\n\n"
                    + "Esta acción no se puede deshacer.",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (op != JOptionPane.YES_OPTION) {
                return;
            }

            try {

                boolean ok = v.getGestorEntidades().getGestorReservas().eliminarReserva(cliente, reserva);

                if (!ok) {
                    throw new IllegalStateException("No fue posible eliminar la reserva.");
                }

                JOptionPane.showMessageDialog(this, "Reserva eliminada correctamente.");

                v.mostrarAdministrarReservas(cliente);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        mensaje(ex),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        });

        add(barra(volver, elim), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}