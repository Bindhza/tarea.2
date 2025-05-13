package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ReunionVirtual extends Reunion{
    private String enlace;

    public ReunionVirtual(Date fecha, Instant horaPrevista, Duration duracionPrevista, String enlace, tipoReunion tipo) {
        super(fecha, horaPrevista, duracionPrevista, tipo);
        this.enlace = enlace;
    }
}
