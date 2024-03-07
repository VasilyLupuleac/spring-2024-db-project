package ui;

import run.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdatePopUp {
    private JFrame frame;
    private JTextField valueField;
    private UpdatePopUp() {
        frame = new JFrame("Update value");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set default close operation

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel valueLabel = new JLabel("Value:");
        panel.add(valueLabel, gbc);

        gbc.gridx = 1;
        JTextField valueField = new JTextField(10);
        panel.add(valueField, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String value = valueField.getText();
                //TODO add functionality here
                frame.dispose();
            }
        });
        panel.add(saveButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UpdatePopUp();
            }
        });
    }
}
