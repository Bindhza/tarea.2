package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ReunionPresencial extends Reunion {

    private String sala;

    public ReunionPresencial(Date fecha, Instant horaPrevista, Duration duracionPrevista, String sala, tipoReunion tipo) {
        super(fecha, horaPrevista, duracionPrevista, tipo);
        this.sala = sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    }
}
