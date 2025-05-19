/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.*;

import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Daniel Ortiz
 */

/**
 * Classe que representa la gestió d'una central elèctrica. Aquesta classe permet gestionar diferents
 * aspectes de la central, com la demanda de potència, el reactor, el sistema de refrigeració i altres
 * components relacionats amb el funcionament de la central.
 *
 * Inclou funcions per gestionar barres de control, activar/desactivar el reactor i les bombes refrigerants,
 * obtenir dades de la demanda de potència i finalitzar el dia, entre altres operacions.
 *
 * Aquesta classe fa ús de diferents enumeracions per gestionar els menús interactius de l'usuari
 * i permet la interacció mitjançant la consola.
 *
 * Les dades com la demanda màxima i mínima, així com les característiques de la distribució normal,
 * es defineixen com a constants.
 */
public class CentralUB {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;

    private Adaptador adaptador;
    private Dades dades;

    /*ENUMS Y STRINGS A MOSTRAR EN MENU*/

    /**
     * Enumeració que defineix les opcions disponibles en el menú principal de la central.
     */
    private enum OpcionsMenu {
        GestionarBarresControl,
        GestionarReactor,
        GestionarSistemaRefrigeracio,
        MostrarEstatCentral,
        MostrarBitacola,
        MostrarIncidencies,
        ObtenirDemandaSatisfeta,
        FinalitzarDia,
        GuardarDades,
        CarregarDades,
        Sortir
    }

    private enum OpcionsBarres {
        ObtenirInsercioBarres,
        EstablirInsercioBarres,
        Sortir
    }

    private enum OpcionsReactor {
        ActivarReactor,
        DesactivarReactor,
        MostrarEstatReactor,
        Sortir
    }

