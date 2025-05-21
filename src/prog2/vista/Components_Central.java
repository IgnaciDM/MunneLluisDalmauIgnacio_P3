package prog2.vista;

import prog2.model.BombaRefrigerant;
import prog2.model.Reactor;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;
import java.awt.event.*;

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
    private JList listforaservei;
    private JButton desactivarBomba1Button;
    private JButton desactivarBomba2Button;
    private JButton desactivarBomba3Button;
    private JButton desactivarBomba4Button;
    private JSpinner spinner1;


    public Components_Central() {
        this.centralUB = new CentralUB();
        this.insercioBarres = centralUB.getAdaptador().getInsercioBarres();
        this.reactor = centralUB.getAdaptador().mostraReactor();
        this.refrigeracio = centralUB.getAdaptador().mostraSistemaRefrigeracio();
        setContentPane(contentPane);
        setSize(600, 500);
        setModal(true);
        omplirLlista();

        spinnerBarres = new JSpinner(); // Aquesta línia ha d'existir abans de fer servir el component
        spinnerBarres.setModel(new SpinnerNumberModel(0, 0, 10, 1));


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
                if (centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(0).getForaDeServei() == false) {
                    try {
                        centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(0).activa();
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {

                }
            }
        });
        desactivarBomba1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(0).desactiva();
            }
        });
        buttonBomba2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(1).getForaDeServei() == false) {
                    try {
                        centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(1).activa();
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {

                }
            }
        });
        desactivarBomba2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(1).desactiva();
            }
        });
        buttonBomba3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(2).getForaDeServei() == false) {
                    try {
                        centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(2).activa();
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {

                }
            }
        });
        desactivarBomba3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(2).desactiva();
            }
        });
        buttonBomba4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(3).getForaDeServei() == false) {
                    try {
                        centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(3).activa();
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {

                }
            }
        });
        desactivarBomba4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(3).desactiva();
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
        listforaservei.setModel(model);
    }

}
