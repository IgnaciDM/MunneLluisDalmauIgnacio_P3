import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.Bitacola;
import prog2.model.PaginaBitacola;
import prog2.model.PaginaIncidencies;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de proves unitàries per a la classe {@link Bitacola}.
 */
class TestBitacola {

    private Bitacola bitacola;

    /**
     * Subclasse concreta de {@link PaginaBitacola} per permetre la seva instanciació en els tests.
     */
    static class PaginaBitacolaConcreta extends PaginaBitacola {
        public PaginaBitacolaConcreta(int dia) {
            super(dia);
        }

        @Override
        public String toString() {
            return "PaginaBitacolaConcreta";
        }
    }

    /**
     * Inicialitza una nova instància de {@link Bitacola} abans de cada test.
     */
    @BeforeEach
    void setUp() {
        bitacola = new Bitacola();
    }

    /**
     * Comprova que el mètode {@code afegeixPagina} afegeix correctament una pàgina a la bitàcola.
     */
    @Test
    void testAfegeixPagina() {
        PaginaBitacola pagina = new PaginaBitacolaConcreta(1);
        bitacola.afegeixPagina(pagina);
        assertEquals(1, bitacola.getPaginesbitacola().size());
        assertEquals(pagina, bitacola.getPaginesbitacola().get(0));
    }

    /**
     * Comprova que el mètode {@code getIncidencies} retorna només les pàgines de tipus {@link PaginaIncidencies}.
     */
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

    /**
     * Comprova que el mètode {@code toString} retorna un text que conté la informació esperada.
     */
    @Test
    void testToString() {
        PaginaBitacola pagina = new PaginaBitacolaConcreta(1);
        bitacola.afegeixPagina(pagina);
        String output = bitacola.toString();
        assertTrue(output.contains("Bitacola"));
        assertTrue(output.contains("PaginaBitacolaConcreta")); // del toString sobrescrit
    }
}

