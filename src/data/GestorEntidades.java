package data;

import java.util.ArrayList;
import model.Registrable;

/**
 * Clase encargada de administrar las entidades registrables
 * del sistema Llanquihue Tour.
 */
public class GestorEntidades {

    // Colección que almacena todas las entidades registrables
    private ArrayList<Registrable> entidades;

    /**
     * Constructor de la clase GestorEntidades.
     */
    public GestorEntidades() {
        entidades = new ArrayList<>();
    }

    // Getters
    public ArrayList<Registrable> getEntidades() {
        return entidades;
    }

    // Métodos

    /**
     * Agrega una entidad registrable a la colección.
     *
     * @param entidad Entidad que se desea agregar.
     */
    public void agregarEntidad(Registrable entidad) {
        entidades.add(entidad);
    }

    /**
     * Elimina una entidad registrable de la colección.
     *
     * @param entidad Entidad que se desea eliminar.
     * @return true si la entidad fue eliminada; false si no estaba registrada.
     */
    public boolean eliminarEntidad(Registrable entidad) {
        return entidades.remove(entidad);
    }
}
