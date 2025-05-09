package org.example;

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public abstract class Reunion {

    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio, horaFin;

    private ArrayList<Asistencia> asistencias;

    private ArrayList<? extends Invitable> invitados;

    private Invitacion invitacion;

    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.invitacion = new Invitacion(horaPrevista);
        this.invitados = new ArrayList<>();
    }

    public Date getFecha() {
        return fecha;
    }

    public Instant getHoraPrevista() {
        return horaPrevista;
    }

    public Duration getDuracionPrevista() {
        return duracionPrevista;
    }

    public ArrayList<Asistencia> obtenerAsistencias(){
        return asistencias;
    }

    public ArrayList<? extends Invitable> obtenerAusencias(){
        ArrayList<? extends Invitable> ausencias = new ArrayList<>(invitados);

        for(Asistencia a: asistencias){
            ausencias.remove(a.getEmpleado());
        }
        return ausencias;
    }

    public ArrayList<Retraso> obtenerRetrasos(){
        ArrayList<Retraso> retrasos = new ArrayList<>();
        for (Asistencia a:asistencias) {
            if (a instanceof Retraso){
                retrasos.add((Retraso) a);
            }
        }
        return retrasos;
    }


    public int obtenerTotalAsistencia(){
        return asistencias.size();
    }

    public float obtenerPorcentageAsistencia(){
        return (float) asistencias.size() / invitados.size();
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
