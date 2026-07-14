package ui;

import java.awt.*;
import javax.swing.*;
import model.Alojamiento;
import model.Correo;
import model.Direccion;
import model.ProveedorAlojamiento;
import model.Rut;
import util.CorreoInvalidoException;
import util.RutInvalidoException;

/**
 * Panel para registrar un proveedor de alojamiento.
 */
public class PanelProveedorAlojamiento extends PanelBase {

    // Datos del proveedor
    private JTextField txtNombre;
    private JTextField txtRut;
    private JTextField txtCorreo;
    private JTextField txtCalle;
    private JTextField txtNumero;
    private JTextField txtCiudad;
    private JTextField txtRegion;

    // Datos del alojamiento
    private JTextField txtNombreAlojamiento;
    private JTextField txtCalleAlojamiento;
    private JTextField txtNumeroAlojamiento;
    private JTextField txtCiudadAlojamiento;
    private JTextField txtRegionAlojamiento;
    private JTextField txtHabitacionesDisponibles;

    // Botones
    private JButton btnRegistrar;
    private JButton btnVolver;

    /**
     * Constructor del panel.
     *
     * @param ventana Ventana principal.
     */
    public PanelProveedorAlojamiento(VentanaPrincipal ventana) {
        super(ventana);
        inicializarComponentes();
    }

    /**
     * Inicializa los componentes del panel.
     */
    private void inicializarComponentes() {

        setLayout(new GridBagLayout());

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 10, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        panelFormulario.add(lblTitulo, gbc);

        // Subtítulo
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 15, 5);

        panelFormulario.add(crearSubtitulo("Registrar Proveedor de Alojamiento"), gbc
        );

        gbc.gridwidth = 1;

        // Datos del proveedor
        txtNombre = crearCampoFormulario(panelFormulario, "Nombre:", gbc, 2);

        txtRut = crearCampoFormulario(panelFormulario, "RUT:", gbc, 3);

        txtCorreo = crearCampoFormulario(panelFormulario, "Correo:", gbc, 4);

        txtCalle = crearCampoFormulario(panelFormulario, "Calle:", gbc, 5);

        txtNumero = crearCampoFormulario(panelFormulario, "Número:", gbc, 6);

        txtCiudad = crearCampoFormulario(panelFormulario, "Ciudad:", gbc, 7);

        txtRegion = crearCampoFormulario(panelFormulario, "Región:", gbc, 8);

        // Datos del alojamiento
        txtNombreAlojamiento = crearCampoFormulario(panelFormulario, "Nombre Alojamiento:", gbc, 9);

        txtCalleAlojamiento = crearCampoFormulario(panelFormulario, "Calle Alojamiento:", gbc, 10);

        txtNumeroAlojamiento = crearCampoFormulario(panelFormulario, "Número Alojamiento:", gbc, 11);

        txtCiudadAlojamiento = crearCampoFormulario(panelFormulario, "Ciudad Alojamiento:", gbc, 12);

        txtRegionAlojamiento = crearCampoFormulario(panelFormulario, "Región Alojamiento:", gbc, 13);

        txtHabitacionesDisponibles = crearCampoFormulario(panelFormulario, "Habitaciones Disponibles:", gbc, 14);

        // Botones
        btnRegistrar = crearBoton("Registrar");
        btnVolver = crearBoton("Volver");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnVolver);

        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.insets = new Insets(20, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        panelFormulario.add(panelBotones, gbc);

        add(panelFormulario);

        // Eventos
        btnRegistrar.addActionListener(e -> registrarProveedor());

        btnVolver.addActionListener(e -> ventana.cambiarPanel(new PanelMenuAgregarRegistro(ventana)));
    }

    /**
     * Valida los datos, crea el proveedor de alojamiento y lo agrega
     * al gestor de entidades.
     */
    private void registrarProveedor() {

        try {
            String nombre = txtNombre.getText().trim();
            String rutIngresado = txtRut.getText().trim();
            String correoIngresado = txtCorreo.getText().trim();
            String calle = txtCalle.getText().trim();
            String numeroIngresado = txtNumero.getText().trim();
            String ciudad = txtCiudad.getText().trim();
            String region = txtRegion.getText().trim();

            String nombreAlojamiento =
                    txtNombreAlojamiento.getText().trim();
            String calleAlojamiento =
                    txtCalleAlojamiento.getText().trim();
            String numeroAlojamientoIngresado =
                    txtNumeroAlojamiento.getText().trim();
            String ciudadAlojamiento =
                    txtCiudadAlojamiento.getText().trim();
            String regionAlojamiento =
                    txtRegionAlojamiento.getText().trim();
            String habitacionesIngresadas =
                    txtHabitacionesDisponibles.getText().trim();

            if (nombre.isEmpty() || rutIngresado.isEmpty() || correoIngresado.isEmpty() || calle.isEmpty() || numeroIngresado.isEmpty() || ciudad.isEmpty() || region.isEmpty() || nombreAlojamiento.isEmpty() || calleAlojamiento.isEmpty() || numeroAlojamientoIngresado.isEmpty() || ciudadAlojamiento.isEmpty() || regionAlojamiento.isEmpty() || habitacionesIngresadas.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Todos los campos son obligatorios.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int numero = Integer.parseInt(numeroIngresado);
            int numeroAlojamiento = Integer.parseInt(numeroAlojamientoIngresado);
            int habitacionesDisponibles = Integer.parseInt(habitacionesIngresadas);

            Rut rut = new Rut(rutIngresado);
            Correo correo = new Correo(correoIngresado);

            Direccion direccionProveedor = new Direccion(calle, numero, ciudad, region);

            Direccion direccionAlojamiento = new Direccion(calleAlojamiento, numeroAlojamiento, ciudadAlojamiento, regionAlojamiento);

            Alojamiento alojamiento = new Alojamiento(nombreAlojamiento, habitacionesDisponibles, direccionAlojamiento);

            ProveedorAlojamiento proveedor = new ProveedorAlojamiento(nombre, rut, correo, direccionProveedor, alojamiento);

            ventana.getGestorEntidades().agregarEntidad(proveedor);

            JOptionPane.showMessageDialog(
                    this,
                    "Proveedor de alojamiento registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarFormulario();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Los números de dirección y las habitaciones disponibles "
                    + "deben ser valores enteros.",
                    "Datos numéricos inválidos",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (RutInvalidoException | CorreoInvalidoException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Datos inválidos",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Datos inválidos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarFormulario() {

        txtNombre.setText("");
        txtRut.setText("");
        txtCorreo.setText("");
        txtCalle.setText("");
        txtNumero.setText("");
        txtCiudad.setText("");
        txtRegion.setText("");
        txtNombreAlojamiento.setText("");
        txtCalleAlojamiento.setText("");
        txtNumeroAlojamiento.setText("");
        txtCiudadAlojamiento.setText("");
        txtRegionAlojamiento.setText("");
        txtHabitacionesDisponibles.setText("");

        txtNombre.requestFocus();
    }
}
