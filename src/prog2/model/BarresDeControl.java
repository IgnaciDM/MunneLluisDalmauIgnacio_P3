package prog2.model;
//Barres de control: es fan servir per controlar la intensitat de la reacció
//nuclear. Per a això, es poden inserir dins del combustible. Per exemple,
//1Una representació una mica més detallada i amb una animació es pot trobar en aquest enllaç.
//si s’insereixen al 100%, la reacció nuclear s’atura. En canvi, si s’extreuen
//completament (es a dir, el grau d’inserció és del 0%), la reacció es produeix
//amb la màxima intensitat

import prog2.vista.CentralUBException;

public class BarresDeControl {
    int grauInsercio;
    boolean activat;

    public void activa() {
        this.activat = true;
    }

    public void deactiva() {
        this.activat = false;
    }

    public boolean getActivat() {
        return this.activat;
    }

    public int getGrauInsercio() {
        return grauInsercio;
    }

    public void setGrauInsercio(int grauInsercio) throws CentralUBException {
        if (grauInsercio >= 0 && grauInsercio <= 100) {
            this.grauInsercio = grauInsercio;
        } else{
            throw new CentralUBException("El grau d'insercio no es correcta");
        }
    }

    public float getCostOperatiu() {
        if (activat) {
            return 5;
        } else {
            return 0;
        }
    }
}
