package org.example;

import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

public class Empleado implements Invitable {

    private String id, nombre, apellidos, correo;
    private ArrayList<Invitacion> invitaciones;

    public Empleado(String correo, String apellidos, String nombre, String id) {
        this.correo = correo;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.id = id;
    }

    @Override
    public void invitar(Invitacion inv) {
        Instant fechaReunionI = inv.getReunion().getHoraPrevista();
        Instant fechaReunionF = fechaReunionI.plus(inv.getReunion().getDuracionPrevista());
        for(Invitacion i: invitaciones){
            Instant fechaInvI = i.getReunion().getHoraPrevista();
            Instant fechaInvF = fechaInvI.plus(inv.getReunion().getDuracionPrevista());
            // revisamos si alguna reunion a la cual fue invitado choca con la nueva invitacion
            if(fechaReunionF.isBefore(fechaInvI) && fechaInvI.isBefore(fechaReunionF)){
               throw new AgendaOcupadaException("");
            }
        }
        invitaciones.add(inv);
    }

    public void asistir(Instant hora){
        for(Invitacion i:invitaciones){

        }
    }
}

