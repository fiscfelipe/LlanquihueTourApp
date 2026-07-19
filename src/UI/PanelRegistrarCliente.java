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
import model.Cliente;
import model.Correo;
import model.Direccion;
import model.Rut;
import model.Telefono;

/**
 * Permite registrar un nuevo cliente.
 */
public class PanelRegistrarCliente extends PanelBase {

    // Atributos

    private final JTextField txtNombre = new JTextField(25);
    private final JTextField txtRut = new JTextField(25);
    private final JTextField txtCorreo = new JTextField(25);
    private final JTextField txtTelefono = new JTextField(25);
    private final JTextField txtCalle = new JTextField(25);
    private final JTextField txtNumero = new JTextField(25);
    private final JTextField txtCiudad = new JTextField(25);
    private final JTextField txtRegion = new JTextField(25);

    // Constructor

    /**
     * Construye el formulario para registrar un cliente.
     *
     * @param v Ventana principal de la aplicación.
     */
    public PanelRegistrarCliente(VentanaPrincipal v) {

        super(v, "Registrar Cliente");

        JPanel formulario = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 8, 6, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        String[] nombres = {
            "Nombre",
            "RUT",
            "Correo",
            "Teléfono",
            "Calle",
            "Número",
            "Ciudad",
            "Región"
        };

        JTextField[] campos = {
            txtNombre,
            txtRut,
            txtCorreo,
            txtTelefono,
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
        JButton limpiar = boton("Limpiar");
        JButton registrar = boton("Registrar");

        volver.addActionListener(e -> v.mostrarMenuClientesReservas());
        limpiar.addActionListener(e -> limpiar());
        registrar.addActionListener(e -> registrar());

        add(barra(volver, limpiar, registrar), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

    /**
     * Registra un nuevo cliente.
     */
    private void registrar() {

        List<String> errores = new ArrayList<>();

        Integer numero = null;
        Rut rut = null;
        Correo correo = null;
        Telefono telefono = null;
        Direccion direccion = null;

        try {

            numero = Integer.valueOf(txtNumero.getText().trim());

        } catch (Exception ex) {

            errores.add("El número debe ser un entero válido.");

        }

        try {

            rut = new Rut(txtRut.getText());

        } catch (Exception ex) {

            errores.add(mensaje(ex));

        }

        try {

            correo = new Correo(txtCorreo.getText());

        } catch (Exception ex) {

            errores.add(mensaje(ex));

        }

        try {

            telefono = new Telefono(txtTelefono.getText());

        } catch (Exception ex) {

            errores.add(mensaje(ex));

        }

        if (numero != null) {

            try {

                direccion = new Direccion(
                        txtCalle.getText(),
                        numero,
                        txtCiudad.getText(),
                        txtRegion.getText());

            } catch (Exception ex) {

                errores.add(mensaje(ex));

            }

        }

        if (txtNombre.getText().trim().isEmpty()) {

            errores.add("El nombre no puede estar vacío.");

        }

        if (!errores.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    String.join("\n", errores),
                    "Errores de validación",
                    JOptionPane.ERROR_MESSAGE);

            return;

        }

        try {

            Cliente cliente = new Cliente(
                    txtNombre.getText(),
                    rut,
                    correo,
                    telefono,
                    direccion);

            ventana.getGestorEntidades().getGestorPersonas().agregarPersona(cliente);

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente registrado correctamente.");

            limpiar();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    mensaje(ex),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * Limpia los campos del formulario.
     */
    private void limpiar() {

        for (JTextField t : new JTextField[]{
            txtNombre,
            txtRut,
            txtCorreo,
            txtTelefono,
            txtCalle,
            txtNumero,
            txtCiudad,
            txtRegion
        }) {

            t.setText("");

        }

        txtNombre.requestFocusInWindow();

    }

}