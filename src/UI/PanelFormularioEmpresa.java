package UI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Correo;
import model.Direccion;
import model.EmpresaExterna;
import model.Proveedor;
import model.Rut;
import model.Telefono;

/**
 * Define el formulario común para registrar una empresa externa.
 */
public abstract class PanelFormularioEmpresa extends PanelBase {

    // Atributos

    private final JTextField txtNombreEmpresa;
    private final JTextField txtCorreoEmpresa;
    private final JTextField txtNombreProveedor;
    private final JTextField txtRutProveedor;
    private final JTextField txtCorreoProveedor;
    private final JTextField txtTelefonoProveedor;
    private final JTextField txtCargoProveedor;
    private final JTextField txtCalle;
    private final JTextField txtNumero;
    private final JTextField txtCiudad;
    private final JTextField txtRegion;

    // Constructor

    /**
     * Construye el formulario común de registro.
     *
     * @param ventana Ventana principal de la aplicación.
     * @param titulo Título específico del formulario.
     */
    protected PanelFormularioEmpresa(VentanaPrincipal ventana, String titulo) {

        super(ventana, titulo);

        txtNombreEmpresa = new JTextField(24);
        txtCorreoEmpresa = new JTextField(24);
        txtNombreProveedor = new JTextField(24);
        txtRutProveedor = new JTextField(24);
        txtCorreoProveedor = new JTextField(24);
        txtTelefonoProveedor = new JTextField(24);
        txtCargoProveedor = new JTextField(24);
        txtCalle = new JTextField(24);
        txtNumero = new JTextField(24);
        txtCiudad = new JTextField(24);
        txtRegion = new JTextField(24);

        JPanel formulario = new JPanel(new GridBagLayout());

        formulario.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 170));

        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.insets = new Insets(3, 8, 3, 8);
        restricciones.fill = GridBagConstraints.HORIZONTAL;

        int fila = 0;

        agregarSubtitulo(formulario, restricciones, fila++, "Datos de la Empresa");
        agregarCampo(formulario, restricciones, fila++, "Nombre", txtNombreEmpresa);
        agregarCampo(formulario, restricciones, fila++, "Correo", txtCorreoEmpresa);
        agregarSubtitulo(formulario, restricciones, fila++, "Proveedor Responsable");
        agregarCampo(formulario, restricciones, fila++, "Nombre", txtNombreProveedor);
        agregarCampo(formulario, restricciones, fila++, "RUT", txtRutProveedor);
        agregarCampo(formulario, restricciones, fila++, "Correo", txtCorreoProveedor);
        agregarCampo(formulario, restricciones, fila++, "Teléfono", txtTelefonoProveedor);
        agregarCampo(formulario, restricciones, fila++, "Cargo", txtCargoProveedor);
        agregarCampo(formulario, restricciones, fila++, "Calle", txtCalle);
        agregarCampo(formulario, restricciones, fila++, "Número", txtNumero);
        agregarCampo(formulario, restricciones, fila++, "Ciudad", txtCiudad);
        agregarCampo(formulario, restricciones, fila, "Región", txtRegion);

        add(formulario, BorderLayout.CENTER);

        JButton volver = boton("Volver");
        JButton limpiar = boton("Limpiar");
        JButton registrar = boton("Registrar");

        volver.addActionListener(e -> ventana.mostrarSeleccionTipoEmpresa());
        limpiar.addActionListener(e -> limpiarCampos());
        registrar.addActionListener(e -> registrarEmpresa());

        add(barra(volver, limpiar, registrar), BorderLayout.SOUTH);

    }

    // Setters

    // Getters

    // Métodos públicos

    /**
     * Crea la empresa concreta seleccionada por el usuario.
     *
     * @param nombre Nombre de la empresa.
     * @param correo Correo de la empresa.
     * @param proveedor Proveedor responsable.
     * @return Empresa externa creada.
     * @throws Exception Si los datos no cumplen las reglas del modelo.
     */
    protected abstract EmpresaExterna crearEmpresa(String nombre, Correo correo, Proveedor proveedor) throws Exception;

    // Métodos privados

    private void registrarEmpresa() {

        List<String> errores = new ArrayList<>();

        validarCamposVacios(errores);

        Integer numero = convertirNumero(errores);
        Rut rut = crearRut(errores);
        Correo correoEmpresa = crearCorreo(txtCorreoEmpresa.getText(), errores);
        Correo correoProveedor = crearCorreo(txtCorreoProveedor.getText(), errores);
        Telefono telefono = crearTelefono(errores);
        Direccion direccion = crearDireccion(numero, errores);

        if (!errores.isEmpty()) {
            mostrarErrores(errores);
            return;
        }

        try {

            Proveedor proveedor = new Proveedor(txtNombreProveedor.getText(), rut, correoProveedor, telefono, direccion, txtCargoProveedor.getText());

            EmpresaExterna empresa = crearEmpresa(txtNombreEmpresa.getText(), correoEmpresa, proveedor);

            validarDuplicados(empresa, proveedor);

            ventana.getGestorEntidades().getGestorPersonas().agregarPersona(proveedor);
            ventana.getGestorEntidades().getGestorEmpresas().agregarEmpresa(empresa);

            JOptionPane.showMessageDialog(this,
                    "Empresa registrada exitosamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarCampos();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    mensaje(ex),
                    "Error de registro",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    private void validarCamposVacios(List<String> errores) {
        JTextField[] campos = {txtNombreEmpresa, txtCorreoEmpresa, txtNombreProveedor, txtRutProveedor, txtCorreoProveedor, txtTelefonoProveedor, txtCargoProveedor, txtCalle, txtNumero, txtCiudad, txtRegion};
        String[] nombres = {"nombre de la empresa", "correo de la empresa", "nombre del proveedor", "RUT del proveedor", "correo del proveedor", "teléfono del proveedor", "cargo del proveedor", "calle", "número", "ciudad", "región"};

        for (int i = 0; i < campos.length; i++) {
            if (campos[i].getText().trim().isEmpty()) {
                errores.add("El campo " + nombres[i] + " es obligatorio.");
            }
        }
    }

    private Integer convertirNumero(List<String> errores) {
        try {
            return Integer.valueOf(txtNumero.getText().trim());
        } catch (NumberFormatException ex) {
            errores.add("El número de la dirección debe ser un entero válido.");
            return null;
        }
    }

    private Rut crearRut(List<String> errores) {
        try {
            return new Rut(txtRutProveedor.getText());
        } catch (Exception ex) {
            errores.add(mensaje(ex));
            return null;
        }
    }

    private Correo crearCorreo(String valor, List<String> errores) {
        try {
            return new Correo(valor);
        } catch (Exception ex) {
            errores.add(mensaje(ex));
            return null;
        }
    }

    private Telefono crearTelefono(List<String> errores) {
        try {
            return new Telefono(txtTelefonoProveedor.getText());
        } catch (Exception ex) {
            errores.add(mensaje(ex));
            return null;
        }
    }

    private Direccion crearDireccion(Integer numero, List<String> errores) {

        if (numero == null) {
            return null;
        }

        try {
            return new Direccion(txtCalle.getText(), numero, txtCiudad.getText(), txtRegion.getText());
        } catch (Exception ex) {
            errores.add(mensaje(ex));
            return null;
        }

    }

    private void validarDuplicados(EmpresaExterna empresa, Proveedor proveedor) {

        if (ventana.getGestorEntidades().getGestorEmpresas().buscarEmpresa(empresa.obtenerIdentificador()) != null) {
            throw new IllegalArgumentException("Ya existe una empresa registrada con ese identificador.");
        }

        if (ventana.getGestorEntidades().getGestorPersonas().buscarPersona(proveedor.obtenerIdentificador()) != null) {
            throw new IllegalArgumentException("Ya existe una persona registrada con ese identificador.");
        }

    }

    private void mostrarErrores(List<String> errores) {

        JOptionPane.showMessageDialog(this,
                String.join("\n", errores),
                "Errores de validación",
                JOptionPane.ERROR_MESSAGE);

    }

    private void limpiarCampos() {

        JTextField[] campos = {txtNombreEmpresa, txtCorreoEmpresa, txtNombreProveedor, txtRutProveedor, txtCorreoProveedor, txtTelefonoProveedor, txtCargoProveedor, txtCalle, txtNumero, txtCiudad, txtRegion};

        for (JTextField campo : campos) {
            campo.setText("");
        }

        txtNombreEmpresa.requestFocusInWindow();

    }

    private void agregarSubtitulo(JPanel formulario, GridBagConstraints restricciones, int fila, String texto) {

        restricciones.gridx = 0;
        restricciones.gridy = fila;
        restricciones.gridwidth = 2;
        restricciones.weightx = 1;
        restricciones.insets = new Insets(fila == 0 ? 0 : 12, 8, 5, 8);

        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(etiqueta.getFont().deriveFont(java.awt.Font.BOLD, 16f));

        formulario.add(etiqueta, restricciones);

        restricciones.gridwidth = 1;
        restricciones.insets = new Insets(3, 8, 3, 8);

    }

    private void agregarCampo(JPanel formulario, GridBagConstraints restricciones, int fila, String etiqueta, JTextField campo) {

        restricciones.gridx = 0;
        restricciones.gridy = fila;
        restricciones.weightx = 0;

        formulario.add(new JLabel(etiqueta + ":"), restricciones);

        restricciones.gridx = 1;
        restricciones.weightx = 1;

        formulario.add(campo, restricciones);

    }

}
