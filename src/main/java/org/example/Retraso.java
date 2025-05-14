package org.example;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Subclase de asistencia, que registra cuando una persona llega tarde, guardando la hora
 */
public class Retraso extends Asistencia {
    private final Instant hora;

    public Retraso(Invitable asistente, Instant hora) {
        super(asistente);
        if (hora == null) {
            throw new NullPointerException("HORA NO EXISTE");
        }
        this.hora = hora;
    }

    /**
     * Retorna la hora
     *
     * @return la hora a la que llego la persona
     */
    public Instant getHora() {
        return hora;
    }

    @Override
    public String toString() {
        ZonedDateTime fechaInicio = hora.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFinal = formatoHora.format(fechaInicio);

        return this.getAsistente() + " con retraso, hora: " + horaFinal;
    }
}
