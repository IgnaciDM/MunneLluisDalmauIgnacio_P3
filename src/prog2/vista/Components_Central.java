package prog2.vista;

import prog2.model.BombaRefrigerant;
import prog2.model.Reactor;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Components_Central extends JDialog {
    CentralUB centralUB;
    float insercioBarres;
    Reactor reactor;
    SistemaRefrigeracio refrigeracio;
    private JPanel contentPane;
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

    public Components_Central() {
        this.centralUB = new CentralUB();
        this.insercioBarres = centralUB.getAdaptador().getInsercioBarres();
        this.reactor = centralUB.getAdaptador().mostraReactor();
        this.refrigeracio = centralUB.getAdaptador().mostraSistemaRefrigeracio();
        setContentPane(contentPane);
        setSize(600, 500);
        setModal(true);
        omplirLlista();
        sliderBarres.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                try {
                    centralUB.getAdaptador().setInsercioBarres(sliderBarres.getExtent());
                } catch (CentralUBException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonactivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (centralUB.getAdaptador().mostraReactor().gettemperatura() <= 1000) {
                    try {
                        centralUB.getAdaptador().activaReactor();
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {

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
