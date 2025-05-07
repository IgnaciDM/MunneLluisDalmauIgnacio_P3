package prog2.model;

/**
 * Representa una pàgina de la bitàcola amb l'estat dels components principals
 * d'una central nuclear en un dia concret.
 * Inclou informació sobre la inserció de barres de control, així com l'estat
 * dels subsistemes: reactor, sistema de refrigeració, generador de vapor i turbina.
 *
 * Hereta de la classe {@link PaginaBitacola}.
 *
 */
public class PaginaEstat extends PaginaBitacola {

    /**
     * Percentatge d'inserció de les barres de control (0-100%).
     */
    private float barres;

    /**
     * Estat del reactor actiu en aquest dia.
     */
    private Reactor reactorActiu;

    /**
     * Estat del sistema de refrigeració actiu.
     */
    private SistemaRefrigeracio sistemaRefrigeracioActiu;

    /**
     * Estat del generador de vapor actiu.
     */
    private GeneradorVapor generadorVaporActiu;

    /**
     * Estat de la turbina activa.
     */
    private Turbina turbinaActiva;

    /**
     * Crea una nova pàgina d'estat per un dia determinat.
     *
     * @param dia Dia registrat.
     * @param barres Percentatge d'inserció de les barres.
     * @param reactorActiu Estat del reactor.
     * @param sistemaRefrigeracioActiu Estat del sistema de refrigeració.
     * @param generadorVaporActiu Estat del generador de vapor.
     * @param turbinaActiva Estat de la turbina.
     */
    public PaginaEstat(int dia, float barres, Reactor reactorActiu,
                       SistemaRefrigeracio sistemaRefrigeracioActiu,
                       GeneradorVapor generadorVaporActiu,
                       Turbina turbinaActiva) {
        super(dia);
        this.barres = barres;
        this.reactorActiu = reactorActiu;
        this.sistemaRefrigeracioActiu = sistemaRefrigeracioActiu;
        this.generadorVaporActiu = generadorVaporActiu;
        this.turbinaActiva = turbinaActiva;
    }

    /**
     * Retorna el percentatge d'inserció de les barres de control.
     *
     * @return Percentatge d'inserció de barres.
     */
    public float getInserciobarres() {
        return barres;
    }

    /**
     * Retorna l'estat del reactor actiu.
     *
     * @return Reactor actiu.
     */
    public Reactor getReactorActiu() {
        return reactorActiu;
    }

    /**
     * Retorna l'estat del sistema de refrigeració actiu.
     *
     * @return Sistema de refrigeració actiu.
     */
    public SistemaRefrigeracio getSistemaRefrigeracioActiu() {
        return sistemaRefrigeracioActiu;
    }

    /**
     * Retorna l'estat del generador de vapor actiu.
     *
     * @return Generador de vapor actiu.
     */
    public GeneradorVapor getGeneradorVaporActiu() {
        return generadorVaporActiu;
    }

    /**
     * Retorna l'estat de la turbina activa.
     *
     * @return Turbina activa.
     */
    public Turbina getTurbinaActiva() {
        return turbinaActiva;
    }

    /**
     * Retorna una representació textual de la pàgina d'estat per mostrar a l'usuari.
     *
     * @return Cadena amb la informació detallada del dia.
     */
    @Override
    public String toString() {
        return "\n" + "--- Pàgina Estat (Dia " + getDia() + ") ---\n" +
                "Inserció Barres: " + barres + " %" + "\n" +
                "Output Reactor: " + reactorActiu + "\n" +
                "Output Sistema de Refrigeració: " + "\n" + sistemaRefrigeracioActiu + "\n" +
                "Output Generador de Vapor: " + generadorVaporActiu + "\n" +
                "Output Turbina: " + turbinaActiva + "\n";
    }
}

