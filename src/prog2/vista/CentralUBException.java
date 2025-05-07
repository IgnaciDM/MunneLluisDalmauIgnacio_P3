package prog2.vista;

/**
 * Excepció personalitzada per gestionar errors específics en el context de la central elèctrica.
 * Aquesta classe estén la classe `Exception` i es fa servir per llançar errors relacionats amb
 * el funcionament de la central o operacions incorrectes.
 *
 * La classe disposa de dos constructors: un per defecte (sense missatge) i un altre que accepta
 * un missatge d'error personalitzat per proporcionar més informació sobre l'error produït.
 */
public class CentralUBException extends Exception {

    /**
     * Constructor per defecte de la classe `CentralUBException`.
     * Utilitza el constructor de la classe pare `Exception` sense proporcionar un missatge d'error.
     */
    public CentralUBException() {
        super();
    }

    /**
     * Constructor de la classe `CentralUBException` que accepta un missatge d'error personalitzat.
     * Aquest missatge es passarà al constructor de la classe pare `Exception`.
     *
     * @param message El missatge d'error que proporciona informació sobre l'excepció.
     */
    public CentralUBException(String message) {
        super(message);
    }
}
