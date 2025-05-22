package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Diàleg per guardar o carregar les dades de la central des d’un fitxer.
 * Permet seleccionar un fitxer per desar les dades actuals o carregar-ne de noves.
 */
public class GuardarCarregarDades extends JDialog {

    /**
     * Referència a l’objecte CentralUB que conté les dades a guardar o carregar.
     */
    CentralUB centralUB;

    /**
     * Panell principal del diàleg.
     */
    private JPanel contentPane;

    /**
     * Panell (no utilitzat explícitament en aquest fragment) destinat a la visualització o organització de components.
     */
    private JPanel panelVisualitzacio;

    /**
     * Botó per carregar dades des d’un fitxer.
     */
    private JButton ButtonCarregarDades;

    /**
     * Botó per guardar les dades en un fitxer.
     */
    private JButton ButtonGuardarDades;

    /**
     * Constructor del diàleg. Inicialitza la finestra i defineix el comportament
     * dels botons per carregar i guardar dades.
     *
     * @param centralUB instància de CentralUB sobre la qual es realitzen les operacions.
     */
    public GuardarCarregarDades(CentralUB centralUB) {
        this.centralUB = centralUB;
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        setSize(400, 200);

        // Listener del botó per guardar dades
        ButtonGuardarDades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showSaveDialog(GuardarCarregarDades.this);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File fitxer = fileChooser.getSelectedFile();
                    try {
                        centralUB.getAdaptador().guardaDades(fitxer.getAbsolutePath());
                        JOptionPane.showMessageDialog(null, "Dades desades correctament.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error en desar dades: " + ex.getMessage());
                    }
                }
            }
        });

        // Listener del botó per carregar dades
        ButtonCarregarDades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showOpenDialog(GuardarCarregarDades.this);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File fitxer = fileChooser.getSelectedFile();
                    try {
                        centralUB.getAdaptador().carregaDades(fitxer.getAbsolutePath());
                        JOptionPane.showMessageDialog(null, "Dades carregades correctament.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error en carregar dades: " + ex.getMessage());
                    }
                }
            }
        });
    }
}
