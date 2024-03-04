package db.dataEntry;

import db.DataEntry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MusicianDataEntry extends DataEntry {
    int musicianID;
    String musicianName;
    Date dateOfBirth;
    char gender;
    String country;

    public MusicianDataEntry newInstance() {
        return new MusicianDataEntry();
    }

    public MusicianDataEntry(int musicianID, String musicianName, Date dateOfBirth, char gender, String country) {
        this.musicianID = musicianID;
        this.musicianName = musicianName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.country = country;
    }

    public String getField(String label) {
        switch (fieldIndexMap.get(label)) {
            case 0:
                return Integer.toString(musicianID);
            case 1:
                return musicianName;
            case 2:
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                return df.format(dateOfBirth);
            case 3:
                return Character.toString(gender);
            case 4:
                return country;
        }
        return null;
    }

    protected String[] fields = new String[]{"MusicianID", "MusicianName", "DateOfBirth", "Gender", "Country"};

    public MusicianDataEntry() {
        initializeFieldMap();
    }

    public boolean parseFrom(ResultSet resultSet, String... fields) {
        try {
            resultSet.next();
            for (int i = 0; i < fields.length; i++) {
                switch (fieldIndexMap.get(fields[i])) {
                    case 0:
                        musicianID = resultSet.getInt(i);
                        break;
                    case 1:
                        musicianName = resultSet.getString(i);
                        break;
                    case 2:
                        dateOfBirth = resultSet.getDate(i);
                        break;
                    case 3:
                        gender = resultSet.getString(i).charAt(0);
                        break;
                    case 4:
                        country = resultSet.getString(i);
                        break;
                }
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}

