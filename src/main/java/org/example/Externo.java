package org.example;

/**
 * Representa a una persona ajena a una empresa, la cual puede ser invitada a sus reuniones
 * de todos modos
 */
public class Externo implements Invitable {
    private final String nombre;
    private final String apellidos;

    public Externo(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    /**
     * Implementacion de la interfaz Invitable, se agrega a sí mismo a la lista de invitados
     *
     * @param r reunion a la que se es invitado
     */
    @Override
    public void invitar(Reunion r) {
        if (r == null) {
            throw new NullPointerException("REUNION NO EXISTE");
        }
        r.crearInvitacion(this);
    }

    @Override
    public String toString() {

        return nombre + " " + apellidos;
    }
}
