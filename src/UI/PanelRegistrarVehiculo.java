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
import model.EmpresaTransporte;
import model.Patente;
import model.Vehiculo;

/**
 * Permite registrar un vehículo para una empresa de transporte.
 */
public class PanelRegistrarVehiculo extends PanelBase {

    // Atributos

    private final EmpresaTransporte empresa;

    private final JTextField txtTipo = new JTextField(25);
    private final JTextField txtPatente = new JTextField(25);
    private final JTextField txtCapacidad = new JTextField(25);

    // Constructor

    /**
     * Construye el formulario para registrar un vehículo.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param empresa Empresa a la que pertenecerá el vehículo.
     */
    public PanelRegistrarVehiculo(VentanaPrincipal ventana, EmpresaTransporte empresa) {

        super(ventana, "Registrar Vehículo");

        this.empresa = empresa;

        JPanel formulario = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        String[] nombres = {
            "Tipo de vehículo",
            "Patente",
            "Asientos disponibles"
        };

        JTextField[] campos = {
            txtTipo,
            txtPatente,
            txtCapacidad
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
        JButton registrar = boton("Registrar Vehículo");

        volver.addActionListener(e -> ventana.mostrarAdministrarVehiculos(empresa));
        registrar.addActionListener(e -> registrar());

        add(barra(volver, registrar), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

    /**
     * Registra un nuevo vehículo para la empresa seleccionada.
     */
    private void registrar() {

        List<String> errores = new ArrayList<>();

        Integer capacidad = null;
        Patente patente = null;

        if (txtTipo.getText().trim().isEmpty()) {

            errores.add("El tipo de vehículo no puede estar vacío.");

        }

        try {

            capacidad = Integer.valueOf(txtCapacidad.getText().trim());

        } catch (Exception ex) {

            errores.add("Los asientos disponibles deben corresponder a un número entero.");

        }

        try {

            patente = new Patente(txtPatente.getText());

        } catch (Exception ex) {

            errores.add(mensaje(ex));

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

            empresa.agregarVehiculo(new Vehiculo( txtTipo.getText(), patente, capacidad));

            JOptionPane.showMessageDialog(
                    this,
                    "Vehículo registrado correctamente.");

            txtTipo.setText("");
            txtPatente.setText("");
            txtCapacidad.setText("");

            txtTipo.requestFocusInWindow();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    mensaje(ex),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

}