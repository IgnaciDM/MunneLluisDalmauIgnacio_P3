import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.PaginaIncidencies;
import prog2.model.Reactor;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReactor {
    private Reactor reactor;

    @BeforeEach
    void setUp() {
        reactor = new Reactor();
    }

    @Test
    void testInicial() {
        assertFalse(reactor.getActivat());
        assertEquals(25, reactor.gettemperatura());
    }

    @Test
    void testTemperatura(){
        reactor.settemperatura(100);
        assertEquals(100,reactor.gettemperatura());
    }

    @Test
    void testActivaReactor() throws CentralUBException {
        reactor.settemperatura(10);
        reactor.activa();
        assertTrue(reactor.getActivat());
    }
    @Test
    void testActivaExcepcio() {
        reactor.settemperatura(1001);
        CentralUBException ex = assertThrows(CentralUBException.class, () -> {
            reactor.activa();
        });
        assertEquals("El Reactor te una temperatura superior a 1000 graus, no es pot activar", ex.getMessage());
        assertFalse(reactor.getActivat());
    }
    @Test
    void testDesactiva() throws CentralUBException {
        reactor.settemperatura(500);
        reactor.activa();
        reactor.desactiva();
        assertFalse(reactor.getActivat());
    }

    @Test
    void testRevisaAmbIncidencia() {
        PaginaIncidencies p = new PaginaIncidencies(1);
        reactor.settemperatura(1001); // temperatura massa alta
        reactor.revisa(p);

        assertEquals(1, p.getIncidencies().size());
        assertTrue(p.getIncidencies().get(0).contains("Reactor")); // o el text exacte esperat
    }

    @Test
    void testRevisaSenseIncidencia() {
        PaginaIncidencies p = new PaginaIncidencies(1);
        reactor.settemperatura(500);
        reactor.revisa(p);

        assertEquals(0, p.getIncidencies().size());
    }

    @Test
    void testGetCostOperatiuDesactivat() {
        assertEquals(0, reactor.getCostOperatiu());
    }

    @Test
    void testGetCostOperatiuActivat() throws CentralUBException {
        reactor.settemperatura(500);
        reactor.activa();
        assertEquals(35, reactor.getCostOperatiu());
    }

    @Test
    void testCalculaOutputDesactivat() {
        reactor.settemperatura(400);
        assertEquals(400, reactor.calculaOutput(80));
    }

    @Test
    void testCalculaOutputActivat() throws CentralUBException {
        reactor.settemperatura(400);
        reactor.activa();
        assertEquals(400 + (100 - 80) * 10, reactor.calculaOutput(80));
    }

    @Test
    void testToString() {
        assertEquals("Reactor [Activat: No, Temperatura: 25.0 ºC]", reactor.toString());
    }
}
