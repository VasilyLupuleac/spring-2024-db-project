package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage {
    private JFrame frame;

    public HomePage() {
        frame = new JFrame("Home Page");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(welcomeLabel, gbc);

        gbc.gridy++;
        JButton searchButton = new JButton("Search 🔍");
        searchButton.setPreferredSize(new Dimension(400, 50));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSearchWindow();
            }
        });
        panel.add(searchButton, gbc);


        gbc.gridy++;
        JButton addButton = new JButton("Add +");
        addButton.setPreferredSize(new Dimension(400, 50));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddOptions();
            }
        });
        panel.add(addButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void openSearchWindow() {
        JFrame searchFrame = new JFrame("Search");
        searchFrame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Search functionality will be implemented here.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        searchFrame.add(panel);
        searchFrame.setVisible(true);
    }

    private void openAddOptions() {
        final JDialog addOptionsDialog = new JDialog(frame, "Add Options", true); // Set modal to true for a modal dialog
        addOptionsDialog.setSize(300, 150);
        addOptionsDialog.setResizable(false); // Ensure dialog is not resizable
        addOptionsDialog.setLocationRelativeTo(frame); // Center the dialog relative to the frame


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton songButton = new JButton("Song");
        songButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddSongWindow();
                addOptionsDialog.dispose(); // Close the dialog after selection
            }
        });
        panel.add(songButton);

        JButton bandButton = new JButton("Band");
        bandButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddBandWindow();
                addOptionsDialog.dispose(); // Close the dialog after selection
            }
        });
        panel.add(bandButton);

        JButton albumButton = new JButton("Album");
        albumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddAlbumWindow();
                addOptionsDialog.dispose(); // Close the dialog after selection
            }
        });
        panel.add(albumButton);

        addOptionsDialog.add(panel);
        addOptionsDialog.setVisible(true);
    }

    private void openAddSongWindow() {
        JFrame addSongFrame = new JFrame("Add Song");
        addSongFrame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Add song functionality will be implemented here.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        addSongFrame.add(panel);
        addSongFrame.setVisible(true);
    }

    private void openAddBandWindow() {
        JFrame addBandFrame = new JFrame("Add Band");
        addBandFrame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Add band functionality will be implemented here.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        addBandFrame.add(panel);
        addBandFrame.setVisible(true);
    }

    private void openAddAlbumWindow() {
        JFrame addAlbumFrame = new JFrame("Add Album");
        addAlbumFrame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Add album functionality will be implemented here.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        addAlbumFrame.add(panel);
        addAlbumFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage();
            }
        });
    }
}


