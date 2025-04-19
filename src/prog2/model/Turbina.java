package prog2.model;

import prog2.vista.CentralUBException;

public class Turbina {
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
        if (this.activa == false) {
            return 0;
        } else {
            return 20;
        }
    }

    public float calculaOutput(float input){
        if (this.activa == false) {
            return 0;
        } if (input < 100) {
            return 0;
        } else {
            return input*2;
        }
    }
}
