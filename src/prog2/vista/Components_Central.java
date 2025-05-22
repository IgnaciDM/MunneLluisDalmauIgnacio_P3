// Paquet on es troba aquesta classe
package prog2.vista;

// Importació de classes del model
import prog2.model.BombaRefrigerant;
import prog2.model.Reactor;
import prog2.model.SistemaRefrigeracio;

// Importació de llibreries gràfiques i gestió d'esdeveniments
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

// Classe que representa una finestra modal per gestionar components de la central
public class Components_Central extends JDialog {

    // Atributs necessaris per gestionar la lògica
    CentralUB centralUB; // Referència al model principal
    float insercioBarres; // Grau d'inserció de barres del reactor (entre 0 i 100)
    Reactor reactor; // Còpia local del reactor
    SistemaRefrigeracio refrigeracio; // Còpia local del sistema de refrigeració

    // Components de la interfície gràfica
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

    // Constructor
    public Components_Central(CentralUB centralUB) {
        this.centralUB = centralUB;

        // Es creen còpies dels components per modificar-los sense alterar l'original directament
        this.insercioBarres = centralUB.getAdaptador().getInsercioBarres();
        this.reactor = new Reactor(centralUB.getAdaptador().mostraReactor());
        this.refrigeracio = new SistemaRefrigeracio(centralUB.getAdaptador().mostraSistemaRefrigeracio());

        // Configuració bàsica de la finestra
        setContentPane(contentPane);
        setSize(600, 500);
        setModal(true); // Fa que sigui una finestra modal (bloqueja la resta fins que es tanqui)

        // Inicialització de l’estat dels botons de les bombes
        actualitzaEstatBomba(buttonBomba1, refrigeracio.getllistabombes().get(0), 1);
        actualitzaEstatBomba(buttonBomba2, refrigeracio.getllistabombes().get(1), 2);
        actualitzaEstatBomba(buttonBomba3, refrigeracio.getllistabombes().get(2), 3);
        actualitzaEstatBomba(buttonBomba4, refrigeracio.getllistabombes().get(3), 4);

        // Configura el "slider" (barra d'inserció de barres)
        slider1.setMinimum(0);
        slider1.setMaximum(100);
        slider1.setValue((int) insercioBarres);
        slider1.addChangeListener(e -> {
            insercioBarres = slider1.getValue();
            afegirMissatge("Insercio Barres: " + insercioBarres);
        });

        // Gestió del botó per activar el reactor
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
                    afegirMissatge("Reactor no es pot activar perquè la seva temperatura supera els 1000 graus");
                }
            }
        });

        // Botó per desactivar el reactor
        buttondesactivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reactor.desactiva();
                afegirMissatge("Reactor desactivat");
            }
        });

        // Botons per activar/desactivar cadascuna de les bombes, amb comprovació de l'estat
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

        // Repeteix el mateix esquema per la bomba 2
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

        // Bomba 3
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

        // Bomba 4
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

        // Botó per aplicar les modificacions fetes a la còpia local i actualitzar el model real
        aplicarModificacionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Actualitza els valors al model real
                    centralUB.getAdaptador().setInsercioBarres(insercioBarres);
                    centralUB.getAdaptador().mostraReactor().settemperatura(reactor.gettemperatura());
                    if (reactor.getActivat()) {
                        centralUB.getAdaptador().mostraReactor().activa();
                    } else {
                        centralUB.getAdaptador().mostraReactor().desactiva();
                    }

                    // Actualitza les bombes al model real
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

        // Botó per cancel·lar i tancar la finestra
        cancelarModificacionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    // Afegeix un missatge a la llista de missatges
    private void afegirMissatge(String missatge) {
        DefaultListModel model = (DefaultListModel) listMissatge.getModel();
        model.clear();
        model.addElement(missatge);
    }

    // Actualitza el color i el text del botó en funció de l’estat de la bomba
    private void actualitzaEstatBomba(JButton boto, BombaRefrigerant bomba, int numBomba) {
        if (bomba.getForaDeServei()) {
            boto.setBackground(Color.GRAY);
            boto.setText("Bomba " + numBomba + ": Fora de servei");
            afegirMissatge("Bomba " + numBomba + " fora de servei.");
        } else {
            if (bomba.getActivat()) {
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
