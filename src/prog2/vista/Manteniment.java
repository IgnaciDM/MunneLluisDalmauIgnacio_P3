package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manteniment extends JDialog {
    private JPanel contentPane;
    private JComboBox<String> opcions;
    private JButton okButton;
    private JTextField Nbomba;

    CentralUB centralUB;

    public Manteniment(CentralUB centralUB) {
        this.centralUB = centralUB;

        setContentPane(contentPane);
        setModal(true);
        Nbomba.setEnabled(false); // al principio deshabilitado
        contentPane.add(Nbomba);  // ya está visible pero no editable


        opcions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) opcions.getSelectedItem();

                if ("Bomba".equals(seleccion)) {
                    Nbomba.setEnabled(true); // ahora se puede escribir
                } else {
                    Nbomba.setEnabled(false); // no editable
                }
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcio = (String) opcions.getSelectedItem();
                String informacio;

                try {
                    switch (opcio) {
                        case "Reactor":

                            break;
                        case "Bomba":
                            break;
                        default:
                            informacio = "Opció no reconeguda";
                    }

                } catch (Exception ex) {

                }
            }
        });

        Nbomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = Nbomba.getText();
                try {
                    int numero = Integer.parseInt(texto);
                    JOptionPane.showMessageDialog(null, "Número válido: " + numero);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número no válido");
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
