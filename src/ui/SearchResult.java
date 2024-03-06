package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResult {
    private JFrame frame;

    //Window
    public SearchResult() {
        frame = new JFrame("Search Result");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top Result Panel
        JPanel topResultPanel = new JPanel(new BorderLayout());
        JLabel topResultLabel = new JLabel("Top Result");
        topResultPanel.add(topResultLabel, BorderLayout.CENTER);
        mainPanel.add(topResultPanel, BorderLayout.NORTH);

        // Songs Panel
        JPanel songsPanel = new JPanel(new BorderLayout());
        JLabel songsLabel = new JLabel("Songs");
        songsPanel.add(songsLabel, BorderLayout.NORTH);
        mainPanel.add(songsPanel, BorderLayout.CENTER);

        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditInfoWindow();
            }
        });
        buttonPanel.add(editButton);

        JButton leaveReviewButton = new JButton("Leave Review");
        leaveReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLeaveReviewWindow();
            }
        });
        buttonPanel.add(leaveReviewButton);

        JButton showAllButton = new JButton("Show All");
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to show all songs
            }
        });
        buttonPanel.add(showAllButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }


    //Open EditInfo Window
    private void openEditInfoWindow() {
        new EditInfo();
    }

    //Open LeaveReview Window
    private void openLeaveReviewWindow() {
        new LeaveReview();
    }


    //MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SearchResult();
            }
        });
    }
}
