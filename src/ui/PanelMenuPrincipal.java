package ui;

import java.awt.GridLayout;
import javax.swing.JButton;

/**
 * Panel correspondiente al menú principal.
 */
public class PanelMenuPrincipal extends PanelBase {

    private JButton btnAgregarRegistro;
    private JButton btnEliminarRegistro;
    private JButton btnVisualizarRegistros;
    private JButton btnSalir;

    /**
     * Constructor del panel.
     *
     * @param ventana Ventana principal.
     */
    public PanelMenuPrincipal(VentanaPrincipal ventana) {

        super(ventana);

        inicializarComponentes();
    }

    /**
     * Inicializa los componentes del panel.
     */
    private void inicializarComponentes() {

        setLayout(new GridLayout(5, 1, 0, 15));

        btnAgregarRegistro = crearBoton("Agregar Registro");
        btnEliminarRegistro = crearBoton("Eliminar Registro");
        btnVisualizarRegistros = crearBoton("Visualizar Registros");
        btnSalir = crearBoton("Salir");

        btnAgregarRegistro.addActionListener(e -> ventana.cambiarPanel(new PanelMenuAgregarRegistro(ventana)));

        btnEliminarRegistro.addActionListener(e -> ventana.cambiarPanel(new PanelEliminarRegistro(ventana)));

        btnVisualizarRegistros.addActionListener(e -> ventana.cambiarPanel(new PanelVisualizarRegistros(ventana)));

        btnSalir.addActionListener(e -> System.exit(0));

        add(lblTitulo);
        add(btnAgregarRegistro);
        add(btnEliminarRegistro);
        add(btnVisualizarRegistros);
        add(btnSalir);
    }
}
