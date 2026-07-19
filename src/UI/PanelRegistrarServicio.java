package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import model.Alojamiento;
import model.EmpresaAlojamiento;
import model.EmpresaExterna;
import model.EmpresaTransporte;
import model.ExcursionCultural;
import model.GuiaTuristico;
import model.PaseoLacustre;
import model.RutaGastronomica;
import model.Servicio;
import model.ServicioAlojamiento;
import model.ServicioTransporte;
import model.Vehiculo;

/**
 * Permite registrar cualquiera de los servicios turísticos disponibles.
 */
public class PanelRegistrarServicio extends PanelBase {

    // Atributos
    private final int tipo;
    private final JTextField txtNombre;
    private final JTextField txtFecha;
    private final JTextField txtHora;
    private final JTextField txtDuracion;
    private final JTextField txtCupos;
    private final JTextField txtPrecio;
    private final JTextField txtExtraUno;
    private final JTextField txtExtraDos;
    private final DefaultListModel<EntidadItem<GuiaTuristico>> modeloGuias;
    private final JList<EntidadItem<GuiaTuristico>> listaGuias;
    private final DefaultListModel<EntidadItem<EmpresaExterna>> modeloEmpresas;
    private final JList<EntidadItem<EmpresaExterna>> listaEmpresas;
    private final DefaultListModel<EntidadItem<Object>> modeloRecursos;
    private final JList<EntidadItem<Object>> listaRecursos;
    private final JButton btnRegistrar;

    // Constructor

