package prog2.model;


public class PaginaEconomica extends PaginaBitacola {

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

    @Override
    public String toString() {
        return "--- Pàgina Econòmica (Dia " + getDia() + ") ---\n" +
                "Demanda Potència: " + demandaPotencia + "\n" +
                "Potència Generada: " + potenciaGenerada + "\n" +
                "Demanda satisfeta: " + demandasatisfeta + "\n" +
                "Beneficis: " + beneficis + "\n" +
                "Penalització: " + penalitzacio + "\n" +
                "Costos Operatius: " + costosOperatius + "\n" +
                "Guanys Acumulats: " + guanysAcumulats + "\n";
    }
}

