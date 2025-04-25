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
            return (float) (input * 0.9);
        }
    }

    @Override
    public String toString() {
        return "GeneradorVapor [Activat: " + (activa ? "Sí" : "No")+"]";
    }
}
