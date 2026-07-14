package ui;

import java.awt.*;
import javax.swing.*;
/**
 * Clase base para todos los paneles de la aplicación.
 */
public abstract class PanelBase extends JPanel {

    protected final VentanaPrincipal ventana;
    protected JLabel lblTitulo;

    /**
     * Constructor de la clase.
     *
     * @param ventana Ventana principal de la aplicación.
     */
    public PanelBase(VentanaPrincipal ventana) {

        this.ventana = ventana;

        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        lblTitulo = new JLabel("Llanquihue Tour");
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
    }

    /**
     * Crea un subtítulo con el estilo utilizado por la aplicación.
     *
     * @param texto Texto del subtítulo.
     * @return Subtítulo configurado.
     */
    protected JLabel crearSubtitulo(String texto) {

        JLabel subtitulo = new JLabel(texto);

        subtitulo.setHorizontalAlignment(JLabel.CENTER);
        subtitulo.setFont(new Font("SansSerif", Font.BOLD, 16));

        return subtitulo;
    }

    /**
     * Agrega una etiqueta y un campo de texto al panel indicado.
     *
     * @param panel Panel donde se agregará el campo.
     * @param textoEtiqueta Texto de la etiqueta.
     * @param gbc Restricciones del GridBagLayout.
     * @param fila Fila donde se agregará el campo.
     * @return Campo de texto creado.
     */
    protected JTextField crearCampoFormulario(
            JPanel panel,
            String textoEtiqueta,
            GridBagConstraints gbc,
            int fila) {

        JLabel etiqueta = new JLabel(textoEtiqueta);

        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.insets = new Insets(5, 5, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        panel.add(etiqueta, gbc);

        JTextField campo = new JTextField(20);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(campo, gbc);

        return campo;
    }

    /**
     * Crea un botón utilizado por la aplicación.
     *
     * @param texto Texto del botón.
     * @return Botón creado.
     */
    protected JButton crearBoton(String texto) {

        return new JButton(texto);
    }
}