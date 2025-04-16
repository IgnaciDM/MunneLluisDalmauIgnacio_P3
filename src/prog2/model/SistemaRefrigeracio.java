package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;

public class SistemaRefrigeracio implements InComponent {
    ArrayList<BombaRefrigerant> llistabombes = new ArrayList<>();
    boolean activa;

    public void afegirBomba(BombaRefrigerant b) {
    llistabombes.add(b);
    }

    public void activa() throws CentralUBException {
        for (int i = 0; i < llistabombes.size(); i++) {
            llistabombes.get(i).activa();
        }
    }

    public void desactiva() {
        for (int i = 0; i < llistabombes.size(); i++) {
            llistabombes.get(i).desactiva();
        }
    }

    public boolean getActivat(){
        for (int i = 0; i < llistabombes.size(); i++) {
            if (llistabombes.get(i).getActivat() == true) {
                return true;
            }
        }
        return false;
    }

    public void revisa (PaginaIncidencies p) {
        for (int i = 0; i < llistabombes.size(); i++) {
            llistabombes.get(i).revisa(p);
        }
    }

    public float getCostOperatiu(){
        float cost=0;
        for (int i = 0; i < llistabombes.size(); i++) {
            cost += llistabombes.get(i).getCostOperatiu();
        }
        return cost;
    }

    public float calculaOutput(float input){
        float output = 0;
        for (int i = 0; i < llistabombes.size(); i++) {
            output=llistabombes.get(i).getCapacitat();
        }
        return output;
    }
}
