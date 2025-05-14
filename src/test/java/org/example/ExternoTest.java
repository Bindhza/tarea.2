package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExternoTest {

    @Test
    void gettersTest() {
        Externo e1 = new Externo("Mario","Benitez");
        assertEquals("Mario",e1.getNombre());
        assertEquals("Benitez",e1.getApellidos());
    }
}