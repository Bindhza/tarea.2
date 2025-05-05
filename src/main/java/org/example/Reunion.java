package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public abstract class Reunion {

    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio, horaFin;

    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
    }

    public ArrayList<Asistencia> obtenerAsistencias(){

    }

    public ArrayList<Empleado> obtenerAusencias(){

    }

    public ArrayList<Retraso> obtenerRetrasos(){

    }

    public int obtenerTotalAsistencia(){

    }

    public float obtenerPorcentageAsistencia(){

    }

    public float calcularTiempoReal(){
        return (float) (horaFin.getEpochSecond() - horaInicio.getEpochSecond()) /60;
    }

    public void iniciar(){
        horaInicio = Instant.now();
    }

    public void finalizar(){
        horaFin = Instant.now();
    }
}
