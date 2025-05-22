package prog2.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra de diàleg que permet visualitzar diferents informacions
 * relacionades amb la central: estat, quadern de bitàcola i incidències.
 */
public class VisualitzarDades extends JDialog {
    /**
     * Referència a la central sobre la qual es consulta la informació.
     */
    CentralUB centralUB;

    /**
     * Panell principal del contingut del diàleg.
     */
    private JPanel contentPane;

    /**
     * Menú desplegable per seleccionar el tipus d'informació a visualitzar.
     */
    private JComboBox comboBox1;

    /**
     * Panell destinat a mostrar informació (no utilitzat explícitament en aquest codi).
     */
    private JPanel panelVisualitzacio;

    /**
     * Botó per activar la visualització de la informació seleccionada.
     */
    private JButton visualitzarInformacióButton;

    /**
     * Àrea de text on es mostrarà la informació resultant.
     */
    private JTextArea textArea1;

    /**
     * Constructor del diàleg. Inicialitza la interfície gràfica i els components.
     *
     * @param centralUB instància de la central que proporciona les dades.
     */
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

    /**
     * Mostra l’estat actual de la central a l’àrea de text.
     * Utilitza {@code centralUB.getAdaptador().mostraEstat()}.
     */
    public void EstatCentral() {
        textArea1.setText(String.valueOf(centralUB.getAdaptador().mostraEstat()));
    }

    /**
     * Mostra el quadern de bitàcola de la central a l’àrea de text.
     * Utilitza {@code centralUB.getAdaptador().mostraBitacola()}.
     */
    public void Bitacora() {
        textArea1.setText(String.valueOf(centralUB.getAdaptador().mostraBitacola()));
    }

    /**
     * Mostra la llista d'incidències registrades a la central a l’àrea de text.
     * Utilitza {@code centralUB.getAdaptador().mostraIncidencies()}.
     */
    public void Incidencies() {
        textArea1.setText(String.valueOf((centralUB.getAdaptador().mostraIncidencies())));
    }
}
