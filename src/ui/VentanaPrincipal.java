package ui;

import data.GestorEntidades;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Ventana principal de la aplicación Llanquihue Tour.
 */
public class VentanaPrincipal extends JFrame {

    private final GestorEntidades gestorEntidades;

    /**
     * Constructor de la ventana principal.
     */
    public VentanaPrincipal() {

        gestorEntidades = new GestorEntidades();

        configurarVentana();

        cambiarPanel(new PanelMenuPrincipal(this));

        setVisible(true);
    }

    /**
     * Configura la ventana principal.
     */
    private void configurarVentana() {

        setTitle("Llanquihue Tour App");
        setSize(800, 600);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    /**
     * Cambia el panel mostrado en la ventana.
     *
     * @param panel Panel que se desea mostrar.
     */
    public void cambiarPanel(JPanel panel) {

        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    /**
     * Devuelve el gestor de entidades.
     *
     * @return Gestor de entidades.
     */
    public GestorEntidades getGestorEntidades() {

        return gestorEntidades;
    }
}