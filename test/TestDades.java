import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.Bitacola;
import prog2.vista.CentralUBException;

import prog2.model.Dades;
import static org.junit.jupiter.api.Assertions.*;

class TestDades {
    private Dades dades;

    @BeforeEach
    public void setUp() {
        dades = new Dades();
    }

    @Test
    public void testInicialitzacioCorrecta() {
        assertEquals(1, dades.mostraEstat().getDia());
        assertEquals(100, dades.getInsercioBarres());
        assertEquals(0, dades.getGuanysAcumulats());
    }

    @Test
    public void testSetInsercioBarresValid() throws CentralUBException {
        dades.setInsercioBarres(75);
        assertEquals(75, dades.getInsercioBarres());
    }

    @Test
    public void testSetInsercioBarresInvalid() {
        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(-10));
        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(150));
    }

    @Test
    public void testActivaIDesactivaReactor() throws CentralUBException {
        dades.activaReactor();
        assertTrue(dades.mostraReactor().getActivat());

        dades.desactivaReactor();
        assertFalse(dades.mostraReactor().getActivat());
    }

    @Test
    public void testActivaIDesactivaBomba() throws CentralUBException {
        dades.desactivaBomba(0); // inicialment totes actives → les desactiva
        dades.activaBomba(0);
        boolean activada = dades.mostraSistemaRefrigeracio().getllistabombes()
                .stream().filter(b -> b.getId() == 0).findFirst().get().getActivat();
        assertTrue(activada);
    }

    @Test
    public void testCalculaPotencia() {
        float pot = dades.calculaPotencia();
        assertTrue(pot > 0); // Generador i turbina actius per defecte
    }

    @Test
    public void testFinalitzaDiaIncrementaDia() {
        int diaInicial = dades.mostraEstat().getDia();
        Bitacola b = dades.finalitzaDia(80);
        assertEquals(diaInicial + 1, dades.mostraEstat().getDia());
        assertEquals(3, b.getPaginesbitacola().size()); // Economia, Estat, Incidències
    }
}
