package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.GuiaTuristico;
import model.Persona;

/**
 * Muestra los guías turísticos registrados y permite buscarlos por RUT.
 */
public class PanelVisualizarGuias extends PanelBase {

    // Atributos

    private final DefaultTableModel modelo;
    private final JTextField txtBuscar;
    private final JLabel lblEstado;

    // Constructor

    /**
     * Construye el panel de visualización de guías turísticos.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelVisualizarGuias(VentanaPrincipal ventana) {

        super(ventana, "Visualizar Guías Turísticos");

        String[] columnas = {
            "RUT",
            "Nombre",
            "Especialidad",
            "Idiomas",
            "Años Exp.",
            "Teléfono",
            "Correo"
        };

        modelo = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {

                return false;

            }

        };

        JTable tabla = new JTable(modelo);

        tabla.setAutoCreateRowSorter(true);

        txtBuscar = new JTextField(20);

        JButton btnBuscar = boton("Buscar");

        btnBuscar.addActionListener(
                e -> buscarGuia());

        txtBuscar.addActionListener(
                e -> buscarGuia());

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelBusqueda.add(new JLabel("Buscar por RUT:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        lblEstado = new JLabel();

        JPanel centro = new JPanel(new BorderLayout(8, 8));

        centro.add(panelBusqueda, BorderLayout.NORTH);
        centro.add(new JScrollPane(tabla), BorderLayout.CENTER);
        centro.add(lblEstado, BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);

        JButton btnVolver = boton("Volver");

        btnVolver.addActionListener(
                e -> ventana.mostrarMenuGuias());

        add(barra(btnVolver), BorderLayout.SOUTH);

        cargarTodosLosGuias();

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

    /**
     * Busca un guía por su RUT o muestra todos si el campo está vacío.
     */
    private void buscarGuia() {

        String rut = txtBuscar.getText().trim();

        if (rut.isEmpty()) {

            cargarTodosLosGuias();

            return;

        }

        modelo.setRowCount(0);

        Persona persona = ventana.getGestorEntidades()
                .getGestorPersonas()
                .buscarPersona(rut);

        if (persona instanceof GuiaTuristico guia) {

            agregarGuia(guia);

            lblEstado.setText("Guía turístico encontrado.");

            return;

        }

        lblEstado.setText("No se encontraron guías turísticos.");

        JOptionPane.showMessageDialog(
                this,
                "No se encontró un guía turístico con el RUT ingresado.",
                "Búsqueda de guía",
                JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Carga todos los guías turísticos registrados.
     */
    private void cargarTodosLosGuias() {

        modelo.setRowCount(0);

        for (GuiaTuristico guia : ventana.getGestorEntidades()
                .getGestorPersonas()
                .getGuiasTuristicos()) {

            agregarGuia(guia);

        }

        lblEstado.setText(
                modelo.getRowCount() == 0
                        ? "No existen guías turísticos registrados."
                        : "Guías registrados: " + modelo.getRowCount());

    }

    /**
     * Agrega un guía al modelo de la tabla.
     *
     * @param guia Guía turístico que se desea mostrar.
     */
    private void agregarGuia(GuiaTuristico guia) {

        modelo.addRow(new Object[]{
            guia.getRut(),
            guia.getNombre(),
            guia.getEspecialidad(),
            guia.getIdiomas(),
            guia.getAniosExperiencia(),
            guia.getTelefono(),
            guia.getCorreo()
        });

    }

}