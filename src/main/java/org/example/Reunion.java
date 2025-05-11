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

    private ArrayList<Asistencia> asistencias;

    private ArrayList<Invitacion> invitaciones;

    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.invitaciones = new ArrayList<>();
    }

    public ArrayList<Asistencia> obtenerAsistencias(){
        return asistencias;
    }

    public ArrayList<Invitable> obtenerAusencias(){
        ArrayList<Invitable> ausencias = new ArrayList<>();

        for(Invitacion i: invitaciones){
            ausencias.add(i.getInvitado());
        }
        for(Asistencia a: asistencias){
            ausencias.remove(a.getAsistente());
        }
        //por favor si es posible cambiar esto, no me gusta
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
        return (float) asistencias.size() / invitaciones.size();
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
