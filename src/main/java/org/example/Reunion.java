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

    public Duration getDuracionPrevista() {
        return duracionPrevista;
    }

    public Instant getHoraPrevista() {
        return horaPrevista;
    }

    public Date getFecha() {
        return fecha;
    }

    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        asistencias = new ArrayList<>();
        invitaciones = new ArrayList<>();
        horaInicio = null;
        horaFin = null;
    }

    public ArrayList<Asistencia> obtenerAsistencias(){
        return asistencias;
    }

    public void crearInvitacion(Invitable i){
        /*
        TODO preguntar a Geoffrey sobre asistencia de un departamento
        en este método se extienden invitaciones a Invitables específicos
        */
    }

    public boolean verificarInvitacion(Invitable i){
        for(Invitacion inv:invitaciones){
            if (inv.getInvitado() == i){
                return true;
            }
        }
        return false;
    }

    /*
    este se usa cuando alguien llega a la reunion; si no está invitado o ya se acabó todo no pasa nada
    no estoy seguro de que clase debería tener este método
    */
    public void llegar(Invitable i){
        if (!verificarInvitacion(i) || horaFin != null) {
            return;
        }

        if (horaInicio == null){
            asistencias.add(new Asistencia(i));
        } else {
            asistencias.add(new Retraso(i,Instant.now()));
        }
    }

    public ArrayList<Invitable> obtenerAusencias(){
        //por favor si es posible cambiar esto, no me gusta
        ArrayList<Invitable> ausencias = new ArrayList<>();

        for(Invitacion i: invitaciones){
            ausencias.add(i.getInvitado());
        }
        for(Asistencia a: asistencias){
            ausencias.remove(a.getAsistente());
        }
        return ausencias;
    }

    public ArrayList<Retraso> obtenerRetrasos(){
        ArrayList<Retraso> retrasos = new ArrayList<>();
        for (Asistencia a:asistencias) {
            // se enojara el profe si uso un instanceof?
            if (a instanceof Retraso){
                retrasos.add((Retraso) a);
            }
        }
        return retrasos;
    }


    public int obtenerTotalAsistencia(){
        return asistencias.size();
    }

    public float obtenerPorcentajeAsistencia(){
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
