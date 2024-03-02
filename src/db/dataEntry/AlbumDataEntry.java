package db.dataEntry;

import db.DataEntry;

public class AlbumDataEntry implements DataEntry {
    int albumID;
    String title;
    int date;
    String coverURL;

    public AlbumDataEntry (int albumID, String title, int date, String coverURL) {
        this.albumID = albumID;
        this.title = title;
        this.date = date;
        this.coverURL = coverURL;
    }

    @Override
    public String toSQL() {
        return "(" + String.join(", ", new String[]{Integer.toString(albumID),
                title, Integer.toString(date), coverURL}) + ")";
    }
}
