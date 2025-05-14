package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Date fechaActual = Date.from(Instant.now());
        ReunionVirtual reunion = new ReunionVirtual(fechaActual, Instant.now(), Duration.ofHours(2),"prueba.com", tipoReunion.MARKETING);
        Empleado empleado1 = new Empleado("prueba@gmail.com", "Perez", "Juanito", "666");
        empleado1.invitar(reunion);
        reunion.llegar(empleado1);
        reunion.iniciar();

        System.out.println();
        System.out.println();
        System.out.println();

        reunion.finalizar();

        System.out.println(reunion);
        System.out.println(reunion);
    }
}