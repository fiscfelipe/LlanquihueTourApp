package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ExcursionCultural;
import model.GuiaTuristico;
import model.PaseoLacustre;
import model.RutaGastronomica;
import model.Servicio;
import model.ServicioAlojamiento;
import model.ServicioTransporte;

/**
 * Muestra los servicios registrados y permite buscarlos por nombre.
 */
public class PanelVisualizarServicios extends PanelBase {

    private final JTabbedPane pestanias;
    private final JTextField txtBuscar;
    private final JLabel lblEstado;
    private Servicio servicioBuscado;

    /**
     * Construye el panel de visualización de servicios.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelVisualizarServicios(VentanaPrincipal ventana) {
        super(ventana, "Visualizar Servicios");

        pestanias = new JTabbedPane();
        txtBuscar = new JTextField(20);
        lblEstado = new JLabel();

        JButton btnBuscar = boton("Buscar");
        btnBuscar.addActionListener(e -> buscarServicio());
        txtBuscar.addActionListener(e -> buscarServicio());

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar por nombre:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        JPanel centro = new JPanel(new BorderLayout(8, 8));
        centro.add(panelBusqueda, BorderLayout.NORTH);
        centro.add(pestanias, BorderLayout.CENTER);
        centro.add(lblEstado, BorderLayout.SOUTH);
        add(centro, BorderLayout.CENTER);

        JButton btnVolver = boton("Volver");
        btnVolver.addActionListener(e -> ventana.mostrarMenuServicios());
        add(barra(btnVolver), BorderLayout.SOUTH);

        mostrarTodosLosServicios();
    }

    /**
     * Busca un servicio por nombre o muestra todos si el campo está vacío.
     */
    private void buscarServicio() {
        String nombre = txtBuscar.getText().trim();

        if (nombre.isEmpty()) {
            mostrarTodosLosServicios();
            return;
        }

        servicioBuscado = ventana.getGestorEntidades()
                .getGestorServicios().buscarServicio(nombre);

        reconstruirPestanias();

        if (servicioBuscado != null) {
            seleccionarPestaniaServicio(servicioBuscado);
            lblEstado.setText("Servicio encontrado.");
            return;
        }

        lblEstado.setText("No se encontraron servicios.");
        JOptionPane.showMessageDialog(
                this,
                "No se encontró un servicio con el nombre ingresado.",
                "Búsqueda de servicio",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Vuelve a mostrar todos los servicios registrados.
     */
    private void mostrarTodosLosServicios() {
        servicioBuscado = null;
        reconstruirPestanias();

        int cantidad = ventana.getGestorEntidades()
                .getGestorServicios().getServicios().size();
        lblEstado.setText(cantidad == 0
                ? "No existen servicios registrados."
                : "Servicios registrados: " + cantidad);
    }

    /**
     * Reconstruye las pestañas utilizando el filtro actual.
     */
    private void reconstruirPestanias() {
        pestanias.removeAll();
        pestanias.addTab("Recreativos", crearPanelRecreativos());
        pestanias.addTab("Transporte", crearTabla(3));
        pestanias.addTab("Alojamiento", crearTabla(4));
    }

    /**
     * Crea el panel de servicios recreativos.
     *
     * @return Panel configurado.
     */
    private JPanel crearPanelRecreativos() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        JPanel panelOpciones = new JPanel();
        JPanel panelTabla = new JPanel(new BorderLayout());
        ButtonGroup grupo = new ButtonGroup();

        JRadioButton rdbRuta = new JRadioButton("Ruta Gastronómica");
        JRadioButton rdbPaseo = new JRadioButton("Paseo Lacustre");
        JRadioButton rdbExcursion = new JRadioButton("Excursión Cultural");

        if (servicioBuscado instanceof PaseoLacustre) {
            rdbPaseo.setSelected(true);
        } else if (servicioBuscado instanceof ExcursionCultural) {
            rdbExcursion.setSelected(true);
        } else {
            rdbRuta.setSelected(true);
        }

        grupo.add(rdbRuta);
        grupo.add(rdbPaseo);
        grupo.add(rdbExcursion);
        panelOpciones.add(rdbRuta);
        panelOpciones.add(rdbPaseo);
        panelOpciones.add(rdbExcursion);

        Runnable actualizarTabla = () -> {
            int tipo = rdbRuta.isSelected()
                    ? 0 : rdbPaseo.isSelected() ? 1 : 2;

            panelTabla.removeAll();
            panelTabla.add(crearTabla(tipo), BorderLayout.CENTER);
            panelTabla.revalidate();
            panelTabla.repaint();
        };

        rdbRuta.addActionListener(e -> actualizarTabla.run());
        rdbPaseo.addActionListener(e -> actualizarTabla.run());
        rdbExcursion.addActionListener(e -> actualizarTabla.run());

        panel.add(panelOpciones, BorderLayout.NORTH);
        panel.add(panelTabla, BorderLayout.CENTER);
        actualizarTabla.run();

        return panel;
    }

    /**
     * Crea una tabla para un tipo de servicio.
     *
     * @param tipo Tipo de servicio representado.
     * @return Tabla dentro de un panel desplazable.
     */
    private JScrollPane crearTabla(int tipo) {
        String[] columnas = obtenerColumnas(tipo);
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        for (Servicio servicio : ventana.getGestorEntidades()
                .getGestorServicios().getServicios()) {
            if (correspondeAlFiltro(servicio)
                    && correspondeAlTipo(servicio, tipo)) {
                modelo.addRow(obtenerFila(servicio, tipo));
            }
        }

        JTable tabla = new JTable(modelo);
        tabla.setAutoCreateRowSorter(true);
        return new JScrollPane(tabla);
    }

    /**
     * Determina si un servicio corresponde al resultado buscado.
     *
     * @param servicio Servicio que se desea evaluar.
     * @return true si debe mostrarse.
     */
    private boolean correspondeAlFiltro(Servicio servicio) {
        return servicioBuscado == null || servicio == servicioBuscado;
    }

    /**
     * Obtiene las columnas correspondientes a un tipo de servicio.
     *
     * @param tipo Tipo de servicio.
     * @return Nombres de las columnas.
     */
    private String[] obtenerColumnas(int tipo) {
        if (tipo <= 2) {
            return new String[]{
                "Nombre", "Fecha", "Guía", "Cupos", "Precio", "Detalle"
            };
        }

        return new String[]{
            "Nombre", "Fecha", "Cupos", "Precio", "Detalle"
        };
    }

    /**
     * Obtiene la fila correspondiente a un servicio.
     *
     * @param servicio Servicio que se desea mostrar.
     * @param tipo Tipo de servicio.
     * @return Datos de la fila.
     */
    private Object[] obtenerFila(Servicio servicio, int tipo) {
        String detalle = obtenerDetalle(servicio);

        if (tipo <= 2) {
            GuiaTuristico guia = obtenerGuia(servicio);
            return new Object[]{
                servicio.getNombre(),
                servicio.getFecha(),
                guia.getNombre(),
                servicio.getCuposMaximos(),
                "$" + servicio.getPrecioPorPersona(),
                detalle
            };
        }

        return new Object[]{
            servicio.getNombre(),
            servicio.getFecha(),
            servicio.getCuposMaximos(),
            "$" + servicio.getPrecioPorPersona(),
            detalle
        };
    }

    /**
     * Determina si un servicio corresponde al tipo indicado.
     *
     * @param servicio Servicio que se desea evaluar.
     * @param tipo Tipo esperado.
     * @return true si corresponde.
     */
    private boolean correspondeAlTipo(Servicio servicio, int tipo) {
        return tipo == 0 && servicio instanceof RutaGastronomica
                || tipo == 1 && servicio instanceof PaseoLacustre
                || tipo == 2 && servicio instanceof ExcursionCultural
                || tipo == 3 && servicio instanceof ServicioTransporte
                || tipo == 4 && servicio instanceof ServicioAlojamiento;
    }

    /**
     * Obtiene el guía de un servicio recreativo.
     *
     * @param servicio Servicio recreativo.
     * @return Guía asignado.
     */
    private GuiaTuristico obtenerGuia(Servicio servicio) {
        if (servicio instanceof RutaGastronomica ruta) {
            return ruta.getGuia();
        }

        if (servicio instanceof PaseoLacustre paseo) {
            return paseo.getGuia();
        }

        return ((ExcursionCultural) servicio).getGuia();
    }

    /**
     * Obtiene el detalle específico de un servicio.
     *
     * @param servicio Servicio consultado.
     * @return Detalle del servicio.
     */
    private String obtenerDetalle(Servicio servicio) {
        if (servicio instanceof RutaGastronomica ruta) {
            return ruta.getNumeroParadas() + " paradas";
        }

        if (servicio instanceof PaseoLacustre paseo) {
            return paseo.getEmbarcacion()
                    + " - " + paseo.getLugarDestino();
        }

        if (servicio instanceof ExcursionCultural excursion) {
            return excursion.getLugarDestino();
        }

        if (servicio instanceof ServicioTransporte transporte) {
            return transporte.getEmpresa().getNombre()
                    + " - " + transporte.getVehiculo().getPatente();
        }

        ServicioAlojamiento alojamiento = (ServicioAlojamiento) servicio;
        return alojamiento.getEmpresa().getNombre()
                + " - " + alojamiento.getAlojamiento().getNombre();
    }

    /**
     * Selecciona la pestaña correspondiente a un servicio encontrado.
     *
     * @param servicio Servicio encontrado.
     */
    private void seleccionarPestaniaServicio(Servicio servicio) {
        if (servicio instanceof ServicioTransporte) {
            pestanias.setSelectedIndex(1);
        } else if (servicio instanceof ServicioAlojamiento) {
            pestanias.setSelectedIndex(2);
        } else {
            pestanias.setSelectedIndex(0);
        }
    }
}
