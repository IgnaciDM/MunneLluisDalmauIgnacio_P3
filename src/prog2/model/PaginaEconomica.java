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
        if (potenciaGenerada > demandaPotencia) {
            this.beneficis = demandaPotencia;
        } else {
            this.beneficis = potenciaGenerada;
        }
        return beneficis;
    }

    public float getPenalitzacio() {
        if (potenciaGenerada > demandaPotencia) {
            this.penalitzacio = 250;
        } else {
            this.penalitzacio = 0;
        }
        return penalitzacio;
    }

    public float getCostosOperatius() {
        float cost = 0;
        .getCostOperatiu();
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
                "Beneficis: " + getBeneficis() + "\n" +
                "Penalització: " + getPenalitzacio() + "\n" +
                "Costos Operatius: " + costosOperatius + "\n" +
                "Guanys Acumulats: " + guanysAcumulats + "\n";
    }
}

