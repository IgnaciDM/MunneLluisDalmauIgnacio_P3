import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.Turbina;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de proves per a la classe {@link Turbina}.
 *
 * <p>Aquesta classe conté les proves per validar el funcionament de la classe {@link Turbina},
 * assegurant-se que la turbina es comporta correctament quan està activada o desactivada,
 * i que genera una sortida adequada i calcula correctament els costos operatius.</p>
 */
class TestTurbina {

    /**
     * Instància de la classe {@link Turbina} que s'utilitzarà a les proves.
     * Aquesta variable s'inicialitza abans de cada prova amb el mètode {@link #setUp()}.
     */
    private Turbina turbina;

    /**
     * Mètode de configuració que s'executa abans de cada prova.
     * Inicialitza la instància de la classe {@link Turbina} en el seu estat inicial.
     */
    @BeforeEach
    void setUp() {
        turbina = new Turbina();
    }

    /**
     * Prova per verificar que la turbina està desactivada per defecte.
     * S'espera que {@link Turbina#getActivat()} retorni {@code false} al principi.
     */
    @Test
    void testInicialDesactivada() {
        assertFalse(turbina.getActivat());
    }

    /**
     * Prova per activar la turbina.
     * S'espera que {@link Turbina#getActivat()} retorni {@code true} després d'activar la turbina.
     */
    @Test
    void testActiva() {
        turbina.activa();
        assertTrue(turbina.getActivat());
    }

    /**
     * Prova per desactivar la turbina després d'haver estat activada.
     * S'espera que {@link Turbina#getActivat()} retorni {@code false} després de desactivar la turbina.
     */
    @Test
    void testDesactiva() {
        turbina.activa();
        turbina.desactiva();
        assertFalse(turbina.getActivat());
    }

    /**
     * Prova per obtenir el cost operatiu quan la turbina està desactivada.
     * S'espera que {@link Turbina#getCostOperatiu()} retorni {@code 0} quan la turbina està desactivada.
     */
    @Test
    void testGetCostOperatiuDesactivada() {
        assertEquals(0, turbina.getCostOperatiu());
    }

    /**
     * Prova per obtenir el cost operatiu quan la turbina està activada.
     * S'espera que {@link Turbina#getCostOperatiu()} retorni {@code 20} quan la turbina està activada.
     */
    @Test
    void testGetCostOperatiuActivada() {
        turbina.activa();
        assertEquals(20, turbina.getCostOperatiu());
    }

    /**
     * Prova per calcular la sortida quan la turbina està desactivada.
     * S'espera que {@link Turbina#calculaOutput(float)} retorni {@code 0} si la turbina està desactivada.
     */
    @Test
    void testCalculaOutputDesactivada() {
        assertEquals(0, turbina.calculaOutput(150));
    }

    /**
     * Prova per calcular la sortida quan la turbina està activada i l'input és menor que 100.
     * S'espera que {@link Turbina#calculaOutput(float)} retorni {@code 0} si l'input és menor a 100.
     */
    @Test
    void testCalculaOutputActivadaInputMenor100() {
        turbina.activa();
        assertEquals(0, turbina.calculaOutput(50));
    }

    /**
     * Prova per calcular la sortida quan la turbina està activada i l'input és major a 100.
     * S'espera que {@link Turbina#calculaOutput(float)} retorni un valor positiu, per exemple, {@code 300},
     * si l'input és major que 100.
     */
    @Test
    void testCalculaOutputActivadaInputMajor100() {
        turbina.activa();
        assertEquals(300, turbina.calculaOutput(150));
    }

    /**
     * Prova per verificar el resultat del mètode {@link Turbina#toString()} quan la turbina està desactivada.
     * S'espera que el resultat sigui "Turbina [Activat: No]" quan la turbina està desactivada.
     */
    @Test
    void testToStringDesactivada() {
        assertEquals("Turbina [Activat: No]", turbina.toString());
    }

    /**
     * Prova per verificar el resultat del mètode {@link Turbina#toString()} quan la turbina està activada.
     * S'espera que el resultat sigui "Turbina [Activat: Sí]" quan la turbina està activada.
     */
    @Test
    void testToStringActivada() {
        turbina.activa();
        assertEquals("Turbina [Activat: Sí]", turbina.toString());
    }
}

