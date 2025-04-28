package prog2.adaptador;

import prog2.model.*;
import prog2.vista.CentralUB;
import prog2.vista.CentralUBException;
import java.io.FileReader;
import java.io.ObjectOutputStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


//La classe Adaptador, com ja s’ha explicat anteriorment, serà utilitzada per intervenir
//entre la vista i les dades del model. Per això, la classe Adaptador farà servir un
//atribut de la classe Dades, per ser la classe principal del nostre model.
//Un exemple de servei proporcionat per la classe Adaptador seria convertir la
//llista d’incidències retornada pel mètode mostraIncidencies de la classe Dades
//com a un únic objecte de tipus String on les incidències es mostren separades per
//salts de línia ('\n'). Això permet que la vista funcioni sense tenir coneixement dels
//detalls d’implementació del model.
public class Adaptador {
    private float demandaPotencia; // Atribut que manté la demanda de potència
    public Dades dades;

    // Constructor per inicialitzar la demanda de potència
    public Adaptador() {
        this.demandaPotencia = demandaPotencia;
        this.dades = new Dades();
    }

    public float getDemandaPotencia() {
        return demandaPotencia;
    }

    public void setDemandaPotencia(float demandaPotencia) {
        this.demandaPotencia = demandaPotencia;
    }

    public PaginaEstat mostraEstat() {
        return dades.mostraEstat();
    }

    public Bitacola mostraBitacola() {
        return dades.mostraBitacola();
    }

    public List<PaginaIncidencies> mostraIncidencies() {
        return dades.mostraIncidencies();
    }

    public float calculaPotencia() {
        return dades.calculaPotencia();
    }

    public float getInsercioBarres() {
        return dades.getInsercioBarres();
    }

    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        dades.setInsercioBarres(insercioBarres);
    }

    public void activaReactor() throws CentralUBException {
        dades.activaReactor();
    }

    public void activaBomba(int id) throws CentralUBException {
        dades.activaBomba(id);
    }

    public void desactivaBomba(int id) {
        dades.desactivaBomba(id);
    }

    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return dades.mostraSistemaRefrigeracio();
    }


    public Reactor mostraReactor() {
        return dades.mostraReactor();
    }

    public void desactivaReactor(){
        dades.desactivaReactor();
    }

    // Finalitzar el dia i retornar la informació necessària
    public String finalitzaDia(float demandaPotencia) {
        // Finalitzar el dia amb la demanda actual de potència
        String info = "Finalitzant el dia amb una demanda de potència de " + demandaPotencia + " unitats.\n";
        setDemandaPotencia(demandaPotencia);
        return info + dades.finalitzaDia(demandaPotencia);
    }



    public void guardaDades(String camiDesti) throws CentralUBException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(camiDesti))) {
            writer.write(dades.mostraBitacola().toString());
        } catch (Exception e) {
            throw new CentralUBException("Error guardant les dades: " + e.getMessage());
        }
    }

    // Mètode per carregar dades
    public void carregaDades(String camiOrigen) throws CentralUBException {
        try (BufferedReader reader = new BufferedReader(new FileReader(camiOrigen))) {
            StringBuilder contingut = new StringBuilder();
            String linia;
            while ((linia = reader.readLine()) != null) {
                contingut.append(linia).append("\n");
            }
        } catch (Exception e) {
            throw new CentralUBException("Error carregant les dades: " + e.getMessage());
        }
    }
}
