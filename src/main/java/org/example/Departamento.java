package org.example;

import java.util.ArrayList;

public class Departamento implements Invitable {
    private String nombre;
    private ArrayList<Empleado> lista;

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public int obtenerCantidadEmpleados(){
        return lista.size();
    }


    @Override
    public void invitar(){

    };
}
