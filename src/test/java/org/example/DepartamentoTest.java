package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartamentoTest {
    Departamento departamento;

    @BeforeEach
    void setUp() {
        departamento = new Departamento("d");
    }


    @Test
    void obtenerCantidadEmpleados(){
        assertEquals(0,departamento.obtenerCantidadEmpleados());
        Empleado a = new Empleado("dwna","doiwand","wdioawn","foawn");
        departamento.agregarEmpleado(a);
        assertEquals(1,departamento.obtenerCantidadEmpleados());
    }


    @Test
    void agregarEmpleado() {
        Empleado e1 = new Empleado("jdnaw","wdwna","uinwaid","dwuiandawio");
        Empleado e2 = new Empleado("wdwnia","awuonf","wdafa","gnrui");
        assertEquals(0, departamento.obtenerCantidadEmpleados());
        departamento.agregarEmpleado(e1);
        assertEquals(1, departamento.obtenerCantidadEmpleados());
        departamento.agregarEmpleado(e2);
        assertEquals(2, departamento.obtenerCantidadEmpleados());
        try {
            departamento.agregarEmpleado(null);
            fail();
        } catch (NullPointerException e) {
            assertTrue(true);
        }

    }
}