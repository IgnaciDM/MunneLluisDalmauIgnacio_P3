package prog2.model;

import java.util.ArrayList;

public class PaginaIncidencies extends PaginaBitacola {
    private ArrayList<String> llistaIncidencia;

    public PaginaIncidencies(int dia) {
        super(dia);
        llistaIncidencia = new ArrayList<>();
    }

    public void afegeixIncidencia(String descIncidencia) {
        llistaIncidencia.add(descIncidencia);
    }

    public String toString() {
        StringBuilder resposta = new StringBuilder();
        for (String incidencia: llistaIncidencia) {
            resposta.append("- Descripció Incidència: ").append(incidencia).append("\n");
        }
        return  "Dia: " + getDia() + "\n" + resposta;
    }
}
