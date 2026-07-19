package UI;

import java.awt.BorderLayout;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import model.Cliente;
import model.ExcursionCultural;
import model.PaseoLacustre;
import model.RutaGastronomica;
import model.Servicio;
import model.ServicioAlojamiento;
import model.ServicioTransporte;

/**
 * Permite seleccionar un servicio para crear una nueva reserva.
 */
public class PanelNuevaReserva extends PanelBase {

    // Atributos

    private final JTabbedPane tabs = new JTabbedPane();
    private final JButton seleccionar;

    // Constructor

    /**
     * Construye el panel para crear una nueva reserva.
     *
     * @param v Ventana principal de la aplicación.
     * @param cliente Cliente que realizará la reserva.
     */
    public PanelNuevaReserva(VentanaPrincipal v, Cliente cliente) {

        super(v, "Nueva Reserva");

        List<Servicio> todos = v.getGestorEntidades().getGestorServicios().getServicios();

        tabs.addTab("Todos", crearLista(todos));
        tabs.addTab("Transporte", crearLista(todos.stream().filter(s -> s instanceof ServicioTransporte).toList()));
        tabs.addTab("Alojamiento", crearLista(todos.stream().filter(s -> s instanceof ServicioAlojamiento).toList()));
        tabs.addTab("Recreativos", crearLista(todos.stream().filter(s -> s instanceof RutaGastronomica || s instanceof PaseoLacustre || s instanceof ExcursionCultural).toList()));

        JPanel p = new JPanel(new BorderLayout(8, 8));

        p.add(new JLabel("Cliente: " + cliente.getRut() + " - " + cliente.getNombre()), BorderLayout.NORTH);
        p.add(tabs, BorderLayout.CENTER);

        add(p, BorderLayout.CENTER);

        JButton volver = boton("Volver");
        seleccionar = boton("Seleccionar");

        seleccionar.setEnabled(false);

        volver.addActionListener(e -> v.mostrarAdministrarReservas(cliente));

        seleccionar.addActionListener(e -> {

            JList<EntidadItem<Servicio>> l = listaActual();

            if (l.getSelectedValue() != null) {
                v.mostrarConfirmarReserva(cliente, l.getSelectedValue().getEntidad());
            }

        });

        tabs.addChangeListener(e -> actualizarBoton());

        add(barra(volver, seleccionar), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

    /**
     * Crea una lista de servicios para una pestaña.
     *
     * @param servicios Servicios que contendrá la lista.
     * @return Lista de servicios dentro de un panel con desplazamiento.
     */
    private JScrollPane crearLista(List<Servicio> servicios) {

        DefaultListModel<EntidadItem<Servicio>> m = new DefaultListModel<>();

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Servicio s : servicios) {
            m.addElement(new EntidadItem<>(s, s.getFecha().format(f) + " - " + s.getNombre()));
        }

        JList<EntidadItem<Servicio>> l = new JList<>(m);

        l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        l.addListSelectionListener(e -> actualizarBoton());

        return new JScrollPane(l);

    }

    /**
     * Obtiene la lista correspondiente a la pestaña actualmente seleccionada.
     *
     * @return Lista de servicios activa.
     */
    @SuppressWarnings("unchecked")
    private JList<EntidadItem<Servicio>> listaActual() {

        return (JList<EntidadItem<Servicio>>) ((JScrollPane) tabs.getSelectedComponent()).getViewport().getView();

    }

    /**
     * Actualiza el estado del botón de selección.
     */
    private void actualizarBoton() {

        if (seleccionar != null) {
            seleccionar.setEnabled(listaActual().getSelectedValue() != null);
        }

    }

}