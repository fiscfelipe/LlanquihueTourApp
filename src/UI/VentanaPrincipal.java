package UI;

import service.RegistroService;
import data.GestorEntidades;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Ventana principal de la aplicación.
 */
public class VentanaPrincipal extends JFrame {

    // Atributos

    private final GestorEntidades gestorEntidades = new GestorEntidades();
    private final JPanel panelCentral = new JPanel(new BorderLayout());

    // Constructor

    /**
     * Construye la ventana principal de la aplicación.
     */
    public VentanaPrincipal() {

        setTitle("Llanquihue Tour App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(crearEncabezado(), BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);

        RegistroService registroService = new RegistroService();
        registroService.cargarTodo(gestorEntidades);

        mostrarMenuPrincipal();

    }

    // Setters

    // Getters

    /**
     * Obtiene el gestor de entidades.
     *
     * @return Gestor de entidades.
     */
    public GestorEntidades getGestorEntidades() {

        return gestorEntidades;

    }

    // Métodos públicos

    public void cambiarPanel(JPanel panel) {

        panelCentral.removeAll();
        panelCentral.add(panel, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();

    }

    public void mostrarMenuPrincipal() {

        cambiarPanel(new PanelMenuPrincipal(this));

    }

    public void mostrarMenuClientesReservas() {

        cambiarPanel(new PanelMenuClientesReservas(this));

    }

    public void mostrarRegistrarCliente() {

        cambiarPanel(new PanelRegistrarCliente(this));

    }

    public void mostrarVisualizarClientes() {

        cambiarPanel(new PanelVisualizarClientes(this));

    }

    public void mostrarSeleccionClienteReservas() {

        cambiarPanel(new PanelSeleccionClienteReservas(this));

    }

    public void mostrarAdministrarReservas(model.Cliente cliente) {

        cambiarPanel(new PanelAdministrarReservas(this, cliente));

    }

    public void mostrarNuevaReserva(model.Cliente cliente) {

        cambiarPanel(new PanelNuevaReserva(this, cliente));

    }

    public void mostrarConfirmarReserva(model.Cliente cliente, model.Servicio servicio) {

        cambiarPanel(new PanelConfirmarReserva(this, cliente, servicio));

    }

    public void mostrarEliminarCliente() {

        cambiarPanel(new PanelEliminarCliente(this));

    }

    public void mostrarConfirmarEliminarCliente(model.Cliente cliente) {

        cambiarPanel(new PanelConfirmarEliminarCliente(this, cliente));

    }

    public void mostrarReserva(model.Cliente cliente, model.Reserva reserva) {

        cambiarPanel(new PanelVisualizarReserva(this, cliente, reserva));

    }

    public void mostrarConfirmarEliminarReserva(model.Cliente cliente, model.Reserva reserva) {

        cambiarPanel(new PanelConfirmarEliminarReserva(this, cliente, reserva));

    }

    public void mostrarMenuEmpresas() {

        cambiarPanel(new PanelMenuEmpresas(this));

    }

    public void mostrarRegistrarEmpresa() {

        mostrarSeleccionTipoEmpresa();

    }

    public void mostrarSeleccionTipoEmpresa() {

        cambiarPanel(new PanelSeleccionTipoEmpresa(this));

    }

    public void mostrarRegistrarEmpresaTransporte() {

        cambiarPanel(new PanelRegistrarEmpresaTransporte(this));

    }

    public void mostrarRegistrarEmpresaAlojamiento() {

        cambiarPanel(new PanelRegistrarEmpresaAlojamiento(this));

    }

    public void mostrarVisualizarEmpresas() {

        cambiarPanel(new PanelVisualizarEmpresas(this));

    }

    public void mostrarEmpresa(model.EmpresaExterna empresa) {

        cambiarPanel(new PanelVisualizarEmpresa(this, empresa));

    }

    public void mostrarProveedor(model.EmpresaExterna empresa) {

        cambiarPanel(new PanelVisualizarProveedor(this, empresa));

    }

    public void mostrarAdministrarVehiculos(model.EmpresaTransporte empresa) {

        cambiarPanel(new PanelAdministrarVehiculos(this, empresa));

    }

    public void mostrarRegistrarVehiculo(model.EmpresaTransporte empresa) {

        cambiarPanel(new PanelRegistrarVehiculo(this, empresa));

    }

    public void mostrarVisualizarVehiculos(model.EmpresaTransporte empresa) {

        cambiarPanel(new PanelVisualizarVehiculos(this, empresa));

    }

    public void mostrarVehiculo(model.EmpresaTransporte empresa, model.Vehiculo vehiculo) {

        cambiarPanel(new PanelVisualizarVehiculo(this, empresa, vehiculo));

    }

    public void mostrarAdministrarAlojamientos(model.EmpresaAlojamiento empresa) {

        cambiarPanel(new PanelAdministrarAlojamientos(this, empresa));

    }

    public void mostrarRegistrarAlojamiento(model.EmpresaAlojamiento empresa) {

        cambiarPanel(new PanelRegistrarAlojamiento(this, empresa));

    }

    public void mostrarVisualizarAlojamientos(model.EmpresaAlojamiento empresa) {

        cambiarPanel(new PanelVisualizarAlojamientos(this, empresa));

    }

    public void mostrarAlojamiento(model.EmpresaAlojamiento empresa, model.Alojamiento alojamiento) {

        cambiarPanel(new PanelVisualizarAlojamiento(this, empresa, alojamiento));

    }

    public void mostrarMenuServicios() {

        cambiarPanel(new PanelMenuServicios(this));

    }

    public void mostrarSeleccionTipoServicio() {

        cambiarPanel(new PanelSeleccionTipoServicio(this));

    }

    public void mostrarRegistrarServicio(int tipo) {

        cambiarPanel(new PanelRegistrarServicio(this, tipo));

    }

    public void mostrarVisualizarServicios() {

        cambiarPanel(new PanelVisualizarServicios(this));

    }

    public void mostrarMenuGuias() {

        cambiarPanel(new PanelMenuGuias(this));

    }

    public void mostrarRegistrarGuia() {

        cambiarPanel(new PanelRegistrarGuia(this));

    }

    public void mostrarVisualizarGuias() {

        cambiarPanel(new PanelVisualizarGuias(this));

    }

    // Métodos privados

    /**
     * Crea el encabezado de la ventana.
     *
     * @return Panel del encabezado.
     */
    private JPanel crearEncabezado() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1280, 105));
        panel.setBorder(BorderFactory.createEmptyBorder(18, 30, 18, 30));
        panel.setBackground(new Color(238, 242, 246));

        JLabel titulo = new JLabel("Llanquihue Tour App", SwingConstants.CENTER);
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));

        JLabel subtitulo = new JLabel("Sistema de Gestión Turística", SwingConstants.CENTER);
        subtitulo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        panel.add(titulo, BorderLayout.CENTER);
        panel.add(subtitulo, BorderLayout.SOUTH);

        return panel;

    }

}
