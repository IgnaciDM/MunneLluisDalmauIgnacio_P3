package prog2.model;

import java.util.ArrayList;
import java.util.List;

public class Bitacola implements InBitacola {
    ArrayList<PaginaBitacola> paginesbitacola = new ArrayList<>();

    @Override
    public void afegeixPagina(PaginaBitacola p) {
        paginesbitacola.add(p);
    }

    @Override
    public List<PaginaIncidencies> getIncidencies() {
        List<PaginaIncidencies> incidencies = new ArrayList<>();
        for (int i = 0; i < paginesbitacola.size(); i++) {
            PaginaBitacola p = paginesbitacola.get(i);
            if (p instanceof PaginaIncidencies) {
                incidencies.add((PaginaIncidencies) p);
            }
        }
        return incidencies;
    }

    @Override
    public String toString() {
        return "Bitacola [" + paginesbitacola +"]";
    }
}
