package prog2.model;

import prog2.vista.CentralUBException;

public class BombaRefrigerant implements InBombaRefrigerant{
    int id;
    boolean activa;
    VariableUniforme VaUni;
    boolean operativa=true;

    BombaRefrigerant(VariableUniforme VaUni,int id) {
        this.id = id;
        this.VaUni = VaUni;
    }

    public int getId() {
        return id;
    }

    public void activa() throws CentralUBException {
        if (operativa)
            this.activa = true;
        else{
            throw new CentralUBException("No esta operativa la bomba refrigerant per tant no es pot activar");
        }
    }

    public void desactiva() {
        this.activa = false;
    }

    public boolean getActivat() {
        return activa;
    }

    @Override
    public void revisa(PaginaIncidencies p) {
        if (VaUni.seguentValor() < 25) {
            this.operativa = false;
        }
    }

    public boolean getForaDeServei() {
        return !operativa;//Estara fora de servei = True quan operativa =False
    }

    public float getCapacitat() {
        if (!activa) {
            return 0;
        } else {
            return 1;//CAPACITAT MODIFICAR per una constant-----------------------------------------------------------------------------------------------------------------------
        }
    }

    public float getCostOperatiu() {
        if (!activa) {
            return 0;
        } else {
            return 130;
        }
    }

    public String toString() {
        return "Id="+ id + ", Activat=" + getActivat() + ", Fora de Servei=" + getForaDeServei();
    }
}
