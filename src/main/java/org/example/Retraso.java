package org.example;

import java.time.Instant;

/**
 * Subclase de asistencia, que registra cuando una persona llega tarde, guardando la hora
 */
public class Retraso extends Asistencia{
    private Instant hora;

    public Retraso(Invitable asistente, Instant hora) {
        super(asistente);
        this.hora = hora;
    }

    public Instant getHora() {
        return hora;
    }
}
