package db.dataEntry;

import db.DataEntry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlbumReviewDataEntry extends DataEntry {
    int reviewID;
    float rating;
    int albumID;
    Date date;
    String text;
    String author;

    public AlbumReviewDataEntry newInstance() {
        return new AlbumReviewDataEntry();
    }


    public AlbumReviewDataEntry(int reviewID, float rating, int albumID, Date date, String text, String author) {
        this.reviewID = reviewID;
        this.rating = rating;
        this.albumID = albumID;
        this.date = date;
        this.text = text;
        this.author = author;
    }

    public String getField(String label) {
        switch (fieldIndexMap.get(label)) {
            case 0:
                return Integer.toString(reviewID);
            case 1:
                return Float.toString(rating);
            case 2:
                return Integer.toString(albumID);
            case 3:
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                return df.format(date);
            case 4:
                return text;
            case 5:
                return author;
        }
        return null;
    }

    protected String[] fields = new String[]{"ReviewID", "Rating", "AlbumID", "ReviewDate", "ReviewText", "Author"};

    public AlbumReviewDataEntry() {
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
                        albumID = resultSet.getInt(i);
                        break;
                    case 3:
                        date = resultSet.getDate(i);
                        break;
                    case 4:
                        text = resultSet.getString(i);
                        break;
                    case 5:
                        author = resultSet.getString(i);
                }
            }

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
