package prog2.model;

import prog2.vista.CentralUBException;

public class GeneradorVapor {
    boolean activa;

    public void activa() throws CentralUBException {
        this.activa = true;
    }

    public void desactiva() {
        this.activa = false;
    }

    public boolean getActivat(){
        return activa;
    }

    public void revisa (PaginaIncidencies p) {
    }

    public float getCostOperatiu(){
        if (activa == false) {
            return 0;
        } else {
            return 25;
        }
    }

    public float calculaOutput(float input){
        if (activa == false) {
            return 25;
        } else {
            return input * 0.9;
        }
    }
}
