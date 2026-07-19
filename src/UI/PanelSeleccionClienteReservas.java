package UI;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import model.Cliente;

/**
 * Permite seleccionar un cliente para administrar sus reservas.
 */
public class PanelSeleccionClienteReservas extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel de selección de clientes.
     *
     * @param v Ventana principal de la aplicación.
     */
    public PanelSeleccionClienteReservas(VentanaPrincipal v) {

        super(v, "Administrar Reservas");

        DefaultListModel<EntidadItem<Cliente>> m = new DefaultListModel<>();

        for (Cliente c : v.getGestorEntidades().getGestorPersonas().getClientes()) {

            m.addElement(new EntidadItem<>(
                    c,
                    c.getRut() + " - " + c.getNombre()));

        }

        JList<EntidadItem<Cliente>> l = new JList<>(m);
        l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel p = new JPanel(new BorderLayout(8, 8));

        p.add(
                new JLabel(
                        m.isEmpty()
                                ? "No existen clientes registrados."
                                : "Seleccione el cliente cuyas reservas desea administrar."),
                BorderLayout.NORTH);

        p.add(new JScrollPane(l), BorderLayout.CENTER);

        add(p, BorderLayout.CENTER);

        JButton volver = boton("Volver");
        JButton adm = boton("Administrar Reservas");

        adm.setEnabled(false);

        l.addListSelectionListener(
                e -> adm.setEnabled(!l.isSelectionEmpty()));

        volver.addActionListener(
                e -> v.mostrarMenuClientesReservas());

        adm.addActionListener(
                e -> v.mostrarAdministrarReservas(
                        l.getSelectedValue().getEntidad()));

        add(barra(volver, adm), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}