package prog2.model;

/**
 * Representa una turbina dins d'un sistema de generació d'energia.
 * La turbina pot estar activa o desactivada, i té associat un cost operatiu i un output energètic calculat
 * en funció de l'entrada proporcionada.
 *
 * Implementa la interfície {@link InComponent}.
 *
 */
public class Turbina implements InComponent {

    /**
     * Estat de la turbina: activa (true) o desactivada (false).
     */
    boolean activa;

    /**
     * Activa la turbina.
     */
    public void activa() {
        this.activa = true;
    }

    /**
     * Desactiva la turbina.
     */
    public void desactiva() {
        this.activa = false;
    }

    /**
     * Retorna l'estat de la turbina (activada o desactivada).
     *
     * @return true si la turbina està activada, false si està desactivada.
     */
    public boolean getActivat(){
        return activa;
    }

    /**
     * Retorna el cost operatiu de la turbina. Si la turbina no està activada, el cost serà 0.
     *
     * @return Cost operatiu de la turbina.
     */
    public float getCostOperatiu(){
        if (activa == false) {
            return 0;
        } else {
            return 20;
        }
    }

    /**
     * Calcula l'output energètic de la turbina en funció d'una entrada determinada.
     * Si la turbina no està activada, l'output serà 0. Si la entrada és inferior a 100,
     * l'output serà 0, de lo contrari, el resultat serà el doble de l'entrada.
     *
     * @param input Entrada que representa la capacitat energètica desitjada.
     * @return Output energètic calculat.
     */
    public float calculaOutput(float input){
        if (activa == false) {
            return 0;
        } else {
            if (input < 100) {
                return 0;
            } else {
                return input * 2;
            }
        }
    }

    /**
     * Revisa l'estat de la turbina. Aquest mètode actualment no implementa cap funcionalitat,
     * però està preparat per poder gestionar incidències en el futur.
     *
     * @param p Pàgina d'incidències on es podrien registrar errors (actualment sense funcionalitat).
     */
    public void revisa(PaginaIncidencies p) {}

    /**
     * Retorna una representació textual de la turbina, indicant si està activada o no.
     *
     * @return Cadena descriptiva de l'estat de la turbina.
     */
    @Override
    public String toString() {
        return "Turbina [Activat: "+(activa ? "Sí" : "No")+"]";
    }
}

