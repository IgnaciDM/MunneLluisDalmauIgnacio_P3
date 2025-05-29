package prog2.model;

import prog2.vista.CentralUBException;

public class EstatTecnic {
    private Reactor reactor;
    private BombaRefrigerant bombaRefrigerant;
    private SistemaRefrigeracio sistemaRefrigeracio;
    int reparacions;

    public EstatTecnic(Reactor reactor,SistemaRefrigeracio sistemaRefrigeracio, float insercioBarres) {
        this.reactor = reactor;
        this.sistemaRefrigeracio = sistemaRefrigeracio;

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
    public void RepararReactor(){
        for (BombaRefrigerant bomba: sistemaRefrigeracio.getllistabombes()) {
            try{
                bomba.activa();
            }catch (CentralUBException e){
                e.printStackTrace();
            }
        }
    }

    public void RepararBombes(int num){
        if (disponibleReparacio()) {
            for (BombaRefrigerant bomba: sistemaRefrigeracio.getllistabombes()) {
                if (bomba.getId()==num){
                    bomba.setForaDeServei(false);
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
