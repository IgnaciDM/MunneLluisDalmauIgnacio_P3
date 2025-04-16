package prog2.model;

import prog2.vista.CentralUBException;

public class BombaRefrigerant implements InBombaRefrigerant{
    int id;
    boolean activa;
    VariableUniforme VaUni;
    boolean operativa;
    float capacitat;
    float costoperatiu;

    BombaRefrigerant(VariableUniforme VaUni,int id) throws CentralUBException {
        this.id = id;
        this.VaUni = VaUni;
    }

    public int getId() {
        return id;
    }

    public void activa() {
        if (operativa == false) {
            this.activa = false;
        } else {
            this.activa = true;
        }
    }

    public void desactiva() {
        this.activa = false;
    }

    public boolean getActivat() {
        return this.activa;
    }

    @Override
    public void revisa(PaginaIncidencies p) {
        if (VaUni.seguentValor() < 25) {
            this.operativa = false;
        }
    }

    public boolean getForaDeServei() {
        return this.operativa;
    }

    public float getCapacitat() {
        if (activa == false) {
            return 0;
        } else {
            return capacitat;
        }
    }

    public float getCostOperatiu() {
        if (activa == false) {
            return 0;
        } else {
            return costoperatiu;
        }
    }

    public String toString() {
        return "Id="+ id + ", Activat=" + activa + ", Fora de servei=" + operativa;
    }
}
