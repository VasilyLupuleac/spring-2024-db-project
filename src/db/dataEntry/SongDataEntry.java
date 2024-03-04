package db.dataEntry;

import db.DataEntry;

public class SongDataEntry extends DataEntry {
    int songID;
    String title;
    int duration;
    String genre;
    int orderNo;

    public SongDataEntry ( int songID, String title, int duration, String genre, int orderNo) {
        this.songID = songID;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.orderNo = orderNo;
    }

    @Override
    public  String toSQL() {
        return "(" + String.join(", ", new String[]{Integer.toString(songID),
                title, Integer.toString(duration), genre, Integer.toString(orderNo)}) + ")";
    }

}
