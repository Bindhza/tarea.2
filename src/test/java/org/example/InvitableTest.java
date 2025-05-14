package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InvitableTest {

    Reunion r;
    Empleado e,e1,e2,e3;
    Departamento d;
    Externo ex;
    @BeforeEach
    void setUp() {
        r = new ReunionPresencial(new Date(324189), Instant.now(), Duration.ZERO,"248",tipoReunion.OTRO);
        e = new Empleado("41ion","kjfnawof","fkjenf","fionaf");
        e1 = new Empleado("41ion","kjfnawof","fkjenf","fionaf");
        e2 = new Empleado("94183","aneoijaad","ph0598u","90jguh");
        e3 = new Empleado("945134183","anebgggbds","phsdfvs0598u","90jgnfsuh");
        d = new Departamento("webiwabo");
        ex = new Externo("fwaoi","fiwnaf");
    }
    @Test
    void invitarIndividual() {
        assertFalse(r.verificarInvitacion(e));
        e.invitar(r);
        assertTrue(r.verificarInvitacion(e));
        assertFalse(r.verificarInvitacion(ex));
        ex.invitar(r);
        assertTrue(r.verificarInvitacion(ex));
    }
    @Test
    void invitarDepartamento(){
        d.agregarEmpleado(e1);
        d.agregarEmpleado(e2);
        d.agregarEmpleado(e3);
        d.invitar(r);
        ex.invitar(r);

        assertTrue(r.verificarInvitacion(e1));
        assertTrue(r.verificarInvitacion(e2));
        assertTrue(r.verificarInvitacion(e3));
        assertTrue(r.verificarInvitacion(d));
        assertTrue(r.verificarInvitacion(ex));

        assertFalse(r.verificarInvitacion(e));
    }

    @Test
    void reunionNula(){
        assertThrows(NullPointerException.class, ()->e.invitar(null));
    }
}