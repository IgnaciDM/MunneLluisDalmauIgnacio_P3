package prog2.vista;

// Importació de llibreries de Swing per a crear interfícies gràfiques
import javax.swing.*;
import java.awt.*;

/**
 * Aquesta classe representa una finestra (JDialog) que es mostra al final del dia.
 * Mostra la bitàcola d'activitat i genera una nova demanda de potència.
 */
public class FinalitzarDia extends JDialog {
    private CentralUB centralUB;        // Referència a l'objecte principal de la central
    private JTextArea textArea1;        // Àrea de text on es mostra la informació
    private JPanel contentPane;         // Panell principal del diàleg

    /**
     * Constructor de la classe FinalitzarDia.
     * Inicialitza la finestra i els components gràfics.
     *
     * @param centralUB objecte CentralUB per accedir a dades del sistema
     */
    public FinalitzarDia(CentralUB centralUB) {
        this.centralUB = centralUB;
        setModal(true); // Finestra modal: bloqueja la interacció amb altres finestres fins que es tanqui
        setContentPane(contentPane);
        setSize(600, 500);
        setLocationRelativeTo(null); // Centra la finestra a la pantalla

        // Creació i configuració del panell principal i àrea de text
        contentPane = new JPanel(new BorderLayout());
        textArea1 = new JTextArea();
        textArea1.setEditable(false); // L'usuari no pot editar el contingut
        contentPane.add(new JScrollPane(textArea1), BorderLayout.CENTER); // S'afegeix un scroll per llegir textos llargs
        setContentPane(contentPane);

        // Crida a la funció per mostrar la informació final del dia
        FinalitzarDiaFi();
    }

    /**
     * Mètode que finalitza el dia, mostra la bitàcola i genera una nova demanda de potència.
     */
    public void FinalitzarDiaFi() {
        // Obtenim la bitàcola del dia com a cadena de text
        String bitacola = centralUB.getAdaptador().getBitacolaDia().toString();

        // Generem la nova demanda de potència per al dia següent
        float novaDemanda = centralUB.generaDemandaPotencia();

        // Es mostra la informació a l'àrea de text
        textArea1.setText(
                "\n\n📝 Bitàcola del dia:\n" + bitacola +
                        "\n\n⚡ Nova demanda de potència generada per demà: " + novaDemanda + " MW"
        );
    }
}

