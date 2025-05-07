package prog2.model;

import java.io.Serializable;

/**
 * Representa una pàgina econòmica de la bitàcola per a un dia concret.
 * Conté dades relatives al rendiment econòmic de la central el dia indicat.
 * Estén la classe {@link PaginaBitacola}.
 */
public class PaginaEconomica extends PaginaBitacola implements Serializable {

    /** Demanda de potència del dia. */
    private final float demandaPotencia;

    /** Potència generada per la central durant el dia. */
    private final float potenciaGenerada;

    /** Percentatge de la demanda que s'ha pogut satisfer. */
    private final float demandasatisfeta;

    /** Beneficis obtinguts durant el dia. */
    private float beneficis;

    /** Penalització per potència no satisfeta. */
    private float penalitzacio;

    /** Costos operatius del dia. */
    private float costosOperatius;

    /** Guanys acumulats fins al dia indicat. */
    private float guanysAcumulats;

    /**
     * Constructor de la pàgina econòmica.
     *
     * @param dia el dia de la pàgina.
     * @param demandaPotencia demanda de potència del dia.
     * @param potenciaGenerada potència generada realment.
     * @param demandasatisfeta percentatge de demanda satisfeta.
     * @param beneficis beneficis obtinguts durant el dia.
     * @param penalitzacio penalització per no satisfer tota la demanda.
     * @param costosOperatius costos operatius del dia.
     * @param guanysAcumulats guanys acumulats fins aquest dia.
     */
    public PaginaEconomica(int dia, float demandaPotencia, float potenciaGenerada, float demandasatisfeta,
                           float beneficis, float penalitzacio, float costosOperatius,
                           float guanysAcumulats) {
        super(dia);
        this.demandaPotencia = demandaPotencia;
        this.potenciaGenerada = potenciaGenerada;
        this.demandasatisfeta = demandasatisfeta;
        this.beneficis = beneficis;
        this.penalitzacio = penalitzacio;
        this.costosOperatius = costosOperatius;
        this.guanysAcumulats = guanysAcumulats;
    }

    /**
     * Retorna la demanda de potència del dia.
     * @return demanda de potència.
     */
    public float getDemandaPotencia() {
        return demandaPotencia;
    }

    /**
     * Retorna la potència generada.
     * @return potència generada.
     */
    public float getPotenciaGenerada() {
        return potenciaGenerada;
    }

    /**
     * Retorna el percentatge de la demanda que ha estat satisfeta.
     * @return percentatge de demanda satisfeta.
     */
    public float getDemandasatisfeta() {
        return demandasatisfeta;
    }

    /**
     * Retorna els beneficis obtinguts.
     * @return beneficis.
     */
    public float getBeneficis() {
        return beneficis;
    }

    /**
     * Retorna la penalització per potència no subministrada.
     * @return penalització.
     */
    public float getPenalitzacio() {
        return penalitzacio;
    }

    /**
     * Retorna els costos operatius del dia.
     * @return costos operatius.
     */
    public float getCostosOperatius() {
        return costosOperatius;
    }

    /**
     * Retorna els guanys acumulats fins aquest dia.
     * @return guanys acumulats.
     */
    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    /**
     * Actualitza els beneficis.
     * @param beneficis nous beneficis.
     */
    public void setBeneficis(float beneficis) {
        this.beneficis = beneficis;
    }

    /**
     * Actualitza la penalització.
     * @param penalitzacio nova penalització.
     */
    public void setPenalitzacio(float penalitzacio) {
        this.penalitzacio = penalitzacio;
    }

    /**
     * Actualitza els costos operatius.
     * @param costosOperatius nous costos operatius.
     */
    public void setCostosOperatius(float costosOperatius) {
        this.costosOperatius = costosOperatius;
    }

    /**
     * Actualitza els guanys acumulats.
     * @param guanysAcumulats nous guanys acumulats.
     */
    public void setGuanysAcumulats(float guanysAcumulats) {
        this.guanysAcumulats = guanysAcumulats;
    }

    /**
     * Retorna una representació textual de la pàgina econòmica.
     * @return cadena amb totes les dades del dia.
     */
    @Override
    public String toString() {
        return "\n" + "--- Pàgina Econòmica (Dia " + getDia() + ") ---\n" +
                "Demanda Potència: " + demandaPotencia + "\n" +
                "Potència Generada: " + potenciaGenerada + "\n" +
                "Demanda satisfeta: " + demandasatisfeta + "%" + "\n" +
                "Beneficis: " + beneficis + "\n" +
                "Penalització: " + penalitzacio + "\n" +
                "Costos Operatius: " + costosOperatius + "\n" +
                "Guanys Acumulats: " + guanysAcumulats + "\n";
    }
}
