package prog2.adaptador;

import prog2.vista.CentralUBException;

public class Adaptador {
    private float demandaPotencia; // Atribut que manté la demanda de potència

    // Constructor per inicialitzar la demanda de potència
    public Adaptador() {
        this.demandaPotencia = demandaPotencia;
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



    // Mètode per guardar dades
    public void guardaDades(String camiDesti) throws CentralUBException {
        try {
            // Lògica per guardar dades del model
            // Es podria serialitzar el model o realitzar altres operacions
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
