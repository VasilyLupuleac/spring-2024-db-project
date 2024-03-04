package db.dataEntry;

import db.DataEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class bmDataEntry extends DataEntry {
    int musicianID;
    int bandID;
    String role;

    public bmDataEntry (int musicianID, int bandID, String role) {
        this.musicianID = musicianID;
        this.bandID = bandID;
        this.role = role;
    }

    public String getField(String label) {
        switch (fieldIndexMap.get(label)) {
            case 0:
                return Integer.toString(musicianID);
            case 1:
                return Integer.toString(bandID);
            case 2:
                return role;
        }
        return null;
    }

    protected String[] fields = new String[]{"MusicianID", "BandID", "Role"};

    public bmDataEntry() {
        initializeFieldMap();
    }
    public ResultSet parseFrom(ResultSet resultSet, String... fields) {
        try {
            resultSet.next();
            for (int i = 0; i < fields.length; i++) {
                switch (fieldIndexMap.get(fields[i])) {
                    case 0:
                        musicianID = resultSet.getInt(i);
                        break;
                    case 1:
                        bandID = resultSet.getInt(i);
                        break;
                    case 2:
                        role  = resultSet.getString(i);
                        break;
                }
            }
        } catch (SQLException e) {
            //TODO handle
        }
        return resultSet;
    }
}