    /**
     * Construye el formulario correspondiente al tipo de servicio indicado.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param tipo Tipo de servicio seleccionado.
     */
    public PanelRegistrarServicio(VentanaPrincipal ventana, int tipo) {
        super(ventana, obtenerTitulo(tipo));

        this.tipo = tipo;
        txtNombre = new JTextField();
        txtFecha = new JTextField();
        txtHora = new JTextField();
        txtDuracion = new JTextField();
        txtCupos = new JTextField();
        txtPrecio = new JTextField();
        txtExtraUno = new JTextField();
        txtExtraDos = new JTextField();
        modeloGuias = new DefaultListModel<>();
        listaGuias = new JList<>(modeloGuias);
        modeloEmpresas = new DefaultListModel<>();
        listaEmpresas = new JList<>(modeloEmpresas);
        modeloRecursos = new DefaultListModel<>();
        listaRecursos = new JList<>(modeloRecursos);
        btnRegistrar = boton("Registrar");

        JPanel formulario = crearFormulario();
        add(new JScrollPane(formulario), BorderLayout.CENTER);

        JButton btnVolver = boton("Volver");
        JButton btnLimpiar = boton("Limpiar");

        btnVolver.addActionListener(e -> ventana.mostrarSeleccionTipoServicio());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnRegistrar.addActionListener(e -> registrarServicio());

        add(barra(btnVolver, btnLimpiar, btnRegistrar), BorderLayout.SOUTH);

        listaEmpresas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarRecursos();
            }
        });
        listaGuias.addListSelectionListener(e -> actualizarBotonRegistrar());
        listaRecursos.addListSelectionListener(e -> actualizarBotonRegistrar());

        cargarDatos();
        actualizarBotonRegistrar();
    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

    private JPanel crearFormulario() {
        JPanel formulario = new JPanel(new GridBagLayout());
        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.insets = new Insets(3, 7, 3, 7);
        restricciones.fill = GridBagConstraints.HORIZONTAL;

        int fila = 0;
        fila = agregarCampo(formulario, restricciones, fila, "Nombre", txtNombre);
        fila = agregarCampo(
                formulario, restricciones, fila,
                "Fecha (dd/MM/yyyy)", txtFecha);
        fila = agregarCampo(
                formulario, restricciones, fila,
                "Hora de inicio (HH:mm)", txtHora);
        fila = agregarCampo(
                formulario, restricciones, fila,
                tipo == 4 ? "Duración (días)" : "Duración (HH:mm)",
                txtDuracion);
        fila = agregarCampo(
                formulario, restricciones, fila,
                "Cupos máximos", txtCupos);
        fila = agregarCampo(
                formulario, restricciones, fila,
                "Precio por persona", txtPrecio);

        if (esServicioRecreativo()) {
            fila = agregarLista(
                    formulario, restricciones, fila,
                    "Guía turístico", listaGuias);
        }

        if (tipo == 0) {
            agregarCampo(
                    formulario, restricciones, fila,
                    "Número de paradas", txtExtraUno);
        } else if (tipo == 1) {
            fila = agregarCampo(
                    formulario, restricciones, fila,
                    "Tipo de embarcación", txtExtraUno);
            agregarCampo(
                    formulario, restricciones, fila,
                    "Destino", txtExtraDos);
        } else if (tipo == 2) {
            agregarCampo(
                    formulario, restricciones, fila,
                    "Destino", txtExtraUno);
        } else {
            fila = agregarLista(
                    formulario,
                    restricciones,
                    fila,
                    tipo == 3
                            ? "Empresa de transporte"
                            : "Empresa de alojamiento",
                    listaEmpresas);
            fila = agregarLista(
                    formulario,
                    restricciones,
                    fila,
                    tipo == 3 ? "Vehículo" : "Alojamiento",
                    listaRecursos);

            if (tipo == 3) {
                fila = agregarCampo(
                        formulario, restricciones, fila,
                        "Lugar de origen", txtExtraUno);
                agregarCampo(
                        formulario, restricciones, fila,
                        "Lugar de destino", txtExtraDos);
            }
        }

        return formulario;
    }

    private static String obtenerTitulo(int tipo) {
        String[] titulos = {
            "Registrar Ruta Gastronómica",
            "Registrar Paseo Lacustre",
            "Registrar Excursión Cultural",
            "Registrar Servicio de Transporte",
            "Registrar Servicio de Alojamiento"
        };

        return titulos[tipo];
    }

    private int agregarCampo(
            JPanel panel,
            GridBagConstraints restricciones,
            int fila,
            String nombre,
            JTextField campo) {

        restricciones.gridy = fila;
        restricciones.gridx = 0;
        restricciones.weightx = 0;
        panel.add(new JLabel(nombre + ":"), restricciones);

        restricciones.gridx = 1;
        restricciones.weightx = 1;
        panel.add(campo, restricciones);

        return fila + 1;
    }

    private int agregarLista(
            JPanel panel,
            GridBagConstraints restricciones,
            int fila,
            String nombre,
            JList<?> lista) {

        restricciones.gridy = fila;
        restricciones.gridx = 0;
        restricciones.weightx = 0;
        panel.add(new JLabel(nombre + ":"), restricciones);

        restricciones.gridx = 1;
        restricciones.weightx = 1;
        JScrollPane desplazamiento = new JScrollPane(lista);
        desplazamiento.setPreferredSize(new Dimension(500, 72));
        panel.add(desplazamiento, restricciones);

        return fila + 1;
    }

    private void cargarDatos() {
        if (esServicioRecreativo()) {
            cargarGuias();
        } else {
            cargarEmpresas();
        }
    }

    private void cargarGuias() {
        for (GuiaTuristico guia : ventana.getGestorEntidades()
                .getGestorPersonas().getGuiasTuristicos()) {
            modeloGuias.addElement(new EntidadItem<>(
                    guia,
                    guia.getRut() + " - "
                    + guia.getNombre() + " - "
                    + guia.getEspecialidad()));
        }

        if (modeloGuias.isEmpty()) {
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                    this,
                    "No existen guías turísticos registrados."));
        }
    }

    private void cargarEmpresas() {
        for (EmpresaExterna empresa : ventana.getGestorEntidades()
                .getGestorEmpresas().getEmpresas()) {
            boolean correspondeTransporte
                    = tipo == 3 && empresa instanceof EmpresaTransporte;
            boolean correspondeAlojamiento
                    = tipo == 4 && empresa instanceof EmpresaAlojamiento;

            if (correspondeTransporte || correspondeAlojamiento) {
                modeloEmpresas.addElement(
                        new EntidadItem<>(empresa, empresa.getNombre()));
            }
        }

        if (modeloEmpresas.isEmpty()) {
            String mensaje = tipo == 3
                    ? "No existen empresas de transporte registradas."
                    : "No existen empresas de alojamiento registradas.";

            SwingUtilities.invokeLater(
                    () -> JOptionPane.showMessageDialog(this, mensaje));
        }
    }

    private void cargarRecursos() {
        modeloRecursos.clear();
        EntidadItem<EmpresaExterna> item = listaEmpresas.getSelectedValue();

        if (item == null) {
            actualizarBotonRegistrar();
            return;
        }

        EmpresaExterna empresa = item.getEntidad();

        if (empresa instanceof EmpresaTransporte empresaTransporte) {
            for (Vehiculo vehiculo : empresaTransporte.getVehiculos()) {
                modeloRecursos.addElement(new EntidadItem<Object>(
                        vehiculo,
                        vehiculo.getPatente() + " - "
                        + vehiculo.getTipo() + " - "
                        + vehiculo.getCapacidad() + " asientos"));
            }
        } else if (empresa instanceof EmpresaAlojamiento empresaAlojamiento) {
            for (Alojamiento alojamiento : empresaAlojamiento.getAlojamientos()) {
                modeloRecursos.addElement(new EntidadItem<Object>(
                        alojamiento,
                        alojamiento.getNombre() + " - Capacidad: "
                        + alojamiento.getCapacidad() + " personas"));
            }
        }

        actualizarBotonRegistrar();
    }

    private void actualizarBotonRegistrar() {
        boolean seleccionValida;

        if (esServicioRecreativo()) {
            seleccionValida = !modeloGuias.isEmpty()
                    && listaGuias.getSelectedValue() != null;
        } else {
            seleccionValida = listaEmpresas.getSelectedValue() != null
                    && listaRecursos.getSelectedValue() != null;
        }

        btnRegistrar.setEnabled(seleccionValida);
    }

    private void registrarServicio() {
        try {
            validarCamposObligatorios();

            LocalDate fecha = LocalDate.parse(
                    txtFecha.getText().trim(),
                    DateTimeFormatter.ofPattern("dd/MM/uuuu"));
            LocalTime hora = LocalTime.parse(txtHora.getText().trim());
            Duration duracion = tipo == 4
                    ? Duration.ofDays(Integer.parseInt(txtDuracion.getText().trim()))
                    : convertirDuracion(txtDuracion.getText());
            int cupos = Integer.parseInt(txtCupos.getText().trim());
            int precio = Integer.parseInt(txtPrecio.getText().trim());

            Servicio servicio = crearServicio(fecha, hora, duracion, cupos, precio);
            ventana.getGestorEntidades().getGestorServicios()
                    .agregarServicio(servicio);

            JOptionPane.showMessageDialog(
                    this,
                    "Servicio registrado exitosamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    mensaje(ex),
                    "Error de registro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private Servicio crearServicio(
            LocalDate fecha,
            LocalTime hora,
            Duration duracion,
            int cupos,
            int precio) {

        if (tipo == 0) {
            GuiaTuristico guia = listaGuias.getSelectedValue().getEntidad();
            return new RutaGastronomica(
                    txtNombre.getText(), fecha, hora, duracion,
                    cupos, precio, guia,
                    Integer.parseInt(txtExtraUno.getText().trim()));
        }

        if (tipo == 1) {
            GuiaTuristico guia = listaGuias.getSelectedValue().getEntidad();
            return new PaseoLacustre(
                    txtNombre.getText(), fecha, hora, duracion,
                    cupos, precio, guia,
                    txtExtraUno.getText(), txtExtraDos.getText());
        }

        if (tipo == 2) {
            GuiaTuristico guia = listaGuias.getSelectedValue().getEntidad();
            return new ExcursionCultural(
                    txtNombre.getText(), fecha, hora, duracion,
                    cupos, precio, guia, txtExtraUno.getText());
        }

        if (tipo == 3) {
            return new ServicioTransporte(
                    txtNombre.getText(), fecha, hora, duracion,
                    cupos, precio,
                    (EmpresaTransporte) listaEmpresas
                            .getSelectedValue().getEntidad(),
                    (Vehiculo) listaRecursos
                            .getSelectedValue().getEntidad(),
                    txtExtraUno.getText(), txtExtraDos.getText());
        }

        return new ServicioAlojamiento(
                txtNombre.getText(), fecha, hora, duracion,
                cupos, precio,
                (EmpresaAlojamiento) listaEmpresas
                        .getSelectedValue().getEntidad(),
                (Alojamiento) listaRecursos
                        .getSelectedValue().getEntidad());
    }

    private void validarCamposObligatorios() {
        JTextField[] campos = {
            txtNombre, txtFecha, txtHora, txtDuracion, txtCupos, txtPrecio
        };

        for (JTextField campo : campos) {
            if (campo.getText().trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Todos los campos son obligatorios.");
            }
        }

        if (tipo <= 3 && txtExtraUno.getText().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Todos los campos son obligatorios.");
        }

        if ((tipo == 1 || tipo == 3)
                && txtExtraDos.getText().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Todos los campos son obligatorios.");
        }
    }

    private Duration convertirDuracion(String texto) {
        String[] partes = texto.trim().split(":");

        if (partes.length != 2) {
            throw new IllegalArgumentException(
                    "La duración debe utilizar el formato HH:mm.");
        }

        return Duration.ofHours(Integer.parseInt(partes[0]))
                .plusMinutes(Integer.parseInt(partes[1]));
    }

    private void limpiarCampos() {
        JTextField[] campos = {
            txtNombre, txtFecha, txtHora, txtDuracion,
            txtCupos, txtPrecio, txtExtraUno, txtExtraDos
        };

        for (JTextField campo : campos) {
            campo.setText("");
        }

        listaGuias.clearSelection();
        listaEmpresas.clearSelection();
        modeloRecursos.clear();
        actualizarBotonRegistrar();
        txtNombre.requestFocusInWindow();
    }

    private boolean esServicioRecreativo() {
        return tipo >= 0 && tipo <= 2;
    }
}
