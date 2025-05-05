import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.Bitacola;
import prog2.model.PaginaBitacola;
import prog2.model.PaginaIncidencies;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestBitacola {

    private Bitacola bitacola;

    // Subclasse simple per poder instanciar PaginaBitacola si és abstracta
    static class PaginaBitacolaConcreta extends PaginaBitacola {
        public PaginaBitacolaConcreta(int dia) {
            super(dia);
        }

        @Override
        public String toString() {
            return "PaginaBitacolaConcreta";
        }
    }

    @BeforeEach
    void setUp() {
        bitacola = new Bitacola();
    }

    @Test
    void testAfegeixPagina() {
        PaginaBitacola pagina = new PaginaBitacolaConcreta(1);
        bitacola.afegeixPagina(pagina);
        assertEquals(1, bitacola.getPaginesbitacola().size());
        assertEquals(pagina, bitacola.getPaginesbitacola().get(0));
    }

    @Test
    void testGetIncidencies() {
        PaginaBitacola pagina1 = new PaginaBitacolaConcreta(1);
        PaginaIncidencies pagina2 = new PaginaIncidencies(1);

        bitacola.afegeixPagina(pagina1);
        bitacola.afegeixPagina(pagina2);

        List<PaginaIncidencies> result = bitacola.getIncidencies();

        assertEquals(1, result.size());
        assertEquals(pagina2, result.get(0));
    }

    @Test
    void testToString() {
        PaginaBitacola pagina = new PaginaBitacolaConcreta(1);
        bitacola.afegeixPagina(pagina);
        String output = bitacola.toString();
        assertTrue(output.contains("Bitacola"));
        assertTrue(output.contains("PaginaBitacolaConcreta")); // del toString sobrescrit
    }
}

