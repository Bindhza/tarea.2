package org.example;

/**
 * Representa un registro de asistencia interno de una reunion
 */

public class Asistencia {
    private final Invitable asistente;

    /**
     * retorna el asistente
     * @return asistente
     */
    public Invitable getAsistente() {
        return asistente;
    }

    public Asistencia(Invitable asistente) {
        this.asistente = asistente;
    }

    @Override
    public String toString() {
        return asistente.toString();
    }
}
