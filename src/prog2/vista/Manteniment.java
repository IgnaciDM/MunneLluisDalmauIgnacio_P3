package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manteniment extends JDialog {
    private JPanel contentPane;
    private JPanel panel;
    private JComboBox<String> opcions;
    private JTextField Nbomba;


    CentralUB centralUB;

    public Manteniment(CentralUB centralUB) {
        this.centralUB = centralUB;

        setContentPane(contentPane);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setModal(true); // ventana modal

        Nbomba.setEnabled(false);


        opcions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) opcions.getSelectedItem();

                if ("Bombes".equals(seleccion)) {
                    Nbomba.setEnabled(true); // activa l'edició
                } else {
                    Nbomba.setEnabled(false); // desactiva si no és "Bomba"
                }
            }
        });

        Nbomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String texto = Nbomba.getText();
                    int numeroBomba = Integer.parseInt(texto);
                    // Acción con el número de bomba:
                    System.out.println("Número de bomba introducido: " + numeroBomba);

                    centralUB.getAdaptador().getDades().getEstatTecnic().RepararBombes(Integer.parseInt(Nbomba.getText()));
                    JOptionPane.showMessageDialog(null, "Element reparat");
                    // Aquí puedes llamar al método que necesites:
                    // centralUB.mantenimentPreventiuBomba(numeroBomba);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introdueix un número vàlid");
                }
            }
        });
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
