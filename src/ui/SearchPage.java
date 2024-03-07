package ui;

import run.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchPage {
    private JFrame frame;

    public SearchPage() {
        //Window
        frame = new JFrame("Search");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, as it's a secondary window

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Insets for spacing


        //Labels and Text Fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(125, 25)); // Customize size
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel bandLabel = new JLabel("Band:");
        panel.add(bandLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField bandField = new JTextField();
        bandField.setPreferredSize(new Dimension(125, 25)); // Customize size
        panel.add(bandField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel albumLabel = new JLabel("Album:");
        panel.add(albumLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        JTextField albumField = new JTextField();
        albumField.setPreferredSize(new Dimension(125, 25)); // Customize size
        panel.add(albumField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel genreLabel = new JLabel("Genre:");
        panel.add(genreLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        JTextField genreField = new JTextField();
        genreField.setPreferredSize(new Dimension(125, 25)); // Customize size
        panel.add(genreField, gbc);



        // Sort Button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.LINE_END; // Align to the right
        gbc.insets = new Insets(20, 10, 10, 10); // Adjust top padding
        String[] options = {"Highest rating ↑↓", "Most reviews ↑↓"};
        JComboBox<String> sortOptions = new JComboBox<>(options);

        sortOptions.setPreferredSize(new Dimension(120, 30)); // Adjust size
        panel.add(sortOptions, gbc);

        // Search Button
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END; // Align to the right
        gbc.insets = new Insets(45, 30, 20, 10); // Adjust top and bottom padding
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(90, 40));
        panel.add(searchButton, gbc);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                try {
                    String args[] = new String[]{nameField.getText() ,albumField.getText(), bandField.getText(), genreField.getText()};
                    for (int i = 0; i < 4; i++)
                        if (args[i] == null)
                            args[i] = "";
                    ResultSet result;
                    if (sortOptions.getSelectedIndex() == 0)
                        result = Main.songs.songSearchTopRated(args[0], args[1], args[2], args[3]);
                    else
                        result = Main.songs.songSearchMostReviewed(args[0], args[1], args[2], args[3]);
                    openSearchResultWindow(result, args);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }

    // Method to open SearchResult window
    private void openSearchResultWindow(ResultSet result, String[] args) {
        // Create and display the SearchResult window
        frame.dispose();
        new SearchResult(result, args);
    }

    
    //MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SearchPage();
            }
        });
    }
}
