package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * Instancia especifica de Reunion, que se celebra presencialmente
 */
public class ReunionPresencial extends Reunion {

    private String sala;

    public ReunionPresencial(Date fecha, Instant horaPrevista, Duration duracionPrevista, String sala, tipoReunion tipo) {
        super(fecha, horaPrevista, duracionPrevista, tipo);
        this.sala = sala;
    }

    /**
     * Cambia la sala donde se va a celebrar la reunion
     * @param sala el nombre de la sala de reuniones
     */
    public void setSala(String sala) {
        this.sala = sala;
    }

    /**
     * obtiene la sala donde se va a celebrar la reunion
     * @return el nombre de la sala de reuniones
     */
    public String getSala() {
        return sala;
    }

    @Override
    public String toString(){
        return "Reunion Precencial "+"\nSala: " + sala + "   " + super.toString();
    }
}
