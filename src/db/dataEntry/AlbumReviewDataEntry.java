package db.dataEntry;

import db.DataEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlbumReviewDataEntry implements DataEntry {
    int reviewID;
    float rating;
    Date date;
    String text;
    String author;

    public AlbumReviewDataEntry (int reviewID, float rating, Date date, String text, String author) {
        this.reviewID = reviewID;
        this.rating = rating;
        this.date = date;
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
