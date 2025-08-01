package org.example;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * Reuniones
 *
 * @author Máximo Ignacio Beltrán Aranzáez
 * @author Benjamín Poblete
 */
// poner nombre completo
public class Main {
    public static void main(String[] args) throws ReunionSinIniciarException, IOException, NoEstaInvitadoException, ReunionFinalizadaException, ReunionIniciadaException, ReunionSinFinalizarException {

        Date fechaActual = Date.from(Instant.now());
        ReunionVirtual reunion = new ReunionVirtual(fechaActual, Instant.now(), Duration.ofHours(2), "prueba.com", tipoReunion.MARKETING);
        Empleado empleado = new Empleado("prueba@gmail.com", "Perez", "Juanito", "666");
        empleado.invitar(reunion);
        reunion.asistir(empleado);
        reunion.iniciar();
        reunion.agregarNota("Hola");

        System.out.println();
        System.out.println();
        System.out.println();

        try {
            reunion.finalizar();
        } catch (ReunionSinIniciarException error) {
            System.out.println("reunion no iniciada");
        }

        System.out.println(reunion);
        System.out.println(reunion);
        System.out.println(empleado);
        System.out.println(reunion.obtenerAsistencias());
        System.out.println(reunion.obtenerAusencias());


    }
}