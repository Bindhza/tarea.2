package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    private final ArrayList<Asistencia> asistencias;
    private final ArrayList<Invitacion> invitaciones;
    private final ArrayList<Nota> notas;
    private Date fecha;
    private Instant horaPrevista, horaInicio, horaFin;
    private Duration duracionPrevista;
    private tipoReunion tipoReunion;


    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista, tipoReunion tipoReunion) {
        if (fecha == null || horaPrevista == null || duracionPrevista == null || tipoReunion == null) {
            throw new NullPointerException();
        }
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
     * Obtiene la duracion prevista
     *
     * @return un objeto Duration, que representa la duracion esperada de la reunion
     */
    public Duration getDuracionPrevista() {
        return duracionPrevista;
    }

    /**
     * Cambia la duracion prevista de la reunion
     *
     * @param duracionPrevista la duracion esperada de la reunion
     */
    public void setDuracionPrevista(Duration duracionPrevista) {
        if (duracionPrevista == null) {
            throw new NullPointerException();
        }
        this.duracionPrevista = duracionPrevista;
    }

    /**
     * Obtiene la hora prevista de inicio de la reunion
     *
     * @return un objeto Instant, que representa la hora de inicio
     */
    public Instant getHoraPrevista() {
        return horaPrevista;
    }

    /**
     * Cambia la hora prevista de la reunion
     *
     * @param horaPrevista la nueva hora
     */
    public void setHoraPrevista(Instant horaPrevista) {
        if (horaPrevista == null) {
            throw new NullPointerException();
        }
        this.horaPrevista = horaPrevista;
    }

    /**
     * Obtiene la fecha en la cual se celebrara la reunion
     *
     * @return un objeto Date, que representa la fecha de inicio
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Cambia la fecha cuando se celebra la reunion
     *
     * @param fecha la nueva fecha
     */
    public void setFecha(Date fecha) {
        if (fecha == null) {
            throw new NullPointerException();
        }
        this.fecha = fecha;
    }

    /**
     * Obtiene el tipo de reunion que se va a celebrar
     *
     * @return una enumeracion que representa el tipo de reunion
     */
    public tipoReunion getTipoReunion() {
        return tipoReunion;
    }

    /**
     * Cambia el tipo de reunion
     *
     * @param tipoReunion una enumeracion que representa los distintos tipos de reunion
     */
    public void setTipoReunion(tipoReunion tipoReunion) {
        if (tipoReunion == null){
            throw new NullPointerException();
        }
        this.tipoReunion = tipoReunion;
    }

    /**
     * Obtiene una copia de las asistencias hasta el momento a la reunion
     *
     * @return los asistentes a la reunion
     */
    public ArrayList<Asistencia> obtenerAsistencias() {
        return new ArrayList<>(asistencias); // cambiado para evitar cambios en el arreglo desde afuera
    }

    /**
     * agrega una invitacion a la lista de invitaciones, lo usa la interfaz Invitable
     *
     * @param i la persona o departamento que se va a invitar
     */
    public void crearInvitacion(Invitable i) {
        //esta excepcion nunca deberia pasar si se usa correctamente la interfaz Invitable
        if (i == null){
            throw new NullPointerException();
        }
        invitaciones.add(new Invitacion(Instant.now(), i));
    }

    /**
     * verifica si un objeto esta invitado a la reunion
     *
     * @param i la persona o departamento a ser comprobado
     * @return true si esta invitado, false si no lo esta
     */
    public boolean verificarInvitacion(Invitable i) {
        if (i == null){
            throw new NullPointerException();
        }
        for (Invitacion inv : invitaciones) {
            if (inv.getInvitado() == i) {
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
     * Registra un invitado en el registro de asistencias, los departamentos no se registran,
     * ya que solo las personas pueden asistir
     *
     * @param i la persona que va a llegar
     */
    public void asistir(Invitable i) throws NoEstaInvitadoException, ReunionFinalizadaException {
        if (horaFin != null) {
            throw new ReunionFinalizadaException();
        }
        if (!verificarInvitacion(i)){
            throw new NoEstaInvitadoException();
        }

        if (i instanceof Departamento) {
            return;
        }

        if (horaInicio == null) {
            asistencias.add(new Asistencia(i));
        } else {
            asistencias.add(new Retraso(i, Instant.now()));
        }
    }

    /**
     * Retorna una lista de la gente que no ha llegado a la reunion
     *
     * @return un ArrayList que contiene la gente ausente de la reunion
     */
    public ArrayList<Invitable> obtenerAusencias() throws ReunionSinFinalizarException, ReunionSinIniciarException {
        //por favor si es posible cambiar esto, no me gusta
        if (horaInicio == null) {
            throw new ReunionSinIniciarException();
        }
        if (horaFin == null) {
            throw new ReunionSinFinalizarException();
        }
        ArrayList<Invitable> ausencias = new ArrayList<>();

        for (Invitacion i : invitaciones) {
            if (i.getInvitado() instanceof Departamento) {
                continue;
            }
            ausencias.add(i.getInvitado());
        }
        for (Asistencia a : asistencias) {
            ausencias.remove(a.getAsistente());
        }
        return ausencias;
    }

    /**
     * Obtiene una lista de las personas que llegaron atrasadas a la reunion, asociados a una
     * hora de llegada
     *
     * @return un ArrayList que contiene los objetos Retraso asociados a cada persona
     */
    public ArrayList<Retraso> obtenerRetrasos() {
        ArrayList<Retraso> retrasos = new ArrayList<>();
        for (Asistencia a : asistencias) {
            // se enojara el profe si uso un instanceof?
            if (a instanceof Retraso) {
                retrasos.add((Retraso) a);
            }
        }
        return retrasos;
    }

    /**
     * Obtiene la cantidad de personas que han llegado a la reunion
     *
     * @return un entero representando la cantidad de personas que han llegado a la reunion
     */
    public int obtenerTotalAsistencia() {
        return asistencias.size();
    }

    /**
     * Obtiene el porcentaje de personas que asistio a la reunion, los departamentos no se cuentan, pero
     * si las personas que forman parte de este
     *
     * @return un número decimal que representa el porcentaje
     */
    public float obtenerPorcentajeAsistencia() {
        float personasEsperadas = invitaciones
                .stream()
                .filter(p -> !(p.getInvitado() instanceof Departamento))
                .toList().size();
        return asistencias.size() / personasEsperadas;
    }

    /**
     * Calcula la duracion real de la reunion
     *
     * @return un objeto Duracion que representa la duracion real
     */
    public Duration calcularTiempoReal() {
        if (horaInicio == null || horaFin == null) {
            return null;
        }
        return Duration.between(horaInicio, horaFin);
    }

    /**
     * Inicia la reunion, registrando la hora de inicio
     * @return instante en el que fue iniciada la reunion
     */
    public Instant iniciar() throws ReunionIniciadaException {
        if (horaInicio != null){
            throw new ReunionIniciadaException();
        }
        horaInicio = Instant.now();
        return horaInicio;
    }

    /**
     * Finaliza la reunion, registrando y devolviendo la hora de término
     * @return el instante en el que fue finalizada la reunion
     */
    public Instant finalizar() throws ReunionSinIniciarException, ReunionFinalizadaException {
        if (horaInicio == null) {
            throw new ReunionSinIniciarException();
        }
        if (horaFin != null) {
            throw new ReunionFinalizadaException();
        }
        horaFin = Instant.now();
        return horaFin;
    }

    /**
     * Agrega una nueva nota a la reunion
     *
     * @param s el contenido de la nueva nota
     */
    public void agregarNota(String s) {
        if(s == null){
            return;
        }
        notas.add(new Nota(s));
    }

    /**
     * Devuelve todas las notas tomadas en la reunion
     *
     * @return ArrayList con el contenido de todas las notas de la reunion
     */
    public ArrayList<Nota> obtenerNotas() {
        return new ArrayList<>(notas);
    }

    /**
     * Genera un informe sobre la reunion
     * el informe se almacenara en informe.txt
     *
     * @throws IOException cuando falla la creacion del archivo
     */
    public void generarInforme() throws IOException {
        File archivo = new File("informe.txt");
        FileWriter writer = new FileWriter(archivo);
        PrintWriter print = new PrintWriter(writer);
        print.println(this);
        print.close();

    }

    @Override
    public String toString() {
        ZonedDateTime zonedHoraPrevista = horaPrevista.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String strHoraPrevista = formatoHora.format(zonedHoraPrevista);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String strFecha = formatoFecha.format(this.fecha);

        String strHoraInicio = "La reunión no ha comenzado";
        if (horaInicio != null) {
            ZonedDateTime zonedHoraInicio = horaInicio.atZone(ZoneId.systemDefault());
            strHoraInicio = formatoHora.format(zonedHoraInicio);
        }

        String strHoraFin = "La reunión no ha finalizado";
        if (horaFin != null) {
            ZonedDateTime zonedHoraFin = horaFin.atZone(ZoneId.systemDefault());
            strHoraFin = formatoHora.format(zonedHoraFin);
        }

        String strListaAsistencia = "|";
        for (Asistencia asistencia : asistencias) {
            strListaAsistencia += " " + asistencia + " |";
        }

        long horasPrevistas = duracionPrevista.toHours();
        long minutosPrevistos = duracionPrevista.toMinutesPart();
        String strDuracionPrevista = String.format("%02d:%02d", horasPrevistas, minutosPrevistos);

        String strListaInvitaciones = "/";
        for (Invitacion invitacion : invitaciones) {
            strListaInvitaciones += " " + invitacion + " /";
        }

        String strDuracionReal = "No disponible";
        if (horaInicio != null && horaFin != null) {
            long horasReales = this.calcularTiempoReal().toHours();
            long minutosReales = this.calcularTiempoReal().toMinutesPart();
            strDuracionReal = String.format("%02d:%02d", horasReales, minutosReales);
        }

        String strNotas = "* ";
        for (Nota nota : this.obtenerNotas()) {
            strNotas += nota + " * ";
        }

        return "Tipo de Reunion: " + tipoReunion + "   Fecha: " + strFecha +
                "   Duracion Prevista: " + strDuracionPrevista +
                "   Duracion Real: " + strDuracionReal +
                "\nHora de Inicio Prevista: " + strHoraPrevista +
                "   Hora de inicio: " + strHoraInicio +
                "   Hora de termino: " + strHoraFin +
                "\nInvitaciones:\n" + strListaInvitaciones +
                "\nAsistentes:\n" + strListaAsistencia +
                "\nPorcentaje de Asistencia: " + this.obtenerPorcentajeAsistencia() * 100 + "%" +
                "\nNotas: " + strNotas;
    }
}
