import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.BombaRefrigerant;
import prog2.model.SistemaRefrigeracio;
import prog2.model.PaginaIncidencies;
import prog2.model.VariableUniforme;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de proves JUnit per verificar el comportament de la classe {@link SistemaRefrigeracio}.
 */
class TestSistemaRefrigeracio {

    private SistemaRefrigeracio sistema;
    private PaginaIncidencies pagina;

    /**
     * Configura un entorn net abans de cada prova, inicialitzant el sistema i la pàgina d'incidències.
     */
    @BeforeEach
    void setUp() {
        sistema = new SistemaRefrigeracio();
        pagina = new PaginaIncidencies(1);
    }

    /**
     * Crea una instància de {@link BombaRefrigerant} amb un {@link VariableUniforme} simulat.
     * @param id Identificador de la bomba.
     * @return Nova instància de BombaRefrigerant.
     */
    private BombaRefrigerant creaBomba(int id) {
        VariableUniforme var = new VariableUniforme(0); // Simula valors deterministes
        return new BombaRefrigerant(var, id);
    }

    /**
     * Comprova que es pot afegir una bomba al sistema i que es guarda correctament.
     */
    @Test
    void testAfegirBomba() {
        BombaRefrigerant b = creaBomba(1);
        sistema.afegirBomba(b);
        assertEquals(1, sistema.getllistabombes().size());
        assertEquals(b, sistema.getllistabombes().get(0));
    }

    /**
     * Verifica que el sistema està activat si almenys una bomba està activada.
     */
    @Test
    void testGetActivat() throws CentralUBException {
        BombaRefrigerant b1 = creaBomba(1);
        BombaRefrigerant b2 = creaBomba(2);
        sistema.afegirBomba(b1);
        sistema.afegirBomba(b2);
        b1.activa();
        assertTrue(sistema.getActivat());
    }

    /**
     * Comprova que el sistema no està activat si cap bomba està activada.
     */
    @Test
    void testGetActivatCapActiva() {
        BombaRefrigerant b1 = creaBomba(1);
        sistema.afegirBomba(b1);
        assertFalse(sistema.getActivat());
    }

    /**
     * Comprova el càlcul de sortida del sistema quan dues bombes estan activades.
     */
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

    /**
     * Comprova que la sortida no supera l'entrada si el nombre de bombes actives és limitat.
     */
    @Test
    void testCalculaOutputInferior() throws CentralUBException {
        BombaRefrigerant b1 = creaBomba(1);
        b1.activa();
        sistema.afegirBomba(b1);

        float input = 200;
        float output = sistema.calculaOutput(input);
        assertEquals(200, output);
    }

    /**
     * Comprova el càlcul del cost operatiu total del sistema amb bombes afegides.
     */
    @Test
    void testGetCostOperatiu() {
        BombaRefrigerant b1 = creaBomba(1);
        BombaRefrigerant b2 = creaBomba(2);
        sistema.afegirBomba(b1);
        sistema.afegirBomba(b2);
        System.out.println("sistema.getCostOperatiu()");
        assertEquals(260, sistema.getCostOperatiu());
    }

    /**
     * Comprova que es poden desactivar les bombes del sistema.
     */
    @Test
    void testDesactiva() throws CentralUBException {
        BombaRefrigerant b1 = creaBomba(1);
        b1.activa();
        sistema.afegirBomba(b1);
        sistema.desactiva();
        assertFalse(b1.getActivat());
    }

    /**
     * Verifica que el sistema detecta bombes fora de servei i afegeix la incidència corresponent.
     */
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
