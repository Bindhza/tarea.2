package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * Instancia de Reunion, que se celebra en linea
 */
public class ReunionVirtual extends Reunion {
    private String enlace;

    public ReunionVirtual(Date fecha, Instant horaPrevista, Duration duracionPrevista, String enlace, tipoReunion tipo) {
        super(fecha, horaPrevista, duracionPrevista, tipo);
        this.enlace = enlace;
    }

    /**
     * Obtiene el enlace de la reunion virtual
     *
     * @return el enlace de la reunion
     */
    public String getEnlace() {
        return enlace;
    }

    /**
     * Cambia el enlace de la reunion
     *
     * @param enlace el nuevo enlace de la reunion
     */
    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    @Override
    public String toString() {
        return "Reunion Online (Link: " + enlace + ")\n" + super.toString();
    }
}
