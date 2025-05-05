import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.Turbina;

import static org.junit.jupiter.api.Assertions.*;

class TestTurbina {

    private Turbina turbina;

    @BeforeEach
    void setUp() {
        turbina = new Turbina();
    }

    @Test
    void testInicialDesactivada() {
        assertFalse(turbina.getActivat());
    }

    @Test
    void testActiva() {
        turbina.activa();
        assertTrue(turbina.getActivat());
    }

    @Test
    void testDesactiva() {
        turbina.activa();
        turbina.desactiva();
        assertFalse(turbina.getActivat());
    }

    @Test
    void testGetCostOperatiuDesactivada() {
        assertEquals(0, turbina.getCostOperatiu());
    }

    @Test
    void testGetCostOperatiuActivada() {
        turbina.activa();
        assertEquals(20, turbina.getCostOperatiu());
    }

    @Test
    void testCalculaOutputDesactivada() {
        assertEquals(0, turbina.calculaOutput(150));
    }

    @Test
    void testCalculaOutputActivadaInputMenor100() {
        turbina.activa();
        assertEquals(0, turbina.calculaOutput(50));
    }

    @Test
    void testCalculaOutputActivadaInputMajor100() {
        turbina.activa();
        assertEquals(300, turbina.calculaOutput(150));
    }

    @Test
    void testToStringDesactivada() {
        assertEquals("Turbina [Activat: No]", turbina.toString());
    }

    @Test
    void testToStringActivada() {
        turbina.activa();
        assertEquals("Turbina [Activat: Sí]", turbina.toString());
    }
}
