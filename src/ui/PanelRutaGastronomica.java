package ui;

import java.awt.*;

import javax.swing.*;

import model.RutaGastronomica;

/**
 * Panel para registrar una Ruta Gastronómica.
 */
public class PanelRutaGastronomica extends PanelBase {

    private JTextField txtNombre;
    private JTextField txtDuracionHoras;
    private JTextField txtNumeroParadas;

    private JButton btnRegistrar;
    private JButton btnVolver;

    /**
     * Constructor del panel.
     *
     * @param ventana Ventana principal.
     */
    public PanelRutaGastronomica(VentanaPrincipal ventana) {

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

        panelFormulario.add(lblTitulo, gbc);


        // Subtítulo
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 15, 5);

        panelFormulario.add(crearSubtitulo("Registrar Ruta Gastronómica"), gbc);

        gbc.gridwidth = 1;
        
        // Campos
        txtNombre = crearCampoFormulario(panelFormulario, "Nombre:", gbc, 2);

        txtDuracionHoras = crearCampoFormulario(panelFormulario, "Duración (horas):", gbc, 3);

        txtNumeroParadas = crearCampoFormulario(panelFormulario, "Número de paradas:", gbc, 4);

        
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
        btnRegistrar.addActionListener(e -> registrarRuta());

        btnVolver.addActionListener(e -> ventana.cambiarPanel(new PanelMenuAgregarRegistro(ventana)));

    }

    /**
     * Registra una ruta gastronómica.
     */
    private void registrarRuta() {

        try {

            String nombre = txtNombre.getText().trim();

            if (nombre.isEmpty() || txtDuracionHoras.getText().trim().isEmpty() || txtNumeroParadas.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Todos los campos son obligatorios.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);

                return;

            }

            int duracion = Integer.parseInt(txtDuracionHoras.getText().trim());

            int numeroParadas = Integer.parseInt(txtNumeroParadas.getText().trim());

            RutaGastronomica ruta = new RutaGastronomica(nombre, duracion, numeroParadas);

            ventana.getGestorEntidades().agregarEntidad(ruta);

            JOptionPane.showMessageDialog(
                    this,
                    "Ruta gastronómica registrada correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La duración y el número de paradas deben ser valores enteros.",
                    "Datos inválidos",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * Limpia el formulario.
     */
    private void limpiarFormulario() {

        txtNombre.setText("");
        txtDuracionHoras.setText("");
        txtNumeroParadas.setText("");

        txtNombre.requestFocus();

    }

}