package org.example;

import java.time.Instant;

public class Invitacion {
    private Instant hora;
    private Invitable invitado;

    public Invitacion(Instant hora, Invitable invitado) {
        this.hora = hora;
        this.invitado = invitado;
    }

    public Invitable getInvitado() {
        return invitado;
    }
}
