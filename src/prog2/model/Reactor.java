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
        if (temperatura >= 1000) {
            throw new CentralUBException("El Reactor te una temperatura igual o superio a 1000 graus, no es pot activar");
        } else {
            this.activa = true;
        }
    }

    public void desactiva() {
        this.activa = false;
    }

    public boolean getActivat(){
        return activa;
    }

    public void revisa (PaginaIncidencies p) {
        if (temperatura >= 1000) {
            this.activa = false;
            p.afegeixIncidencia("El reactor es va desactivar per superar la temperatura màxima");
        }
    }

    public float getCostOperatiu(){
        if (this.activa == false) {
            return 0;
        } else {
            return 35;
        }
    }

    public float calculaOutput(float input){
        if (this.activa == false) {
            return gettemperatura();
        } else {
            return gettemperatura() + (100 - input) * 10;
        }
    }

    @Override
    public String toString() {
        return "Reactor [Activat: " + (getActivat() ? "Sí" : "No") + ", Temperatura: " + temperatura + " ºC]";
    }

}
