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

        buttonBomba1.setBackground(Color.RED);
        buttonBomba2.setBackground(Color.RED);
        buttonBomba3.setBackground(Color.RED);
        buttonBomba4.setBackground(Color.RED);


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
                            buttonBomba1.setBackground(Color.GREEN);
                            afegirMissatge("Bomba 1 activada.");

                        } else {
                            bomba1.desactiva();
                            buttonBomba1.setBackground(Color.RED);
                            afegirMissatge("Bomba 1 desactivada");
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                    afegirMissatge("Bomba 1 fora de servei.");
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
                            buttonBomba2.setBackground(Color.GREEN);
                            afegirMissatge("Bomba 2 activada.");
                        } else {
                            bomba2.desactiva();
                            buttonBomba2.setBackground(Color.RED);
                            afegirMissatge("Bomba 2 desactivada");
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                    afegirMissatge("Bomba 2 fora de servei.");
                }
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
                            buttonBomba3.setBackground(Color.GREEN);
                            afegirMissatge("Bomba 3 activada.");
                        } else {
                            bomba3.desactiva();
                            buttonBomba3.setBackground(Color.RED);
                            afegirMissatge("Bomba 3 desactivada");
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                    afegirMissatge("Bomba 3 fora de servei.");
                }
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
                            buttonBomba4.setBackground(Color.GREEN);
                            afegirMissatge("Bomba 4 activada.");

                        } else {
                            bomba4.desactiva();
                            buttonBomba4.setBackground(Color.RED);
                            afegirMissatge("Bomba 4 desactivada");
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
                    afegirMissatge("Bomba 4 fora de servei.");
                }
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
                    } //
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

}

