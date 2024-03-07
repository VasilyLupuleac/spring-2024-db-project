package ui;

import db.Table;
import run.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatePopUp {
    private JFrame frame;
    private JTextField valueField;
    private String name;
    public UpdatePopUp(String name, Table table, String fieldName, String idLabel, int id, String[] args) {
        this.name = name;
        frame = new JFrame("Update " + name);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set default close operation

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel valueLabel = new JLabel("New " + name + ":");
        panel.add(valueLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 5;
        JTextField valueField = new JTextField(10);
        valueField.setPreferredSize(new Dimension(400, 30));
        panel.add(valueField, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel errorMessage = new JLabel();
        errorMessage.setForeground(Color.red);
        Font f2 = errorMessage.getFont();
        errorMessage.setFont(f2.deriveFont(f2.getStyle() & ~Font.BOLD));
        panel.add(errorMessage, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    errorMessage.setText("");
                    table.setById(id, idLabel, valueField.getText(), fieldName);
                    frame.dispose();
                    ResultSet result = Main.songs.songSearchTopRated(
                            args[0], args[1], args[2], args[3]
                    );
                    new SearchResult(result, args);
            } catch (SQLException ex) {
                    errorMessage.setText("Invalid value");
                }
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

            }
        });
    }
}
