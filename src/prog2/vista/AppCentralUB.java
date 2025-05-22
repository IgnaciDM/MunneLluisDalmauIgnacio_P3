package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AppCentralUB extends JFrame {

    CentralUB centralUB = new CentralUB();
    private JPanel panelCentral;
    private JButton gestioComponentsCentralButton;
    private JButton visualitzarInformacióDeLaButton;
    private JButton finalitzarDiaButton;
    private JButton guardarICarregarDadesButton;
    private JTextArea textAreaDies;

    public AppCentralUB() {
        setTitle("CentralUB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelCentral);
        setSize(500, 400);
        setLocationRelativeTo(null);
        textAreaDies.setText(
                "Dia: " + centralUB.getDia() + "\n" +
                "Demanda de Potencia: " + centralUB.getDemandaPotencia() + "\n" +
                "Guanys Acumulats: " + centralUB.getAdaptador().getGuanysAcumulats()
        );
        gestioComponentsCentralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Components_Central componentsCentral = new Components_Central(centralUB);
                componentsCentral.setVisible(true);
            }
        });
        visualitzarInformacióDeLaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualitzarDades visualitzarDades = new VisualitzarDades(centralUB);
                visualitzarDades.setVisible(true);
            }
        });
        finalitzarDiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralUB.finalitzaDia();
                FinalitzarDia finalitzarDia = new FinalitzarDia(centralUB);
                finalitzarDia.setVisible(true);
                textAreaDies.setText(
                                "Dia: " + centralUB.getDia() + "\n" +
                                "Demanda de Potencia: " + centralUB.getDemandaPotencia() + "\n" +
                                "Guanys Acumulats: " + centralUB.getAdaptador().getGuanysAcumulats()
                );
            }
        });
        guardarICarregarDadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuardarCarregarDades guardarCarregarDades = new GuardarCarregarDades(centralUB);
                guardarCarregarDades.setVisible(true);
            }
        });
        textAreaDies.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB app = new AppCentralUB();
            app.setVisible(true);
        });
    }
}
