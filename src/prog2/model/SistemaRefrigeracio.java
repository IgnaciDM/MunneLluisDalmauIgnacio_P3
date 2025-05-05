package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class SistemaRefrigeracio implements InComponent {
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
            it.next().revisa(p);
            if (it.next().getForaDeServei() == true) {
                p.afegeixIncidencia("La bomba " + it.next().getId() + " esta fora de servei");
                it.next().desactiva();
            }
        }
    }

    public float getCostOperatiu(){
        float cost=0;
        Iterator<BombaRefrigerant> it = llistabombes.iterator();
        while (it.hasNext()) {
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


    //IMPLEMENTAT EXTRA PER FER LES TRES OP DEL MENU___________________________________________________________________
    //Activar Bomba per un ID
    /*
    public void activaId(int id) throws CentralUBException {
        for (BombaRefrigerant bomba: sistemaRefrigeracio){
            if (bomba.getId() == id) {
                if (bomba.getForaDeServei()){
                    throw new CentralUBException("La Bomba esta fora de servei");
                } else{
                    try {
                        bomba.activa();
                    } catch (CentralUBException e) {
                        System.out.println(e);
                    }
                }
            }
        }
        throw new CentralUBException("No s'ha trobat la bomba amb id: "+ id);
    }
    //Desactivar Bomba per un ID
    public void desactivaId(int id) throws CentralUBException {
        for (BombaRefrigerant bomba: sistemaRefrigeracio){
            if (bomba.getId() == id) {
                bomba.desactiva();
            }
        }
        throw new CentralUBException("No s'ha trobat la bomba amb id: "+ id);
    }
    //Mostrar Estat de les Bombes
    public void mostrarBombes() {
        for (BombaRefrigerant bomba: sistemaRefrigeracio){
            System.out.println(bomba.toString());
        }
    }
    */
}
