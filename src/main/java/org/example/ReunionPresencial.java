package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ReunionPresencial extends Reunion {

    private String sala;

    public ReunionPresencial(Date fecha, Instant horaPrevista, Duration duracionPrevista, String sala) {
        super(fecha, horaPrevista, duracionPrevista);
        this.sala = sala;
    }
}
