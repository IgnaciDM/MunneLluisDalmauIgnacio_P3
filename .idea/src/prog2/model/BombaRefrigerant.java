package prog2.model;

import java.io.Serializable;

public class BombaRefrigerant implements InBombaRefrigerant{
    int id;
    boolean activa;
    boolean operativa;

    BombaRefrigerant(int id, VariableUniforme VaUni) {
        this.id = id;
        this.activa = activa;
        this.operativa = operativa;
    }

    public int getId() {
        return id;
    }

    public void activa() throws CentralUBException; {
        if (this.operativa == false) {
            throw new CentralUBException("La Bomba Refrigerant esta fora de servei");
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

    public void revisa (PaginaIncidencies p) {

    }

    public boolean getForaDeServei() {
        return this.operativa;
    }

    public float getCapacitat() {

    }

    public float getCostOperatiu() {

    }

    public String toString() {
        return "Id="+ id + ", Activat=" + activa + ", Fora de servei=" + operativa;
    }
}
