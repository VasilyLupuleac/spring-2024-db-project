package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HomePage {
    private JFrame frame;

    public HomePage() {
        frame = new JFrame("Home Page");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Window
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(welcomeLabel, gbc);

        //Search Button
        gbc.gridy++;
        JButton searchButton = new JButton("Search 🔍");
        searchButton.setPreferredSize(new Dimension(400, 50));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSearchWindow();
            }
        });
        panel.add(searchButton, gbc);

        //Add Button
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


    //Search Window 
    private void openSearchWindow() {
        new SearchPage();
    }

    //Add Options Window 
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

    //Add SONG Window
    private void openAddSongWindow() {
    /*try {
        // Establish connection to your SQL database here
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "username", "password");
        new SongAdd(connection);
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(frame, "Error connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
    } */
    closeHomePage();
    new SongAdd(null);
}


    //Add BAND Windpow
    private void openAddBandWindow() {
        closeHomePage(); //Close the HomePage frame
        new BandAddOptions();
    } 


    //Closing
    private void closeHomePage() {
        frame.dispose(); //Close the HomePage frame
    }
    
    //Add ALBUM Window
    private void openAddAlbumWindow() {
        closeHomePage();
        new AlbumAdd();
    
    }

    //MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage();
            }
        });
    }
}


