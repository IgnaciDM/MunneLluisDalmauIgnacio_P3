package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

public class Turbina implements Serializable {
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
            return 20;
        }
    }

    public float calculaOutput(float input){
        if (activa == false) {
            return 0;
        } else {
            if (input < 100) {
                return 0;
            } else {
                return input * 2;
            }
        }
    }

    @Override
    public String toString() {
        return "Turbina [Activat: "+(activa? "Sí" : "No")+"]";
    }
}
