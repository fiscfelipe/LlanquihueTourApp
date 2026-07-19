package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import model.Cliente;
import model.Reserva;
import model.Servicio;

/**
 * Panel que permite confirmar la creación de una reserva para un cliente.
 */
public class PanelConfirmarReserva extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel de confirmación de una reserva.
     *
     * @param v Ventana principal de la aplicación.
     * @param cliente Cliente que realizará la reserva.
     * @param servicio Servicio que será reservado.
     */
    public PanelConfirmarReserva(VentanaPrincipal v, Cliente cliente, Servicio servicio) {

        super(v, "Confirmación de Reserva");

        JPanel centro = new JPanel(new BorderLayout(10, 10));

        centro.add(new JLabel("Cliente: " + cliente.getRut() + " - " + cliente.getNombre()), BorderLayout.NORTH);
        centro.add(areaDatos(servicio.mostrarDatos()), BorderLayout.CENTER);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, servicio.getCuposMaximos(), 1));

        JLabel total = new JLabel("$0");

        JPanel datos = new JPanel(new GridLayout(2, 2, 12, 8));

        datos.add(new JLabel("Cantidad de personas:"));
        datos.add(spinner);
        datos.add(new JLabel("Precio Total:"));
        datos.add(total);

        centro.add(datos, BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);

        JButton volver = boton("Volver");
        JButton crear = boton("Crear Reserva");

        crear.setEnabled(false);

        spinner.addChangeListener(e -> {

            int n = (Integer) spinner.getValue();

            crear.setEnabled(n > 0);

            total.setText(NumberFormat.getCurrencyInstance(new Locale("es", "CL")).format((long) n * servicio.getPrecioPorPersona()));

        });

        volver.addActionListener(e -> v.mostrarNuevaReserva(cliente));

        crear.addActionListener(e -> {

            try {

                Reserva r = v.getGestorEntidades().getGestorReservas().crearReserva(cliente, servicio, (Integer) spinner.getValue());

                JOptionPane.showMessageDialog(this,
                        "Reserva registrada correctamente.\n"
                        + "Código: "
                        + String.format("R-%05d", r.getCodigo()));

                v.mostrarAdministrarReservas(cliente);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        mensaje(ex),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        });

        add(barra(volver, crear), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}