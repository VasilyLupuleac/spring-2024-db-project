package ui;

import run.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class BandAddManually extends JFrame {
    private JTextField nameField, foundationField, disbandmentField;
    private JPanel memberPanel;

    //Window
    public BandAddManually() {
        setTitle("Add Band Manually");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Input fields for Band name, Foundation year, and Disbandment year
        JPanel bandInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Adjust spacing

        JLabel nameLabel = new JLabel("Band Name:");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150, 25)); // Set preferred size
        bandInfoPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        bandInfoPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel foundationLabel = new JLabel("Foundation Year:");
        foundationField = new JTextField();
        foundationField.setPreferredSize(new Dimension(150, 25)); // Set preferred size
        bandInfoPanel.add(foundationLabel, gbc);
        gbc.gridx = 1;
        bandInfoPanel.add(foundationField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel disbandmentLabel = new JLabel("Disbandment Year:");
        disbandmentField = new JTextField();
        disbandmentField.setPreferredSize(new Dimension(150, 25)); // Set preferred size
        bandInfoPanel.add(disbandmentLabel, gbc);
        gbc.gridx = 1;
        bandInfoPanel.add(disbandmentField, gbc);

        panel.add(bandInfoPanel, BorderLayout.NORTH);

       // Add Member button
       JButton addMemberButton = new JButton("Add Member   +");
       setAddMemberButtonSize(addMemberButton, 100, 50); // Set default size
       addMemberButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               addMemberPopup();
           }
       });
       panel.add(addMemberButton, BorderLayout.CENTER); 

       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //X Button
        JButton xButton = new JButton("X");
        xButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Open MissingBandPopup
                new BandAddOptions();
            }
        });
        buttonPanel.add(xButton);

        //Ok Button
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String foundation = foundationField.getText();
                if (foundation.isEmpty())
                    foundation = "0";
                String disband = disbandmentField.getText();
                if (disband.isEmpty())
                    disband = "0";
                try {
                    Main.bands.addBand(name, Integer.parseInt(foundation), Integer.parseInt(disband));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
                // Go to HomePage
                new HomePage();
            }
        });
        buttonPanel.add(okButton);


        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Method to set the size of the Add member button
    private void setAddMemberButtonSize(JButton button, int width, int height) {
        button.setPreferredSize(new Dimension(width, height));
    }


    //POPUP Window
    private void addMemberPopup() {
        JFrame popupFrame = new JFrame("Musician:");
        popupFrame.setSize(400, 300);
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupFrame.setLayout(new BorderLayout());
    
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
    
        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel, gbc);
    
        gbc.gridx = 1;
        JTextField nameField = new JTextField(10); // Set the width of the input box
        panel.add(nameField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel roleLabel = new JLabel("Role:");
        panel.add(roleLabel, gbc);
    
        gbc.gridx = 1;
        JTextField roleField = new JTextField(10); // Set the width of the input box
        panel.add(roleField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel dobLabel = new JLabel("Date of Birth (DD.MM.YYYY):");
        panel.add(dobLabel, gbc);
    
        gbc.gridx = 1;
        JTextField dobField = new JTextField(10); // Set the width of the input box
        panel.add(dobField, gbc);
    
        JButton xButton = new JButton("X");
        xButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the popup
                popupFrame.dispose();
            }
        });
    
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get user inputs
                String name = nameField.getText();
                String role = roleField.getText();
                String dob = dobField.getText();
    
                /* 
                // Add the information to the database
                if (saveMusicianInformation(name, role, dob)) {
                    // If saved successfully, close the popup
                    popupFrame.dispose();
                } else {
                    // Show an error message if saving fails
                    // JOptionPane.showMessageDialog(popupFrame, "Failed to save musician information", "Error", JOptionPane.ERROR_MESSAGE);
                } */
                popupFrame.dispose();
            }
        });
    
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the left
        buttonPanel.add(xButton);
        buttonPanel.add(okButton);
    
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding around the panel
    
        popupFrame.add(panel, BorderLayout.CENTER);
        popupFrame.add(buttonPanel, BorderLayout.SOUTH);
    
        popupFrame.setLocationRelativeTo(null);
        popupFrame.setVisible(true);
    }
    
    
    
    //MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BandAddManually();
            }
        });
    }
}
