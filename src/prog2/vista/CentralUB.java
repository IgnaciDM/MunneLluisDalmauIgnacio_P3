/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.Reactor;

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
        //demandaPotencia = generaDemandaPotencia();
        
        // Afegir codi adicional si fos necessari:

    }
    
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        Scanner sc = new Scanner(System.in);


        Menu<OpcionsMenu> principal = new Menu("Menu Principal", OpcionsMenu.values());

        OpcionsMenu op = null;

        do {
            System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
            principal.mostrarMenu();
            op = principal.getOpcio(sc);
            switch (op) {
                case GestionarBarresControl:
                    gestioBarres(sc);
                    break;
                case GestionarReactor:
                    gestioReactor(sc);
                    break;
                case GestionarSistemaRefrigeracio:
                    gestioSistema(sc);
                    break;
                case MostrarEstatCentral:

                    break;
                case MostrarBitacola:

                    break;
                case MostrarIncidencies:

                    break;
                case ObtenirDemandaSatisfeta:

                    break;
                case FinalitzarDia:

                    break;
                case GuardarDades:

                    break;
                case CarregarDades:

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

                    break;
                case EstablirInsercioBarres:

                    break;
                case Sortir:

                    break;
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

                    break;
                case DesactivarReactor:

                    break;
                case MostrarEstatReactor:

                    break;
                case Sortir:

                    break;
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

                    break;
                case DesactivarTotesBombes:

                    break;
                case ActivarBomba:

                    break;
                case DesactivarBomba:

                    break;
                case MostrarEstat:

                    break;
                case Sortir:

                    break;
            }
        } while (op != OpcionsSistema.Sortir);

    }
    /*
            ActivarTotesBombes,
        DesactivarTotesBombes,
        ActivarBomba,
        DesactivarBomba,
        MostrarEstat,
        Sortir
    public float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX) {
            return DEMANDA_MAX;
        }
        else {
            if (valor < DEMANDA_MIN) {
                return DEMANDA_MIN;
            }else{
                return valor;
                }
        }
    }

     */
    /*
    public void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        String info = new String();
        info = adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }

     */
}

