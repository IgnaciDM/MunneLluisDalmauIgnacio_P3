package prog2.vista;

import prog2.model.BombaRefrigerant;
import prog2.model.Reactor;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

/**
 * Classe que representa una finestra modal per gestionar els components de la central nuclear.
 * Permet modificar l'estat del reactor, el grau d'inserció de barres, i les bombes de refrigeració.
 * Les modificacions es fan sobre còpies locals fins que es decideix aplicar-les al model real.
 */
public class Components_Central extends JDialog {

    /** Referència al model principal */
    CentralUB centralUB;

    /** Grau d'inserció de les barres del reactor */
    float insercioBarres;

    /** Còpia local del reactor */
    Reactor reactor;

    /** Còpia local del sistema de refrigeració */
    SistemaRefrigeracio refrigeracio;

    // Components gràfics de la interfície
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

    /**
     * Constructor de la finestra de gestió de components.
     * Inicialitza la interfície i carrega l'estat actual de reactor i refrigeració.
     *
     * @param centralUB referència a la instància principal del model {@code CentralUB}
     */
    public Components_Central(CentralUB centralUB) {
        this.centralUB = centralUB;
        this.insercioBarres = centralUB.getAdaptador().getInsercioBarres();
        this.reactor = new Reactor(centralUB.getAdaptador().mostraReactor());
        this.refrigeracio = new SistemaRefrigeracio(centralUB.getAdaptador().mostraSistemaRefrigeracio());

        setContentPane(contentPane);
        setSize(600, 500);
        setModal(true); // Finestra modal

        // Inicialització d'estats de les bombes
        actualitzaEstatBomba(buttonBomba1, refrigeracio.getllistabombes().get(0), 1);
        actualitzaEstatBomba(buttonBomba2, refrigeracio.getllistabombes().get(1), 2);
        actualitzaEstatBomba(buttonBomba3, refrigeracio.getllistabombes().get(2), 3);
        actualitzaEstatBomba(buttonBomba4, refrigeracio.getllistabombes().get(3), 4);

        // Configura el slider de control d'inserció de barres
        slider1.setMinimum(0);
        slider1.setMaximum(100);
        slider1.setValue((int) insercioBarres);
        slider1.addChangeListener(e -> {
            insercioBarres = slider1.getValue();
            afegirMissatge("Insercio Barres: " + insercioBarres);
        });

        // Acció per activar el reactor
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

        // Acció per desactivar el reactor
        buttondesactivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reactor.desactiva();
                afegirMissatge("Reactor desactivat");
            }
        });

        // Accions per cada bomba de refrigeració
        buttonBomba1.addActionListener(e -> gestionaBomba(buttonBomba1, 0));
        buttonBomba2.addActionListener(e -> gestionaBomba(buttonBomba2, 1));
        buttonBomba3.addActionListener(e -> gestionaBomba(buttonBomba3, 2));
        buttonBomba4.addActionListener(e -> gestionaBomba(buttonBomba4, 3));

        // Aplica les modificacions fetes a les còpies al model real
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

        // Cancel·la els canvis i tanca la finestra
        cancelarModificacionsButton.addActionListener(e -> setVisible(false));
    }

    /**
     * Afegeix un missatge informatiu a la llista de missatges de la interfície.
     *
     * @param missatge el text a mostrar
     */
    private void afegirMissatge(String missatge) {
        DefaultListModel model = (DefaultListModel) listMissatge.getModel();
        model.clear();
        model.addElement(missatge);
    }

    /**
     * Actualitza l'estat visual d'un botó de bomba segons l'estat de la bomba associada.
     *
     * @param boto     el botó a modificar
     * @param bomba    la bomba de refrigeració associada
     * @param numBomba el número de bomba (per a mostrar al text)
     */
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

    /**
     * Gestiona l'activació/desactivació d'una bomba segons el seu estat actual.
     *
     * @param boto el botó associat a la bomba
     * @param idx  l'índex de la bomba a la llista del sistema de refrigeració
     */
    private void gestionaBomba(JButton boto, int idx) {
        BombaRefrigerant bomba = refrigeracio.getllistabombes().get(idx);
        if (!bomba.getForaDeServei()) {
            try {
                if (!bomba.getActivat()) {
                    bomba.activa();
                } else {
                    bomba.desactiva();
                }
            } catch (CentralUBException ex) {
                JOptionPane.showMessageDialog(null, "Error activant la bomba: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "La bomba està fora de servei.");
            afegirMissatge("Bomba " + (idx + 1) + " fora de servei.");
        }
        actualitzaEstatBomba(boto, bomba, idx + 1);
    }
}
