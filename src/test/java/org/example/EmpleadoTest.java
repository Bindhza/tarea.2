package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmpleadoTest {
    @Test
    void gettersTest() {
        Empleado e1 = new Empleado("webiwabo@xyz.to","Sanchez Sotelo", "Juan", "2244208");
        assertEquals("webiwabo@xyz.to",e1.getCorreo());
        assertEquals("Sanchez Sotelo",e1.getApellidos());
        assertEquals("Juan",e1.getNombre());
        assertEquals("2244208",e1.getId());

    }
}
