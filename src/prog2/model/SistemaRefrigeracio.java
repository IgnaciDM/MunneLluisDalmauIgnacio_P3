package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Representa el sistema de refrigeració d'una central nuclear.
 * Gestiona un conjunt de bombes refrigerants, permetent activar-les o desactivar-les,
 * revisar l'estat de les bombes, controlar el cost operatiu i calcular l'output energètic.
 *
 * Implementa la interfície {@link InComponent}.
 *
 */
public class SistemaRefrigeracio implements InComponent, Serializable {

    /**
     * Llista de bombes refrigerants gestionades pel sistema de refrigeració.
     */
    ArrayList<BombaRefrigerant> llistabombes = new ArrayList<>();

    /**
     * Estat del sistema de refrigeració: actiu (true) o inactiu (false).
     */
    boolean activa;

    /**
     * Retorna la llista de bombes refrigerants.
     *
     * @return Llista d'objectes {@link BombaRefrigerant}.
     */
    public ArrayList<BombaRefrigerant> getllistabombes() {
        return llistabombes;
    }

    /**
     * Afegeix una nova bomba al sistema de refrigeració.
     *
     * @param b La bomba que s'afegirà al sistema.
     */
    public void afegirBomba(BombaRefrigerant b) {
        llistabombes.add(b);
    }

    /**
     * Activa totes les bombes del sistema de refrigeració.
     *
     * @throws CentralUBException Si alguna bomba està fora de servei.
     */
    ////////////////////////////////AQUEST METODE NO S'ESTA UTILITZANT solucionar
    public void activa() throws CentralUBException {
        Iterator<BombaRefrigerant> it = llistabombes.iterator();
        while (it.hasNext()) {
            if (it.next().getForaDeServei()) {
                throw new CentralUBException("La Bomba " + it.next().getId() + " esta fora de servei, es mantindra desactivada");
            } else {
                it.next().activa();
            }
        }
    }

    /**
     * Desactiva totes les bombes del sistema de refrigeració.
     */
    public void desactiva() {
        Iterator<BombaRefrigerant> it = llistabombes.iterator();
        while (it.hasNext()) {
            it.next().desactiva();
        }
    }

    /**
     * Comprova si alguna de les bombes està activada.
     *
     * @return true si alguna bomba està activada, false en cas contrari.
     */
    public boolean getActivat() {
        Iterator<BombaRefrigerant> it = llistabombes.iterator();
        while (it.hasNext()) {
            if (it.next().getActivat() == true) {
                return true;
            }
        }
        return false;
    }

    /**
     * Revisa l'estat de les bombes del sistema i registra incidències si alguna bomba està fora de servei.
     *
     * @param p Pàgina d'incidències on es registraran els errors.
     */
    public void revisa(PaginaIncidencies p) {
        Iterator<BombaRefrigerant> it = llistabombes.iterator();
        while (it.hasNext()) {
            it.next().revisa(p);
            if (it.next().getForaDeServei() == true) {
                p.afegeixIncidencia("La bomba " + it.next().getId() + " esta fora de servei");
                it.next().desactiva();
            }
        }
    }

    /**
     * Retorna el cost operatiu total del sistema de refrigeració, sumant els costos de totes les bombes activades.
     *
     * @return Cost operatiu total.
     */
    public float getCostOperatiu() {
        float cost = 0;
        Iterator<BombaRefrigerant> it = llistabombes.iterator();
        while (it.hasNext()) {
            it.next(); // avançar l'iterador
            cost += 130;
        }
        return cost;
    }

    /**
     * Calcula l'output energètic del sistema de refrigeració basat en la quantitat de bombes activades
     * i el valor d'entrada (input).
     *
     * @param input Entrada que representa el percentatge de refrigeració desitjat.
     * @return Output energètic calculat.
     */
    public float calculaOutput(float input) {
        int N = 0;
        for (BombaRefrigerant bomba : llistabombes) {
            if (bomba.getActivat() == true) {
                N += 1;
            }
        }
        if (250 * N > input) {
            return input;
        } else {
            return 250 * N;
        }
    }

    /**
     * Retorna una representació textual del sistema de refrigeració, incloent l'estat i les bombes.
     *
     * @return Cadena descriptiva del sistema de refrigeració.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sistema de Refrigeració[\n");
        sb.append("  Estat: ").append(getActivat() ? "Activat" : "Desactivat").append("\n");
        sb.append("  Bombes:\n");
        for (BombaRefrigerant bomba : llistabombes) {
            sb.append("    - ").append(bomba.toString()).append("\n");
        }
        sb.append("]");
        return sb.toString();
    }
}
