package prog2.model;

import java.util.List;

public abstract class Bitacola implements InBitacola{
    @Override
    public void afegeixPagina(prog2.model.PaginaBitacola p) {

    }

    @Override
    public List<prog2.model.PaginaIncidencies> getIncidencies() {
        return List.of();
    }
}
