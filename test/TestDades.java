import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.Bitacola;
import prog2.model.InDades;
import prog2.vista.CentralUBException;
import prog2.model.Dades;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de proves unitàries per a la classe {@link Dades}, que gestiona l'estat
 * general de la central i coordina els seus components: reactor, bomba, generador, etc.
 */
class TestDades {

    private InDades dades;

    /**
     * Inicialitza l'objecte {@code Dades} abans de cada test.
     */
    @BeforeEach
    public void setUp() {
        dades = new Dades();
    }

    /**
     * Comprova que la inicialització dels valors per defecte és correcta:
     * dia 1, inserció de barres al 100%, guanys acumulats a 0.
     */
    @Test
    public void testInicialitzacioCorrecta() {
        assertEquals(1, dades.mostraEstat().getDia());
        assertEquals(100, dades.getInsercioBarres());
        assertEquals(0, dades.getGuanysAcumulats());
    }

    /**
     * Comprova que es pot canviar la inserció de barres a un valor vàlid.
     */
    @Test
    public void testSetInsercioBarresValid() throws CentralUBException {
        dades.setInsercioBarres(75);
        assertEquals(75, dades.getInsercioBarres());
    }

    /**
     * Comprova que establir un valor de barres fora dels límits permesos genera excepcions.
     */
    @Test
    public void testSetInsercioBarresInvalid() {
        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(-10));
        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(150));
    }

    /**
     * Comprova que es pot activar i desactivar el reactor correctament.
     */
    @Test
    public void testActivaIDesactivaReactor() throws CentralUBException {
        dades.activaReactor();
        assertTrue(dades.mostraReactor().getActivat());

        dades.desactivaReactor();
        assertFalse(dades.mostraReactor().getActivat());
    }

    /**
     * Comprova que es pot activar i desactivar una bomba de refrigeració concretament.
     */
    @Test
    public void testActivaIDesactivaBomba() throws CentralUBException {
        dades.desactivaBomba(0); // Inicialment estan totes activades → es desactiva
        dades.activaBomba(0);
        boolean activada = dades.mostraSistemaRefrigeracio().getllistabombes()
                .stream().filter(b -> b.getId() == 0).findFirst().get().getActivat();
        assertTrue(activada);
    }

    /**
     * Comprova que el càlcul de potència retorna un valor positiu quan els components actius funcionen.
     */

    @Test
    void testCalculaPotencia() throws CentralUBException {

        dades.setInsercioBarres(40);// Exemple: 40% d'inserció
        // Reactor: temperatura += (100 - 40) * 10 = +600 → 700
        // Refrigeració: 2 bombes → N=2 → 250*N = 500 → min(700, 500) = 500
        // Generador: 90% de 500 = 450
        // Turbina: input = 450 → output = 450*2 = 900
        dades.activaReactor();
        dades.activaBomba(0);
        dades.activaBomba(1);
        float potencia = dades.calculaPotencia();
        assertEquals(900.0f, potencia, 0.001);
    }

    /**
     * Comprova que finalitzar un dia incrementa correctament el dia i genera tres pàgines a la bitàcola.
     */
    @Test
    public void testFinalitzaDiaIncrementaDia() {
        int diaInicial = dades.mostraEstat().getDia();
        Bitacola b = dades.finalitzaDia(80);
        assertEquals(diaInicial + 1, dades.mostraEstat().getDia());
        assertEquals(3, b.getPaginesbitacola().size()); // Economia, Estat, Incidències
    }
}
