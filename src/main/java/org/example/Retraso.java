package org.example;

import java.time.Instant;

public class Retraso extends Asistencia{
    private Instant hora;

    public Retraso(Invitable asistente, Instant hora) {
        super(asistente);
        this.hora = hora;
    }
}
