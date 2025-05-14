package org.example;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa una reunion organizada por una empresa especifica
 */
public abstract class Reunion {

    private Date fecha;
    private Instant horaPrevista, horaInicio, horaFin;
    private Duration duracionPrevista;
    private tipoReunion tipoReunion;

    private ArrayList<Asistencia> asistencias;

    private ArrayList<Invitacion> invitaciones;

    private ArrayList<Nota> notas;

    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista, tipoReunion tipoReunion) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.tipoReunion = tipoReunion;
        asistencias = new ArrayList<>();
        invitaciones = new ArrayList<>();
        notas = new ArrayList<>();
        horaInicio = null;
        horaFin = null;
    }

    /**
     * Cambia la fecha cuando se celebra la reunion
     * @param fecha la nueva fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * cambia la hora prevista de la reunion
     * @param horaPrevista la nueva hora
     */
    public void setHoraPrevista(Instant horaPrevista) {
        this.horaPrevista = horaPrevista;
    }

    /**
     * cambia la duracion prevista de la reunion
     * @param duracionPrevista la duracion esperada de la reunion
     */
    public void setDuracionPrevista(Duration duracionPrevista) {
        this.duracionPrevista = duracionPrevista;
    }

    /**
     * cambia el tipo de reunion
     * @param tipoReunion una enumeracion que representa los distintos tipos de reunion
     */
    public void setTipoReunion(tipoReunion tipoReunion) {
        this.tipoReunion = tipoReunion;
    }

    /**
     * obtiene la duracion prevista
     * @return un objeto Duration, que representa la duracion esperada de la reunion
     */
    public Duration getDuracionPrevista() {
        return duracionPrevista;
    }

    /**
     * obtiene la hora prevista de inicio de la reunion
     * @return un objeto Instant, que representa la hora de inicio
     */
    public Instant getHoraPrevista() {
        return horaPrevista;
    }

    /**
     * obtiene la fecha en la cual se celebrara la reunion
     * @return un objeto Date, que representa la fecha de inicio
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * obtiene el tipo de reunion que se va a celebrar
     * @return una enumeracion que representa el tipo de reunion
     */
    public tipoReunion getTipoReunion() {
        return tipoReunion;
    }

    /**
     * obtiene una copia de las asistencias hasta el momento a la reunion
     * @return los asistentes a la reunion
     */
    public ArrayList<Asistencia> obtenerAsistencias(){
        return new ArrayList<>(asistencias); // cambiado para evitar cambios en el arreglo desde afuera
    }

    /**
     * agrega una invitacion a la lista de invitaciones, lo usa la interfaz Invitable
     * @param i la persona o departamento que se va a invitar
     */
    public void crearInvitacion(Invitable i){
        invitaciones.add(new Invitacion(Instant.now(),i));
    }

    /**
     * verifica si el objeto esta invitado a la reunion
     * @param i
     * @return true si esta invitado, false si no lo esta
     */
    private boolean verificarInvitacion(Invitable i){
        for(Invitacion inv:invitaciones){
            if (inv.getInvitado() == i){
                return true;
            }
        }
        return false;
    }

    /*
    este se usa cuando alguien llega a la reunion; si no está invitado o ya se acabó todo no pasa nada
    no estoy seguro de que clase debería tener este método, pero esto me conforta más
    */

    /**
     * Registra un invitado en el registro de asistencias, los departamentos no se registran
     * ya que solo las personas pueden asistir
     * @param i la persona que va a llegar
     */
    public void llegar(Invitable i){
        if (!verificarInvitacion(i) || horaFin != null || i instanceof Departamento) {
            return;
        }

        if (horaInicio == null){
            asistencias.add(new Asistencia(i));
        } else {
            asistencias.add(new Retraso(i,Instant.now()));
        }
    }

    /**
     * Retorna una lista de la gente que no ha llegado a la reunion
     * @return un ArrayList que contiene la gente ausente de la reunion
     */
    public ArrayList<Invitable> obtenerAusencias(){
        //por favor si es posible cambiar esto, no me gusta
        ArrayList<Invitable> ausencias = new ArrayList<>();

        for(Invitacion i: invitaciones){
            if (i.getInvitado() instanceof Departamento){
                continue;
            }
            ausencias.add(i.getInvitado());
        }
        for(Asistencia a: asistencias){
            ausencias.remove(a.getAsistente());
        }
        return ausencias;
    }

    /**
     * Obtiene una lista de las personas que llegaron atrasadas a la reunion, asociados a una
     * hora de llegada
     * @return un ArrayList que contiene los objetos Retraso asociados a cada persona
     */
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

    /**
     * Obtiene la cantidad de personas que han llegado a la reunion
     * @return un entero representando la cantidad de personas que han llegado a la reunion
     */
    public int obtenerTotalAsistencia(){
        return asistencias.size();
    }

    /**
     * Obtiene el porcentaje de personas que asistio a la reunion, los departamentos no se cuentan, pero
     * si las personas que forman parte de este
     * @return un número decimal que representa el porcentaje
     */
    public float obtenerPorcentajeAsistencia(){
        float personasEsperadas = invitaciones
                .stream()
                .filter(p -> !(p.getInvitado() instanceof Departamento))
                .toList().size();
        return asistencias.size() / personasEsperadas;
    }

    /**
     * Calcula la duracion real de la reunion
     * @return un objeto Duracion que representa la duracion real
     */
    public Duration calcularTiempoReal(){
        if (horaInicio == null || horaFin == null) {
            return null;
        }
        return Duration.between(horaInicio,horaFin);
    }

    /**
     * Inicia la reunion, registrando la hora de inicio
     */
    public void iniciar(){
        horaInicio = Instant.now();
    }

    /**
     * Finaliza la reunion, registrando la hora de termino
     */
    public void finalizar() throws ReunionSinIniciarException {
        if(horaInicio==null){
            throw new ReunionSinIniciarException();
        }
        horaFin = Instant.now();
    }

    /**
     * agrega una nueva nota a la reunion
     * @param s el contenido de la nueva nota
     */
    public void agregarNota(String s){
        notas.add(new Nota(s));
    }

    /**
     * devuelve todas las notas tomadas en la reunion
     * @return ArrayList con el contenido de todas las notas de la reunion
     */
    public ArrayList<Nota> obtenerNotas(){
        return new ArrayList<>(notas);
    }

    @Override
    public String toString(){

        ZonedDateTime horaInicial = horaPrevista.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaPrevista = formatoHora.format(horaInicial);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formatoFecha.format(this.fecha);

        horaInicial = horaInicio.atZone(ZoneId.systemDefault());
        String horaInicio = formatoHora.format(horaInicial);

        horaInicial = horaFin.atZone(ZoneId.systemDefault());
        String horaFin = formatoHora.format(horaInicial);

        String listaAsistencia = "|";
        for(Asistencia a: asistencias){

            listaAsistencia += " " + a + " |";
        }

        long horas = duracionPrevista.toHours();
        long minutos = duracionPrevista.toMinutesPart();
        String resultado = String.format("%02d:%02d", horas, minutos);

        String listaInvitaciones = "/";
        for(Invitacion a: invitaciones){

            listaInvitaciones += " " + a + " /";
        }

        horas = this.calcularTiempoReal().toHours();
        minutos = this.calcularTiempoReal().toMinutesPart();
        String resultadoDuracion = String.format("%02d:%02d", horas, minutos);

        String notas = "* ";
        for(Nota n: this.obtenerNotas()){
            notas += n + " * ";
        }

        if(this.horaInicio == null || this.horaFin == null){
            //agregar exepcion de cuando no inicio o termino la reunion
    }
        return "Tipo de Reunion: " + tipoReunion + "   Fecha: " + fecha + "   Duracion Prevista: " + resultado + "   Duracion Real: " + resultadoDuracion +
                "\nHora de Inicio Prevista: " + horaPrevista + "   Hora de inicio: " + horaInicio + "   Hora de termino: " + horaFin +
                "\nInvitaciones:\n" + listaInvitaciones + "\nAsistentes:\n" + listaAsistencia + "\nPorcentaje de Asistencia: " + this.obtenerPorcentajeAsistencia()*100 + "%" +
                "\nNotas: " + notas;
    }
}
