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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import model.EmpresaAlojamiento;
import model.EmpresaExterna;
import model.EmpresaTransporte;

/**
 * Muestra las empresas registradas y permite buscarlas por nombre.
 */
public class PanelVisualizarEmpresas extends PanelBase {

    // Atributos

    private final DefaultListModel<EntidadItem<EmpresaExterna>> modeloTransporte;
    private final DefaultListModel<EntidadItem<EmpresaExterna>> modeloAlojamiento;
    private final JList<EntidadItem<EmpresaExterna>> listaTransporte;
    private final JList<EntidadItem<EmpresaExterna>> listaAlojamiento;
    private final JTabbedPane pestanas;
    private final JTextField txtBuscar;
    private final JLabel lblEstado;
    private final JButton btnVer;

    // Constructor

    /**
     * Construye el panel de visualización de empresas.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelVisualizarEmpresas(VentanaPrincipal ventana) {

        super(ventana, "Visualizar Empresas");

        modeloTransporte = new DefaultListModel<>();
        modeloAlojamiento = new DefaultListModel<>();
        listaTransporte = new JList<>(modeloTransporte);
        listaAlojamiento = new JList<>(modeloAlojamiento);
        pestanas = new JTabbedPane();
        txtBuscar = new JTextField(20);
        lblEstado = new JLabel();
        btnVer = boton("Ver Empresa");

        configurarLista(listaTransporte);
        configurarLista(listaAlojamiento);

        pestanas.addTab("Transporte", new JScrollPane(listaTransporte));
        pestanas.addTab("Alojamiento", new JScrollPane(listaAlojamiento));

        pestanas.addChangeListener(
                e -> actualizarEstado());

        JButton btnBuscar = boton("Buscar");

        btnBuscar.addActionListener(
                e -> buscarEmpresa());

        txtBuscar.addActionListener(
                e -> buscarEmpresa());

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelBusqueda.add(new JLabel("Buscar por nombre:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        JPanel centro = new JPanel(new BorderLayout(8, 8));

        centro.add(panelBusqueda, BorderLayout.NORTH);
        centro.add(pestanas, BorderLayout.CENTER);
        centro.add(lblEstado, BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);

        JButton btnVolver = boton("Volver");

        btnVolver.addActionListener(
                e -> ventana.mostrarMenuEmpresas());

        btnVer.addActionListener(
                e -> verEmpresa());

        btnVer.setEnabled(false);

        add(barra(btnVolver, btnVer), BorderLayout.SOUTH);

        cargarTodasLasEmpresas();
        actualizarEstado();

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

    /**
     * Configura una lista de empresas.
     *
     * @param lista Lista que se desea configurar.
     */
    private void configurarLista(
            JList<EntidadItem<EmpresaExterna>> lista) {

        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lista.addListSelectionListener(
                e -> actualizarEstado());

    }

    /**
     * Busca una empresa por nombre o muestra todas si el campo está vacío.
     */
    private void buscarEmpresa() {

        String nombre = txtBuscar.getText().trim();

        if (nombre.isEmpty()) {

            cargarTodasLasEmpresas();

            return;

        }

        limpiarModelos();

        EmpresaExterna empresa = ventana.getGestorEntidades()
                .getGestorEmpresas()
                .buscarEmpresa(nombre);

        if (empresa != null) {

            agregarEmpresa(empresa);
            seleccionarPestana(empresa);

            lblEstado.setText("Empresa encontrada.");

            actualizarEstado();

            return;

        }

        lblEstado.setText("No se encontraron empresas.");

        actualizarEstado();

        JOptionPane.showMessageDialog(
                this,
                "No se encontró una empresa con el nombre ingresado.",
                "Búsqueda de empresa",
                JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Carga todas las empresas registradas.
     */
    private void cargarTodasLasEmpresas() {

        limpiarModelos();

        for (EmpresaExterna empresa : ventana.getGestorEntidades()
                .getGestorEmpresas()
                .getEmpresas()) {

            agregarEmpresa(empresa);

        }

        int cantidad = modeloTransporte.size() + modeloAlojamiento.size();

        lblEstado.setText(
                cantidad == 0
                        ? "No existen empresas registradas."
                        : "Empresas registradas: " + cantidad);

        actualizarEstado();

    }

    /**
     * Agrega una empresa al modelo correspondiente.
     *
     * @param empresa Empresa que se desea mostrar.
     */
    private void agregarEmpresa(EmpresaExterna empresa) {

        EntidadItem<EmpresaExterna> item = new EntidadItem<>(
                empresa,
                empresa.getNombre());

        if (empresa instanceof EmpresaTransporte) {

            modeloTransporte.addElement(item);

        } else if (empresa instanceof EmpresaAlojamiento) {

            modeloAlojamiento.addElement(item);

        }

    }

    /**
     * Limpia ambos modelos de empresas.
     */
    private void limpiarModelos() {

        modeloTransporte.clear();
        modeloAlojamiento.clear();

        listaTransporte.clearSelection();
        listaAlojamiento.clearSelection();

    }

    /**
     * Selecciona la pestaña correspondiente al tipo de empresa.
     *
     * @param empresa Empresa encontrada.
     */
    private void seleccionarPestana(EmpresaExterna empresa) {

        pestanas.setSelectedIndex(
                empresa instanceof EmpresaTransporte ? 0 : 1);

    }

    /**
     * Obtiene la lista visible en la pestaña actual.
     *
     * @return Lista actual.
     */
    private JList<EntidadItem<EmpresaExterna>> listaActual() {

        return pestanas.getSelectedIndex() == 0
                ? listaTransporte
                : listaAlojamiento;

    }

    /**
     * Actualiza el estado del botón para ver una empresa.
     */
    private void actualizarEstado() {

        btnVer.setEnabled(
                listaActual().getSelectedValue() != null);

    }

    /**
     * Abre la información de la empresa seleccionada.
     */
    private void verEmpresa() {

        EntidadItem<EmpresaExterna> item = listaActual().getSelectedValue();

        if (item == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una empresa.");

            return;

        }

        ventana.mostrarEmpresa(item.getEntidad());

    }

}