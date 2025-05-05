package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;

public class SistemaRefrigeracio implements InComponent, Serializable {
    ArrayList<BombaRefrigerant> llistabombes = new ArrayList<>();
    boolean activa;

    public ArrayList<BombaRefrigerant> getllistabombes(){
       return llistabombes;
    }

    public void afegirBomba(BombaRefrigerant b) {
        llistabombes.add(b);
    }

    ////////////////////////////////AQUEST METODE NO S'ESTA UTILITZANT solucionar
    public void activa() throws CentralUBException {
        for (int i = 0; i < llistabombes.size(); i++) {
            if (llistabombes.get(i).getForaDeServei()) {
                throw new CentralUBException("La Bomba "+i+" esta fora de servei, es mantindra desactivada");
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
            else {
                return false;
            }
        }
        return false;
    }

    public void revisa (PaginaIncidencies p) {
        for (int i = 0; i < llistabombes.size(); i++) {
            llistabombes.get(i).revisa(p);
            if (llistabombes.get(i).getForaDeServei() == true) {
                p.afegeixIncidencia("La bomba " + i + " esta fora de servei");
                llistabombes.get(i).desactiva();
            }
        }
    }

    public float getCostOperatiu(){
        float cost=0;
        for (int i = 0; i < llistabombes.size(); i++) {
            cost += 130;
        }
        return cost;
    }

    public float calculaOutput(float input){
        int N = 0;
        for (BombaRefrigerant bomba: llistabombes) {
            if (bomba.getActivat() == true) {
                N += 1;
            }
        }
        if (250*N > input) {
            return input;
        } else {
            return 250*N;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sistema de Refrigeració[\n");
        sb.append("  Estat: ").append(getActivat() ? "Activat" : "Desactivat").append("\n");
        sb.append("  Bombes:\n");
        for (BombaRefrigerant bomba : llistabombes) {
            sb.append("    - ").append(bomba.toString()).append("\n");
        }
        sb.append("]");
        return sb.toString();
    }
}
