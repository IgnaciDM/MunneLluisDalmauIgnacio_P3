package prog2.model;

import java.awt.datatransfer.FlavorListener;

public class EstatTecnic {
    private Reactor reactor;
    private BombaRefrigerant bombaRefrigerant;
    private SistemaRefrigeracio sistemaRefrigeracio;


    EstatTecnic(Reactor reactor) {
        this.reactor = reactor;
        this.bombaRefrigerant= bombaRefrigerant;
    }
    void RepararReactor(){

    }

    void RepararBombes(int num){
        for (BombaRefrigerant bomba: sistemaRefrigeracio.getllistabombes()) {

        }
    }

    float CalcularCost(){

        return 0;
    }


}
