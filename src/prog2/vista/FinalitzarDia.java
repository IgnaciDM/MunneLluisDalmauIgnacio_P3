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
        // Finalitzar el dia i obtenir la bitàcola
        String bitacola = centralUB.getAdaptador().getBitacolaDia().toString();
        float novaDemanda = centralUB.generaDemandaPotencia();

        textArea1.setText(
                        "\n\n📝 Bitàcola del dia:\n" + bitacola +
                        "\n\n⚡ Nova demanda de potència generada per demà: " + novaDemanda + " MW"
        );
    }
}
