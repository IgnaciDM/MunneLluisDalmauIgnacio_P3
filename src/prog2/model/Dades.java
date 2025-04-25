/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import prog2.vista.CentralUBException;

import java.util.List;

/**
 *
 * @author Daniel Ortiz
 */
public class Dades implements InDades{
    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;

    /////////////////////////////////////////////////////////////////////////////////////
    private final VariableUniforme variableUniforme;
    private final Bitacola bitacola;
    private float insercioBarres;
    private final Reactor reactor;
    private final SistemaRefrigeracio sistemaRefrigeracio;
    private final GeneradorVapor generadorVapor;
    private final Turbina turbina;
    private int dia;
    private final float guanysAcumulats;

    ///////////////////////////////////////////////////////////////////////////////////

    // Afegir atributs:

    public Dades() throws CentralUBException {
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.reactor.desactiva();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;
        
        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);
        
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);

        this.sistemaRefrigeracio.desactiva();
    }

    //--------------------------------------------------------------------------

    public float getInsercioBarres() {
        return insercioBarres;
    }

    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        if (insercioBarres >= 0 && insercioBarres <= 100) {
                this.insercioBarres = insercioBarres;
        } else{
            throw new CentralUBException("El grau d'insercio no es correcta");
        }
    }

    //--------------------------------------------------------------------------

    public void activaReactor() throws CentralUBException {
        reactor.activa();
    }

    public void desactivaReactor(){
        reactor.desactiva();
    }

    public Reactor mostraReactor() {
        return reactor;//Equivalent a posar reactor.toString
    }
    //-------------------------------------------------------------------------


    public void activaBomba(int id) throws CentralUBException {
        for (int i = 0; i < sistemaRefrigeracio.getllistabombes().size(); i++) {
            if (sistemaRefrigeracio.getllistabombes().get(i).getId() == id) {
                sistemaRefrigeracio.getllistabombes().get(i).activa();
            }
        }
    }

    public void desactivaBomba(int id) {
        for (int i = 0; i < sistemaRefrigeracio.getllistabombes().size(); i++) {
            if (sistemaRefrigeracio.getllistabombes().get(i).getId() == id) {
                sistemaRefrigeracio.getllistabombes().get(i).desactiva();
            }
        }
    }

    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }
    //-------------------------------------------------------------------------
    public PaginaEstat mostraEstat() {
        return new PaginaEstat(dia,insercioBarres, reactor, sistemaRefrigeracio, generadorVapor, turbina);
    }
    //-------------------------------------------------------------------------
    public Bitacola mostraBitacola() {
        return bitacola;
    }
    //-------------------------------------------------------------------------

    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();
    }
    //-------------------------------------------------------------------------

    public float calculaPotencia() {
        return turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres))));
    }
    //-------------------------------------------------------------------------

    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }
    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a 
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou 
     * beneficis, penalització per excès de potència, costos operatius y 
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){
        // 1. Calcular potència generada
        float potenciaGenerada = turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres))));

        float demandasatisfeta = (potenciaGenerada/demandaPotencia)*100;

        // 2. Calcular beneficis (fins a la demanda)
        float beneficis = Math.min(potenciaGenerada, demandaPotencia);

        // 3. Penalització si hi ha excés
        float penalitzacio = (potenciaGenerada > demandaPotencia) ? PENALITZACIO_EXCES_POTENCIA : 0;

        // 4. Costos operatius (només dels components actius)
        float costOperatiu = 0;

        if (reactor.getActivat()) {
            costOperatiu += reactor.getCostOperatiu();
        }

        if (generadorVapor.getActivat()) {
            costOperatiu += generadorVapor.getCostOperatiu();
        }

        if (turbina.getActivat()) {
            costOperatiu += turbina.getCostOperatiu();
        }

        costOperatiu += sistemaRefrigeracio.getCostOperatiu(); // ja suma només les bombes actives

        // 5. Guanys nets i acumulats
        float guanysNets = beneficis - costOperatiu - penalitzacio;
        float nouGuanysAcumulats = guanysAcumulats + guanysNets;

        // 6. Retornar pàgina econòmica
        return new PaginaEconomica(dia, demandaPotencia, potenciaGenerada, demandasatisfeta,
                beneficis, penalitzacio, costOperatiu,
                nouGuanysAcumulats);
    }


    /**
     * Aquest mètode ha de establir la nova temperatura del reactor.
     */
    private void refrigeraReactor() {
          // Completar
    }

    /**
     * Aquest mètode ha de revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void revisaComponents(PaginaIncidencies paginaIncidencies) {
          // Completar
    }

    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);
        
        // Genera pàgina d'estat amb la configuració escollida (la nova pàgina
        // d'estat inclou la nova configuració escollida pel operador abans de
        // refrigerar el reactor)
        PaginaEstat paginaEstat = mostraEstat();

        // Actualitza estat de la central...

        // Refrigera el reactor
        refrigeraReactor();

        // Revisa els components de la central i registra incidències
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        revisaComponents(paginaIncidencies);
        
        // Incrementa dia
        dia += 1;
        
        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);
        
        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }

}
