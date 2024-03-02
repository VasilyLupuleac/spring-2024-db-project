package db.dataEntry;

import db.DataEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SongReviewDataEntry implements DataEntry {
    int reviewID;
    float rating;
    String text;
    String author;
    Date date;

    SongReviewDataEntry (int reviewID, float rating, String text, String author, Date date) {
        this.reviewID = reviewID;
        this.rating = rating;
        this.text = text;
        this.author = author;
    }

    @Override
    public String toSQL() {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        return "(" + String.join(", ", new String[]{Integer.toString(reviewID),
                Float.toString(rating), text, author, df.format(date)}) + ")";
    }
}
