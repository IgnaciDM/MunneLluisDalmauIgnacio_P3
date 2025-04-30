package prog2.adaptador;

import prog2.model.*;
import prog2.vista.CentralUB;
import prog2.vista.CentralUBException;

import java.io.*;

import java.util.ArrayList;
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
        System.out.println("Estat de la central:" + "\n");
        return dades.mostraEstat();
    }

    public String mostraBitacola() {
        String resposta = "Bitacola:" + "\n";
        for (int i = 0; i<dades.mostraBitacola().getPaginesbitacola().size(); i++) {
            resposta += dades.mostraBitacola().getPaginesbitacola().get(i) + "\n";
        }
        return resposta;
    }

    public String mostraIncidencies() {
        List<PaginaIncidencies> llistaIncidencies = dades.mostraIncidencies();
        String info = "Llista de Incidencies:" + "\n";
        for (int i = 0; i<llistaIncidencies.size(); i++) {
            info += llistaIncidencies.get(i) + "\n";
        }
        return info;
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
        System.out.println("La Bitocala d'aquest dia ha sigut:");
        setDemandaPotencia(demandaPotencia);
        Bitacola BitacolaDia = dades.finalitzaDia(demandaPotencia);
        for (int i = 0; i<BitacolaDia.getPaginesbitacola().size(); i++) {
            info += BitacolaDia.getPaginesbitacola().get(i) + "\n";
        }
        return info;
    }



    public void guardaDades(String camiDesti) throws CentralUBException {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            // Obrir el camiDesti
            fout = new FileOutputStream(camiDesti);
            oos = new ObjectOutputStream(fout);

            // Escribir el objeto 'dades' al archivo
            oos.writeObject(dades);
        } catch (IOException e) {
            throw new CentralUBException("Error guardant las dades: " + e.getMessage());
        } finally {
            //Tancant els streams
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
        System.out.println("Dades guardades satisfactoriament a "+camiDesti);
    }


    // Mètode per carregar dades
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
        System.out.println("Dades carregades satisfactoriament de "+camiOrigen+" a la CentralUB");
    }
}


