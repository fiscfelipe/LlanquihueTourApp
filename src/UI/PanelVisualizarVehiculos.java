package UI;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import model.EmpresaTransporte;
import model.Vehiculo;

/**
 * Muestra los vehículos registrados de una empresa de transporte.
 */
public class PanelVisualizarVehiculos extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel de visualización de vehículos.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param empresa Empresa de transporte cuyos vehículos serán visualizados.
     */
    public PanelVisualizarVehiculos(VentanaPrincipal ventana, EmpresaTransporte empresa) {

        super(ventana, "Visualizar Vehículos");

        DefaultListModel<EntidadItem<Vehiculo>> modelo = new DefaultListModel<>();

        for (Vehiculo vehiculo : empresa.getVehiculos()) {
            modelo.addElement(
                    new EntidadItem<>(vehiculo, vehiculo.getPatente().toString()));
        }

        JList<EntidadItem<Vehiculo>> lista = new JList<>(modelo);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(lista), BorderLayout.CENTER);

        JButton volver = boton("Volver");
        JButton ver = boton("Ver Vehículo");

        ver.setEnabled(false);

        lista.addListSelectionListener(
                e -> ver.setEnabled(lista.getSelectedValue() != null));

        volver.addActionListener(
                e -> ventana.mostrarAdministrarVehiculos(empresa));

        ver.addActionListener(
                e -> ventana.mostrarVehiculo(
                        empresa,
                        lista.getSelectedValue().getEntidad()));

        add(barra(volver, ver), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}