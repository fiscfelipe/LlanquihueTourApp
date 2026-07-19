package UI;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Cliente;

/**
 * Panel que solicita la confirmación para eliminar un cliente del sistema.
 */
public class PanelConfirmarEliminarCliente extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel de confirmación para eliminar un cliente.
     *
     * @param v Ventana principal de la aplicación.
     * @param cliente Cliente que será eliminado.
     */
    public PanelConfirmarEliminarCliente(VentanaPrincipal v, Cliente cliente) {

        super(v, "Confirmar Eliminación del Cliente");

        JPanel p = new JPanel(new BorderLayout(8, 8));

        p.add(areaDatos(cliente.mostrarDatos()
                + "\n\nReservas registradas: " + cliente.getReservas().size()
                + "\n\nAl eliminar este cliente se eliminarán sus reservas, "
                + "se restaurarán los cupos asociados y la acción no podrá "
                + "deshacerse."), BorderLayout.CENTER);

        add(p, BorderLayout.CENTER);

        JButton volver = boton("Volver");
        JButton elim = boton("Eliminar Cliente");

        volver.addActionListener(e -> v.mostrarEliminarCliente());

        elim.addActionListener(e -> {

            int op = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de eliminar este cliente?\n\n"
                    + "Se eliminarán también todas sus reservas y se "
                    + "restaurarán los cupos de los servicios asociados.\n\n"
                    + "Esta acción no se puede deshacer.",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (op != JOptionPane.YES_OPTION) {
                return;
            }

            try {

                v.getGestorEntidades().getGestorReservas().eliminarReservasCliente(cliente);

                boolean ok = v.getGestorEntidades().getGestorPersonas().eliminarPersona(cliente.obtenerIdentificador());

                if (!ok) {
                    throw new IllegalStateException("No fue posible eliminar el cliente.");
                }

                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");

                v.mostrarMenuClientesReservas();

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