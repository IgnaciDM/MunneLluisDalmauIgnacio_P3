package prog2.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Aquesta classe modela una variable aleatòria uniforme. Es fa servir per determinar de manera aleatòria si una bomba refrigerant
 * estarà fora de servei al començar un nou dia. La classe `VariableUniforme` genera números aleatoris entre 0 i 99 mitjançant
 * l'objecte {@link Random} i el mètode `nextInt(100)`.
 *
 * El seu ús principal és en la classe {@link BombaRefrigerant}, on es passa un objecte `VariableUniforme` com a paràmetre per
 * determinar si una bomba quedarà fora de servei per un valor aleatori generat a través del mètode `seguentValor()`.
 *
 * @author Daniel Ortiz
 */
public class VariableUniforme implements Serializable {

    /**
     * Objecte Random utilitzat per generar valors aleatoris entre 0 i 99.
     */
    private Random random;

    /**
     * Constructor que inicialitza l'objecte `Random` amb una llavor proporcionada per assegurar la reproduïbilitat.
     *
     * @param seed Llavor per a la generació de nombres aleatoris, garantint que la seqüència de números generats sigui
     *             la mateixa per a una mateixa llavor.
     */
    public VariableUniforme(long seed) {
        this.random = new Random(seed);
    }

    /**
     * Genera un nombre aleatori entre 0 i 99 (incloent 0, però excloent 100).
     * Aquest nombre s'utilitza per determinar si la bomba refrigerant estarà fora de servei.
     *
     * @return Un valor enter aleatori entre 0 i 99.
     */
    public int seguentValor() {
        return random.nextInt(100);
    }
}
