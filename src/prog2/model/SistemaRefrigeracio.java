package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class SistemaRefrigeracio implements InComponent,Serializable {
    ArrayList<BombaRefrigerant> llistabombes;
    boolean activa;

    public SistemaRefrigeracio() {
        this.llistabombes = new ArrayList<>();
        this.activa = false;
    }

    public SistemaRefrigeracio(SistemaRefrigeracio original) {
        ArrayList<BombaRefrigerant> l = new ArrayList<>();
        for (BombaRefrigerant b : original.llistabombes) {
            l.add(new BombaRefrigerant(b));
        }
        this.llistabombes = l;
        this.activa = original.activa;
    }

    public ArrayList<BombaRefrigerant> getllistabombes(){
       return llistabombes;
    }

    public void afegirBomba(BombaRefrigerant b) {
        llistabombes.add(b);
    }

    ////////////////////////////////AQUEST METODE NO S'ESTA UTILITZANT solucionar
    public void activa() throws CentralUBException {
        Iterator<BombaRefrigerant> it = llistabombes.iterator();
        while (it.hasNext()) {
            if (it.next().getForaDeServei()) {
                throw new CentralUBException("La Bomba " + it.next().getId() + " esta fora de servei, es mantindra desactivada");
            } else {
                it.next().activa();
            }
        }
    }

    public void desactiva() {
        Iterator<BombaRefrigerant> it = llistabombes.iterator();
        while (it.hasNext()) {
            it.next().desactiva();
        }
    }

    public boolean getActivat(){
        Iterator<BombaRefrigerant> it = llistabombes.iterator();
        while (it.hasNext()) {
            if (it.next().getActivat() == true) {
                return true;
            }
        }
        return false;
    }

    public void revisa (PaginaIncidencies p) {
        Iterator<BombaRefrigerant> it = llistabombes.iterator();
        while (it.hasNext()) {
            BombaRefrigerant b = it.next();
            b.revisa(p);
            if (b.getForaDeServei() == true) {
                p.afegeixIncidencia("La bomba " + b.getId() + " esta fora de servei");
                b.desactiva();
            }
        }
    }

    public float getCostOperatiu(){
        float cost=0;
        for (BombaRefrigerant bomba: llistabombes) {
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
