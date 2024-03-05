// AlbumAdd.java
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class AlbumAdd extends JFrame {
    private JTextField nameField, yearField;
    private JLabel pictureLabel;

    public AlbumAdd() {
        //Window
        setTitle("Add Album");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        //Upload Album Picture
        pictureLabel = new JLabel("Upload Album Picture:");
        panel.add(pictureLabel, gbc);

        JButton uploadButton = new JButton("Upload");
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadPicture();
            }

            private void uploadPicture() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFileChooser fileChooser = new JFileChooser();
                        int returnValue = fileChooser.showOpenDialog(AlbumAdd.this);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                    
                            pictureLabel.setText("Uploaded: " + selectedFile.getName());
                        }
                    }
                });
            }
            
        });
        gbc.gridx = 1;
        panel.add(uploadButton, gbc);


        // Name
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END; // Align the label to the end of its display area
        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START; // Reset the anchor for the text field
        nameField = new JTextField(10);
        panel.add(nameField, gbc);

        // Year
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END; // Align the label to the end of its display area
        JLabel yearLabel = new JLabel("Year:");
        panel.add(yearLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START; // Reset the anchor for the text field
        yearField = new JTextField(10);
        panel.add(yearField, gbc);


        //Buttons
        gbc.gridy++;
        gbc.insets = new Insets(60, 10, 10, 10); // Larger top inset for spacing
        gbc.anchor = GridBagConstraints.LINE_END; // Align buttons to the right

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //X Button
        JButton cancelButton = new JButton("X");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
                new HomePage(); // Go back to home page
            }
        });
        buttonPanel.add(cancelButton);

        //Ok Button
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform any necessary actions here, such as saving data to database
                dispose(); // Close the window
                new HomePage(); // Go back to home page
            }
        });
        buttonPanel.add(okButton);

        panel.add(buttonPanel, gbc);

        add(panel);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    //MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AlbumAdd();
            }
        });
    }
}
