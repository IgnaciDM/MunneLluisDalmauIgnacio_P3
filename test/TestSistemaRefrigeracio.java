import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.BombaRefrigerant;
import prog2.model.SistemaRefrigeracio;
import prog2.model.PaginaIncidencies;
import prog2.model.VariableUniforme;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class TestSistemaRefrigeracio {

    private SistemaRefrigeracio sistema;
    private PaginaIncidencies pagina;

    @BeforeEach
    void setUp() {
        sistema = new SistemaRefrigeracio();
        pagina = new PaginaIncidencies(1);
    }

    private BombaRefrigerant creaBomba(int id) {
        // Crea una VariableUniforme con valores que simulen el comportamiento esperado
        VariableUniforme var = new VariableUniforme(0); // o los valores que use tu lógica
        return new BombaRefrigerant(var, id);
    }

    @Test
    void testAfegirBomba() {
        BombaRefrigerant b = creaBomba(1);
        sistema.afegirBomba(b);
        assertEquals(1, sistema.getllistabombes().size());
        assertEquals(b, sistema.getllistabombes().get(0));
    }

    @Test
    void testGetActivat() throws CentralUBException {
        BombaRefrigerant b1 = creaBomba(1);
        BombaRefrigerant b2 = creaBomba(2);
        sistema.afegirBomba(b1);
        sistema.afegirBomba(b2);
        b1.activa();
        assertTrue(sistema.getActivat());
    }

    @Test
    void testGetActivatCapActiva() {
        BombaRefrigerant b1 = creaBomba(1);
        sistema.afegirBomba(b1);
        assertFalse(sistema.getActivat());
    }

    @Test
    void testCalculaOutput() throws CentralUBException {
        BombaRefrigerant b1 = creaBomba(1);
        BombaRefrigerant b2 = creaBomba(2);
        b1.activa();
        b2.activa();
        sistema.afegirBomba(b1);
        sistema.afegirBomba(b2);

        float input = 600;
        float output = sistema.calculaOutput(input);
        assertEquals(500, output);
    }

    @Test
    void testCalculaOutputInferior() throws CentralUBException {
        BombaRefrigerant b1 = creaBomba(1);
        b1.activa();
        sistema.afegirBomba(b1);

        float input = 200;
        float output = sistema.calculaOutput(input);
        assertEquals(200, output);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    void testGetCostOperatiu() {
        BombaRefrigerant b1 = creaBomba(1);
        BombaRefrigerant b2 = creaBomba(2);
        sistema.afegirBomba(b1);
        sistema.afegirBomba(b2);
        System.out.println("sistema.getCostOperatiu()");
        assertEquals(260, sistema.getCostOperatiu());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    void testDesactiva() throws CentralUBException {
        BombaRefrigerant b1 = creaBomba(1);
        b1.activa();
        sistema.afegirBomba(b1);
        sistema.desactiva();
        assertFalse(b1.getActivat());
    }

    @Test
    void testRevisa() {
        BombaRefrigerant b1 = creaBomba(1);
        sistema.afegirBomba(b1);
        b1.setForaDeServei(true);

        sistema.revisa(pagina);

        assertTrue(pagina.getIncidencies().contains("La bomba 1 esta fora de servei"));
        assertFalse(b1.getActivat());
    }
}
