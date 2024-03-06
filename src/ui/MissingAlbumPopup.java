package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MissingAlbumPopup extends JDialog {
    private boolean addAlbumClicked = false;

    public MissingAlbumPopup(JFrame parent) {
        super(parent, "Album Not Found", true); // Set modal to true for a modal dialog
        setSize(400, 200);
        setResizable(false); // Ensure dialog is not resizable
        setLocationRelativeTo(parent); // Center the dialog relative to the parent frame

        JPanel panel = new JPanel(new BorderLayout());

        JLabel messageLabel = new JLabel("The album is not in our database. Do you want to add it?");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAlbumClicked = true;
                dispose(); // Close the dialog after clicking Yes
            }
        });
        buttonPanel.add(yesButton);

        JButton noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog after clicking No
            }
        });
        buttonPanel.add(noButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public boolean isAddAlbumClicked() {
        return addAlbumClicked;
    }
}
