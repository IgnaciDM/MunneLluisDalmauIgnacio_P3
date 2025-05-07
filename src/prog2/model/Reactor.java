package prog2.model;

import prog2.vista.CentralUBException;

/**
 * Representa el reactor d'una central nuclear.
 * El reactor pot estar actiu o inactiu, i té una temperatura associada.
 * S'encarrega de calcular el seu output energètic, controlar l'activació
 * segons la temperatura, i notificar incidències si es supera un llindar crític.
 *
 * Implementa la interfície {@link InComponent}.
 *
 */
public class Reactor implements InComponent {

    /**
     * Temperatura actual del reactor, inicialitzada a 25 graus.
     */
    float temperatura = 25;

    /**
     * Estat del reactor: actiu (true) o inactiu (false).
     */
    boolean activa;

    /**
     * Retorna la temperatura actual del reactor.
     *
     * @return Temperatura en graus Celsius.
     */
    public float gettemperatura() {
        return temperatura;
    }

    /**
     * Estableix una nova temperatura per al reactor.
     *
     * @param temperatura Nova temperatura en graus Celsius.
     */
    public void settemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Activa el reactor, sempre que la seva temperatura sigui inferior o igual a 1000 graus.
     *
     * @throws CentralUBException Si la temperatura supera els 1000 graus.
     */
    public void activa() throws CentralUBException {
        if (temperatura > 1000) {
            throw new CentralUBException("El Reactor te una temperatura superior a 1000 graus, no es pot activar");
        } else {
            this.activa = true;
        }
    }

    /**
     * Desactiva el reactor.
     */
    public void desactiva() {
        this.activa = false;
    }

    /**
     * Consulta si el reactor està activat.
     *
     * @return true si està activat, false en cas contrari.
     */
    public boolean getActivat() {
        return activa;
    }

    /**
     * Revisa l'estat del reactor i registra una incidència si la temperatura és massa alta.
     *
     * @param p Pàgina d'incidències on registrar el problema detectat.
     */
    public void revisa(PaginaIncidencies p) {
        if (temperatura >= 1000) {
            this.activa = false;
            p.afegeixIncidencia("El reactor es va desactivar per superar la temperatura màxima de 1000 graus");
        }
    }

    /**
     * Retorna el cost operatiu del reactor.
     *
     * @return 35 si està activat, 0 si no ho està.
     */
    public float getCostOperatiu() {
        if (this.activa == false) {
            return 0;
        } else {
            return 35;
        }
    }

    /**
     * Calcula la sortida del reactor segons si està activat i el valor d'input.
     *
     * @param input Percentatge d'inserció de barres de control (0-100).
     * @return Output energètic o temperatura si no està activat.
     */
    public float calculaOutput(float input) {
        if (this.activa == false) {
            return gettemperatura();
        } else {
            return gettemperatura() + (100 - input) * 10;
        }
    }

    /**
     * Retorna una representació textual del reactor, indicant si està activat i la seva temperatura.
     *
     * @return Cadena descriptiva del reactor.
     */
    @Override
    public String toString() {
        return "Reactor [Activat: " + (getActivat() ? "Sí" : "No") + ", Temperatura: " + temperatura + " ºC]";
    }
}

