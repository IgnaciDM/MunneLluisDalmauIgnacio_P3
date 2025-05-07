package prog2.model;


/**
 * Representa un generador de vapor en una central, que pot estar activat o desactivat.
 * Proporciona informació sobre el seu estat, cost operatiu i capacitat de producció.
 */
public class GeneradorVapor implements InComponent {

    /**
     * Indica si el generador de vapor està activat.
     */
    boolean activa;

    /**
     * Activa el generador de vapor.
     */
    public void activa() {
        this.activa = true;
    }

    /**
     * Desactiva el generador de vapor.
     */
    public void desactiva() {
        this.activa = false;
    }

    /**
     * Retorna si el generador està activat o no.
     *
     * @return true si està activat, false en cas contrari.
     */
    public boolean getActivat() {
        return activa;
    }

    /**
     * Calcula el cost operatiu actual del generador.
     *
     * @return el cost operatiu: 25 si està activat, 0 si està desactivat.
     */
    public float getCostOperatiu() {
        if (activa == false) {
            return 0;
        } else {
            return 25;
        }
    }

    /**
     * Calcula la sortida de vapor generada segons una entrada donada.
     *
     * @param input el valor d'entrada al generador.
     * @return 25 si està desactivat; si està activat, retorna el 90% de l'entrada.
     */
    public float calculaOutput(float input) {
        if (activa == false) {
            return 25;
        } else {
            return (float) (input * 0.9);
        }
    }

    /**
     * Mètode per revisar l'estat del generador i registrar possibles incidències.
     * De moment, aquest mètode no té implementació.
     *
     * @param p la pàgina d'incidències on es poden registrar problemes.
     */
    public void revisa(PaginaIncidencies p) {
        // Mètode per a futures revisions i control d'incidències.
    }

    /**
     * Retorna una representació textual del generador de vapor.
     *
     * @return una cadena que indica si el generador està activat o no.
     */
    @Override
    public String toString() {
        return "GeneradorVapor [Activat: " + (activa ? "Sí" : "No") + "]";
    }
}

