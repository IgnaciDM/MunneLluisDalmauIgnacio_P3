package prog2;
import prog2.model.vista;
import java.io.Serializable;
import java.util.ArrayList;

public class SistemaRefrigeració implements InComponent {
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
            llistabombes.get(i).revisa();
        }
    }

    public float getCostOperatiu(){
        float cost;
        for (int i = 0; i < llistabombes.size(); i++) {
            cost += llistabombes.get(i).getCostOperatiu();
        }
        return cost;
    }

    public float calculaOutput(float input){
        for (int i = 0; i < llistabombes.size(); i++) {
            llistabombes.get(i).getcapacitat();
        }
    }
}
