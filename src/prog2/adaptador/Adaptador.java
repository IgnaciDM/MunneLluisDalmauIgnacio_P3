package prog2.adaptador;

import prog2.model.Bitacola;
import prog2.model.Dades;
import prog2.model.PaginaBitacola;
import prog2.vista.CentralUB;
import prog2.vista.CentralUBException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



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
    public Bitacola bitacola;

    // Constructor per inicialitzar la demanda de potència
    public Adaptador() {
        this.demandaPotencia = demandaPotencia;
        this.bitacola = new Bitacola();
    }

    public float getDemandaPotencia() {
        return demandaPotencia;
    }

    public void setDemandaPotencia(float demandaPotencia) {
        this.demandaPotencia = demandaPotencia;
    }

    // Finalitzar el dia i retornar la informació necessària
    public String finalitzaDia(float demandaPotencia) {
        // Finalitzar el dia amb la demanda actual de potència
        String info = "Finalitzant el dia amb una demanda de potència de " + demandaPotencia + " unitats.\n";
        setDemandaPotencia(demandaPotencia);
        return info; // Retorna tota la informació generada
    }



    public void guardaDades(String camiDesti) throws CentralUBException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(camiDesti))) {
            writer.write(bitacola.toString());
        } catch (Exception e) {
            throw new CentralUBException("Error guardant les dades: " + e.getMessage());
        }
    }

    // Mètode per carregar dades
    public void carregaDades(String camiOrigen) throws CentralUBException {
        try {
            // Lògica per carregar les dades del model
        } catch (Exception e) {
            throw new CentralUBException("Error carregant les dades: " + e.getMessage());
        }
    }
}
