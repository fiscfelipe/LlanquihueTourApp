package ui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Panel para seleccionar el tipo de registro.
 */
public class PanelMenuAgregarRegistro extends PanelBase {

    private JLabel lblSubtitulo;

    private JButton btnProveedorTransporte;
    private JButton btnProveedorAlojamiento;
    private JButton btnGuiaTuristico;
    private JButton btnRutaGastronomica;
    private JButton btnPaseoLacustre;
    private JButton btnExcursionCultural;
    private JButton btnVolver;

    /**
     * Constructor del panel.
     *
     * @param ventana Ventana principal.
     */
    public PanelMenuAgregarRegistro(VentanaPrincipal ventana) {

        super(ventana);

        inicializarComponentes();

    }

    /**
     * Inicializa los componentes del panel.
     */
    private void inicializarComponentes() {

        setLayout(new GridLayout(9, 1, 0, 15));

        lblSubtitulo = crearSubtitulo("Seleccione el tipo de registro");

        btnProveedorTransporte = crearBoton("Proveedor Transporte");
        btnProveedorAlojamiento = crearBoton("Proveedor Alojamiento");
        btnGuiaTuristico = crearBoton("Guía Turístico");
        btnRutaGastronomica = crearBoton("Ruta Gastronómica");
        btnPaseoLacustre = crearBoton("Paseo Lacustre");
        btnExcursionCultural = crearBoton("Excursión Cultural");
        btnVolver = crearBoton("Volver");

        // Eventos
        btnProveedorTransporte.addActionListener(e -> ventana.cambiarPanel(new PanelProveedorTransporte(ventana)));

        btnProveedorAlojamiento.addActionListener(e -> ventana.cambiarPanel(new PanelProveedorAlojamiento(ventana)));

        btnGuiaTuristico.addActionListener(e -> ventana.cambiarPanel(new PanelGuiaTuristico(ventana)));

        btnRutaGastronomica.addActionListener(e -> ventana.cambiarPanel(new PanelRutaGastronomica(ventana)));

        btnPaseoLacustre.addActionListener(e -> ventana.cambiarPanel(new PanelPaseoLacustre(ventana)));

        btnExcursionCultural.addActionListener(e -> ventana.cambiarPanel(new PanelExcursionCultural(ventana)));

        btnVolver.addActionListener(e -> ventana.cambiarPanel(new PanelMenuPrincipal(ventana)));

        add(lblTitulo);
        add(lblSubtitulo);
        add(btnProveedorTransporte);
        add(btnProveedorAlojamiento);
        add(btnGuiaTuristico);
        add(btnRutaGastronomica);
        add(btnPaseoLacustre);
        add(btnExcursionCultural);
        add(btnVolver);

    }

}