import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.PaginaIncidencies;
import prog2.model.Reactor;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de proves unitàries per a la classe {@link Reactor}.
 * Es comproven els comportaments inicials, mètodes de configuració,
 * activació i desactivació, càlculs d'output, costos i gestió d'incidències.
 */
public class TestReactor {
    private Reactor reactor;

    /**
     * Inicialitza un nou objecte {@link Reactor} abans de cada test.
     */
    @BeforeEach
    void setUp() {
        reactor = new Reactor();
    }

    /**
     * Comprova que el reactor estigui inicialment desactivat
     * i amb una temperatura de 25 graus.
     */
    @Test
    void testInicial() {
        assertFalse(reactor.getActivat());
        assertEquals(25, reactor.gettemperatura());
    }

    /**
     * Comprova que es pugui establir i recuperar correctament la temperatura.
     */
    @Test
    void testTemperatura() {
        reactor.settemperatura(100);
        assertEquals(100, reactor.gettemperatura());
    }

    /**
     * Comprova que el reactor es pugui activar si la temperatura és segura.
     */
    @Test
    void testActivaReactor() throws CentralUBException {
        reactor.settemperatura(10);
        reactor.activa();
        assertTrue(reactor.getActivat());
    }

    /**
     * Comprova que es llanci una excepció quan s’intenta activar
     * el reactor amb una temperatura superior a 1000 ºC.
     */
    @Test
    void testActivaExcepcio() {
        reactor.settemperatura(1001);
        CentralUBException ex = assertThrows(CentralUBException.class, () -> {
            reactor.activa();
        });
        assertEquals("El Reactor te una temperatura superior a 1000 graus, no es pot activar", ex.getMessage());
        assertFalse(reactor.getActivat());
    }

    /**
     * Comprova que el reactor es desactiva correctament després d'estar activat.
     */
    @Test
    void testDesactiva() throws CentralUBException {
        reactor.settemperatura(500);
        reactor.activa();
        reactor.desactiva();
        assertFalse(reactor.getActivat());
    }

    /**
     * Comprova que es registra una incidència si la temperatura és massa alta.
     */
    @Test
    void testRevisaAmbIncidencia() {
        PaginaIncidencies p = new PaginaIncidencies(1);
        reactor.settemperatura(1001); // temperatura massa alta
        reactor.revisa(p);

        assertEquals(1, p.getIncidencies().size());
        assertTrue(p.getIncidencies().get(0).contains("El reactor es va desactivar per superar la temperatura màxima de 1000 graus")); // o el text exacte esperat
    }

    /**
     * Comprova que no es registri cap incidència si la temperatura és segura.
     */
    @Test
    void testRevisaSenseIncidencia() {
        PaginaIncidencies p = new PaginaIncidencies(1);
        reactor.settemperatura(500);
        reactor.revisa(p);

        assertEquals(0, p.getIncidencies().size());
    }

    /**
     * Comprova que el cost operatiu sigui 0 quan el reactor està desactivat.
     */
    @Test
    void testGetCostOperatiuDesactivat() {
        assertEquals(0, reactor.getCostOperatiu());
    }

    /**
     * Comprova que el cost operatiu sigui 35 quan el reactor està activat.
     */
    @Test
    void testGetCostOperatiuActivat() throws CentralUBException {
        reactor.settemperatura(500);
        reactor.activa();
        assertEquals(35, reactor.getCostOperatiu());
    }

    /**
     * Comprova el càlcul de l'output quan el reactor està desactivat.
     */
    @Test
    void testCalculaOutputDesactivat() {
        reactor.settemperatura(400);
        assertEquals(400, reactor.calculaOutput(80));
    }

    /**
     * Comprova el càlcul de l'output quan el reactor està activat.
     */
    @Test
    void testCalculaOutputActivat() throws CentralUBException {
        reactor.settemperatura(400);
        reactor.activa();
        assertEquals(400 + (100 - 80) * 10, reactor.calculaOutput(80));
    }

    /**
     * Comprova que el mètode {@code toString} retorni el format esperat.
     */
    @Test
    void testToString() {
        assertEquals("Reactor [Activat: No, Temperatura: 25.0 ºC]", reactor.toString());
    }
}

