package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class ReunionTest {
    Reunion r;
    Empleado empleado1,empleado2,empleado3;
    @BeforeEach
    void setUp() {
        r = new ReunionVirtual(new Date(193892), Instant.now(), Duration.ZERO,"58192",tipoReunion.OTRO);
        empleado1 = new Empleado("iofnwa","69827","5273","589172");
        empleado2 = new Empleado("09iue7h8yuf","kivnuih","ifnawubh ","3918uh");
        empleado3 = new Empleado("iofnwpv98rua","vojaiud69827","5273vkoaj","5891bkoaij72");
    }
    @Test
    void crearYVerificarInvitacion(){
        assertFalse(r.verificarInvitacion(empleado1));
        r.crearInvitacion(empleado1);
        assertTrue(r.verificarInvitacion(empleado1));
    }
    @Test
    void crearConNull(){
        assertThrows(NullPointerException.class,() -> r.crearInvitacion(null));
    }
    @Test
    void iniciarYTerminarReunion() throws ReunionIniciadaException, ReunionSinIniciarException, ReunionFinalizadaException {
        assertThrows(ReunionSinIniciarException.class, () -> r.finalizar());
        Instant inicio = r.iniciar();
        assertThrows(ReunionIniciadaException.class, () -> r.iniciar());
        Instant fin = r.finalizar();
        assertThrows(ReunionFinalizadaException.class, () -> r.finalizar());
        assertEquals(r.calcularTiempoReal(),Duration.between(inicio,fin));
    }
    @Test
    void notaNula(){
        r.agregarNota(null);
        assertEquals(0, r.obtenerNotas().size());
    }
    @Test
    void agregarNotas(){
        r.agregarNota("holi");
        r.agregarNota("adios");
        r.agregarNota(null);
        r.agregarNota(":(){ :|:& };:");
        ArrayList<Nota> notas = r.obtenerNotas();
        assertEquals("holi",notas.get(0).getContenido());
        assertEquals("adios",notas.get(1).getContenido());
        assertEquals(":(){ :|:& };:",notas.get(2).getContenido());
    }
    @Test
    void probarAsistencias() throws NoEstaInvitadoException, ReunionFinalizadaException, ReunionIniciadaException, ReunionSinIniciarException {
        empleado1.invitar(r);
        empleado2.invitar(r);
        empleado3.invitar(r);
        r.asistir(empleado1);
        r.iniciar();
        r.asistir(empleado3);
        r.finalizar();
        assertEquals(2, r.obtenerTotalAsistencia());
        assertEquals(1,r.obtenerRetrasos().size());
        assertEquals(empleado3,r.obtenerRetrasos().getFirst().getAsistente());
    }
    @Test
    void casosExtremosAsistir() throws ReunionFinalizadaException, ReunionIniciadaException, ReunionSinIniciarException, NoEstaInvitadoException {
        empleado1.invitar(r);
        Departamento d = new Departamento("mfeainub");
        d.invitar(r);
        r.iniciar();
        assertThrows(NullPointerException.class, () -> r.asistir(null));
        assertThrows(NoEstaInvitadoException.class, () -> r.asistir(empleado2));
        r.asistir(d);
        r.finalizar();
        assertThrows(ReunionFinalizadaException.class, () -> r.asistir(empleado3));
        assert !r
                .obtenerAsistencias()
                .stream()
                .map(p -> p.getAsistente())
                .toList()
                .contains(d);
        // el departamento puede asistir, pero no lo contamos en la estadística
    }
    @Test
    void probarAusencias() throws NoEstaInvitadoException, ReunionFinalizadaException, ReunionIniciadaException, ReunionSinIniciarException, ReunionSinFinalizarException {
        empleado1.invitar(r);
        empleado2.invitar(r);
        empleado3.invitar(r);
        assertThrows(ReunionSinIniciarException.class, () -> r.obtenerAusencias());
        r.iniciar();
        r.asistir(empleado1);
        r.asistir(empleado2);
        assertThrows(ReunionSinFinalizarException.class, () -> r.obtenerAusencias());
        r.finalizar();
        assertEquals(empleado3,r.obtenerAusencias().getFirst());
    }

    @Test
    void probarEstadisticasAsistencia() throws ReunionIniciadaException, NoEstaInvitadoException, ReunionFinalizadaException, ReunionSinIniciarException {
        Departamento d = new Departamento("mfeainumhui90¿'''b");
        d.agregarEmpleado(empleado1);
        d.agregarEmpleado(empleado2);
        d.agregarEmpleado(empleado3);
        d.invitar(r);
        r.iniciar();
        r.asistir(empleado1);
        r.asistir(empleado2);
        r.finalizar();
        assertEquals((float) 2/3,r.obtenerPorcentajeAsistencia());
        assertEquals(2,r.obtenerTotalAsistencia());
    }
    @Test
    void arrayListsTest() throws NoEstaInvitadoException, ReunionFinalizadaException, ReunionIniciadaException {
        empleado1.invitar(r);
        empleado2.invitar(r);
        empleado3.invitar(r);
        ArrayList<Asistencia> asistencias = r.obtenerAsistencias();
        assertEquals(0,asistencias.size());
        r.iniciar();
        r.asistir(empleado1);
        r.asistir(empleado2);
        r.asistir(empleado3);
        //entregamos una copia, no el arreglo real
        //asi nadie puede modificar la asistencia excepto la reunion misma
        assertEquals(0,asistencias.size());
        assertEquals(3,r.obtenerTotalAsistencia());

    }

}