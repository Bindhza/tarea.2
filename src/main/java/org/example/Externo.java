package org.example;

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
    public void invitar(Reunion r) {
        r.crearInvitacion(this);
    }
}
