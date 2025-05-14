package org.example;

/**
 * Representa a un empleado que pertenece a una empresa
 */

public class Empleado implements Invitable {

    private String id, nombre, apellidos, correo;

    public Empleado(String correo, String apellidos, String nombre, String id) {
        this.correo = correo;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.id = id;
    }

    /**
     * Implementacion de la interfaz Invitable
     * @param r reunion a la cual se es invitado
     */
    @Override
    public void invitar(Reunion r) {
        r.crearInvitacion(this);
    }

    @Override
    public String toString(){

        return id + "-" + nombre + " " + apellidos+ "-" + correo;
    }
}