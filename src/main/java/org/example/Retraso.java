package org.example;

import java.time.Instant;

public class Retraso extends Asistencia{
    private Instant hora;

    public Retraso(Empleado empleado, Instant hora) {
        super(empleado);
        this.hora = hora;
    }
}
