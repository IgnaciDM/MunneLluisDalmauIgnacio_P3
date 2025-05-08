package prog2.adaptador;

import prog2.model.*;
import prog2.vista.CentralUB;
import prog2.vista.CentralUBException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * La classe `Adaptador` actua com a intermediari entre la vista i el model de dades en el sistema de simulació de la central.
 * Aquesta classe proporciona serveis per obtenir informació de l'estat de la central, les incidències, el sistema de refrigeració,
 * entre altres, i per interactuar amb els components de la central com el reactor i les bombes.
 * També inclou mètodes per guardar i carregar dades, així com per gestionar l'activitat de la central.
 */
public class Adaptador {
    private float demandaPotencia; // Atribut que manté la demanda de potència
    public Dades dades;

    /**
     * Constructor de la classe `Adaptador`, inicialitza l'atribut `demandaPotencia` a 0
     * i crea un objecte de la classe `Dades`.
     */
    public Adaptador() {
        this.demandaPotencia = demandaPotencia;
        this.dades = new Dades();
    }

    /**
     * Retorna la demanda de potència actual.
     *
     * @return La demanda de potència actual.
     */
    public float getDemandaPotencia() {
        return demandaPotencia;
    }

    /**
     * Estableix la demanda de potència.
     *
     * @param demandaPotencia El valor de la demanda de potència a establir.
     */
    public void setDemandaPotencia(float demandaPotencia) {
        this.demandaPotencia = demandaPotencia;
    }

    /**
     * Mostra l'estat actual de la central.
     *
     * @return Un objecte de tipus `PaginaEstat` amb l'estat de la central.
     */
    public PaginaEstat mostraEstat() {
        System.out.println("Estat de la central:" + "\n");
        return dades.mostraEstat();
    }

    /**
     * Mostra la bitàcola de la central.
     *
     * @return Una cadena de text amb les pàgines de la bitàcola.
     */
    public String mostraBitacola() {
        String resposta = "Bitacola:" + "\n";
        Iterator<PaginaBitacola> it = dades.mostraBitacola().getPaginesbitacola().iterator();
        while (it.hasNext()) {
            resposta += it.next() + "\n";
        }
        return resposta;
    }

    /**
     * Mostra la llista d'incidències de la central.
     *
     * @return Una cadena de text amb les incidències.
     */
    public String mostraIncidencies() {
        String info = "Llista de Incidencies:" + "\n";
        Iterator<PaginaIncidencies> it = dades.mostraIncidencies().iterator();
        while (it.hasNext()) {
            info += it.next() + "\n";
        }
        return info;
    }

    /**
     * Calcula la potència actual de la central.
     *
     * @return La potència actual calculada.
     */
    public float calculaPotencia() {
        return dades.calculaPotencia();
    }

    /**
     * Obté el valor d'inserció de barres.
     *
     * @return El valor d'inserció de barres.
     */
    public float getInsercioBarres() {
        return dades.getInsercioBarres();
    }

    /**
     * Estableix el valor d'inserció de barres.
     *
     * @param insercioBarres El valor d'inserció de barres a establir.
     * @throws CentralUBException Si hi ha algun error en establir el valor.
     */
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        dades.setInsercioBarres(insercioBarres);
    }

    /**
     * Activa el reactor de la central.
     *
     * @throws CentralUBException Si hi ha un error en activar el reactor.
     */
    public void activaReactor() throws CentralUBException {
        dades.activaReactor();
    }

    /**
     * Activa una bomba específica de la central.
     *
     * @param id El identificador de la bomba a activar.
     * @throws CentralUBException Si hi ha un error en activar la bomba.
     */
    public void activaBomba(int id) throws CentralUBException {
        dades.activaBomba(id);
    }

    /**
     * Desactiva una bomba específica de la central.
     *
     * @param id El identificador de la bomba a desactivar.
     */
    public void desactivaBomba(int id) {
        dades.desactivaBomba(id);
    }

    /**
     * Mostra el sistema de refrigeració de la central.
     *
     * @return Un objecte de tipus `SistemaRefrigeracio` amb la informació del sistema de refrigeració.
     */
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return dades.mostraSistemaRefrigeracio();
    }

    /**
     * Mostra el reactor de la central.
     *
     * @return Un objecte de tipus `Reactor` amb la informació del reactor.
     */
    public Reactor mostraReactor() {
        return dades.mostraReactor();
    }

    /**
     * Desactiva el reactor de la central.
     */
    public void desactivaReactor() {
        dades.desactivaReactor();
    }

    /**
     * Finalitza el dia i registra la informació de la bitàcola.
     *
     * @param demandaPotencia La demanda de potència a finalitzar el dia.
     * @return Una cadena de text amb la informació de la bitàcola del dia.
     */
    public String finalitzaDia(float demandaPotencia) {
        // Finalitzar el dia amb la demanda actual de potència
        String info = "Finalitzant el dia amb una demanda de potència de " + demandaPotencia + " unitats.\n";
        System.out.println("La Bitocala d'aquest dia ha sigut:");
        setDemandaPotencia(demandaPotencia);
        Iterator<PaginaBitacola> it = dades.finalitzaDia(demandaPotencia).getPaginesbitacola().iterator();
        while (it.hasNext()) {
            info += it.next() + "\n";
        }
        return info;
    }

    /**
     * Desa les dades de la central en un fitxer especificat.
     *
     * @param camiDesti El camí al fitxer on es desaran les dades.
     * @throws CentralUBException Si hi ha un error durant el procés de desar les dades.
     */
    public void guardaDades(String camiDesti) throws CentralUBException {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            fout = new FileOutputStream(camiDesti);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(dades);
        } catch (IOException e) {
            throw new CentralUBException("Error guardant les dades: " + e.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                System.out.println("Error tancant els streams: " + e.getMessage());
            }
        }
        System.out.println("Dades guardades satisfactoriament a " + camiDesti);
    }

    /**
     * Carrega les dades de la central des d'un fitxer especificat.
     *
     * @param camiOrigen El camí al fitxer des del qual es carregaran les dades.
     * @throws CentralUBException Si hi ha un error durant el procés de càrrega de les dades.
     */
    public void carregaDades(String camiOrigen) throws CentralUBException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(camiOrigen))) {
            Object obj = ois.readObject();
            if (obj instanceof Dades) {
                this.dades = (Dades) obj;
            } else {
                throw new CentralUBException("El fitxer no conté un objecte de tipus Dades.");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new CentralUBException("Error carregant les dades: " + e.getMessage());
        }
        System.out.println("Dades carregades satisfactoriament de " + camiOrigen + " a la CentralUB");
    }
}
