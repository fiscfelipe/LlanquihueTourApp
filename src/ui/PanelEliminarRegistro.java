package ui;

import java.awt.*;
import javax.swing.*;
import model.Persona;
import model.Registrable;
import model.ServicioTuristico;

/**
 * Panel que permite seleccionar y eliminar registros del sistema.
 */
public class PanelEliminarRegistro extends PanelBase {

    private JTabbedPane tabbedPane;

    private DefaultComboBoxModel<Registrable> modeloPersonas;
    private DefaultComboBoxModel<Registrable> modeloServicios;

    private JComboBox<Registrable> comboPersonas;
    private JComboBox<Registrable> comboServicios;

    private JButton btnEliminar;
    private JButton btnVolver;

    /**
     * Constructor del panel.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelEliminarRegistro(VentanaPrincipal ventana) {
        super(ventana);
        inicializarComponentes();
        cargarRegistros();
    }

    /**
     * Inicializa los componentes gráficos del panel.
     */
    private void inicializarComponentes() {

        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        panelSuperior.add(crearSubtitulo("Eliminar Registro"),BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);

        modeloPersonas = new DefaultComboBoxModel<>();
        modeloServicios = new DefaultComboBoxModel<>();

        comboPersonas = new JComboBox<>(modeloPersonas);
        comboServicios = new JComboBox<>(modeloServicios);

        RenderizadorEntidad renderizador = new RenderizadorEntidad();
        comboPersonas.setRenderer(renderizador);
        comboServicios.setRenderer(renderizador);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Personas", crearPanelSeleccion(comboPersonas));
        tabbedPane.addTab("Servicios Turísticos",crearPanelSeleccion(comboServicios));

        add(tabbedPane, BorderLayout.CENTER);

        btnEliminar = crearBoton("Eliminar");
        btnVolver = crearBoton("Volver");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        btnEliminar.addActionListener(e -> eliminarRegistroSeleccionado());

        btnVolver.addActionListener(e -> ventana.cambiarPanel(new PanelMenuPrincipal(ventana)));
    }

    /**
     * Crea el contenido de una pestaña con su lista desplegable.
     *
     * @param combo Lista desplegable que se mostrará.
     * @return Panel configurado.
     */
    private JPanel crearPanelSeleccion(JComboBox<Registrable> combo) {

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(80, 100, 80, 100));
        panel.add(combo, BorderLayout.NORTH);

        return panel;
    }

    /**
     * Carga las entidades en la pestaña correspondiente utilizando instanceof.
     */
    private void cargarRegistros() {

        modeloPersonas.removeAllElements();
        modeloServicios.removeAllElements();

        for (Registrable entidad : ventana.getGestorEntidades().getEntidades()) {

            if (entidad instanceof Persona) {
                modeloPersonas.addElement(entidad);

            } else if (entidad instanceof ServicioTuristico) {
                modeloServicios.addElement(entidad);
            }
        }

        actualizarEstadoBoton();
    }

    /**
     * Elimina la entidad seleccionada en la pestaña activa.
     */
    private void eliminarRegistroSeleccionado() {

        Registrable entidadSeleccionada = obtenerEntidadSeleccionada();

        if (entidadSeleccionada == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No existen registros disponibles para eliminar.",
                    "Sin registros",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar este registro?\n\n"
                + entidadSeleccionada.mostrarResumen(),
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        boolean eliminado = ventana.getGestorEntidades()
                .eliminarEntidad(entidadSeleccionada);

        if (eliminado) {
            JOptionPane.showMessageDialog(
                    this,
                    "Registro eliminado correctamente.",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

            cargarRegistros();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible eliminar el registro.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Obtiene la entidad seleccionada en la pestaña activa.
     *
     * @return Entidad seleccionada o null cuando no hay registros.
     */
    private Registrable obtenerEntidadSeleccionada() {

        if (tabbedPane.getSelectedIndex() == 0) {
            return (Registrable) comboPersonas.getSelectedItem();
        }

        return (Registrable) comboServicios.getSelectedItem();
    }

    /**
     * Habilita el botón Eliminar solo cuando existe una selección válida.
     */
    private void actualizarEstadoBoton() {

        boolean existenRegistros = modeloPersonas.getSize() > 0 || modeloServicios.getSize() > 0;

        btnEliminar.setEnabled(existenRegistros);
    }

    /**
     * Renderiza cada entidad con un texto breve y legible dentro del JComboBox.
     */
    private static class RenderizadorEntidad extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> lista, Object valor, int indice, boolean seleccionado, boolean tieneFoco) {

            super.getListCellRendererComponent(lista, valor, indice, seleccionado, tieneFoco);

            if (valor instanceof Persona) {
                Persona persona = (Persona) valor;
                setText(formatearTipo(valor) + " - " + persona.getNombre());

            } else if (valor instanceof ServicioTuristico) {
                ServicioTuristico servicio = (ServicioTuristico) valor;
                setText(formatearTipo(valor) + " - " + servicio.getNombre());
            }

            return this;
        }

        /**
         * Convierte el nombre de una clase en un texto separado por espacios.
         */
        private static String formatearTipo(Object objeto) {

            return objeto.getClass().getSimpleName().replaceAll("([a-záéíóú])([A-ZÁÉÍÓÚ])", "$1 $2");
        }
    }
}
