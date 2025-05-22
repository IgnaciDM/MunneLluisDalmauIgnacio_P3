// Paquet on es troba aquesta classe
package prog2.vista;

// Importació de llibreries per crear interfícies gràfiques (Swing)
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame { // Classe principal de la vista gràfica, hereta de JFrame (finestra)

    CentralUB centralUB = new CentralUB(); // Instància del model CentralUB
    private JPanel panelCentral; // Panell principal on s'afegeixen els components
    private JButton gestioComponentsCentralButton; // Botó per gestionar els components de la central
    private JButton visualitzarInformacióDeLaButton; // Botó per visualitzar informació de la central
    private JButton finalitzarDiaButton; // Botó per finalitzar el dia
    private JButton guardarICarregarDadesButton; // Botó per guardar o carregar dades
    private JTextArea textAreaDies; // Àrea de text per mostrar informació del dia, potència i guanys

    public AppCentralUB() { // Constructor de la vista
        setTitle("CentralUB"); // Títol de la finestra
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Tanca l'aplicació quan tanques la finestra
        setContentPane(panelCentral); // Estableix el panell principal com a contingut de la finestra
        setSize(500, 400); // Mida de la finestra
        setLocationRelativeTo(null); // Centra la finestra a la pantalla

        // Inicialitza el text mostrant dades del dia actual
        textAreaDies.setText(
                "Dia: " + centralUB.getDia() + "\n" +
                        "Demanda de Potencia: " + centralUB.getDemandaPotencia() + "\n" +
                        "Guanys Acumulats: " + centralUB.getAdaptador().getGuanysAcumulats()
        );

        // Acció per obrir la finestra de gestió de components
        gestioComponentsCentralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Components_Central componentsCentral = new Components_Central(centralUB);
                componentsCentral.setVisible(true); // Mostra la nova finestra
            }
        });

        // Acció per visualitzar dades de la central
        visualitzarInformacióDeLaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualitzarDades visualitzarDades = new VisualitzarDades(centralUB);
                visualitzarDades.setVisible(true);
            }
        });

        // Acció per finalitzar el dia i actualitzar la informació
        finalitzarDiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralUB.finalitzaDia(); // Crida al mètode que avança el dia al model
                FinalitzarDia finalitzarDia = new FinalitzarDia(centralUB);
                finalitzarDia.setVisible(true); // Mostra la finestra relacionada amb el final del dia

                // Actualitza la informació de l'àrea de text
                textAreaDies.setText(
                        "Dia: " + centralUB.getDia() + "\n" +
                                "Demanda de Potencia: " + centralUB.getDemandaPotencia() + "\n" +
                                "Guanys Acumulats: " + centralUB.getAdaptador().getGuanysAcumulats()
                );
            }
        });

        // Acció per obrir la finestra de guardar o carregar dades
        guardarICarregarDadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuardarCarregarDades guardarCarregarDades = new GuardarCarregarDades(centralUB);
                guardarCarregarDades.setVisible(true);
            }
        });
    }

    // Mètode main: punt d'entrada de l'aplicació
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Assegura que la UI s’executi al fil de Swing
            AppCentralUB app = new AppCentralUB(); // Crea una instància de la finestra principal
            app.setVisible(true); // Fa visible la finestra
        });
    }
}

