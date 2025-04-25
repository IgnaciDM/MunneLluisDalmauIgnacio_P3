package prog2.model;

import java.util.ArrayList;
import java.util.List;

public class Bitacola implements InBitacola{
    ArrayList<PaginaBitacola> paginesbitacola = new ArrayList<>();

    @Override
    public void afegeixPagina(prog2.model.PaginaBitacola p) {
        paginesbitacola.add(p);
    }

    @Override
    public List<prog2.model.PaginaIncidencies> getIncidencies() {
        List<prog2.model.PaginaIncidencies> incidencies = new ArrayList<>();
        for (int i = 0; i < paginesbitacola.size(); i++) {
            PaginaBitacola p = paginesbitacola.get(i);
            if (p instanceof prog2.model.PaginaIncidencies) {
                incidencies.add((prog2.model.PaginaIncidencies) p);
            }
        }
        return incidencies;
    }

    @Override
    public String toString() {
        return "Bitacola [" +"paginesbitacola:" + paginesbitacola +"]";
    }
}
