/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;

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

    /* Constructor*/
    public CentralUB() {
        variableNormal = new prog2.vista.VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        
        // Afegir codi adicional si fos necessari:

    }
    
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");

        String[] llistaOpcions = {"Gestionar Barres Control","Gestionar Reactor", "Gestionar Sistema Refrigeració", "" +
                "Mostrar Estat Central", "Mostrar Bitacola", "Mostrar Incidencies", "Obtenir demanda satisfeta amb configuracio actual",
                "Finalitzar dia", "Guardar dades", "Carregar dades", "Sortir"};

        String[] llistaopcioBarres = {"Obtenir insercio Barres", "Establir Insercio Barres", "Sortir"};

        String[] llistaopcioReactor = {"Activar Reactor", "DEsactivar Reactor", "Mostrar Estat del Reactor", "Sortir"};

        String[] llistaopcioSistema = {"Activar Totes les bombes", "Desactivar totes les Bombes", "Activar Bomba", "Desactiva Bomba", "Mostrar Estat", "Sortir"};

        Menu principal = new Menu<>("Menu Principal", llistaOpcions);

        Menu Barres = new Menu<>("Menu Barres", llistaopcioBarres);

        Menu Reactor = new Menu<>("Menu Reactor", llistaopcioReactor);

        Menu Sistema = new Menu<>("Menu Sistema", llistaopcioSistema);

        while (true) {
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        principal.mostrarMenu();
        Scanner opcio = new Scanner(System.in);
        principal.getOpcio(opcio);
        if (opcio == 1) {

        }

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
