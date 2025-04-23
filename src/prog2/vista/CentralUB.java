/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.*;

import java.util.Scanner;

/**
 *
 * @author Daniel Ortiz
 */


public class CentralUB {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;
    
    /** Generador aleatori de la demanda de potència **/
    private prog2.vista.VariableNormal variableNormal;
    
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;

    private Adaptador adaptador;
    //private BarresDeControl barres;  // Atribut de la classe
    //private Reactor reactor;
    private SistemaRefrigeracio sistema;
    private PaginaBitacola paginaBitacola;
    private Dades dades;

    /*ENUMS Y STRINGS A MOSTRAR EN MENU*/

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
    public CentralUB() {
        variableNormal = new prog2.vista.VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        // Inicialitzem l'objecte adaptador

        // Afegir codi adicional si fos necessari:
        adaptador = new Adaptador();
        //barres = new BarresDeControl();
        //reactor = new Reactor();
        sistema = new SistemaRefrigeracio();
        paginaBitacola = new PaginaBitacola(1);
    }

    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
        Scanner sc = new Scanner(System.in);

        Menu<OpcionsMenu> principal = new Menu("Menu Principal", OpcionsMenu.values());

        try {
            dades = new Dades();
        } catch (CentralUBException e) {
            System.out.println(e.getMessage());
        }

        OpcionsMenu op = null;
        int dia=1;

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

                    //paginaBitacola.toString();

                    break;
                case MostrarBitacola:
                    //Mostra tot el contingut de la bitàcola fins al dia actual, incloent las pàgines d’estat, econòmiques i d’incidències.

                    break;
                case MostrarIncidencies:
                    //Mostra totes les pàgines d’incidències de la bitàcola fins al dia actual.

                    break;
                case ObtenirDemandaSatisfeta:
                    //Mostra la demanda de poténcia del dia en curs, la potència generada amb la configuración de la central actual i el percentatge de demanda satisfeta corresponent.
                    break;
                case FinalitzarDia:
                    //Es duen a terme totes les acciones relacionades amb la finalització d’un dia (veure Apartat 2.3).
                    finalitzaDia();
                    dia+=1;
                    paginaBitacola.setDia(dia);//FALTEN MOLTES MES COSES A FER
                    break;
                case GuardarDades:
                    //Guarda les dades de l’aplicació.
                    try {
                        adaptador.guardaDades("ENCARA FALTA PER FER");
                    } catch (CentralUBException e) {
                        System.out.println("Error Guardar: "+e.getMessage());
                    }
                    break;
                case CarregarDades:
                    //Carrega les dades de l’aplicació.
                    try {
                        adaptador.carregaDades("ENCARA FALTA PER FER");
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
                    System.out.println(dades.getInsercioBarres());
                    break;
                case EstablirInsercioBarres:
                    //Sol·licita a l’usuari el grau d’inserció de les barres
                    System.out.println("Especifiqui el grau d'insercio de barres 1-100:");
                    try{
                        dades.setInsercioBarres(sc.nextInt());
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
                        dades.activaReactor();
                    }catch(CentralUBException e){
                        System.out.println("Error Reactor: "+e.getMessage());
                        break;
                    }
                    System.out.println("Reactor activat");
                    break;
                case DesactivarReactor:
                    // Permet desactivar el reactor.
                    dades.desactivaReactor();
                    System.out.println("Reactor desactivat");
                    break;
                case MostrarEstatReactor:
                    // Mostra si el reactor està activat i la seva temperatura.
                    System.out.println(dades.mostraReactor());
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
                    try {
                        sistema.activa();
                    } catch (CentralUBException e) {
                        System.out.println("Error Sistema: "+e.getMessage());
                    }
                    break;
                case DesactivarTotesBombes:
                    //Desactiva totes les bombes refrigerants
                    sistema.desactiva();
                    break;
                case ActivarBomba:
                    //Donat el seu identificador numèric (entre 0 i 3), permet activar una bomba refrigerant.
                    System.out.println("Introdueix la id d'una de les bombes 0-3");
                    try {
                        dades.activaBomba(sc.nextInt());
                    } catch (CentralUBException e) {
                        System.out.println("Error Sistema: "+e.getMessage());
                    }
                    break;
                case DesactivarBomba:
                    //Donat l’identificador numèric d’una bomba refrigerant, permet desactivar-la.
                    System.out.println("Introdueix la id d'una de les bombes 0-3");
                    dades.desactivaBomba(sc.nextInt());
                    break;
                case MostrarEstat:
                    // mostra l’estat actual de totes les bombes del sistema de refrigeració.
                    System.out.println(dades.mostraSistemaRefrigeracio());
                    break;
                case Sortir:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + op);
            }
        } while (op != OpcionsSistema.Sortir);

    }


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

}

