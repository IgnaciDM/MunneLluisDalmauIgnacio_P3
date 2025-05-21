package prog2.vista;

import javax.swing.*;
import java.awt.*;

public class FinalitzarDia extends JDialog {
    private CentralUB centralUB;
    private JTextArea textArea1;
    private JPanel contentPane;



    public FinalitzarDia(CentralUB centralUB) {
        this.centralUB = centralUB;
        setModal(true);
        setContentPane(contentPane);
        setSize(600, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout());
        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        contentPane.add(new JScrollPane(textArea1), BorderLayout.CENTER);
        setContentPane(contentPane);
        FinalitzarDiaFi();
    }
    public void FinalitzarDiaFi(){
        // Obtenir estat actual i demanda
        String estat = centralUB.getAdaptador().mostraEstat().toString();
        float DemandaActual = centralUB.getAdaptador().getDemandaPotencia();

        // Finalitzar el dia i obtenir la bitàcola
        String bitacola = centralUB.getAdaptador().finalitzaDia(DemandaActual);
        float novaDemanda = centralUB.getAdaptador().getDemandaPotencia();

        textArea1.setText(
                "📘 Estat actualitzat de la central:\n" + estat +
                        "\n\n📝 Bitàcola del dia:\n" + bitacola +
                        "\n\n⚡ Nova demanda de potència generada per demà: " + novaDemanda + " MW"
        );
    }
}
