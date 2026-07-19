package UI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Alojamiento;
import model.Direccion;
import model.EmpresaAlojamiento;

/**
 * Permite registrar un nuevo alojamiento para una empresa de alojamiento.
 */
public class PanelRegistrarAlojamiento extends PanelBase {

    // Atributos

    private final EmpresaAlojamiento empresa;

    private final JTextField txtNombre = new JTextField(25);
    private final JTextField txtTipo = new JTextField(25);
    private final JTextField txtCapacidad = new JTextField(25);
    private final JTextField txtCalle = new JTextField(25);
    private final JTextField txtNumero = new JTextField(25);
    private final JTextField txtCiudad = new JTextField(25);
    private final JTextField txtRegion = new JTextField(25);

    // Constructor

    /**
     * Construye el formulario para registrar un alojamiento.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param empresa Empresa a la que pertenecerá el alojamiento.
     */
    public PanelRegistrarAlojamiento(VentanaPrincipal ventana, EmpresaAlojamiento empresa) {

        super(ventana, "Registrar Alojamiento");

        this.empresa = empresa;

        JPanel formulario = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 8, 6, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        String[] nombres = {
            "Nombre",
            "Tipo de alojamiento",
            "Capacidad",
            "Calle",
            "Número",
            "Ciudad",
            "Región"
        };

        JTextField[] campos = {
            txtNombre,
            txtTipo,
            txtCapacidad,
            txtCalle,
            txtNumero,
            txtCiudad,
            txtRegion
        };

        for (int i = 0; i < nombres.length; i++) {

            c.gridx = 0;
            c.gridy = i;
            c.weightx = 0;

            formulario.add(new JLabel(nombres[i] + ":"), c);

            c.gridx = 1;
            c.weightx = 1;

            formulario.add(campos[i], c);

        }

        add(formulario, BorderLayout.CENTER);

        JButton volver = boton("Volver");
        JButton registrar = boton("Registrar Alojamiento");

        volver.addActionListener(e -> ventana.mostrarAdministrarAlojamientos(empresa));
        registrar.addActionListener(e -> registrar());

        add(barra(volver, registrar), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

    /**
     * Registra un alojamiento para la empresa seleccionada.
     */
    private void registrar() {

        List<String> errores = new ArrayList<>();

        Integer capacidad = null;
        Integer numero = null;
        Direccion direccion = null;

        if (txtNombre.getText().trim().isEmpty()) {
            errores.add("El nombre del alojamiento no puede estar vacío.");
        }

        if (txtTipo.getText().trim().isEmpty()) {
            errores.add("El tipo de alojamiento no puede estar vacío.");
        }

        try {

            capacidad = Integer.valueOf(txtCapacidad.getText().trim());

        } catch (Exception ex) {

            errores.add("La capacidad debe corresponder a un número entero.");

        }

        try {

            numero = Integer.valueOf(txtNumero.getText().trim());

        } catch (Exception ex) {

            errores.add("El número de la dirección debe corresponder a un número entero.");

        }

        if (numero != null) {

            try {

                direccion = new Direccion(txtCalle.getText(), numero, txtCiudad.getText(), txtRegion.getText());

            } catch (Exception ex) {

                errores.add(mensaje(ex));

            }

        }

        if (!errores.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    String.join("\n", errores),
                    "Errores de validación",
                    JOptionPane.ERROR_MESSAGE);

            return;

        }

        try {

            empresa.agregarTipoAlojamiento(new Alojamiento(txtNombre.getText(), txtTipo.getText(), capacidad, direccion));

            JOptionPane.showMessageDialog(this,
                    "Alojamiento registrado correctamente.");

            for (JTextField campo : new JTextField[]{
                txtNombre,
                txtTipo,
                txtCapacidad,
                txtCalle,
                txtNumero,
                txtCiudad,
                txtRegion
            }) {
                campo.setText("");
            }

            txtNombre.requestFocusInWindow();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    mensaje(ex),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

}