package org.example;

import java.time.Instant;

public class Invitacion {
    private Instant hora;
    private Reunion reunion; //la reunion referenciada
    public Invitacion(Instant hora) {
        this.hora = hora;
    }
    public Reunion getReunion(){
        return reunion;
    }
}
