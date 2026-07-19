package UI;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import model.Cliente;

/**
 * Panel que permite seleccionar un cliente para eliminarlo del sistema.
 */
public class PanelEliminarCliente extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel para seleccionar un cliente.
     *
     * @param v Ventana principal de la aplicación.
     */
    public PanelEliminarCliente(VentanaPrincipal v) {

        super(v, "Eliminar Cliente");

        DefaultListModel<EntidadItem<Cliente>> m = new DefaultListModel<>();

        for (Cliente c : v.getGestorEntidades().getGestorPersonas().getClientes()) {
            m.addElement(new EntidadItem<>(c, c.getRut() + " - " + c.getNombre()));
        }

        JList<EntidadItem<Cliente>> l = new JList<>(m);

        l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel p = new JPanel(new BorderLayout(8, 8));

        p.add(new JLabel(m.isEmpty()
                ? "No existen clientes registrados."
                : "Seleccione un cliente"), BorderLayout.NORTH);

        p.add(new JScrollPane(l), BorderLayout.CENTER);

        add(p, BorderLayout.CENTER);

        JButton volver = boton("Volver");
        JButton sel = boton("Seleccionar");

        sel.setEnabled(false);

        l.addListSelectionListener(e -> sel.setEnabled(!l.isSelectionEmpty()));

        volver.addActionListener(e -> v.mostrarMenuClientesReservas());

        sel.addActionListener(e -> v.mostrarConfirmarEliminarCliente(l.getSelectedValue().getEntidad()));

        add(barra(volver, sel), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}