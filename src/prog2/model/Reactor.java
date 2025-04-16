package prog2.model;
import prog2.vista.CentralUBException;

public class Reactor implements InComponent {
    float temperatura;
    boolean activa;



    public float gettemperatura() {
        return temperatura;
    }

    public void settemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

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
            return 35;
        }
    }

    public float calculaOutput(float input){
        return 0;
    }

}
