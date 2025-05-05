import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.Bitacola;
import prog2.model.PaginaBitacola;
import prog2.model.PaginaIncidencies;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestBitacola {

    private Bitacola bitacola;

    /*
    // Dummy class for testing
    static class DummyPaginaBitacola extends PaginaBitacola {}

    // Dummy class for testing
    static class DummyPaginaIncidencies extends PaginaIncidencies {

    }

    @BeforeEach
    void setUp() {
        bitacola = new Bitacola();
    }

    @Test
    void testAfegeixPagina() {
        PaginaBitacola pagina = new DummyPaginaBitacola();
        bitacola.afegeixPagina(pagina);
        assertEquals(1, bitacola.getPaginesbitacola().size());
        assertEquals(pagina, bitacola.getPaginesbitacola().get(0));
    }

    @Test
    void testGetIncidencies() {
        PaginaBitacola normalPage = new DummyPaginaBitacola();
        PaginaIncidencies incidentPage = new DummyPaginaIncidencies();

        bitacola.afegeixPagina(normalPage);
        bitacola.afegeixPagina(incidentPage);

        List<PaginaIncidencies> result = bitacola.getIncidencies();

        assertEquals(1, result.size());
        assertEquals(incidentPage, result.get(0));
    }

    @Test
    void testToString() {
        PaginaBitacola pagina = new DummyPaginaBitacola();
        bitacola.afegeixPagina(pagina);
        String output = bitacola.toString();
        assertTrue(output.contains("Bitacola"));
        assertTrue(output.contains("paginesbitacola"));
    }

     */
}
