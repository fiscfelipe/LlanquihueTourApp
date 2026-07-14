package ui;

import java.awt.*;

import javax.swing.*;

import model.Persona;
import model.Registrable;
import model.ServicioTuristico;

/**
 * Panel para visualizar los registros del sistema.
 */
public class PanelVisualizarRegistros extends PanelBase {

    private JTabbedPane tabbedPane;

    private JTextArea areaPersonas;
    private JTextArea areaServicios;

    private JButton btnVolver;

    /**
     * Constructor del panel.
     *
     * @param ventana Ventana principal.
     */
    public PanelVisualizarRegistros(VentanaPrincipal ventana) {

        super(ventana);

        inicializarComponentes();

        cargarRegistros();

    }

    /**
     * Inicializa los componentes del panel.
     */
    private void inicializarComponentes() {

        setLayout(new BorderLayout(10, 10));

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());

        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        panelSuperior.add(crearSubtitulo("Visualizar Registros"), BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        
        // Áreas de texto
        areaPersonas = new JTextArea();
        areaServicios = new JTextArea();

        areaPersonas.setEditable(false);
        areaServicios.setEditable(false);

        JScrollPane scrollPersonas = new JScrollPane(areaPersonas);
        JScrollPane scrollServicios = new JScrollPane(areaServicios);

        // Pestañas
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Personas", scrollPersonas);
        tabbedPane.addTab("Servicios Turísticos", scrollServicios);

        add(tabbedPane, BorderLayout.CENTER);

        // Botón volver
        btnVolver = crearBoton("Volver");

        JPanel panelInferior = new JPanel(new FlowLayout());

        panelInferior.add(btnVolver);

        add(panelInferior, BorderLayout.SOUTH);

        // Eventos
        btnVolver.addActionListener(e -> ventana.cambiarPanel(new PanelMenuPrincipal(ventana)));

    }

    /**
     * Carga los registros almacenados en el gestor.
     */
    private void cargarRegistros() {

        areaPersonas.setText("");
        areaServicios.setText("");

        for (Registrable entidad : ventana.getGestorEntidades().getEntidades()) {

            if (entidad instanceof Persona) {

                areaPersonas.append("========================================\n");

                areaPersonas.append(entidad.getClass().getSimpleName() + "\n");

                areaPersonas.append("----------------------------------------\n");

                areaPersonas.append(entidad.mostrarResumen());

                areaPersonas.append("\n\n");

            } else if (entidad instanceof ServicioTuristico) {

                areaServicios.append("========================================\n");

                areaServicios.append(entidad.getClass().getSimpleName() + "\n");

                areaServicios.append("----------------------------------------\n");

                areaServicios.append(entidad.mostrarResumen());

                areaServicios.append("\n\n");

            }

        }

        if (areaPersonas.getText().isBlank()) {

            areaPersonas.setText("No existen personas registradas.");

        }

        if (areaServicios.getText().isBlank()) {

            areaServicios.setText("No existen servicios turísticos registrados.");

        }

    }

}