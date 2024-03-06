package ui;

import db.Database;
import run.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.*;

public class OpenConnectionPage {
    private JFrame frame;

    public OpenConnectionPage () {
        //Window
        frame = new JFrame("Connecting to the database");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, as it's a secondary window

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Insets for spacing


        //Labels and Text Fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name of the database:");
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(125, 25)); // Customize size
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel hostLabel = new JLabel("Host:");
        panel.add(hostLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField hostField = new JTextField();
        hostField.setPreferredSize(new Dimension(125, 25)); // Customize size
        panel.add(hostField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel portLabel = new JLabel("Port No.:");
        panel.add(portLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        JTextField portField = new JTextField();
        portField.setPreferredSize(new Dimension(125, 25)); // Customize size
        panel.add(portField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel unameLabel = new JLabel("Username:");
        panel.add(unameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        JTextField unameField = new JTextField();
        unameField.setPreferredSize(new Dimension(125, 25)); // Customize size
        panel.add(unameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(125, 25)); // Customize size
        panel.add(passwordField, gbc);

        //port error message field
        gbc.gridx = 2;
        gbc.gridy = 2;
        JLabel portErrorMessage = new JLabel();
        portErrorMessage.setForeground(Color.red);
        Font f = portErrorMessage.getFont();
        portErrorMessage.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        panel.add(portErrorMessage, gbc);

        // general error message field
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel errorMessage = new JLabel();
        errorMessage.setForeground(Color.red);
        Font f2 = errorMessage.getFont();
        errorMessage.setFont(f2.deriveFont(f2.getStyle() & ~Font.BOLD));
        panel.add(errorMessage, gbc);


        // Connect Button
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END; // Align to the right
        gbc.insets = new Insets(45, 30, 20, 10); // Adjust top and bottom padding
        JButton connectButton = new JButton("Connect");
        connectButton.setPreferredSize(new Dimension(90, 40));
        panel.add(connectButton, gbc);


        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String portStr = portField.getText();
                try {
                    int portInt = Integer.parseInt(portStr);
                    if (portInt < 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    portErrorMessage.setText("Invalid port number");
                    return;
                }
                portErrorMessage.setText("");
                String name = nameField.getText();
                String host = hostField.getText();
                String username = unameField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    Database db = new Database(name, host, portStr, username, password);
                    errorMessage.setText("");
                    Main.setDatabase(db);
                } catch (ClassNotFoundException ex) {
                    errorMessage.setText("Cannot find the driver :(");
                    return;
                } catch (SQLException ex) {
                    errorMessage.setText("Cannot connect to the database");
                    return;
                }
                OpenHomePageWindow();

            }
        });

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void OpenHomePageWindow () {
        new HomePage();
    }


    //MAIN
    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                new OpenConnectionPage();
            }
        });


    }
}
