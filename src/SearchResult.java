package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResult {
    private JFrame frame;
    private JPanel mainPanel;

    // Window
    public SearchResult() {
        frame = new JFrame("Search Result");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create a scroll pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scrollPane);

        //Multiple panels 
        for (int i = 0; i < 10; i++) { //Displaying 10 panels
            mainPanel.add(createSongPanel());
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));   //spacing between panels
        }

        frame.setVisible(true);
        frame.setLocationRelativeTo(null); 
    }

//Panel for each song
private JPanel createSongPanel() {
    JPanel songPanel = new JPanel(new BorderLayout());

    //Left Panel for Photo
    JPanel leftPanel = new JPanel();
    leftPanel.setPreferredSize(new Dimension(100, 100)); // Adjust size according to your requirement
    leftPanel.setBackground(Color.lightGray); // Placeholder for Album Photo
    songPanel.add(leftPanel, BorderLayout.WEST);

    //Right Panel for Song Details
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    //Song Title
    JLabel titleLabel = new JLabel("  Song Title");
    titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    rightPanel.add(titleLabel);

    //Band / Album
    JLabel bandAlbumLabel = new JLabel("  Band / Album");
    bandAlbumLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    rightPanel.add(bandAlbumLabel);

    //Genre, Year
    JLabel genreYearLabel = new JLabel("  Genre, Year");
    genreYearLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    rightPanel.add(genreYearLabel);

    //Rating, Number of ratings
    JLabel ratingNumberLabel = new JLabel("  Rating, Number of ratings");
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
                new SearchResult();
            }
        });
    }
}
