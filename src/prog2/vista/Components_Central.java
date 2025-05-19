package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.Reactor;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;

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

    public Components_Central() {
        this.centralUB = new CentralUB();
        this.insercioBarres = centralUB.getAdaptador().getInsercioBarres();
        this.reactor = centralUB.getAdaptador().mostraReactor();
        this.refrigeracio = centralUB.getAdaptador().mostraSistemaRefrigeracio();
        setContentPane(contentPane);
        setSize(600, 500);
        setModal(true);
    }
}
