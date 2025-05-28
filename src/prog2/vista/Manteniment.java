package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manteniment extends JDialog {
    private JPanel contentPane;
    private JPanel panel;

    CentralUB centralUB;

    public Manteniment(CentralUB centralUB) {
        this.centralUB = centralUB;

        setContentPane(contentPane);
        setSize(600, 500);
        setModal(true); // Finestra modal
        setContentPane(contentPane);
        setVisible(true);

    }

    private void gestionarOpcioBomba() {
        String input = JOptionPane.showInputDialog(
                null,
                "Introdueix el número de la bomba:",
                "Entrada necessària",
                JOptionPane.QUESTION_MESSAGE
        );

        if (input != null) {
            try {
                int numero = Integer.parseInt(input);
                // Acá usás el número para lo que necesites
                JOptionPane.showMessageDialog(
                        null,
                        "Has introduït el número: " + numero,
                        "Número introduït",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Has d’introduir un número vàlid.",
                        "Error de format",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


}
