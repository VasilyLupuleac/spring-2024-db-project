package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BandAddOptions {
    private JFrame frame;

    public BandAddOptions() {
        frame = new JFrame("Band Adding Options");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        //Window
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("How do you want to proceed with adding the band?");
        gbc.gridwidth = 2; // Span two columns
        panel.add(label, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1; // Reset gridwidth to default
        gbc.anchor = GridBagConstraints.CENTER; // Align components to center horizontally

        // Increase space between label and buttons
        gbc.insets = new Insets(20, 10, 10, 10);

        // Automatically Button
        gbc.gridx = 0; // Reset gridx to 0
        JButton automaticallyButton = new JButton("Automatically");
        automaticallyButton.setPreferredSize(new Dimension(150, 30)); // Set preferred size
        automaticallyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // !LOGIC for adding band automatically!
                JOptionPane.showMessageDialog(frame, "Band will be added automatically.");
            }
        });
        panel.add(automaticallyButton, gbc);


        // Manually Button
        gbc.gridx = 1; // Set gridx to 1 to place next to Automatically button
        JButton manuallyButton = new JButton("Manually");
        manuallyButton.setPreferredSize(new Dimension(150, 30)); // Set preferred size
        manuallyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // !LOGIC for adding band manually!
                openManualBandPage();
                frame.dispose(); // Close this frame after selecting manually
            }
        });
        panel.add(manuallyButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }


    // Manual Window
    private void openManualBandPage() {
        JFrame manualBandFrame = new JFrame("Manual Band Page");
        manualBandFrame.setSize(600, 400);

        JPanel panel = new JPanel();
        // Add components for manually adding a band here

        manualBandFrame.add(panel);
        manualBandFrame.setVisible(true);
        manualBandFrame.setLocationRelativeTo(null); // Center the frame on the screen
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BandAddOptions();
            }
        });
    }
}
