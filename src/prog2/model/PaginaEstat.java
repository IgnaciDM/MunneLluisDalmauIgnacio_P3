package prog2.model;


public class PaginaEstat extends PaginaBitacola {
    private float inserciobarres;
    private Reactor reactorActiu;
    private SistemaRefrigeracio sistemaRefrigeracioActiu;
    private GeneradorVapor generadorVaporActiu;
    private Turbina turbinaActiva;

    public PaginaEstat(int dia, float inserciobarres, Reactor reactorActiu, SistemaRefrigeracio sistemaRefrigeracioActiu, GeneradorVapor generadorVaporActiu, Turbina turbinaActiva) {
        super(dia);
        this.inserciobarres = inserciobarres;
        this.reactorActiu = reactorActiu;
        this.sistemaRefrigeracioActiu = sistemaRefrigeracioActiu;
        this.generadorVaporActiu = generadorVaporActiu;
        this.turbinaActiva = turbinaActiva;

    }

    public float getInserciobarres() {
        return inserciobarres;
    }

    public Reactor getReactorActiu() {
        return reactorActiu;
    }

    public SistemaRefrigeracio getSistemaRefrigeracioActiu() {
        return sistemaRefrigeracioActiu;
    }

    public GeneradorVapor getGeneradorVaporActiu() {
        return generadorVaporActiu;
    }

    public Turbina getTurbinaActiva() {
        return turbinaActiva;
    }

    public String toString() {
        return "\n" + "--- Pàgina Estat (Dia " + getDia() + ") ---\n" +
                "Inserció Barres: " + inserciobarres + " %" + "\n" +
                "Output Reactor: " + reactorActiu + "\n" +
                "Output Sistema de Refrigeració: " + "\n" + sistemaRefrigeracioActiu + "\n" +
                "Output Generador de Vapor: " + generadorVaporActiu + "\n" +
                "Output Turbina: " + turbinaActiva + "\n";
    }
}