    private enum OpcionsSistema {
        ActivarTotesBombes,
        DesactivarTotesBombes,
        ActivarBomba,
        DesactivarBomba,
        MostrarEstat,
        Sortir
    }


    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] llistaOpcions = {"Gestionar Barres Control",
            "Gestionar Reactor",
            "Gestionar Sistema Refrigeració",
            "Mostrar Estat Central",
            "Mostrar Bitacola",
            "Mostrar Incidencies",
            "Obtenir demanda satisfeta amb configuracio actual",
            "Finalitzar dia",
            "Guardar dades",
            "Carregar dades",
            "Sortir"
    };

    // Declarem descripcions personalitzades per a les opcions del menú secundari
    static private String[] llistaopcioBarres = {"Obtenir insercio Barres",
            "Establir Insercio Barres",
            "Sortir"
    };

    static private String[] llistaopcioReactor = {"Activar Reactor",
            "DEsactivar Reactor",
            "Mostrar Estat del Reactor",
            "Sortir"
    };

    static private String[] llistaopcioSistema = {"Activar Totes les bombes",
            "Desactivar totes les Bombes",
            "Activar Bomba",
            "Desactiva Bomba",
            "Mostrar Estat",
            "Sortir"
    };


    /* Constructor*/
    /**
     * Constructor de la classe `CentralUB`. Inicialitza el generador de demanda de potència aleatòria
     * i l'adaptador per interactuar amb altres components.
     */
    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        // Inicialitzem l'objecte adaptador

        // Afegir codi adicional si fos necessari:
        adaptador = new Adaptador();
    }

    /**
     * Gestió interactiva de la central elèctrica. Permet a l'usuari realitzar accions com gestionar el reactor,
     * el sistema de refrigeració i obtenir dades sobre la demanda de potència.
     *
     */
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
        Scanner sc = new Scanner(System.in);

        Menu<OpcionsMenu> principal = new Menu("Menu Principal", OpcionsMenu.values());

        OpcionsMenu op = null;

        do {
            principal.mostrarMenu();
            op = principal.getOpcio(sc);

            switch (op) {
                case GestionarBarresControl:
                    //Dona pas a un submenú que permet obtenir o establir la inserció de les barres, que ha de ser un nombre real entre 0 i 100
                    gestioBarres(sc);
                    break;
                case GestionarReactor:
                    //Mostra un sub-menú per gestionar el reactor.
                    gestioReactor(sc);
                    break;
                case GestionarSistemaRefrigeracio:
                    //Mostra un sub-menú amb opcions per controlar el sistema de refrigeració.
                    gestioSistema(sc);
                    break;
                case MostrarEstatCentral:
                    //Mostra la pàgina de bitàcola d’estat corresponent al dia actual (veure Apartat 2.4.2).
                    //Aquesta informació és provisional i només es farà efectiva al finalitzar el dia
                    System.out.println(adaptador.mostraEstat());
                    break;
                case MostrarBitacola:
                    //Mostra tot el contingut de la bitàcola fins al dia actual, incloent las pàgines d’estat, econòmiques i d’incidències.
                    System.out.println(adaptador.mostraBitacola());
                    break;
                case MostrarIncidencies:
                    //Mostra totes les pàgines d’incidències de la bitàcola fins al dia actual.
                    System.out.println(adaptador.mostraIncidencies());
                    break;
                case ObtenirDemandaSatisfeta:
                    //Mostra la demanda de poténcia del dia en curs, la potència generada amb la configuración de la central actual i el percentatge de demanda satisfeta corresponent.
                    System.out.println("⚡ Demanda de potència: "+demandaPotencia);
                    System.out.println("\uD83D\uDD0B Potència generada: "+adaptador.calculaPotencia()+" MW"); //🔋
                    System.out.println("✅ Percentatge de demanda satisfeta: "+(adaptador.calculaPotencia()/demandaPotencia)*100+" %");
                    break;
                case FinalitzarDia:
                    //Es duen a terme totes les acciones relacionades amb la finalització d’un dia (veure Apartat 2.3).
                    finalitzaDia();

                    break;
                case GuardarDades:
                    //Guarda les dades de l’aplicació.
                    System.out.println("Especifiqui el cami de desti del fitxer, on guardar les dades (s'afegira .dat per defecte)");
                    try {
                        adaptador.guardaDades(sc.nextLine()+".dat");
                    } catch (CentralUBException e) {
                        System.out.println("Error Guardar:  ,"+e.getMessage());
                    }
                    break;
                case CarregarDades:
                    //Carrega les dades de l’aplicació.
                    System.out.println("Especifiqui el cami d'Origen del fitxer, d'on carregar les dades (s'afegira .dat per defecte)");
                    try {
                        adaptador.carregaDades(sc.nextLine()+".dat");
                    } catch (CentralUBException e) {
                        System.out.println("Error Carregar: "+e.getMessage());
                    }
                    break;
                case Sortir:
                    break;
            }
        } while (op != OpcionsMenu.Sortir);

    }

    public void gestioBarres(Scanner sc){

        Menu<OpcionsBarres> Barres = new Menu<>("Menu Barres", OpcionsBarres.values());

        OpcionsBarres op = null;

        do {
            Barres.mostrarMenu();
            op = Barres.getOpcio(sc);
            switch (op) {
                case ObtenirInsercioBarres:
                    //Mostra per pantalla la inserció de les barres.
                    System.out.println(adaptador.getInsercioBarres());
                    break;
                case EstablirInsercioBarres:
                    //Sol·licita a l’usuari el grau d’inserció de les barres
                    System.out.println("Especifiqui el grau d'insercio de barres 1-100:");
                    try{
                        adaptador.setInsercioBarres(sc.nextInt());
                    }catch(CentralUBException e){
                        System.out.println("Error Barres: "+e.getMessage());
                    }
                    break;
                case Sortir:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + op);
            }
        } while (op != OpcionsBarres.Sortir);

    }

    public void gestioReactor(Scanner sc){
        Menu<OpcionsReactor> Reactor = new Menu<>("Menu Reactor", OpcionsReactor.values());

        OpcionsReactor op = null;

        do {
            Reactor.mostrarMenu();
            op = Reactor.getOpcio(sc);
            switch (op) {
                case ActivarReactor:
                    //Permet activar el reactor.
                    try{
                        adaptador.activaReactor();
                    } catch(CentralUBException e) {
                        System.out.println("Error Reactor: "+e.getMessage());
                        break;
                    }
                    System.out.println("Reactor activat");
                    break;
                case DesactivarReactor:
                    // Permet desactivar el reactor.
                    adaptador.desactivaReactor();
                    System.out.println("Reactor desactivat");
                    break;
                case MostrarEstatReactor:
                    // Mostra si el reactor està activat i la seva temperatura.
                    System.out.println(adaptador.mostraReactor());
                    break;
                case Sortir:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + op);
            }
        } while (op != OpcionsReactor.Sortir);

    }

    public void gestioSistema(Scanner sc){

        Menu<OpcionsSistema> Sistema = new Menu<>("Menu Sistema", OpcionsSistema.values());

        OpcionsSistema op = null;
        do {
            Sistema.mostrarMenu();
            op = Sistema.getOpcio(sc);
            switch (op) {
                case ActivarTotesBombes:
                    //Activa totes les bombes refrigerants.
                    Iterator<BombaRefrigerant> it = adaptador.mostraSistemaRefrigeracio().getllistabombes().iterator();
                    while (it.hasNext()) {
                        try {
                            it.next().activa();
                        } catch(CentralUBException e) {
                            System.out.println("Error Sistema: "+e.getMessage());
                        }
                    }
                    break;
                case DesactivarTotesBombes:
                    //Desactiva totes les bombes refrigerants
                    Iterator<BombaRefrigerant> it2 = adaptador.mostraSistemaRefrigeracio().getllistabombes().iterator();
                    while (it2.hasNext()) {
                        it2.next().desactiva();
                    }
                    break;
                case ActivarBomba:
                    //Donat el seu identificador numèric (entre 0 i 3), permet activar una bomba refrigerant.
                    System.out.println("Introdueix la id d'una de les bombes 0-3");
                    try {
                        adaptador.activaBomba(sc.nextInt());
                    } catch (CentralUBException e) {
                        System.out.println("Error Sistema: "+e.getMessage());
                    }
                    break;
                case DesactivarBomba:
                    //Donat l’identificador numèric d’una bomba refrigerant, permet desactivar-la.
                    System.out.println("Introdueix la id d'una de les bombes 0-3");
                    adaptador.desactivaBomba(sc.nextInt());
                    break;
                case MostrarEstat:
                    // mostra l’estat actual de totes les bombes del sistema de refrigeració.
                    System.out.println(adaptador.mostraSistemaRefrigeracio());
                    break;
                case Sortir:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + op);
            }
        } while (op != OpcionsSistema.Sortir);

    }

    /**
     * Genera la demanda de potència aleatòria per al dia actual, tenint en compte les limitacions
     * establertes per les constants `DEMANDA_MAX` i `DEMANDA_MIN`.
     *
     * @return El valor generat per la demanda de potència.
     */
    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
        if (valor < DEMANDA_MIN)
            return DEMANDA_MIN;
        else
            return valor;
    }

    /**
     * Finalitza el dia actual, mostrant informació sobre l'estat de la central i generant una nova demanda
     * de potència per al següent dia.
     */
    private void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        String info = new String();
        info = adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");

        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }

    public Adaptador getAdaptador() {
        return adaptador;
    }
}

