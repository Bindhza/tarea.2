package org.example;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * Reuniones
 * @author Maximo Beltran
 * @author Benjamin Poblete
 */

public class Main {
    public static void main(String[] args) throws ReunionSinIniciarException, IOException {

        Date fechaActual = Date.from(Instant.now());
        ReunionVirtual reunion = new ReunionVirtual(fechaActual, Instant.now(), Duration.ofHours(2),"prueba.com", tipoReunion.MARKETING);
        Empleado empleado = new Empleado("prueba@gmail.com", "Perez", "Juanito", "666");
        empleado.invitar(reunion);
        reunion.llegar(empleado);
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

        try {
            reunion.generarInforme();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}