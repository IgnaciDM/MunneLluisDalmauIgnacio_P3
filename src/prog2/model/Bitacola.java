package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Bitacola implements InBitacola, Serializable {
    ArrayList<PaginaBitacola> paginesbitacola = new ArrayList<>();

    public ArrayList<PaginaBitacola> getPaginesbitacola() {
        return paginesbitacola;
    }

    @Override
    public void afegeixPagina(PaginaBitacola p) {
        paginesbitacola.add(p);
    }

    @Override
    public List<PaginaIncidencies> getIncidencies() {
        List<PaginaIncidencies> incidencies = new ArrayList<>();
        Iterator<PaginaBitacola> it = paginesbitacola.iterator();
        while (it.hasNext()) {
            if (it.next() instanceof PaginaIncidencies) {
                incidencies.add((PaginaIncidencies) it.next());
            }
        }
        return incidencies;
    }

    @Override
    public String toString() {
        return "Bitacola [" + paginesbitacola +"]";
    }
}
