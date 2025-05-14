package org.example;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstanciasReunionTest {
    @Test
    public void testGettersYSetters() {
        ReunionVirtual rv = new ReunionVirtual(new Date(193892), Instant.now(), Duration.ZERO,"mawabo.xyz",tipoReunion.OTRO);
        ReunionPresencial rp = new ReunionPresencial(new Date(193892), Instant.now(), Duration.ZERO,"A-616",tipoReunion.OTRO);
        assertEquals("mawabo.xyz", rv.getEnlace());
        assertEquals("A-616",rp.getSala());
        rv.setEnlace("zoom.gg");
        rp.setSala("A1");
        assertEquals("zoom.gg", rv.getEnlace());
        assertEquals("A1", rp.getSala());
    }
}
