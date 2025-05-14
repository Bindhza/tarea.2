package org.example;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RetrasoTest {

    @Test
    void getHora() throws NoEstaInvitadoException, ReunionFinalizadaException, ReunionIniciadaException {
        Reunion r = new ReunionVirtual(new Date(193892), Instant.now(), Duration.ZERO,"58192",tipoReunion.OTRO);
        Externo e1 = new Externo("ainuw","finaw");
        e1.invitar(r);
        r.iniciar();
        Instant llegada = r.asistir(e1);
        Retraso ret = r.obtenerRetrasos().getFirst();
        assertEquals(llegada,ret.getHora());
    }
}