package UI;

/**
 * Representa un elemento asociado a una entidad para ser mostrado en
 * componentes gráficos como listas o cuadros combinados.
 *
 * @param <T> Tipo de la entidad asociada.
 */
public class EntidadItem<T> {

    // Atributos

    private final T entidad;
    private final String texto;

    // Constructor

    /**
     * Construye un nuevo elemento asociado a una entidad.
     *
     * @param entidad Entidad representada.
     * @param texto Texto que se mostrará en la interfaz.
     */
    public EntidadItem(T entidad, String texto) {

        this.entidad = entidad;
        this.texto = texto;

    }

    // Setters

    // Getters

    /**
     * Obtiene la entidad asociada.
     *
     * @return Entidad representada.
     */
    public T getEntidad() {

        return entidad;

    }

    // Métodos públicos

    /**
     * Obtiene el texto que representará a la entidad en la interfaz.
     *
     * @return Texto representativo de la entidad.
     */
    @Override
    public String toString() {

        return texto;

    }

    // Métodos privados

}