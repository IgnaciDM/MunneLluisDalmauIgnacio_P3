package prog2.vista;

// Importació de llibreries de Swing per a crear interfícies gràfiques
import javax.swing.*;
import java.awt.*;

/**
 * Classe {@code FinalitzarDia} que representa una finestra modal (JDialog) mostrada al final del dia.
 * Aquesta finestra presenta un resum de la jornada en forma de bitàcola i genera automàticament
 * una nova demanda de potència per al següent dia.
 */
public class FinalitzarDia extends JDialog {

    /** Referència al model principal {@code CentralUB}, per accedir a dades i operacions del sistema. */
    private CentralUB centralUB;

    /** Àrea de text on es mostra la bitàcola del dia i la nova demanda. */
    private JTextArea textArea1;

    /** Panell principal del diàleg. */
    private JPanel contentPane;

    /**
     * Constructor de la classe {@code FinalitzarDia}.
     * Inicialitza i configura la interfície gràfica per mostrar la informació del final del dia.
     *
     * @param centralUB instància de {@code CentralUB} que proporciona accés a la lògica del sistema
     */
    public FinalitzarDia(CentralUB centralUB) {
        this.centralUB = centralUB;

        setModal(true); // Finestra modal que bloqueja altres finestres fins que es tanqui
        setSize(600, 500);
        setLocationRelativeTo(null); // Centra la finestra a la pantalla

        // Configuració del panell principal i àrea de text
        contentPane = new JPanel(new BorderLayout());
        textArea1 = new JTextArea();
        textArea1.setEditable(false); // Només lectura
        contentPane.add(new JScrollPane(textArea1), BorderLayout.CENTER);
        setContentPane(contentPane);

        // Execució del procés de finalització del dia
        FinalitzarDiaFi();
    }

    /**
     * Mètode que s'encarrega de finalitzar el dia.
     * Recupera la bitàcola d'activitats i mostra una nova demanda de potència generada aleatòriament.
     */
    public void FinalitzarDiaFi() {
        // Obté la bitàcola del dia
        String bitacola = centralUB.getAdaptador().getBitacolaDia().toString();

        // Genera una nova demanda de potència per al dia següent
        float novaDemanda = centralUB.generaDemandaPotencia();

        // Mostra la informació recollida a l'àrea de text
        textArea1.setText(
                "\n\n📝 Bitàcola del dia:\n" + bitacola +
                        "\n\n⚡ Nova demanda de potència generada per demà: " + novaDemanda + " MW"
        );
    }
}


