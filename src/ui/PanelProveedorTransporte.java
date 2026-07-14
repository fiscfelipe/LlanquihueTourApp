package ui;

import java.awt.*;
import javax.swing.*;

import model.Correo;
import model.Direccion;
import model.Patente;
import model.ProveedorTransporte;
import model.Rut;
import model.Vehiculo;

import util.*;

/**
 * Panel para registrar un proveedor de transporte.
 */
public class PanelProveedorTransporte extends PanelBase {

    // Campos del formulario
    private JTextField txtNombre;
    private JTextField txtRut;
    private JTextField txtCorreo;
    private JTextField txtCalle;
    private JTextField txtNumero;
    private JTextField txtCiudad;
    private JTextField txtRegion;
    private JTextField txtTipoVehiculo;
    private JTextField txtPatente;
    private JTextField txtAsientosDisponibles;

    // Botones
    private JButton btnRegistrar;
    private JButton btnVolver;

    /**
     * Constructor del panel.
     *
     * @param ventana Ventana principal.
     */
    public PanelProveedorTransporte(VentanaPrincipal ventana) {

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

        panelFormulario.add(crearSubtitulo("Registrar Proveedor de Transporte"), gbc);

        gbc.gridwidth = 1;
        
        // Campos
        txtNombre = crearCampoFormulario(panelFormulario, "Nombre:", gbc, 2);

        txtRut = crearCampoFormulario(panelFormulario, "RUT:", gbc, 3);

        txtCorreo = crearCampoFormulario(panelFormulario, "Correo:", gbc, 4);

        txtCalle = crearCampoFormulario(panelFormulario, "Calle:", gbc, 5);

        txtNumero = crearCampoFormulario( panelFormulario, "Número:", gbc, 6);

        txtCiudad = crearCampoFormulario(panelFormulario, "Ciudad:", gbc, 7);

        txtRegion = crearCampoFormulario(panelFormulario, "Región:", gbc, 8);

        txtTipoVehiculo = crearCampoFormulario(panelFormulario, "Tipo de Vehículo:", gbc, 9);

        txtPatente = crearCampoFormulario(panelFormulario, "Patente:", gbc, 10);

        txtAsientosDisponibles = crearCampoFormulario(panelFormulario, "Asientos Disponibles:", gbc, 11);

        
        // Botones
        btnRegistrar = crearBoton("Registrar");
        btnVolver = crearBoton("Volver");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnVolver);

        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
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
     * Valida los datos ingresados, crea el proveedor de transporte
     * y lo agrega al gestor de entidades.
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
            String tipoVehiculo = txtTipoVehiculo.getText().trim();
            String patenteIngresada = txtPatente.getText().trim();
            String asientosIngresados =
                    txtAsientosDisponibles.getText().trim();

            if (nombre.isEmpty() || rutIngresado.isEmpty() || correoIngresado.isEmpty() || calle.isEmpty() || numeroIngresado.isEmpty() || ciudad.isEmpty() || region.isEmpty() || tipoVehiculo.isEmpty() || patenteIngresada.isEmpty() || asientosIngresados.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Todos los campos son obligatorios.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int numero = Integer.parseInt(numeroIngresado);
            int asientosDisponibles = Integer.parseInt(asientosIngresados);

            Rut rut = new Rut(rutIngresado);

            Correo correo = new Correo(correoIngresado);

            Direccion direccion = new Direccion(calle, numero, ciudad, region);

            Patente patente = new Patente(patenteIngresada);

            Vehiculo vehiculo = new Vehiculo(tipoVehiculo, patente, asientosDisponibles);

            ProveedorTransporte proveedor = new ProveedorTransporte(nombre, rut, correo, direccion, vehiculo);

            ventana.getGestorEntidades().agregarEntidad(proveedor);

            JOptionPane.showMessageDialog(
                    this,
                    "Proveedor de transporte registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarFormulario();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El número de dirección y los asientos disponibles "
                    + "deben ser valores enteros.",
                    "Datos numéricos inválidos",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (RutInvalidoException | CorreoInvalidoException | PatenteInvalidaException e) {

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
        txtTipoVehiculo.setText("");
        txtPatente.setText("");
        txtAsientosDisponibles.setText("");

        txtNombre.requestFocus();
    }
}