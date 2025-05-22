package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe principal de la interfície gràfica de l'aplicació CentralUB.
 * Aquesta classe hereta de {@link JFrame} i mostra una finestra amb opcions per gestionar i visualitzar
 * el funcionament d'una central nuclear simulada.
 */
public class AppCentralUB extends JFrame {

    /** Instància del model principal CentralUB */
    CentralUB centralUB = new CentralUB();

    /** Panell principal on s'afegiran els components gràfics */
    private JPanel panelCentral;

    /** Botó per gestionar els components de la central */
    private JButton gestioComponentsCentralButton;

    /** Botó per visualitzar la informació del funcionament de la central */
    private JButton visualitzarInformacióDeLaButton;

    /** Botó per finalitzar el dia de simulació */
    private JButton finalitzarDiaButton;

    /** Botó per guardar o carregar les dades de la simulació */
    private JButton guardarICarregarDadesButton;

    /** Àrea de text per mostrar el dia actual, demanda de potència i guanys acumulats */
    private JTextArea textAreaDies;

    /**
     * Constructor de la finestra principal.
     * Configura la finestra i assigna funcionalitats als botons.
     */
    public AppCentralUB() {
        setTitle("CentralUB"); // Defineix el títol de la finestra
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Tanca l'aplicació en tancar la finestra
        setContentPane(panelCentral); // Estableix el panell principal com a contingut
        setSize(500, 400); // Defineix la mida de la finestra
        setLocationRelativeTo(null); // Centra la finestra a la pantalla

        // Inicialitza el text mostrant les dades del dia actual
        textAreaDies.setText(
                "Dia: " + centralUB.getDia() + "\n" +
                        "Demanda de Potencia: " + centralUB.getDemandaPotencia() + "\n" +
                        "Guanys Acumulats: " + centralUB.getAdaptador().getGuanysAcumulats()
        );

        // Acció del botó per gestionar els components de la central
        gestioComponentsCentralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Components_Central componentsCentral = new Components_Central(centralUB);
                componentsCentral.setVisible(true);
            }
        });

        // Acció del botó per visualitzar informació detallada de la central
        visualitzarInformacióDeLaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualitzarDades visualitzarDades = new VisualitzarDades(centralUB);
                visualitzarDades.setVisible(true);
            }
        });

        // Acció del botó per finalitzar el dia i actualitzar la informació
        finalitzarDiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralUB.finalitzaDia(); // Avança al següent dia
                FinalitzarDia finalitzarDia = new FinalitzarDia(centralUB);
                finalitzarDia.setVisible(true);

                // Actualitza el panell d'estat amb les noves dades
                textAreaDies.setText(
                        "Dia: " + centralUB.getDia() + "\n" +
                                "Demanda de Potencia: " + centralUB.getDemandaPotencia() + "\n" +
                                "Guanys Acumulats: " + centralUB.getAdaptador().getGuanysAcumulats()
                );
            }
        });

        // Acció del botó per obrir la finestra de guardar/carregar dades
        guardarICarregarDadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuardarCarregarDades guardarCarregarDades = new GuardarCarregarDades(centralUB);
                guardarCarregarDades.setVisible(true);
            }
        });
    }

    /**
     * Mètode principal (main) de l'aplicació.
     * Inicia la interfície gràfica en el fil de Swing.
     *
     * @param args Arguments de la línia de comandes (no utilitzats)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB app = new AppCentralUB();
            app.setVisible(true);
        });
    }
}


