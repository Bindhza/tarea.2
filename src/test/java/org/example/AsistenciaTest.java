package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsistenciaTest {


    @Test
    void getAsistente() {
        Externo a = new Externo("a","b");
        Empleado b = new Empleado("a","b","c","d");
        Asistencia asistencia1 = new Asistencia(a);
        Asistencia asistencia2 = new Asistencia(b);
        assertEquals(a,asistencia1.getAsistente());
        assertEquals(b,asistencia2.getAsistente());
    }

    @Test
    void testToString() {
        Externo a = new Externo("a","b");
        Empleado b = new Empleado("a","b","c","d");
        Asistencia asistencia1 = new Asistencia(a);
        Asistencia asistencia2 = new Asistencia(b);
        assertEquals(a.toString(),asistencia1.toString());
        assertEquals(b.toString(),asistencia2.toString());

    }
}