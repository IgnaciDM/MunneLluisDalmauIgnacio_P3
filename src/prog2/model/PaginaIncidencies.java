package prog2.model;

import java.util.ArrayList;

public class PaginaIncidencies extends PaginaBitacola {
    private ArrayList<String> textIncidencia;

    public PaginaIncidencies(int dia) {
        super(dia);

    }

    public void afegeixIncidencia(String descIncidencia) {
        this.textIncidencia.add(descIncidencia); //Revisar
    }

    public String toString() {
        String resposta = "";
        for (int i = 0; i < this.textIncidencia.size(); i++) {
            resposta += "- Descripció Incidència: " + this.textIncidencia.get(i) + "\n";
        }
        return  "Dia: " + getDia() + "\n" + resposta;
    }
}
