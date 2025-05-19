/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import java.io.Serializable;
import java.util.Random;

/**
 * Aquesta classe modela una variable aleatòria de distribució normal (gaussiana) amb mitjana i desviació estàndard específiques.
 * Genera valors aleatoris basats en la distribució normal utilitzant la llavor proporcionada.
 *
 * La classe utilitza l'objecte {@link Random} per generar els valors aleatoris mitjançant el mètode `nextGaussian()`,
 * que retorna un valor aleatori amb distribució normal estàndard (mitjana 0 i desviació estàndard 1).
 * Els valors generats es transformaran a la distribució normal definida pels paràmetres `mean` i `standardDeviation`.
 *
 * @author Daniel Ortiz
 */
public class VariableNormal implements Serializable {

    /**
     * Objecte Random utilitzat per generar valors aleatoris.
     */
    private Random random;

    /**
     * Mitjana de la distribució normal.
     */
    private float mean;

    /**
     * Desviació estàndard de la distribució normal.
     */
    private float standardDeviation;

    /**
     * Constructor que inicialitza els paràmetres de la variable normal amb una mitjana, desviació estàndard i una llavor per a la generació de nombres aleatoris.
     *
     * @param mean Mitjana de la distribució normal.
     * @param standardDeviation Desviació estàndard de la distribució normal.
     * @param seed Llavor per a la generació de nombres aleatoris, assegurant la reproduïbilitat.
     */
    public VariableNormal(float mean, float standardDeviation, long seed) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.random = new Random(seed);
    }

    /**
     * Genera i retorna el següent valor aleatori basat en la distribució normal definida per la mitjana i desviació estàndard.
     *
     * @return Un valor flotant aleatori que segueix la distribució normal definida per `mean` i `standardDeviation`.
     */
    public float seguentValor() {
        return (float) mean + standardDeviation * (float) random.nextGaussian();
    }
}
