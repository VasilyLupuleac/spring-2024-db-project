package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SongAdd {
    private JFrame frame;
    private JTextField titleField, bandField, albumField;
    private Connection connection;
    
    //Window
    public SongAdd(Connection connection) {
        this.connection = connection;
        frame = new JFrame("Add Song");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Override window close behavior


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Title:");
        panel.add(titleLabel, gbc);

        gbc.gridx = 1;
        titleField = new JTextField(20);
        panel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel bandLabel = new JLabel("Band:");
        panel.add(bandLabel, gbc);

        gbc.gridx = 1;
        bandField = new JTextField(20);
        panel.add(bandField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel albumLabel = new JLabel("Album:");
        panel.add(albumLabel, gbc);

        gbc.gridx = 1;
        albumField = new JTextField(20);
        panel.add(albumField, gbc);

        //Cancel Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window
                new HomePage();
            }
        });
        buttonPanel.add(cancelButton);

        //Next Button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openNextWindow();
            }
        });
        buttonPanel.add(nextButton);
        panel.add(buttonPanel, gbc);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    //More Info Window 
    private void openNextWindow() {
        JFrame moreInfoFrame = new JFrame("More Info");
        moreInfoFrame.setSize(400, 200);
        moreInfoFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
    
        JLabel genreLabel = new JLabel("Genre:");
        panel.add(genreLabel, gbc);
    
        gbc.gridx = 1;
        JTextField genreField = new JTextField(20);
        panel.add(genreField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel durationLabel = new JLabel("Duration:");
        panel.add(durationLabel, gbc);
    
        gbc.gridx = 1;
        JTextField durationField = new JTextField(20);
        panel.add(durationField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String genre = genreField.getText();
                String duration = durationField.getText();
    
                // Add genre and duration to database
                addGenreAndDurationToDatabase(genre, duration);
    
                // Close the More Info window and return to HomePage
                moreInfoFrame.dispose();
                new HomePage();
            }
        });
        panel.add(okButton, gbc);
    
        moreInfoFrame.add(panel);
        moreInfoFrame.setVisible(true);
        moreInfoFrame.setLocationRelativeTo(frame);
    }
    
    private void addGenreAndDurationToDatabase(String genre, String duration) {
        // Add your code to insert genre and duration into the database here
        // Example: You can use a PreparedStatement similar to adding a song
    }
    


    
    //Example from chatGPT
    /*  private void addSongToDatabase() {
        String title = titleField.getText();
        String band = bandField.getText();
        String album = albumField.getText();

        if (title.isEmpty() || band.isEmpty() || album.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Assume you have a table called Songs with columns Title, Band, and Album
            String query = "INSERT INTO Songs (Title, Band, Album) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, band);
            preparedStatement.setString(3, album);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Song added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose(); // Close the window after adding the song
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding song to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } */
}

