package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

/**
 * Classe que representa una bomba refrigerant dins del sistema de la central.
 * Cada bomba pot estar activa o desactivada, operativa o fora de servei,
 * i té associada una variable uniforme per controlar el seu estat.
 */
public class BombaRefrigerant implements InBombaRefrigerant, Serializable {

    /** Identificador de la bomba. */
    int id;

    /** Estat d'activació de la bomba. */
    boolean activa;

    /** Variable uniforme associada per determinar si està operativa. */
    VariableUniforme VaUni;

    /** Indica si la bomba està operativa (true) o fora de servei (false). */
    public boolean operativa = true;

    /**
     * Constructor de la classe BombaRefrigerant.
     * @param VaUni la variable uniforme associada
     * @param id l'identificador de la bomba
     */
    public BombaRefrigerant(VariableUniforme VaUni, int id) {
        this.id = id;
        this.VaUni = VaUni;
    }

    /**
     * Retorna l'identificador de la bomba.
     * @return l'id de la bomba
     */
    public int getId() {
        return id;
    }

    /**
     * Activa la bomba, si no està fora de servei.
     * @throws CentralUBException si la bomba està fora de servei
     */
    public void activa() throws CentralUBException {
        if (this.getForaDeServei())
            throw new CentralUBException("La bomba refrigerant " + getId() + " es troba fora de servei, per tant no es pot activar");
        else {
            this.activa = true;
        }
    }

    /**
     * Desactiva la bomba.
     */
    public void desactiva() {
        this.activa = false;
    }

    /**
     * Indica si la bomba està activada.
     * @return true si està activada, false en cas contrari
     */
    public boolean getActivat() {
        return activa;
    }

    /**
     * Revisa l'estat de la bomba segons el valor retornat per la variable uniforme.
     * Si el valor és menor que 25, es marca la bomba com a fora de servei.
     * @param p la pàgina d'incidències (no utilitzada directament)
     */
    @Override
    public void revisa(PaginaIncidencies p) {
        if (VaUni.seguentValor() < 25) {
            this.operativa = false;
        }
    }

    /**
     * Estableix si la bomba ha d'estar fora de servei.
     * Si és cert, es desactiva automàticament.
     * @param foraDeServei true si s'ha de marcar com fora de servei
     */
    public void setForaDeServei(boolean foraDeServei) {
        if (foraDeServei) {
            desactiva(); // Si fora de servei és true, es desactiva
        }
        this.operativa = !foraDeServei; // Fora de servei = true implica operativa = false
    }

    /**
     * Retorna si la bomba està fora de servei.
     * @return true si està fora de servei, false si està operativa
     */
    public boolean getForaDeServei() {
        return !operativa;
    }

    /**
     * Retorna la capacitat de la bomba. Si està activada, retorna 250. En cas contrari, 0.
     * @return la capacitat de la bomba
     */
    @Override
    public float getCapacitat() {
        if (activa) {
            return 250;
        }
        return 0;
    }

    /**
     * Retorna el cost operatiu de la bomba. Si està activada, retorna 130. En cas contrari, 0.
     * @return el cost operatiu de la bomba
     */
    public float getCostOperatiu() {
        if (!activa) {
            return 0;
        } else {
            return 130;
        }
    }

    /**
     * Retorna una representació textual de la bomba refrigerant.
     * @return una cadena amb l'id, si està activada i si està fora de servei
     */
    public String toString() {
        return "Id=" + id + ", Activat=" + getActivat() + ", Fora de Servei=" + getForaDeServei();
    }
}
