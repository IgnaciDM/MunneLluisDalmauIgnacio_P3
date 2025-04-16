package prog2.model;

public class PaginaIncidencies extends PaginaBitacola {
    private String textIncidencia;

    public PaginaIncidencies(int dia, String textIncidencia) {
        super(dia);
        this.textIncidencia = textIncidencia;
    }

    public void afegeixIncidencia(String descIncidencia) {

    }

    public String toString() {
        return  "Dia: " + getDia() + "\n" + textIncidencia +
                "- Descripció Incidència: El reactor es va desactivar per superar la temperatura màxima" + "\n" +
                "- Descripció Incidència: La bomba refrig. 2 està fora de servei";
    }
}
