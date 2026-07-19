package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Clase base para los paneles de la aplicación.
 * Proporciona elementos comunes como el título, la creación de botones,
 * barras de acciones y áreas de visualización de datos.
 */
public abstract class PanelBase extends JPanel {

    // Atributos

    protected final VentanaPrincipal ventana;

    // Constructor

    /**
     * Construye un panel base con el formato común de la aplicación.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param titulo Título que se mostrará en el panel.
     */
    protected PanelBase(VentanaPrincipal ventana, String titulo) {

        this.ventana = ventana;

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 60, 30, 60));

        JLabel etiqueta = new JLabel(titulo, SwingConstants.CENTER);
        etiqueta.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        add(etiqueta, BorderLayout.NORTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    /**
     * Crea un botón con el tamaño estándar utilizado en la aplicación.
     *
     * @param texto Texto que mostrará el botón.
     * @return Botón configurado.
     */
    protected JButton boton(String texto) {

        JButton b = new JButton(texto);
        b.setPreferredSize(new Dimension(180, 38));

        return b;

    }

    /**
     * Crea una barra horizontal con los componentes indicados.
     *
     * @param componentes Componentes que se agregarán a la barra.
     * @return Panel que contiene los componentes.
     */
    protected JPanel barra(Component... componentes) {

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

        for (int i = 0; i < componentes.length; i++) {

            p.add(componentes[i]);

            if (i < componentes.length - 1) {
                p.add(Box.createHorizontalGlue());
            }

        }

        return p;

    }

    /**
     * Crea un área de texto de solo lectura para mostrar información.
     *
     * @param texto Texto que contendrá el área.
     * @return Área de texto dentro de un panel con desplazamiento.
     */
    protected JScrollPane areaDatos(String texto) {

        JTextArea area = new JTextArea(texto);

        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        return new JScrollPane(area);

    }

    /**
     * Obtiene un mensaje legible a partir de una excepción.
     *
     * @param ex Excepción ocurrida.
     * @return Mensaje de la excepción o el nombre de su clase si no existe un
     * mensaje.
     */
    protected String mensaje(Throwable ex) {

        return ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();

    }

    // Métodos privados

}