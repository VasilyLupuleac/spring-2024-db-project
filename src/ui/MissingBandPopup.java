package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MissingBandPopup extends JDialog {
    private boolean addBandClicked = false;

    public MissingBandPopup(JFrame parent) {
        //Window
        super(parent, "Band Not Found", true); // Set modal to true for a modal dialog
        setSize(400, 200);
        setResizable(false); // Ensure dialog is not resizable
        setLocationRelativeTo(parent); // Center the dialog relative to the parent frame

        JPanel panel = new JPanel(new BorderLayout());

        JLabel messageLabel = new JLabel("The band is not in our database. Do you want to add it?");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Yes Button
        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBandClicked = true;
                dispose(); // Close the dialog after clicking Yes
                openBandAddOptions(); // Open BandAddOptions window
            }
        });
        buttonPanel.add(yesButton);

        // No Button
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

    public boolean isAddBandClicked() {
        return addBandClicked;
    }

    // Open BandAddOptions window
    private void openBandAddOptions() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BandAddOptions();
            }
        });
    }
}
