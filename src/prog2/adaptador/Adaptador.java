package prog2.adaptador;

import prog2.vista.CentralUBException;

public class Adaptador {
    private float demandaPotencia; // Atribut que manté la demanda de potència

    // Constructor per inicialitzar la demanda de potència
    public Adaptador() {
        this.demandaPotencia = demandaPotencia;
    }

    // Finalitzar el dia i retornar la informació necessària
    public String finalitzaDia(float demandaPotencia) {
        // Finalitzar el dia amb la demanda actual de potència
        String info = "Finalitzant el dia amb una demanda de potència de " + demandaPotencia + " unitats.\n";

        // Generar nova demanda de potència per al següent dia
        this.demandaPotencia = generaDemandaPotencia();
        info += "La nova demanda de potència per al proper dia és de " + this.demandaPotencia + " unitats.";

        return info; // Retorna tota la informació generada
    }

    // Mètode per generar la nova demanda de potència
    public float generaDemandaPotencia() {
        // Aquí pots aplicar alguna lògica per generar una nova demanda de potència
        // Per exemple, podries generar un valor aleatori dins d'un rang determinat
        return (float) (Math.random() * 100); // Exempli de generació aleatòria
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
