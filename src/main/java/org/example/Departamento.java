package org.example;

import java.util.ArrayList;

/**
 * Representa el departamento de una empresa, conformado por empleados
 */
public class Departamento implements Invitable {
    private final String nombre;
    private final ArrayList<Empleado> empleados;

    public Departamento(String nombre) {
        this.nombre = nombre;
        empleados = new ArrayList<>();
    }

    /**
     * Retorna la cantidad de empleados en el departamento
     **/
    public int obtenerCantidadEmpleados() {
        return empleados.size();
    }


    /**
     * Metodo para agregar empleados
     *
     * @param empleado el empleado a agregar
     */
    public void agregarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new NullPointerException("EMPLEADO NO EXISTE");
        } else {
            empleados.add(empleado);
        }
    }

    /**
     * Implementacion de la interfaz Invitable, se agrega a sí mismo a la lista de invitados
     * e invita a todos los empleados del departamento a la reunion
     *
     * @param r reunion a la cual es invitado
     */
    @Override
    public void invitar(Reunion r) {
        r.crearInvitacion(this);
        for (Empleado empleado : empleados) {
            empleado.invitar(r);
        }
    }

    @Override
    public String toString() {
        String listaEmpleados = "|";
        for (Empleado e : empleados) {
            listaEmpleados += " " + e + " |";
        }
        return nombre + "\n" + listaEmpleados;
    }
}
