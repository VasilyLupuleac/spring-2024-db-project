package db.dataEntry;

import db.DataEntry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SongReviewDataEntry extends DataEntry {
    int reviewID;
    float rating;
    String text;
    String author;
    Date date;
    int songID;

    public SongReviewDataEntry newInstance() {
        return new SongReviewDataEntry();
    }

    SongReviewDataEntry (int reviewID, float rating, String text, String author, Date date, int songID) {
        this.reviewID = reviewID;
        this.rating = rating;
        this.text = text;
        this.author = author;
        this.date = date;
        this.songID = songID;
    }
    public String getField(String label) {
        switch (fieldIndexMap.get(label)) {
            case 0:
                return Integer.toString(reviewID);
            case 1:
                return Float.toString(rating);
            case 2:
                return text;
            case 3:
                return author;
            case 4:
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                return df.format(date);
            case 5:
                return Integer.toString(songID);
        }
        return null;
    }

    protected String[] fields = new String[]{"ReviewID", "Rating", "ReviewText", "Author", "ReviewDate", "songID"};

    public SongReviewDataEntry() {
        initializeFieldMap();
    }
    public boolean parseFrom(ResultSet resultSet, String... fields) {
        try {
            resultSet.next();
            for (int i = 1; i <= fields.length; i++) {
                switch (fieldIndexMap.get(fields[i - 1])) {
                    case 0:
                        reviewID = resultSet.getInt(i);
                        break;
                    case 1:
                        rating = resultSet.getFloat(i);
                        break;
                    case 2:
                        text = resultSet.getString(i);
                        break;
                    case 3:
                        author = resultSet.getString(i);
                        break;
                    case 4:
                        date = resultSet.getDate(i);
                        break;
                    case 5:
                        songID = resultSet.getInt(i);
                }
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
