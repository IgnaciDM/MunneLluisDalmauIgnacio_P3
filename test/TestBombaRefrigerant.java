import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.BombaRefrigerant;
import prog2.model.PaginaIncidencies;
import prog2.model.VariableUniforme;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de proves unitàries per a la classe {@link BombaRefrigerant}.
 * Es comprova el comportament de la bomba refrigerant davant diverses situacions
 * com ara activació, desactivació, revisió per incidències i càlcul de costos/capacitat.
 */
class TestBombaRefrigerant {

    private VariableUniforme variableAlta; // retornarà sempre > 25
    private VariableUniforme variableBaixa; // retornarà sempre < 25
    private BombaRefrigerant bomba;

    /**
     * Inicialitza dues variables uniformes i una bomba refrigerant abans de cada test.
     * {@code variableAlta} no provoca incidències. {@code variableBaixa} sí.
     */
    @BeforeEach
    public void setUp() {
        variableAlta = new VariableUniforme(26); // no provocarà incidència
        variableBaixa = new VariableUniforme(10); // provocarà incidència
        bomba = new BombaRefrigerant(variableAlta, 1);
    }

    /**
     * Comprova que la bomba s’inicialitza correctament amb els valors esperats.
     */
    @Test
    public void testInicialitzacioCorrecta() {
        assertEquals(1, bomba.getId());
        assertTrue(bomba.operativa); // accessible directament
        assertFalse(bomba.getForaDeServei());
    }

    /**
     * Comprova que els mètodes {@code activa()} i {@code desactiva()} funcionen correctament.
     */
    @Test
    public void testActivaDesactiva() throws CentralUBException {
        bomba.desactiva();
        assertFalse(bomba.getActivat());

        bomba.activa();
        assertTrue(bomba.getActivat());
    }

    /**
     * Comprova que no es pot activar una bomba que està fora de servei.
     */
    @Test
    public void testActivaQuanForaDeServei() {
        bomba.setForaDeServei(false);
        bomba.operativa = false;

        assertThrows(CentralUBException.class, () -> bomba.activa());
    }

    /**
     * Comprova la capacitat i el cost operatiu quan la bomba està activada.
     */
    @Test
    public void testCapacitatICostQuanActiva() throws CentralUBException {
        bomba.activa();
        assertEquals(250f, bomba.getCapacitat());
        assertEquals(130f, bomba.getCostOperatiu());
    }

    /**
     * Comprova que la capacitat i el cost operatiu són 0 quan la bomba està desactivada.
     */
    @Test
    public void testCapacitatICostQuanDesactiva() {
        bomba.desactiva();
        assertEquals(0f, bomba.getCapacitat());
        assertEquals(0f, bomba.getCostOperatiu());
    }

    /**
     * Comprova que es detecta una incidència durant la revisió i que la bomba queda fora de servei.
     */
    @Test
    public void testRevisioAmbIncidencia() {
        bomba = new BombaRefrigerant(variableBaixa, 2);
        PaginaIncidencies p = new PaginaIncidencies(1);

        bomba.revisa(p);

        assertTrue(bomba.getForaDeServei());
    }

    /**
     * Comprova que el mètode {@code toString()} reflecteix correctament l'estat de la bomba.
     */
    @Test
    public void testToString() {
        bomba.desactiva();
        bomba.setForaDeServei(true);

        assertEquals("Id=1, Activat=false, Fora de Servei=true", bomba.toString());
    }
}
