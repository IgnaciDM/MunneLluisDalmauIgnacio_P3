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
    private JButton buttonactivarReactor;
    private JButton buttondesactivarReactor;
    private JButton buttonBomba1;
    private JButton buttonBomba2;
    private JButton buttonBomba3;
    private JButton buttonBomba4;
    private JList listMissatge;
    private JButton aplicarModificacionsButton;
    private JButton cancelarModificacionsButton;
    private JSlider slider1;


// hola

    public Components_Central(CentralUB centralUB) {
        this. centralUB = centralUB;
        this.insercioBarres = centralUB.getAdaptador().getInsercioBarres();
        this.reactor = new Reactor(centralUB.getAdaptador().mostraReactor());
        this.refrigeracio = new SistemaRefrigeracio(centralUB.getAdaptador().mostraSistemaRefrigeracio());
        setContentPane(contentPane);
        setSize(600, 500);
        setModal(true);

        actualitzaEstatBomba(buttonBomba1, refrigeracio.getllistabombes().get(0), 1);
        actualitzaEstatBomba(buttonBomba2, refrigeracio.getllistabombes().get(1), 2);
        actualitzaEstatBomba(buttonBomba3, refrigeracio.getllistabombes().get(2), 3);
        actualitzaEstatBomba(buttonBomba4, refrigeracio.getllistabombes().get(3), 4);

        slider1.setMinimum(0);  // O el mínimo que quieras
        slider1.setMaximum(100);  // O el máximo que quieras
        slider1.setValue((int) insercioBarres);  // Valor inicial

        slider1.addChangeListener(e -> {
            insercioBarres = slider1.getValue();
            afegirMissatge("Insercio Barres: "+insercioBarres);
        });

        buttonactivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reactor.gettemperatura() <= 1000) {
                    try {
                        reactor.activa();
                        afegirMissatge("Reactor activat");
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    afegirMissatge("Reactor no es pot activar perque la seva temperatura supera els 1000 graus");
                }
            }
        });

        buttondesactivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reactor.desactiva();
                afegirMissatge("Reactor desactivat");
            }
        });
        buttonBomba1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BombaRefrigerant bomba1 = refrigeracio.getllistabombes().get(0);
                if (!bomba1.getForaDeServei()) {
                    try {
                        if (!bomba1.getActivat()) {
                            bomba1.activa();
                        } else {
                            bomba1.desactiva();
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                    actualitzaEstatBomba(buttonBomba1, bomba1, 1);
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                }
            }
        });
        buttonBomba2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BombaRefrigerant bomba2 = refrigeracio.getllistabombes().get(1);
                if (!bomba2.getForaDeServei()) {
                    try {
                        if (!bomba2.getActivat()) {
                            bomba2.activa();
                        } else {
                            bomba2.desactiva();
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                    afegirMissatge("Bomba 2 fora de servei.");
                }
                actualitzaEstatBomba(buttonBomba2, bomba2, 2);
            }
        });
        buttonBomba3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BombaRefrigerant bomba3 = refrigeracio.getllistabombes().get(2);
                if (!bomba3.getForaDeServei()) {
                    try {
                        if (!bomba3.getActivat()) {
                            bomba3.activa();
                        } else {
                            bomba3.desactiva();
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                    afegirMissatge("Bomba 3 fora de servei.");
                }
                actualitzaEstatBomba(buttonBomba3, bomba3, 3);
            }
        });
        buttonBomba4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BombaRefrigerant bomba4 = refrigeracio.getllistabombes().get(3);
                if (!bomba4.getForaDeServei()) {
                    try {
                        if (!bomba4.getActivat()) {
                            bomba4.activa();

                        } else {
                            bomba4.desactiva();
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                    afegirMissatge("Bomba 4 fora de servei.");
                }
                actualitzaEstatBomba(buttonBomba4, bomba4, 4);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////

        aplicarModificacionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    centralUB.getAdaptador().setInsercioBarres(insercioBarres);
                    centralUB.getAdaptador().mostraReactor().settemperatura(reactor.gettemperatura());
                    if (reactor.getActivat()) {
                        centralUB.getAdaptador().mostraReactor().activa();
                    } else {
                        centralUB.getAdaptador().mostraReactor().desactiva();
                    }
                    for (int i = 0; i < refrigeracio.getllistabombes().size(); i++) {
                        if (refrigeracio.getllistabombes().get(i).getActivat()) {
                            centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(i).activa();
                        } else {
                            centralUB.getAdaptador().mostraSistemaRefrigeracio().getllistabombes().get(i).desactiva();
                        }
                    }
                } catch (CentralUBException ex) {
                    JOptionPane.showMessageDialog(null, "Error actualitzant la Central: " + ex.getMessage());
                }
            }
        });
        cancelarModificacionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    private void afegirMissatge(String missatge) {
        DefaultListModel model = (DefaultListModel) listMissatge.getModel();
        model.clear();
        model.addElement(missatge);

    }
    private void actualitzaEstatBomba(JButton boto, BombaRefrigerant bomba, int numBomba) {
        if (bomba.getForaDeServei()) {
            boto.setBackground(Color.GRAY);
            boto.setText("Bomba " + numBomba + ": Fora de servei");
            afegirMissatge("Bomba " + numBomba + " fora de servei.");
        } else{
            if (bomba.getActivat()){
                boto.setBackground(Color.GREEN);
                boto.setText("Bomba " + numBomba + ": Activada");
                afegirMissatge("Bomba " + numBomba + " activada.");
            } else {
            boto.setBackground(Color.RED);
            boto.setText("Bomba " + numBomba + ": Desactivada");
            afegirMissatge("Bomba " + numBomba + " desactivada.");
            }
        }
}
}

