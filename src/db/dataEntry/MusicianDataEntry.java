package db.dataEntry;

import db.DataEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MusicianDataEntry implements DataEntry {
    int musicianID;
    String musicianName;
    Date dateOfBirth;
    char gender;
    String country;

    public MusicianDataEntry (int musicianID, String musicianName, Date dateOfBirth, char gender, String country) {
        this.musicianID = musicianID;
        this.musicianName = musicianName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.country = country;
    }

    @Override
    public String toSQL() {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        return "(" + String.join(", ", new String[]{Integer.toString(musicianID),
                musicianName, df.format(dateOfBirth), String.valueOf(gender), country}) + ")";
    }
}
