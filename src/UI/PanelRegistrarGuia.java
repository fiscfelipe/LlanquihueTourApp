package UI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Correo;
import model.Direccion;
import model.GuiaTuristico;
import model.Rut;
import model.Telefono;

/**
 * Permite registrar un nuevo guía turístico.
 */
public class PanelRegistrarGuia extends PanelBase {

    // Atributos

    private final JTextField[] campos;

    // Constructor

    /**
     * Construye el formulario de registro de guías turísticos.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelRegistrarGuia(VentanaPrincipal ventana) {

        super(ventana, "Registrar Guía Turístico");

        campos = new JTextField[11];

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createEmptyBorder(0, 180, 0, 180));

        String[] nombres = {
            "Nombre",
            "RUT",
            "Correo electrónico",
            "Teléfono",
            "Calle",
            "Número",
            "Ciudad",
            "Región",
            "Especialidad",
            "Idiomas",
            "Años de experiencia"
        };

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.insets = new Insets(3, 8, 3, 8);
        restricciones.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < nombres.length; i++) {

            campos[i] = new JTextField(24);

            restricciones.gridy = i;
            restricciones.gridx = 0;
            restricciones.weightx = 0;

            formulario.add(new JLabel(nombres[i] + ":"), restricciones);

            restricciones.gridx = 1;
            restricciones.weightx = 1;

            formulario.add(campos[i], restricciones);

        }

        add(new JScrollPane(formulario), BorderLayout.CENTER);

        JButton btnVolver = boton("Volver");
        JButton btnLimpiar = boton("Limpiar");
        JButton btnRegistrar = boton("Registrar");

        btnVolver.addActionListener(e -> ventana.mostrarMenuGuias());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnRegistrar.addActionListener(e -> registrarGuia());

        add(barra(btnVolver, btnLimpiar, btnRegistrar), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

    /**
     * Registra un nuevo guía turístico.
     */
    private void registrarGuia() {

        List<String> errores = validarCamposVacios();

        if (!errores.isEmpty()) {

            mostrarErrores(errores);

            return;

        }

        try {

            int numero = Integer.parseInt(campos[5].getText().trim());
            int aniosExperiencia = Integer.parseInt(campos[10].getText().trim());

            GuiaTuristico guia = new GuiaTuristico(
                    campos[0].getText(),
                    new Rut(campos[1].getText()),
                    new Correo(campos[2].getText()),
                    new Telefono(campos[3].getText()),
                    new Direccion(
                            campos[4].getText(),
                            numero,
                            campos[6].getText(),
                            campos[7].getText()),
                    campos[8].getText(),
                    campos[9].getText(),
                    aniosExperiencia);

            ventana.getGestorEntidades()
                    .getGestorPersonas()
                    .agregarPersona(guia);

            JOptionPane.showMessageDialog(
                    this,
                    "Guía turístico registrado exitosamente.",
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

    /**
     * Valida que todos los campos del formulario hayan sido completados.
     *
     * @return Lista de errores encontrados.
     */
    private List<String> validarCamposVacios() {

        List<String> errores = new ArrayList<>();

        for (JTextField campo : campos) {

            if (campo.getText().trim().isEmpty()) {

                errores.add("Todos los campos son obligatorios.");

            }

        }

        return errores;

    }

    /**
     * Muestra los errores de validación encontrados.
     *
     * @param errores Lista de errores a mostrar.
     */
    private void mostrarErrores(List<String> errores) {

        JOptionPane.showMessageDialog(
                this,
                String.join("\n", new LinkedHashSet<>(errores)),
                "Errores de validación",
                JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarCampos() {

        for (JTextField campo : campos) {

            campo.setText("");

        }

        campos[0].requestFocusInWindow();

    }

}