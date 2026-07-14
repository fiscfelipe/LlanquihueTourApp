package ui;

import java.awt.*;
import javax.swing.*;

import model.Correo;
import model.Direccion;
import model.GuiaTuristico;
import model.Rut;

import util.CorreoInvalidoException;
import util.RutInvalidoException;

/**
 * Panel para registrar un guía turístico.
 */
public class PanelGuiaTuristico extends PanelBase {

    // Campos del formulario
    private JTextField txtNombre;
    private JTextField txtRut;
    private JTextField txtCorreo;
    private JTextField txtCalle;
    private JTextField txtNumero;
    private JTextField txtCiudad;
    private JTextField txtRegion;
    private JTextField txtEspecialidad;
    private JTextField txtIdiomas;
    private JTextField txtAniosExperiencia;

    // Botones
    private JButton btnRegistrar;
    private JButton btnVolver;

    /**
     * Constructor del panel.
     *
     * @param ventana Ventana principal.
     */
    public PanelGuiaTuristico(VentanaPrincipal ventana) {

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

        panelFormulario.add(crearSubtitulo("Registrar Guía Turístico"), gbc);

        gbc.gridwidth = 1;

        // Campos del formulario
        txtNombre = crearCampoFormulario(panelFormulario, "Nombre:", gbc, 2);

        txtRut = crearCampoFormulario(panelFormulario, "RUT:", gbc, 3);

        txtCorreo = crearCampoFormulario(panelFormulario, "Correo:", gbc, 4);

        txtCalle = crearCampoFormulario(panelFormulario, "Calle:", gbc, 5);

        txtNumero = crearCampoFormulario(panelFormulario, "Número:", gbc, 6);

        txtCiudad = crearCampoFormulario(panelFormulario, "Ciudad:", gbc, 7);

        txtRegion = crearCampoFormulario(panelFormulario, "Región:", gbc, 8);

        txtEspecialidad = crearCampoFormulario(panelFormulario, "Especialidad:", gbc, 9);

        txtIdiomas = crearCampoFormulario(panelFormulario, "Idiomas:", gbc, 10);

        txtAniosExperiencia = crearCampoFormulario(panelFormulario, "Años de Experiencia:", gbc, 11);

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
        btnRegistrar.addActionListener(e -> registrarGuia());

        btnVolver.addActionListener(e -> ventana.cambiarPanel(new PanelMenuAgregarRegistro(ventana)));
    }

    /**
     * Valida los datos ingresados, crea el guía turístico
     * y lo agrega al gestor de entidades.
     */
    private void registrarGuia() {

        try {

            String nombre = txtNombre.getText().trim();
            String rutIngresado = txtRut.getText().trim();
            String correoIngresado = txtCorreo.getText().trim();
            String calle = txtCalle.getText().trim();
            String numeroIngresado = txtNumero.getText().trim();
            String ciudad = txtCiudad.getText().trim();
            String region = txtRegion.getText().trim();
            String especialidad = txtEspecialidad.getText().trim();
            String idiomas = txtIdiomas.getText().trim();
            String experienciaIngresada =
                    txtAniosExperiencia.getText().trim();

            int numero = Integer.parseInt(numeroIngresado);
            int aniosExperiencia = Integer.parseInt(experienciaIngresada);

            Rut rut = new Rut(rutIngresado);
            Correo correo = new Correo(correoIngresado);

            Direccion direccion = new Direccion(calle, numero, ciudad, region);

            GuiaTuristico guia = new GuiaTuristico(nombre, rut, correo, direccion, especialidad, idiomas, aniosExperiencia);

            ventana.getGestorEntidades().agregarEntidad(guia);

            JOptionPane.showMessageDialog(
                    this,
                    "Guía turístico registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarFormulario();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El número de dirección y los años de experiencia deben ser valores enteros.",
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
        txtEspecialidad.setText("");
        txtIdiomas.setText("");
        txtAniosExperiencia.setText("");

        txtNombre.requestFocus();
    }
}
