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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import model.Cliente;
import model.Reserva;

/**
 * Permite visualizar, buscar y administrar las reservas de un cliente.
 */
public class PanelAdministrarReservas extends PanelBase {

    private final Cliente cliente;
    private final DefaultListModel<EntidadItem<Reserva>> modelo;
    private final JList<EntidadItem<Reserva>> lista;
    private final JTextField txtBuscar;
    private final JLabel lblEstado;
    private final JButton btnEliminar;
    private final JButton btnVer;

    /**
     * Construye el panel de administración de reservas.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param cliente Cliente cuyas reservas serán administradas.
     */
    public PanelAdministrarReservas(
            VentanaPrincipal ventana, Cliente cliente) {
        super(ventana, "Administrar Reservas");

        this.cliente = cliente;
        modelo = new DefaultListModel<>();
        lista = new JList<>(modelo);
        txtBuscar = new JTextField(15);
        lblEstado = new JLabel();
        btnEliminar = boton("Eliminar");
        btnVer = boton("Ver Reserva");

        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.addListSelectionListener(e -> actualizarEstadoBotones());

        JPanel encabezado = new JPanel(new BorderLayout(8, 8));
        encabezado.add(new JLabel(
                "Cliente: " + cliente.getRut()
                + " - " + cliente.getNombre()), BorderLayout.NORTH);

        JButton btnBuscar = boton("Buscar");
        btnBuscar.addActionListener(e -> buscarReserva());
        txtBuscar.addActionListener(e -> buscarReserva());

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar por código:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);
        encabezado.add(panelBusqueda, BorderLayout.CENTER);

        JPanel centro = new JPanel(new BorderLayout(8, 8));
        centro.add(encabezado, BorderLayout.NORTH);
        centro.add(new JScrollPane(lista), BorderLayout.CENTER);
        centro.add(lblEstado, BorderLayout.SOUTH);
        add(centro, BorderLayout.CENTER);

        JButton btnVolver = boton("Volver");
        JButton btnNueva = boton("Nueva Reserva");

        btnVolver.addActionListener(e ->
                ventana.mostrarSeleccionClienteReservas());
        btnNueva.addActionListener(e ->
                ventana.mostrarNuevaReserva(cliente));
        btnVer.addActionListener(e -> ventana.mostrarReserva(
                cliente, lista.getSelectedValue().getEntidad()));
        btnEliminar.addActionListener(e ->
                ventana.mostrarConfirmarEliminarReserva(
                        cliente, lista.getSelectedValue().getEntidad()));

        btnEliminar.setEnabled(false);
        btnVer.setEnabled(false);
        add(barra(btnVolver, btnNueva, btnEliminar, btnVer),
                BorderLayout.SOUTH);

        cargarTodasLasReservas();
    }

    /**
     * Busca una reserva por código o muestra todas si el campo está vacío.
     */
    private void buscarReserva() {
        String codigoIngresado = txtBuscar.getText().trim();

        if (codigoIngresado.isEmpty()) {
            cargarTodasLasReservas();
            return;
        }

        int codigo;

        try {
            codigo = Integer.parseInt(codigoIngresado);
        } catch (NumberFormatException ex) {
            mostrarBusquedaSinResultado(
                    "El código de la reserva debe ser un número entero.");
            return;
        }

        Reserva reserva;

        try {
            reserva = ventana.getGestorEntidades()
                    .getGestorReservas().buscarReserva(codigo);
        } catch (IllegalArgumentException ex) {
            mostrarBusquedaSinResultado(ex.getMessage());
            return;
        }

        modelo.clear();

        if (reserva != null && cliente.getReservas().contains(reserva)) {
            agregarReserva(reserva);
            lblEstado.setText("Reserva encontrada.");
            actualizarEstadoBotones();
            return;
        }

        mostrarBusquedaSinResultado(
                "No se encontró una reserva de este cliente con el código ingresado.");
    }

    /**
     * Carga todas las reservas del cliente.
     */
    private void cargarTodasLasReservas() {
        modelo.clear();

        for (Reserva reserva : cliente.getReservas()) {
            agregarReserva(reserva);
        }

        lblEstado.setText(modelo.isEmpty()
                ? "No posee reservas registradas."
                : "Reservas registradas: " + modelo.size());
        actualizarEstadoBotones();
    }

    /**
     * Agrega una reserva al modelo de la lista.
     *
     * @param reserva Reserva que se desea mostrar.
     */
    private void agregarReserva(Reserva reserva) {
        modelo.addElement(new EntidadItem<>(
                reserva,
                String.format(
                        "R-%05d - %s",
                        reserva.getCodigo(),
                        reserva.getServicio().getNombre())));
    }

    /**
     * Muestra un mensaje cuando la búsqueda no entrega resultados.
     *
     * @param mensaje Mensaje que se mostrará al usuario.
     */
    private void mostrarBusquedaSinResultado(String mensaje) {
        modelo.clear();
        lblEstado.setText("No se encontraron reservas.");
        actualizarEstadoBotones();

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Búsqueda de reserva",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Habilita o deshabilita los botones según la selección actual.
     */
    private void actualizarEstadoBotones() {
        boolean seleccionada = !lista.isSelectionEmpty();
        btnEliminar.setEnabled(seleccionada);
        btnVer.setEnabled(seleccionada);
    }
}
