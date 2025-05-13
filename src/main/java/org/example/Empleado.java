package org.example;

public class Empleado implements Invitable {

    private String id, nombre, apellidos, correo;

    public Empleado(String correo, String apellidos, String nombre, String id) {
        this.correo = correo;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.id = id;
    }

    @Override
    public void invitar(Reunion r) {
        r.crearInvitacion(this);
    }
}