package ui;

import db.AlbumTable;
import run.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Math.round;

public class EditInfo {
    private JFrame frame;
    private JPanel mainPanel;

    // Window
    public EditInfo(String title, String album, String band,
                    float rating, int reviews, String genre,
                    int year, String urlString, int songID,
                    int albumID, String[] args) {
        frame = new JFrame("Edit Info");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Window

        JPanel panel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        int coverSize = 250;
        JLabel coverLabel = new JLabel(new ImageIcon(SearchResult.getCover(urlString, coverSize, coverSize)));
        coverLabel.setPreferredSize(new Dimension(coverSize + 20, coverSize + 200));
        leftPanel.add(coverLabel);

        JButton changePictureButton = new JButton("Change picture");
        changePictureButton.setPreferredSize(new Dimension(90, 40));
        leftPanel.add(changePictureButton);

        changePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UpdatePopUp("picture URL", Main.albums, Main.albums.pictureLabel, Main.albums.idLabel, albumID, args);

            }
        });


        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        //Song Title

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel titleLabel = new JLabel("" + title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        rightPanel.add(titleLabel, gbc);

        // Band
        gbc.gridy++;
        JLabel bandLabel = new JLabel("Artist: " + band);
        bandLabel.setFont(new Font("Arial", Font.BOLD, 24));
        rightPanel.add(bandLabel, gbc);



        // Album
        gbc.gridy++;
        JLabel albumLabel = new JLabel("Album: " + album);
        albumLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rightPanel.add(albumLabel, gbc);
        //Rating, Number of ratings
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel ratingNumberLabel = new JLabel("" + 1.0 * round(rating * 100) / 100 + "★, " + reviews + " reviews");
        ratingNumberLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        rightPanel.add(ratingNumberLabel, gbc);

        //Genre

        gbc.gridy++;
        JLabel genreLabel = new JLabel("Genre: " + genre);
        genreLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        JButton changeGenreButton = new JButton("Edit genre");
        rightPanel.add(genreLabel, gbc);
        gbc.gridx++;
        rightPanel.add(changeGenreButton, gbc);

        changeGenreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UpdatePopUp("genre", Main.songs, Main.songs.genreLabel, Main.songs.idLabel, songID, args);
            }
        });

        gbc.gridy += 2;

        JButton deleteButton = new JButton("Delete song");
        deleteButton.setPreferredSize(new Dimension(150, 30));
        rightPanel.add(deleteButton, gbc);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete the song?", "Delete confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION) {
                    try {
                        Main.songs.deleteById(songID, Main.songs.idLabel);
                        ResultSet resultSet = Main.songs.songSearchTopRated(
                                args[0], args[1], args[2], args[3]
                        );
                        new SearchResult(resultSet, args);
                    } catch(SQLException ex) {
                        new HomePage();
                    }
                    frame.dispose();
                }
            }
        });


        panel.add(rightPanel, BorderLayout.CENTER);

        panel.add(leftPanel, BorderLayout.WEST);
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }


    // MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }
}

