package prog2.model;


import java.io.Serializable;

public class PaginaEconomica extends PaginaBitacola implements Serializable {

    private final float demandaPotencia;
    private final float potenciaGenerada;
    private final float demandasatisfeta;
    private float beneficis;
    private float penalitzacio;
    private float costosOperatius;
    private float guanysAcumulats;

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

    public float getDemandaPotencia() {
        return demandaPotencia;
    }

    public float getPotenciaGenerada() {
        return potenciaGenerada;
    }

    public float getDemandasatisfeta() {return demandasatisfeta; }

    public float getBeneficis() {
        return beneficis;
    }

    public float getPenalitzacio() {
        return penalitzacio;
    }

    public float getCostosOperatius() {
        return costosOperatius;
    }

    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    public void setBeneficis(float beneficis) {
        this.beneficis = beneficis;
    }

    public void setPenalitzacio(float penalitzacio) {
        this.penalitzacio = penalitzacio;
    }

    public void setCostosOperatius(float costosOperatius) {
        this.costosOperatius = costosOperatius;
    }

    public void setGuanysAcumulats(float guanysAcumulats) {
        this.guanysAcumulats = guanysAcumulats;
    }

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

