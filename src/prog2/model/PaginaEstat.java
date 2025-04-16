package prog2.model;


public class PaginaEstat extends PaginaBitacola {
    private float temperatura;
    private boolean reactorActiu;
    private boolean sistemaRefrigeracioActiu;
    private boolean generadorVaporActiu;
    private boolean turbinaActiva;

    public PaginaEstat(int dia, float temperatura,
                       boolean reactorActiu,
                       boolean sistemaRefrigeracioActiu,
                       boolean generadorVaporActiu,
                       boolean turbinaActiva) {
        super(dia);
        this.temperatura = temperatura;
        this.reactorActiu = reactorActiu;
        this.sistemaRefrigeracioActiu = sistemaRefrigeracioActiu;
        this.generadorVaporActiu = generadorVaporActiu;
        this.turbinaActiva = turbinaActiva;
    }

    // Getters, toString, etc.
}
