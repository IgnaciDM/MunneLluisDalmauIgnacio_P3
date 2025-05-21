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
    private JTextArea textArea1;

    public VisualitzarDades(CentralUB centralUB) {
        this.centralUB = centralUB;
        setContentPane(contentPane);
        setModal(true);

        visualitzarInformacióButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBox1.getSelectedItem();
                if (seleccion.equals("Estat de la Central")) {
                    EstatCentral();
                } else if (seleccion.equals("Quadern de bitacola")) {
                    Bitacora();
                } else if (seleccion.equals("Llista de Incidencies")) {
                    Incidencies();
                }
            }
        });
    }

    public void EstatCentral() {
        textArea1.setText(String.valueOf(centralUB.getAdaptador().mostraEstat()));
    }

    public void Bitacora() {
        textArea1.setText(String.valueOf(centralUB.getAdaptador().mostraBitacola()));
    }

    public void Incidencies() {
        textArea1.setText(String.valueOf((centralUB.getAdaptador().mostraIncidencies())));
    }
    /*
    DefaultListModel model = new DefaultListModel();
        model.clear();
        model.addElement(centralUB.getAdaptador().mostraIncidencies());
        listInformacio.setModel(model);
     */
}
