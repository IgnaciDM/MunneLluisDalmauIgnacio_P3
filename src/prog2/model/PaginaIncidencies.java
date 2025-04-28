package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PaginaIncidencies extends PaginaBitacola implements Serializable {
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
        if (llistaIncidencia.size() != 0) {
            return "\n" + "--- Pàgina Estat (Dia "+getDia()+ " ) --- " + "\n" + resposta + "\n";
        } else {
            return "\n" + "--- Pàgina Estat (Dia "+getDia()+ " ) --- " + "\n" + "No hi ha Incidencies en aquest dia" + "\n";
        }
    }
}
