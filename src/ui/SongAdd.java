package ui;

import run.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SongAdd {
    private JFrame frame;
    private JTextField titleField, bandField, albumField, orderField;

    
    //Window
    public SongAdd() {

        frame = new JFrame("Add Song");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set default close operation
    


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

        //order number
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel orderLabel = new JLabel("Sequence number in album:");
        panel.add(orderLabel, gbc);

        gbc.gridx = 1;
        orderField = new JTextField(20);
        panel.add(orderField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel errorMessage = new JLabel();
        errorMessage.setForeground(Color.red);
        Font f2 = errorMessage.getFont();
        errorMessage.setFont(f2.deriveFont(f2.getStyle() & ~Font.BOLD));
        panel.add(errorMessage, gbc);

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
                String title = titleField.getText();
                String band = bandField.getText();
                String album = albumField.getText();
                String orderNoStr = orderField.getText();
                int orderNo = 1;
                try {
                    orderNo = Integer.parseInt(orderNoStr);

                } catch (NumberFormatException ex) {
                    errorMessage.setText("Invalid sequence number");
                }
                try {
                    ResultSet result = Main.albums.selectByName(album, Main.albums.nameLabel);
                    result.next();
                    int albumID = result.getInt(Main.albums.idLabel);
                    openNextWindow(title, band, albumID, orderNo);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        buttonPanel.add(nextButton);
        panel.add(buttonPanel, gbc);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    //More Info Window 
    private void openNextWindow(String title, String band, int albumID, int orderNo) {
        JFrame moreInfoFrame = new JFrame("More Info");
        moreInfoFrame.setSize(400, 200);
        moreInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set default close operation
    
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
    
        JLabel genreLabel = new JLabel("Genre:");
        panel.add(genreLabel, gbc);
    
        gbc.gridx = 1;
        JTextField genreField = new JTextField(10);
        panel.add(genreField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel durationLabel = new JLabel("Duration:");
        panel.add(durationLabel, gbc);
    
        gbc.gridx = 1;
        JTextField durationField = new JTextField(10);
        panel.add(durationField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String genre = genreField.getText();
                String duration = durationField.getText();
                if (duration.isEmpty())
                    duration = "0";
                try {
                    Main.songs.addSong(title, albumID, Integer.parseInt(duration), genre, orderNo);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SongAdd();
            }
        });
    }
}




