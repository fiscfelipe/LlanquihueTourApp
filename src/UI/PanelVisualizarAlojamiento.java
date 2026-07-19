package UI;

import java.awt.BorderLayout;
import javax.swing.JButton;
import model.Alojamiento;
import model.EmpresaAlojamiento;

/**
 * Muestra la información de un alojamiento registrado.
 */
public class PanelVisualizarAlojamiento extends PanelBase {

    // Atributos

    // Constructor

    /**
     * Construye el panel para visualizar un alojamiento.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param empresa Empresa a la que pertenece el alojamiento.
     * @param alojamiento Alojamiento que será visualizado.
     */
    public PanelVisualizarAlojamiento(
            VentanaPrincipal ventana,
            EmpresaAlojamiento empresa,
            Alojamiento alojamiento) {

        super(ventana, "Visualizar Alojamiento");

        add(areaDatos(alojamiento.mostrarDatos()), BorderLayout.CENTER);

        JButton volver = boton("Volver");

        volver.addActionListener(
                e -> ventana.mostrarVisualizarAlojamientos(empresa));

        add(barra(volver), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    // Métodos privados

}