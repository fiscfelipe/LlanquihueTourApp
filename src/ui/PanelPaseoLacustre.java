package ui;

import java.awt.*;
import javax.swing.*;
import model.PaseoLacustre;

/**
 * Panel para registrar un Paseo Lacustre.
 */
public class PanelPaseoLacustre extends PanelBase {

    private JTextField txtNombre;
    private JTextField txtDuracionHoras;
    private JTextField txtTipoEmbarcacion;

    private JButton btnRegistrar;
    private JButton btnVolver;

    /**
     * Constructor del panel.
     *
     * @param ventana Ventana principal.
     */
    public PanelPaseoLacustre(VentanaPrincipal ventana) {

        super(ventana);

        inicializarComponentes();

    }

    /**
     * Inicializa los componentes.
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

        panelFormulario.add(lblTitulo, gbc);

        // Subtítulo
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 15, 5);

        panelFormulario.add(crearSubtitulo("Registrar Paseo Lacustre"), gbc);

        gbc.gridwidth = 1;

        // Campos
        txtNombre = crearCampoFormulario(panelFormulario, "Nombre:", gbc, 2);

        txtDuracionHoras = crearCampoFormulario(panelFormulario, "Duración (horas):", gbc, 3);

        txtTipoEmbarcacion = crearCampoFormulario(panelFormulario, "Tipo de embarcación:", gbc, 4);


        // Botones
        btnRegistrar = crearBoton("Registrar");
        btnVolver = crearBoton("Volver");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnVolver);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 5, 5, 5);

        panelFormulario.add(panelBotones, gbc);

        add(panelFormulario);


        // Eventos
        btnRegistrar.addActionListener(e -> registrarPaseo());

        btnVolver.addActionListener(e -> ventana.cambiarPanel(new PanelMenuAgregarRegistro(ventana)));

    }

    /**
     * Registra un paseo lacustre.
     */
    private void registrarPaseo() {

        try {

            String nombre = txtNombre.getText().trim();

            String tipoEmbarcacion = txtTipoEmbarcacion.getText().trim();

            if (nombre.isEmpty() || txtDuracionHoras.getText().trim().isEmpty() || tipoEmbarcacion.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Todos los campos son obligatorios.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);

                return;

            }

            int duracion = Integer.parseInt(txtDuracionHoras.getText().trim());

            PaseoLacustre paseo = new PaseoLacustre(nombre, duracion, tipoEmbarcacion);

            ventana.getGestorEntidades().agregarEntidad(paseo);

            JOptionPane.showMessageDialog(
                    this,
                    "Paseo lacustre registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La duración debe ser un número entero.",
                    "Dato inválido",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * Limpia el formulario.
     */
    private void limpiarFormulario() {

        txtNombre.setText("");
        txtDuracionHoras.setText("");
        txtTipoEmbarcacion.setText("");

        txtNombre.requestFocus();

    }

}