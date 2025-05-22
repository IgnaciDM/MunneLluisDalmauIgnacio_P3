package prog2.vista;

import prog2.model.BombaRefrigerant;
import prog2.model.Reactor;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;


public class Components_Central extends JDialog {
    CentralUB centralUB;
    float insercioBarres;
    Reactor reactor;
    SistemaRefrigeracio refrigeracio;
    private JPanel contentPane;
    private JSpinner spinnerBarres;
    private JSlider sliderBarres;
    private JButton buttonactivarReactor;
    private JButton buttondesactivarReactor;
    private JButton buttonBomba1;
    private JButton buttonBomba2;
    private JButton buttonBomba3;
    private JButton buttonBomba4;
    private JList listMissatge;
    private JSpinner spinner1;



    public Components_Central(CentralUB centralUB) {
        this. centralUB = centralUB;
        this.insercioBarres = centralUB.getAdaptador().getInsercioBarres();
        this.reactor = centralUB.getAdaptador().mostraReactor();
        this.refrigeracio = centralUB.getAdaptador().mostraSistemaRefrigeracio();
        setContentPane(contentPane);
        setSize(600, 500);
        setModal(true);
        omplirLlista();

        spinnerBarres = new JSpinner(); // Aquesta línia ha d'existir abans de fer servir el component
        spinnerBarres.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        buttonBomba1.setBackground(Color.RED);
        buttonBomba2.setBackground(Color.RED);
        buttonBomba3.setBackground(Color.RED);
        buttonBomba4.setBackground(Color.RED);


        buttonactivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (centralUB.getAdaptador().mostraReactor().gettemperatura() <= 1000) {
                    try {
                        centralUB.getAdaptador().activaReactor();
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        buttondesactivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralUB.getAdaptador().desactivaReactor();
            }
        });
        buttonBomba1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BombaRefrigerant bomba1 = centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(0);
                if (!bomba1.getForaDeServei()) {
                    try {
                        if (!bomba1.getActivat()) {
                            bomba1.activa();
                            buttonBomba1.setBackground(Color.GREEN);

                        } else {
                            bomba1.desactiva();
                            buttonBomba1.setBackground(Color.RED);
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                }
            }
        });
        buttonBomba2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BombaRefrigerant bomba2 = centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(1);
                if (!bomba2.getForaDeServei()) {
                    try {
                        if (!bomba2.getActivat()) {
                            bomba2.activa();
                            buttonBomba2.setBackground(Color.GREEN);
                        } else {
                            bomba2.desactiva();
                            buttonBomba2.setBackground(Color.RED);
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                }
            }
        });
        buttonBomba3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BombaRefrigerant bomba3 = centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(2);
                if (!bomba3.getForaDeServei()) {
                    try {
                        if (!bomba3.getActivat()) {
                            bomba3.activa();
                            buttonBomba3.setBackground(Color.GREEN);
                        } else {
                            bomba3.desactiva();
                            buttonBomba3.setBackground(Color.RED);
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                }
            }
        });
        buttonBomba4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BombaRefrigerant bomba4 = centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(3);
                if (!bomba4.getForaDeServei()) {
                    try {
                        if (!bomba4.getActivat()) {
                            bomba4.activa();
                            buttonBomba4.setBackground(Color.GREEN);

                        } else {
                            bomba4.desactiva();
                            buttonBomba4.setBackground(Color.RED);
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                }
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
// Inicialització dels components
        spinnerBarres = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sliderBarres = new JSlider(0, 100, 0);
        spinner1 = new JSpinner();

// Sincronitzar valors
        sliderBarres.setValue((int) (insercioBarres * 100));
        spinnerBarres.setValue(sliderBarres.getValue()); // opcional, per sincronitzar

// Listener per sincronitzar amb slider
        spinner1.addChangeListener(e -> {
            int valor = (int) spinnerBarres.getValue();
            sliderBarres.setValue(valor);
            try {
                centralUB.getAdaptador().setInsercioBarres(valor / 100.0f);
            } catch (CentralUBException ex) {
                JOptionPane.showMessageDialog(null, "Error actualitzant inserció de barres: " + ex.getMessage());
            }
        });
    }

    void omplirLlista(){
        DefaultListModel model = new DefaultListModel();
        model.clear();
        for(BombaRefrigerant item: centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes()){
            if (item.getForaDeServei() == true) {
                model.addElement(item);
            }
        }
        listMissatge.setModel(model);
    }

}
