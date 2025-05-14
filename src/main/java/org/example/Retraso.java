package org.example;

import java.time.Instant;
import java.time.*;
import java.time.format.*;

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
     @Override
    public String toString(){
         ZonedDateTime fechaInicio = hora.atZone(ZoneId.systemDefault());
         DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
         String horaFinal = formatoHora.format(fechaInicio);

        return this.getAsistente() + " con retraso, hora: " + horaFinal;
     }
}
