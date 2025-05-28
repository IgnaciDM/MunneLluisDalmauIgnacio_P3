package prog2.model;

import prog2.vista.CentralUBException;

public class EstatTecnic {
    private Reactor reactor;
    private BombaRefrigerant bombaRefrigerant;
    private SistemaRefrigeracio sistemaRefrigeracio;
    float insercioBarres;
    private Dades dades;
    int reparacions;

    public EstatTecnic(Reactor reactor,BombaRefrigerant bombaRefrigerant,SistemaRefrigeracio sistemaRefrigeracio) {
        this.reactor = reactor;
        this.bombaRefrigerant= bombaRefrigerant;
        this.sistemaRefrigeracio = sistemaRefrigeracio;
        this.insercioBarres = dades.getInsercioBarres();
        this.dades = dades;
    }

    int getReparacions(){
        return reparacions;
    }
    void setReparacions(int reparacio) {
        this.reparacions=reparacio;
    }

    boolean disponibleReparacio(){
        return getReparacions()!=2;
    }
    void RepararReactor(){
        if (disponibleReparacio()) {
            for (float i=insercioBarres; i>0 ;i++) {
                try {
                    dades.setInsercioBarres(i);
                } catch (CentralUBException e) {
                    throw new RuntimeException(e);
                }
            }
            reparacions++;
        } else {
            System.out.println("S'han fet totes les reparacions posibles de dia");
        }
    }

    void RepararBombes(int num){
        if (disponibleReparacio()) {
            for (BombaRefrigerant bomba: sistemaRefrigeracio.getllistabombes()) {
                if (bomba.getId()==num){
                    bombaRefrigerant.setForaDeServei(false);
                }
            }
        }else{
            System.out.println("S'han fet totes les reparacions posibles de dia");
        }
    }

    float CalcularCostosReparacio(){

        return 0;
    }
}
