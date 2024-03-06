package ui;

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

public class SearchResult {
    private JFrame frame;
    private JPanel mainPanel;

    // Window
    public SearchResult(ResultSet result) {
        frame = new JFrame("Search Results");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create a scroll pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scrollPane);

        try {
            while (result.next()) {
                String title = result.getString("title");
                String album = result.getString("album");
                String band = result.getString("band");
                int year = result.getInt("year");
                float rating = result.getFloat("rating");
                int reviews = result.getInt("reviews");
                String genre = result.getString("Genre");
                String url = result.getString("url");
                mainPanel.add(createSongPanel(title, album, band, rating, reviews, genre, year, url));
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));   //spacing between panels
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    static BufferedImage getCover(String urlString) {
        BufferedImage raw = null;
        try {
            URL url = new URL(urlString);
            raw = ImageIO.read(url);}
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        Image scaled = raw.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        BufferedImage result = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        result.getGraphics().drawImage(scaled, 0, 0, null);
        return result;
    }
    //Panel for each song
    private JPanel createSongPanel(String title, String album, String band,
                                   float rating,
                                   int reviews,
                                   String genre, int year, String urlString) {
        JPanel songPanel = new JPanel(new BorderLayout());

        //Left Panel for Photo
        JPanel leftPanel = new JPanel();
        BufferedImage cover = getCover(urlString);
        JLabel picLabel = new JLabel(new ImageIcon(cover));
        leftPanel.add(picLabel);
        leftPanel.setPreferredSize(new Dimension(100, 100)); // Adjust size according to your requirement
        leftPanel.setBackground(Color.lightGray); // Placeholder for Album Photo
        songPanel.add(leftPanel, BorderLayout.WEST);

        //Right Panel for Song Details
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        //Song Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(titleLabel);

        //Band / Album
        JLabel bandAlbumLabel = new JLabel(band + " - " + album);
        bandAlbumLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(bandAlbumLabel);

        //Genre, Year
        JLabel genreYearLabel = new JLabel(genre + ", " + year);
        genreYearLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(genreYearLabel);
        //Rating, Number of ratings
        JLabel ratingNumberLabel = new JLabel(" " + 1.0 * round(rating * 100) / 100 + "★, " + reviews + " reviews");
        ratingNumberLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(ratingNumberLabel);

        songPanel.add(rightPanel, BorderLayout.CENTER);

        //Panel for Edit Button
        JPanel editButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditInfoWindow();
            }
        });
        editButtonPanel.add(editButton);
        songPanel.add(editButtonPanel, BorderLayout.SOUTH);

        return songPanel;
    }


    // Open EditInfo Window
    private void openEditInfoWindow() {
        new EditInfo();
    }

    // Open LeaveReview Window
    private void openLeaveReviewWindow() {
        new LeaveReview();
    }


    // MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println("run");
                //new SearchResult();
            }
        });
    }
}
