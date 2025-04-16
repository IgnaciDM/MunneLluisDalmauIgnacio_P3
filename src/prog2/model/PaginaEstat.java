package prog2.model;


public class PaginaEstat extends PaginaBitacola {
    private float inserciobarres;
    private float reactorActiu;
    private float sistemaRefrigeracioActiu;
    private float generadorVaporActiu;
    private float turbinaActiva;

    public PaginaEstat(int dia, float inserciobarres,
                       float reactorActiu,
                       float sistemaRefrigeracioActiu,
                       float generadorVaporActiu,
                       float turbinaActiva) {
        super(dia);
        this.inserciobarres = inserciobarres;
        this.reactorActiu = reactorActiu;
        this.sistemaRefrigeracioActiu = sistemaRefrigeracioActiu;
        this.generadorVaporActiu = generadorVaporActiu;
        this.turbinaActiva = turbinaActiva;
    }

    public float getInserciobarres() {return inserciobarres;}

    public float getReactorActiu() {return reactorActiu;}

    public float getSistemaRefrigeracioActiu() {return sistemaRefrigeracioActiu;}

    public float getGeneradorVaporActiu() {return generadorVaporActiu;}

    public float getTurbinaActiva() {return turbinaActiva;}

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
