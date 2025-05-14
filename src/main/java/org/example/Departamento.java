package org.example;

import java.util.ArrayList;

/**
 * Representa el departamento de una empresa, conformado por empleados
 */
public class Departamento implements Invitable {
    private String nombre;
    private ArrayList<Empleado> empleados;

    public Departamento(String nombre) {
        this.nombre = nombre;
        empleados = new ArrayList<>();
    }
    /**
    retorna la cantidad de empleados en el departamento
     **/
    public int obtenerCantidadEmpleados(){
        return empleados.size();
    }


    /**
     * Metodo para agregar empleado
     * @param empleado
     */
    public void agregarEmpleado(Empleado empleado){
        switch (empleado){
            case null -> throw new NullPointerException();
            default -> empleados.add(empleado);
        }
    }

    /**
     * Implementacion de la interfaz Invitable, se agrega a sí mismo a la lista de invitados
     * e invita a todos los empleados del departamento a la reunion
     * @param r reunion a la cual es invitado
     */
    @Override
    public void invitar(Reunion r){
        r.crearInvitacion(this);
        for(Empleado empleado : empleados){
            empleado.invitar(r);
        }
    }

    public String toString(){
        String listaEmpleados = "|";
        for(Empleado e: empleados){
            listaEmpleados += " " + empleados + " |";
        }
        return nombre + "\n" +  listaEmpleados;
    }
}
