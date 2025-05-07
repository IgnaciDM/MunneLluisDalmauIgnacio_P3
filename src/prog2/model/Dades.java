/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;
import java.util.Iterator;
import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Daniel Ortiz
 */
/**
 * Classe que representa les dades principals de la central nuclear.
 * Inclou la configuració i estat del reactor, sistema de refrigeració,
 * generador de vapor, turbina i l'economia de la planta.
 */
public class Dades implements InDades, Serializable {

    /**
     * Llavor per generar valors pseudoaleatoris.
     */
    public final static long VAR_UNIF_SEED = 123;

    /**
     * Valor inicial dels guanys.
     */
    public final static float GUANYS_INICIALS = 0;

    /**
     * Preu per unitat de potència venuda.
     */
    public final static float PREU_UNITAT_POTENCIA = 1;

    /**
     * Penalització per generar més potència de la demandada.
     */
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;

    // Atributs de la central
    private final VariableUniforme variableUniforme;
    private final Bitacola bitacola;
    private float GrauBarres;
    private final Reactor reactor;
    private final SistemaRefrigeracio sistemaRefrigeracio;
    private final GeneradorVapor generadorVapor;
    private final Turbina turbina;
    private int dia;
    private float guanysAcumulats;

    /**
     * Constructor de la classe Dades. Inicialitza tots els components
     * de la central i afegeix les bombes de refrigeració.
     */
    public Dades() {
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.GrauBarres = 100;
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

        this.sistemaRefrigeracio.afegirBomba(new BombaRefrigerant(variableUniforme, 0));
        this.sistemaRefrigeracio.afegirBomba(new BombaRefrigerant(variableUniforme, 1));
        this.sistemaRefrigeracio.afegirBomba(new BombaRefrigerant(variableUniforme, 2));
        this.sistemaRefrigeracio.afegirBomba(new BombaRefrigerant(variableUniforme, 3));

        this.sistemaRefrigeracio.desactiva();
    }

    /**
     * Obté el grau d'inserció de les barres del reactor.
     * @return Grau d'inserció (entre 0 i 100).
     */
    public float getInsercioBarres() {
        return GrauBarres;
    }

    /**
     * Defineix el grau d'inserció de les barres del reactor.
     * @param insercioBarres Nou valor del grau (entre 0 i 100).
     * @throws CentralUBException Si el valor no és vàlid.
     */
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        if (insercioBarres >= 0 && insercioBarres <= 100) {
            this.GrauBarres = insercioBarres;
        } else {
            throw new CentralUBException("El grau d'insercio no es correcte");
        }
    }

    /**
     * Activa el reactor.
     * @throws CentralUBException Si no es pot activar.
     */
    public void activaReactor() throws CentralUBException {
        reactor.activa();
    }

    /**
     * Desactiva el reactor.
     */
    public void desactivaReactor() {
        reactor.desactiva();
    }

    /**
     * Retorna una representació del reactor.
     * @return Objecte Reactor.
     */
    public Reactor mostraReactor() {
        return reactor;
    }

    /**
     * Activa una bomba refrigerant pel seu identificador.
     * @param id Identificador de la bomba.
     * @throws CentralUBException Si hi ha un problema d'activació.
     */
    public void activaBomba(int id) throws CentralUBException {
        for (BombaRefrigerant b : sistemaRefrigeracio.getllistabombes()) {
            if (b.getId() == id) {
                b.activa();
            }
        }
    }

    /**
     * Desactiva una bomba refrigerant pel seu identificador.
     * @param id Identificador de la bomba.
     */
    public void desactivaBomba(int id) {
        for (BombaRefrigerant b : sistemaRefrigeracio.getllistabombes()) {
            if (b.getId() == id) {
                b.desactiva();
            }
        }
    }

    /**
     * Retorna el sistema de refrigeració.
     * @return Sistema de refrigeració.
     */
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }

    /**
     * Retorna l'estat actual de la central.
     * @return Pàgina d'estat.
     */
    public PaginaEstat mostraEstat() {
        return new PaginaEstat(dia, GrauBarres, reactor, sistemaRefrigeracio, generadorVapor, turbina);
    }

    /**
     * Retorna la bitàcola completa de la central.
     * @return Bitàcola.
     */
    public Bitacola mostraBitacola() {
        return bitacola;
    }

    /**
     * Retorna la llista d'incidències registrades.
     * @return Llista de pàgines d'incidències.
     */
    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();
    }

    /**
     * Calcula la potència generada per la central.
     * @return Potència generada.
     */
    public float calculaPotencia() {
        return turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(GrauBarres))));
    }

    /**
     * Retorna els guanys acumulats fins ara.
     * @return Guanys acumulats.
     */
    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    /**
     * Actualitza els paràmetres econòmics de la central per al dia actual.
     * @param demandaPotencia Potència que es demanda.
     * @return Objecte PaginaEconomica amb el resum del dia.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia) {
        float potenciaGenerada = calculaPotencia();
        float demandasatisfeta = (potenciaGenerada / demandaPotencia) * 100;
        float beneficis = Math.min(potenciaGenerada, demandaPotencia);
        float penalitzacio = (potenciaGenerada > demandaPotencia) ? PENALITZACIO_EXCES_POTENCIA : 0;

        float costOperatiu = 0;
        if (reactor.getActivat()) costOperatiu += reactor.getCostOperatiu();
        if (generadorVapor.getActivat()) costOperatiu += generadorVapor.getCostOperatiu();
        if (turbina.getActivat()) costOperatiu += turbina.getCostOperatiu();
        costOperatiu += sistemaRefrigeracio.getCostOperatiu();

        float guanysNets = beneficis - costOperatiu - penalitzacio;
        guanysAcumulats += guanysNets;

        return new PaginaEconomica(dia, demandaPotencia, potenciaGenerada, demandasatisfeta,
                beneficis, penalitzacio, costOperatiu, guanysAcumulats);
    }

    /**
     * Refrigera el reactor amb l'output del sistema de refrigeració.
     */
    private void refrigeraReactor() {
        float temp = reactor.calculaOutput(GrauBarres);
        temp -= sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(GrauBarres));
        if (temp < 25) temp = 25;
        reactor.settemperatura(temp);
    }

    /**
     * Revisa l'estat dels components i registra les incidències trobades.
     * @param paginaIncidencies Objecte per registrar les incidències.
     */
    private void revisaComponents(PaginaIncidencies paginaIncidencies) {
        reactor.revisa(paginaIncidencies);
        sistemaRefrigeracio.revisa(paginaIncidencies);
    }

    /**
     * Finalitza el dia: actualitza economia, refrigera, revisa components i registra tot a la bitàcola.
     * @param demandaPotencia Demanda de potència del dia.
     * @return Bitàcola específica del dia.
     */
    public Bitacola finalitzaDia(float demandaPotencia) {
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);
        PaginaEstat paginaEstat = mostraEstat();
        refrigeraReactor();
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        revisaComponents(paginaIncidencies);
        dia++;

        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);

        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }
}

