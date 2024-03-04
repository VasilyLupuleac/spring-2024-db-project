package db.dataEntry;

import db.DataEntry;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SongDataEntry extends DataEntry {
    int songID;
    String title;
    int albumID;
    int duration;
    String genre;
    int orderNo;

    public SongDataEntry ( int songID, String title, int albumID, int duration, String genre, int orderNo) {
        this.songID = songID;
        this.title = title;
        this.albumID = albumID;
        this.duration = duration;
        this.genre = genre;
        this.orderNo = orderNo;
    }

    public String getField(String label) {
        switch (fieldIndexMap.get(label)) {
            case 0:
                return Integer.toString(songID);
            case 1:
                return title;
            case 2:
                return Integer.toString(albumID);
            case 3:
                return Integer.toString(duration);
            case 4:
                return genre;
            case 5:
                return Integer.toString(orderNo);
        }
        return null;
    }

    protected String[] fields = new String[]{"SongID", "Title", "AlbumID", "Duration", "Genre", "OrderNo"};

    public SongDataEntry() {
        initializeFieldMap();
    }
    public ResultSet parseFrom(ResultSet resultSet, String... fields) {
        try {
            resultSet.next();
            for (int i = 0; i < fields.length; i++) {
                switch (fieldIndexMap.get(fields[i])) {
                    case 0:
                        songID = resultSet.getInt(i);
                        break;
                    case 1:
                        title = resultSet.getString(i);
                        break;
                    case 2:
                        albumID = resultSet.getInt(i);
                        break;
                    case 3:
                        duration = resultSet.getInt(i);
                        break;
                    case 4:
                        genre = resultSet.getString(i);
                        break;
                    case 5:
                        orderNo = resultSet.getInt(i);
                }
            }
        } catch (SQLException e) {
            //TODO handle
        }
        return resultSet;
    }

}
