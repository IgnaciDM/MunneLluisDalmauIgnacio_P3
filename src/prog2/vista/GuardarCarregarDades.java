package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GuardarCarregarDades extends JDialog {
    CentralUB centralUB;
    private JPanel contentPane;
    private JPanel panelVisualitzacio;
    private JButton ButtonCarregarDades;
    private JButton ButtonGuardarDades;

    public GuardarCarregarDades(CentralUB centralUB) {
        this.centralUB = centralUB;
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        setSize(400, 200);

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
