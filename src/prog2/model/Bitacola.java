package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * Classe que representa una bitàcola composta per diverses pàgines,
 * les quals poden incloure incidències o altres tipus de registre.
 * Implementa la interfície InBitacola i és serialitzable.
 */
public class Bitacola implements InBitacola, Serializable {
    /** Llista de pàgines de la bitàcola. */
    ArrayList<PaginaBitacola> paginesbitacola = new ArrayList<>();

    /**
     * Retorna la llista de pàgines de la bitàcola.
     * @return una ArrayList amb totes les pàgines de la bitàcola
     */
    public ArrayList<PaginaBitacola> getPaginesbitacola() {
        return paginesbitacola;
    }

    /**
     * Afegeix una nova pàgina a la bitàcola.
     * @param p la pàgina de la bitàcola que es vol afegir
     */
    @Override
    public void afegeixPagina(PaginaBitacola p) {
        paginesbitacola.add(p);
    }

    /**
     * Retorna una llista de les pàgines que són de tipus PaginaIncidencies.
     * Es fa una iteració per totes les pàgines i es filtren les que són
     * instàncies de PaginaIncidencies.
     * @return una llista de pàgines de tipus PaginaIncidencies
     */
    @Override
    public List<PaginaIncidencies> getIncidencies() {
        List<PaginaIncidencies> incidencies = new ArrayList<>();
        Iterator<PaginaBitacola> it = paginesbitacola.iterator();
        while (it.hasNext()) {
            PaginaBitacola p = it.next();
            if (p instanceof PaginaIncidencies) {
                incidencies.add((PaginaIncidencies) p);
            }
        }
        return incidencies;
    }

    /**
     * Retorna una representació en forma de cadena de la bitàcola,
     * incloent totes les pàgines contingudes.
     * @return la representació textual de la bitàcola
     */
    @Override
    public String toString() {
        return "Bitacola [" + paginesbitacola +"]";
    }
}
