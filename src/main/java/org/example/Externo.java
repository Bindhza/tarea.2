package org.example;

/**
 * Representa a una persona ajena a una empresa, la cual puede ser invitada a sus reuniones
 * de todos modos
 */
public class Externo implements Invitable {
    private String nombre;
    private String apellidos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Externo(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    /**
     * Implementacion de la interfaz Invitable, se agrega a si mismo a la lista de invitados
     * @param r reunion a la que se es invitado
     */
    public void invitar(Reunion r) {
        r.crearInvitacion(this);
    }

    @Override
    public String toString(){

        return nombre + " " + apellidos;
    }
}
