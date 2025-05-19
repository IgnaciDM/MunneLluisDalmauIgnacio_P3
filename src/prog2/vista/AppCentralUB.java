package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame {

    private JPanel panelCentral;
    private JButton gestioComponentsCentralButton;
    private JButton visualitzarInformacióDeLaButton;
    private JButton finalitzarDiaButton;
    private JButton guardarICarregarDadesButton;

    public AppCentralUB() {
        setTitle("CentralUB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelCentral);
        setSize(500, 400);
        setLocationRelativeTo(null);
        gestioComponentsCentralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Components_Central componentsCentral = new Components_Central();
                componentsCentral.setVisible(true);
            }
        });
        visualitzarInformacióDeLaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        finalitzarDiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        guardarICarregarDadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
