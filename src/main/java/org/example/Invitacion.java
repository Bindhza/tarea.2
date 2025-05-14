package org.example;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa las invitaciones enviadas por un organizador a una reunion, esta clase
 * solo la maneja la reunion misma y no los invitados a esta, guarda la hora a la que se envio y el invitado
 */
public class Invitacion {
    private Instant hora;
    private Invitable invitado;

    public Invitacion(Instant hora, Invitable invitado) {
        this.hora = hora;
        this.invitado = invitado;
    }

    /**
     * Retorna el objeto invitado a la reunion en cuestion
     * @return el invitado asociado a esta reunion
     */
    public Invitable getInvitado() {
        return invitado;
    }

    @Override
    public String toString(){

        ZonedDateTime horaInicial = hora.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hora = formatoHora.format(horaInicial);
        return invitado + " : " + hora;
    }
}
