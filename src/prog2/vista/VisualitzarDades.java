package prog2.vista;

import javax.swing.*;
import java.awt.*;
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
        setLocationRelativeTo(null);
        setSize(600, 500);


        // Crear panel principal
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        // Parte superior: combo box + botón
        JPanel topPanel = new JPanel();
        comboBox1 = new JComboBox<>(new String[]{
                "Estat de la Central",
                "Quadern de bitacola",
                "Llista de Incidencies"
        });

        visualitzarInformacióButton = new JButton("Visualitzar Informació");

        topPanel.add(comboBox1);
        topPanel.add(visualitzarInformacióButton);

        contentPane.add(topPanel, BorderLayout.NORTH);

        // Centro: àrea de text amb scroll
        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea1);
        contentPane.add(scrollPane, BorderLayout.CENTER);

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
