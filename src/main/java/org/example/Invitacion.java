package org.example;

import java.time.Instant;

public class Invitacion {
    private Instant hora;
    private Invitable invitado;

    public Invitacion(Instant hora) {
        this.hora = hora;
    }

    public Invitable getInvitado() {
        return invitado;
    }
}
