package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualitzarDades extends JDialog {
    CentralUB centralUB;
    private JPanel contentPane;
    private JComboBox comboBox1;
    private JPanel panelVisualitzacio;
    private JButton visualitzarInformacióButton;
    private JList listInformacio;

    public VisualitzarDades(CentralUB centralUB) {
        this.centralUB = centralUB;
        setContentPane(contentPane);
        setModal(true);
        visualitzarInformacióButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getActionCommand() == "Estat de la Central") {
                    EstatCentral();
                }
                if (comboBox1.getActionCommand() == "Quadern de bitacola") {
                    Bitacora();
                }
                if (comboBox1.getActionCommand() == "Llista de Incidencies") {
                    Incidencies();
                }
            }
        });
    }

    public void EstatCentral() {
        DefaultListModel model = new DefaultListModel();
        model.clear();
        model.addElement(centralUB.getAdaptador().mostraEstat());
        listInformacio.setModel(model);
    }

    public void Bitacora() {
        DefaultListModel model = new DefaultListModel();
        model.clear();
        model.addElement(centralUB.getAdaptador().mostraBitacola());
        listInformacio.setModel(model);
    }

    public void Incidencies() {
        DefaultListModel model = new DefaultListModel();
        model.clear();
        model.addElement(centralUB.getAdaptador().mostraIncidencies());
        listInformacio.setModel(model);
    }
}
