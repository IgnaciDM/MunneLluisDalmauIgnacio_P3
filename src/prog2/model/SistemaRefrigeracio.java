package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;

public class SistemaRefrigeracio implements InComponent {
    ArrayList<BombaRefrigerant> llistabombes = new ArrayList<>();
    boolean activa;

    public ArrayList<BombaRefrigerant> getllistabombes(){
       return llistabombes;
    }



    public void afegirBomba(BombaRefrigerant b) {
    llistabombes.add(b);
    }

    public void activa() throws CentralUBException {
        for (int i = 0; i < llistabombes.size(); i++) {
            if (llistabombes.get(i).getForaDeServei()) {
                throw new CentralUBException("La Bomba esta fora de servei, es desactivada");
            } else {
            llistabombes.get(i).activa();
            }
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
            if (llistabombes.get(i).getForaDeServei() == true) {
                p.afegeixIncidencia("La bomba " + i + " esta fora de servei");
            }
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
        int N = 0;
        for (int i = 0; i < llistabombes.size(); i++) {
            if (llistabombes.get(i).getActivat() == true) {
                N += 1;
            }
        }
        if (250*N > input) {
            return input;
        } else {
            return 250*N;
        }
    }
}
