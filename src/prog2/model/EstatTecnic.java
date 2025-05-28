package prog2.model;

import prog2.vista.CentralUBException;

public class EstatTecnic {
    private Reactor reactor;
    private BombaRefrigerant bombaRefrigerant;
    private SistemaRefrigeracio sistemaRefrigeracio;
    float insercioBarres;
    private Dades dades;

    public EstatTecnic(Reactor reactor,BombaRefrigerant bombaRefrigerant,SistemaRefrigeracio sistemaRefrigeracio) {
        this.reactor = reactor;
        this.bombaRefrigerant= bombaRefrigerant;
        this.sistemaRefrigeracio = sistemaRefrigeracio;
        this.insercioBarres = dades.getInsercioBarres();
    }

    void RepararReactor(){
        for (float i=insercioBarres; i>0 ;i++) {
            try {
                dades.setInsercioBarres(100);
            } catch (CentralUBException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void RepararBombes(int num){
        for (BombaRefrigerant bomba: sistemaRefrigeracio.getllistabombes()) {
            if (bomba.getId()==num){
                bombaRefrigerant.setForaDeServei(false);
            }
        }
    }

    float CalcularCostosReparacio(){
        return 0;
    }


}
