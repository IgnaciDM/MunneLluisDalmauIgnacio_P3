import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.BombaRefrigerant;
import prog2.model.PaginaIncidencies;
import prog2.model.VariableUniforme;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class TestBombaRefrigerant {

    private VariableUniforme variableAlta; // retornarà sempre > 25
    private VariableUniforme variableBaixa; // retornarà sempre < 25
    private BombaRefrigerant bomba;

    @BeforeEach
    public void setUp() {
        // Constructor amb valor controlat
        variableAlta = new VariableUniforme(26); // mai provocarà incidència
        variableBaixa = new VariableUniforme(10); // sempre provocarà incidència
        bomba = new BombaRefrigerant(variableAlta, 1);
    }

    @Test
    public void testInicialitzacioCorrecta() {
        assertEquals(1, bomba.getId());
        assertTrue(bomba.operativa); // accessible directament perquè és package-private
        assertFalse(bomba.getForaDeServei());
    }

    @Test
    public void testActivaDesactiva() throws CentralUBException {
        bomba.desactiva();
        assertFalse(bomba.getActivat());

        bomba.activa();
        assertTrue(bomba.getActivat());
    }

    @Test
    public void testActivaQuanForaDeServei() {
        bomba.setForaDeServei(false); // operativa = false → fora de servei = true
        bomba.operativa = false;

        assertThrows(CentralUBException.class, () -> bomba.activa());
    }

    @Test
    public void testCapacitatICostQuanActiva() throws CentralUBException {
        bomba.activa();
        assertEquals(1f, bomba.getCapacitat());
        assertEquals(130f, bomba.getCostOperatiu());
    }

    @Test
    public void testCapacitatICostQuanDesactiva() {
        bomba.desactiva();
        assertEquals(0f, bomba.getCapacitat());
        assertEquals(0f, bomba.getCostOperatiu());
    }

    @Test
    public void testRevisioAmbIncidencia() {
        bomba = new BombaRefrigerant(variableBaixa, 2);
        PaginaIncidencies p = new PaginaIncidencies(1);//MODIFICABLEEEEE

        bomba.revisa(p);

        assertTrue(bomba.getForaDeServei());
    }

    @Test
    public void testToString() {
        bomba.desactiva();
        bomba.setForaDeServei(true);//Indica que no esta operativa

        assertEquals("Id=1, Activat=false, Fora de Servei=true", bomba.toString());
    }
}
