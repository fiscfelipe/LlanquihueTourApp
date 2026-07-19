package UI;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import model.Alojamiento;
import model.EmpresaAlojamiento;

/**
 * Muestra los alojamientos registrados de una empresa de alojamiento.
 */
public class PanelVisualizarAlojamientos extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel para visualizar los alojamientos de una empresa.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param empresa Empresa cuyos alojamientos serán visualizados.
     */
    public PanelVisualizarAlojamientos(
            VentanaPrincipal ventana,
            EmpresaAlojamiento empresa) {

        super(ventana, "Visualizar Alojamientos");

        DefaultListModel<EntidadItem<Alojamiento>> modelo = new DefaultListModel<>();

        for (Alojamiento alojamiento : empresa.getAlojamientos()) {

            modelo.addElement(
                    new EntidadItem<>(
                            alojamiento,
                            alojamiento.getNombre()));

        }

        JList<EntidadItem<Alojamiento>> lista = new JList<>(modelo);

        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(lista), BorderLayout.CENTER);

        JButton volver = boton("Volver");
        JButton ver = boton("Ver Alojamiento");

        ver.setEnabled(false);

        lista.addListSelectionListener(
                e -> ver.setEnabled(lista.getSelectedValue() != null));

        volver.addActionListener(
                e -> ventana.mostrarAdministrarAlojamientos(empresa));

        ver.addActionListener(
                e -> ventana.mostrarAlojamiento(
                        empresa,
                        lista.getSelectedValue().getEntidad()));

        add(barra(volver, ver), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}