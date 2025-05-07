package prog2.model;

import java.io.Serializable;

/**
 * Representa una pàgina de la bitàcola d'una central per a un dia concret.
 * Conté informació bàsica com el número del dia.
 */
public class PaginaBitacola implements Serializable {

    /**
     * Dia associat a aquesta pàgina de la bitàcola.
     */
    private int dia;

    /**
     * Crea una nova pàgina de la bitàcola per al dia especificat.
     *
     * @param dia el dia que representa aquesta pàgina.
     */
    public PaginaBitacola(int dia) {
        this.dia = dia;
    }

    /**
     * Retorna el dia associat a aquesta pàgina.
     *
     * @return el dia de la pàgina.
     */
    public int getDia() {
        return dia;
    }

    /**
     * Actualitza el dia associat a aquesta pàgina.
     *
     * @param dia el nou valor del dia.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Retorna una representació textual de la pàgina de la bitàcola.
     *
     * @return una cadena amb el format "--- Pàgina Bitàcola (Dia X) ---".
     */
    @Override
    public String toString() {
        return "--- Pàgina Bitàcola (Dia " + dia + ") ---";
    }

}
