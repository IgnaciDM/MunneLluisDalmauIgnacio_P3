package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.Reactor;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Components_Central extends JDialog {
    CentralUB centralUB;
    float insercioBarres;
    Reactor reactor;
    SistemaRefrigeracio refrigeracio;
    private JSpinner spinnerBarres; //Fusionar events
    private JPanel contentPane;
    private JSlider sliderBarres;
    private JButton buttonactivarReactor;
    private JButton buttondesactivarReactor;
    private JButton buttonBomba1;
    private JButton buttonBomba2;
    private JButton buttonBomba3;
    private JButton buttonBomba4;

    public Components_Central() {
        this.centralUB = new CentralUB();
        this.insercioBarres = centralUB.getAdaptador().getInsercioBarres();
        this.reactor = centralUB.getAdaptador().mostraReactor();
        this.refrigeracio = centralUB.getAdaptador().mostraSistemaRefrigeracio();
        setContentPane(contentPane);
        setSize(600, 500);
        setModal(true);


        // Configurar Slider (0-100%)
        sliderBarres.setMinimum(0);
        sliderBarres.setMaximum(100);
        sliderBarres.setValue((int)(centralUB.getAdaptador().getInsercioBarres() * 100));

        // Configurar Spinner (mateix rang que el slider)
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(sliderBarres.getValue(), 0, 100, 1);
        spinnerBarres.setModel(spinnerModel);

        // Vincular canvis del spinner amb el slider
        spinnerBarres.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valor = (int) spinnerBarres.getValue();
                sliderBarres.setValue(valor); // Actualitza slider
                try {
                    centralUB.getAdaptador().setInsercioBarres(valor / 100.0f); // Actualitza model
                } catch (CentralUBException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
