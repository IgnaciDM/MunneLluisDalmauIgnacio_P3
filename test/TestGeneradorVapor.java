import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.GeneradorVapor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de proves unitàries per a la classe {@link GeneradorVapor},
 * que representa el component encarregat de generar vapor a partir de l'energia produïda pel reactor.
 */
class TestGeneradorVapor {

    private GeneradorVapor generador;

    /**
     * Inicialitza el generador abans de cada test.
     */
    @BeforeEach
    void setUp() {
        generador = new GeneradorVapor();
    }

    /**
     * Comprova que el generador està desactivat i amb cost operatiu zero just després de la seva creació.
     */
    @Test
    void testInicialmentDesactivat() {
        assertFalse(generador.getActivat());
        assertEquals(0, generador.getCostOperatiu());
    }

    /**
     * Comprova que activar el generador el marca com activat i assigna el cost operatiu correcte.
     */
    @Test
    void testActivarGenerador() {
        generador.activa();
        assertTrue(generador.getActivat());
        assertEquals(25, generador.getCostOperatiu());
    }

    /**
     * Comprova que desactivar el generador el marca com desactivat i posa el cost operatiu a zero.
     */
    @Test
    void testDesactivarGenerador() {
        generador.activa(); // S'activa primer
        generador.desactiva(); // I després es desactiva
        assertFalse(generador.getActivat());
        assertEquals(0, generador.getCostOperatiu());
    }

    /**
     * Comprova que el càlcul de sortida (output) funciona correctament quan el generador està activat.
     * Es perd un 10% de l'energia d'entrada.
     */
    @Test
    void testCalculaOutputActiu() {
        generador.activa();
        float input = 100f;
        float expectedOutput = 90f; // 90% d'eficiència
        assertEquals(expectedOutput, generador.calculaOutput(input), 0.01);
    }

    /**
     * Comprova que, quan el generador està desactivat, la sortida sempre és 25.
     */
    @Test
    void testCalculaOutputDesactivat() {
        float input = 100f;
        assertEquals(25f, generador.calculaOutput(input));
    }

    /**
     * Comprova que el mètode {@code toString} retorna la representació adequada segons l'estat del generador.
     */
    @Test
    void testToString() {
        assertEquals("GeneradorVapor [Activat: No]", generador.toString());
        generador.activa();
        assertEquals("GeneradorVapor [Activat: Sí]", generador.toString());
    }

    /**
     * Test de placeholder per al mètode {@code revisa()}, actualment sense funcionalitat.
     */
    @Test
    void testRevisaNoFaRes() {
        System.out.println("NO FA RES");
    }
}
