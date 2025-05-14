package org.example;

/**
 * Representa un registro de asistencia interno de una reunion
 */

public class Asistencia {
    private final Invitable asistente;

    public Asistencia(Invitable asistente) {
        if (asistente == null) {
            throw new NullPointerException("ASISTENTE NO EXISTE");
        }
        this.asistente = asistente;
    }

    /**
     * Retorna el asistente
     *
     * @return el asistente a la reunion
     */
    public Invitable getAsistente() {
        return asistente;
    }

    @Override
    public String toString() {
        return asistente.toString();
    }
}
