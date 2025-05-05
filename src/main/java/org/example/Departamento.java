package org.example;

import java.util.ArrayList;

public class Departamento {
    private String nombre;
    private ArrayList<Empleado> lista;

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public int obtenerCantidadEmpleados(){
        return lista.size();
    }
}
