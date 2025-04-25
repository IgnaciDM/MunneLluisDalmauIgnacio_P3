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
            else
                return false;
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
