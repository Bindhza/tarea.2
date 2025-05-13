package org.example;

import java.util.ArrayList;

public class Departamento implements Invitable {
    private String nombre;
    private ArrayList<Empleado> empleados;

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public int obtenerCantidadEmpleados(){
        return empleados.size();
    }

    @Override
    public void invitar(Reunion r){
        for(Empleado empleado : empleados){
            empleado.invitar(r);
        }
    };
}
