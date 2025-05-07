package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Representa una pàgina de la bitàcola on es registren les incidències
 * ocorregudes en un dia determinat. Aquesta classe forma part del sistema
 * de seguiment d'una central nuclear i permet guardar descripcions lliures
 * dels problemes detectats.
 *
 * Hereta de {@link PaginaBitacola} i implementa {@link Serializable}.
 */
public class PaginaIncidencies extends PaginaBitacola implements Serializable {

    /**
     * Llista de descripcions de les incidències registrades aquest dia.
     */
    private ArrayList<String> llistaIncidencia;

    /**
     * Crea una nova pàgina d'incidències per a un dia concret.
     *
     * @param dia Dia de la bitàcola al qual es refereix la pàgina.
     */
    public PaginaIncidencies(int dia) {
        super(dia);
        llistaIncidencia = new ArrayList<>();
    }

    /**
     * Afegeix una nova incidència a la llista.
     *
     * @param descIncidencia Descripció textual de la incidència.
     */
    public void afegeixIncidencia(String descIncidencia) {
        llistaIncidencia.add(descIncidencia);
    }

    /**
     * Retorna una representació textual de la pàgina d'incidències,
     * mostrant les incidències registrades o indicant que no n'hi ha cap.
     *
     * @return Cadena amb les incidències o un missatge d'absència.
     */
    @Override
    public String toString() {
        StringBuilder resposta = new StringBuilder();
        for (String incidencia : llistaIncidencia) {
            resposta.append("- Descripció Incidència: ").append(incidencia).append("\n");
        }
        if (llistaIncidencia.size() != 0) {
            return "\n" + "--- Pàgina Incidencies (Dia " + getDia() + " ) --- " + "\n" + resposta + "\n";
        } else {
            return "\n" + "--- Pàgina Incidencies (Dia " + getDia() + " ) --- " + "\n" +
                    "No hi ha Incidencies en aquest dia" + "\n";
        }
    }

    /**
     * Assigna una nova llista d’incidències.
     *
     * @param llistaIncidencia Nova llista d’incidències.
     */
    public void setIncidencies(ArrayList<String> llistaIncidencia) {
        this.llistaIncidencia = llistaIncidencia;
    }

    /**
     * Retorna la llista d’incidències registrades.
     *
     * @return Llista d’incidències.
     */
    public ArrayList<String> getIncidencies() {
        return llistaIncidencia;
    }
}

