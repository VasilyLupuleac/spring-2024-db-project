package db.dataEntry;

import db.DataEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumDataEntry extends DataEntry {
    int albumID;
    String title;
    int date;
    int bandID;
    String coverURL;

    public AlbumDataEntry newInstance() {
        return new AlbumDataEntry();
    }

    public AlbumDataEntry (int albumID, String title, int date, int bandID, String coverURL) {
        this.albumID = albumID;
        this.title = title;
        this.date = date;
        this.bandID = bandID;
        this.coverURL = coverURL;
    }

    public String getField(String label) {
        switch (fieldIndexMap.get(label)) {
            case 0:
                return Integer.toString(albumID);
            case 1:
                return title;
            case 2:
                return Integer.toString(date);
            case 3:
                return Integer.toString(bandID);
            case 4:
                return coverURL;
        }
        return null;
    }

    protected String[] fields = new String[]{"AlbumID", "Title", "ReleaseYear", "BandID", "CoverURL"};

    public AlbumDataEntry() {
        initializeFieldMap();
    }
    public boolean parseFrom(ResultSet resultSet, String... fields) {
        try {
            resultSet.next();
            for (int i = 1; i <= fields.length; i++) {
                switch (fieldIndexMap.get(fields[i - 1])) {
                    case 0:
                        albumID = resultSet.getInt(i);
                        break;
                    case 1:
                        title = resultSet.getString(i);
                        break;
                    case 2:
                        date = resultSet.getInt(i);
                        break;
                    case 3:
                        bandID = resultSet.getInt(i);
                        break;
                    case 4:
                        coverURL = resultSet.getString(i);
                        break;
                }
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
