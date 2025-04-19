package prog2.model;


public class PaginaEstat extends PaginaBitacola {
    private int inserciobarres;
    private Reactor reactorActiu;
    private SistemaRefrigeracio sistemaRefrigeracioActiu;
    private GeneradorVapor generadorVaporActiu;
    private Turbina turbinaActiva;

    public PaginaEstat(int dia, int inserciobarres,
                       Reactor reactorActiu,
                       SistemaRefrigeracio sistemaRefrigeracioActiu,
                       GeneradorVapor generadorVaporActiu,
                       Turbina turbinaActiva) {
        super(dia);
        this.inserciobarres = inserciobarres;
        this.reactorActiu = reactorActiu;
        this.sistemaRefrigeracioActiu = sistemaRefrigeracioActiu;
        this.generadorVaporActiu = generadorVaporActiu;
        this.turbinaActiva = turbinaActiva;
    }

    public int getInserciobarres() {return inserciobarres;}

    public Reactor getReactorActiu() {return reactorActiu;}

    public SistemaRefrigeracio getSistemaRefrigeracioActiu() {return sistemaRefrigeracioActiu;}

    public GeneradorVapor getGeneradorVaporActiu() {return generadorVaporActiu;}

    public Turbina getTurbinaActiva() {return turbinaActiva;}

    public String toString() {
        return " Pàgina Estat" + "\n" +
                "- Dia: " + getDia() + "\n" +
                "- Inserció Barres: " + inserciobarres + " %" + "\n" +
                "- Output Reactor: " + reactorActiu + " Graus" + "\n" +
                "- Output Sistema de Refrigeració: " + sistemaRefrigeracioActiu + " Graus" + "\n" +
                "- Output Generador de Vapor: " + generadorVaporActiu + " Graus" + "\n" +
                "- Output Turbina: " + turbinaActiva + " Graus";
    }
}
