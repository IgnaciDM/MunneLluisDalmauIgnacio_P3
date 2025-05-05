import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.GeneradorVapor;
import prog2.model.PaginaIncidencies;

import static org.junit.jupiter.api.Assertions.*;

class TestGeneradorVapor {

    private GeneradorVapor generador;

    @BeforeEach
    void setUp() {
        generador = new GeneradorVapor();
    }

    @Test
    void testInicialmentDesactivat() {
        assertFalse(generador.getActivat());
        assertEquals(0, generador.getCostOperatiu());
    }

    @Test
    void testActivarGenerador() {
        generador.activa();
        assertTrue(generador.getActivat());
        assertEquals(25, generador.getCostOperatiu());
    }

    @Test
    void testDesactivarGenerador() {
        generador.activa(); // lo activamos primero
        generador.desactiva(); // y lo desactivamos
        assertFalse(generador.getActivat());
        assertEquals(0, generador.getCostOperatiu());
    }

    @Test
    void testCalculaOutputActiu() {
        generador.activa();
        float input = 100f;
        float expectedOutput = 90f;
        assertEquals(expectedOutput, generador.calculaOutput(input), 0.01);
    }

    @Test
    void testCalculaOutputDesactivat() {
        float input = 100f;
        assertEquals(25f, generador.calculaOutput(input));
    }

    @Test
    void testToString() {
        assertEquals("GeneradorVapor [Activat: No]", generador.toString());
        generador.activa();
        assertEquals("GeneradorVapor [Activat: Sí]", generador.toString());
    }

    @Test
    void testRevisaNoFaRes() {
        System.out.println("NO FA RES");
    }
}
