package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ReunionPrecencial extends Reunion {

    private String sala;

    public ReunionPrecencial(Date fecha, Instant horaPrevista, Duration duracionPrevista, String sala) {
        super(fecha, horaPrevista, duracionPrevista);
        this.sala = sala;
    }
}
