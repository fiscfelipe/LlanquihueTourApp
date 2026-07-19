package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import model.Cliente;
import model.Persona;

/**
 * Muestra los clientes registrados y permite buscarlos por RUT.
 */
public class PanelVisualizarClientes extends PanelBase {

    // Atributos

    private final DefaultListModel<EntidadItem<Cliente>> modelo;
    private final JList<EntidadItem<Cliente>> lista;
    private final JTextField txtBuscar;
    private final JLabel lblEstado;
    private final JButton btnVer;

    // Constructor

    /**
     * Construye el panel de visualización de clientes.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelVisualizarClientes(VentanaPrincipal ventana) {

        super(ventana, "Visualizar Clientes");

        modelo = new DefaultListModel<>();
        lista = new JList<>(modelo);
        txtBuscar = new JTextField(20);
        lblEstado = new JLabel();
        btnVer = boton("Ver Cliente");

        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lista.addListSelectionListener(
                e -> btnVer.setEnabled(!lista.isSelectionEmpty()));

        JButton btnBuscar = boton("Buscar");

        btnBuscar.addActionListener(
                e -> buscarCliente());

        txtBuscar.addActionListener(
                e -> buscarCliente());

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelBusqueda.add(new JLabel("Buscar por RUT:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        JPanel centro = new JPanel(new BorderLayout(8, 8));

        centro.add(panelBusqueda, BorderLayout.NORTH);
        centro.add(new JScrollPane(lista), BorderLayout.CENTER);
        centro.add(lblEstado, BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);

        JButton btnVolver = boton("Volver");

        btnVolver.addActionListener(
                e -> ventana.mostrarMenuClientesReservas());

        btnVer.addActionListener(
                e -> mostrarClienteSeleccionado());

        btnVer.setEnabled(false);

        add(barra(btnVolver, btnVer), BorderLayout.SOUTH);

        cargarTodosLosClientes();

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

    /**
     * Busca un cliente por su RUT o muestra todos si el campo está vacío.
     */
    private void buscarCliente() {

        String rut = txtBuscar.getText().trim();

        if (rut.isEmpty()) {

            cargarTodosLosClientes();

            return;

        }

        modelo.clear();

        Persona persona = ventana.getGestorEntidades()
                .getGestorPersonas()
                .buscarPersona(rut);

        if (persona instanceof Cliente cliente) {

            agregarCliente(cliente);

            lblEstado.setText("Cliente encontrado.");

            return;

        }

        lblEstado.setText("No se encontraron clientes.");

        JOptionPane.showMessageDialog(
                this,
                "No se encontró un cliente con el RUT ingresado.",
                "Búsqueda de cliente",
                JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Carga todos los clientes registrados en la lista.
     */
    private void cargarTodosLosClientes() {

        modelo.clear();

        for (Cliente cliente : ventana.getGestorEntidades()
                .getGestorPersonas()
                .getClientes()) {

            agregarCliente(cliente);

        }

        lblEstado.setText(
                modelo.isEmpty()
                        ? "No existen clientes registrados."
                        : "Clientes registrados: " + modelo.size());

    }

    /**
     * Agrega un cliente al modelo de la lista.
     *
     * @param cliente Cliente que se desea mostrar.
     */
    private void agregarCliente(Cliente cliente) {

        modelo.addElement(
                new EntidadItem<>(
                        cliente,
                        cliente.getRut() + " - " + cliente.getNombre()));

    }

    /**
     * Muestra los datos del cliente seleccionado.
     */
    private void mostrarClienteSeleccionado() {

        EntidadItem<Cliente> item = lista.getSelectedValue();

        if (item == null) {

            return;

        }

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(crearArea(item.getEntidad().mostrarDatos())),
                "Información del Cliente",
                JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Crea un área de texto de solo lectura.
     *
     * @param texto Texto que se desea mostrar.
     * @return Área de texto configurada.
     */
    private JTextArea crearArea(String texto) {

        JTextArea area = new JTextArea(texto, 18, 45);

        area.setEditable(false);
        area.setCaretPosition(0);

        return area;

    }

}