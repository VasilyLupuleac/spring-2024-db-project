package db.dataEntry;

import db.DataEntry;


import java.sql.ResultSet;
import java.sql.SQLException;

public class BandDataEntry extends DataEntry {
    public int bandID;
    public String bandName;
    public int foundationYear;
    public int disbandYear;

    public String getField(String label) {
        switch (fieldIndexMap.get(label)) {
            case 0:
                return Integer.toString(bandID);
            case 1:
                return bandName;
            case 2:
                return Integer.toString(foundationYear);
            case 3:
                return Integer.toString(disbandYear);
        }
        return null;
    }

    protected String[] fields = new String[]{"BandID", "BandName", "FoundationDate", "DisbandDate"};

    public BandDataEntry(int bandID, String bandName, int foundationYear, int disbandYear) {
        initializeFieldMap();
        this.bandID = bandID;
        this.bandName = bandName;
        this.disbandYear = disbandYear;
        this.foundationYear = foundationYear;
    }

    public BandDataEntry() {
        initializeFieldMap();
    }


    public boolean parseFrom(ResultSet resultSet, String... fields) {
        try {
            resultSet.next();
            for (int i = 0; i < fields.length; i++) {
                switch (fieldIndexMap.get(fields[i])) {
                    case 0:
                        bandID = resultSet.getInt(i);
                        break;
                    case 1:
                        bandName = resultSet.getString(i);
                        break;
                    case 2:
                        foundationYear = resultSet.getInt(i);
                        break;
                    case 3:
                        disbandYear = resultSet.getInt(i);
                        break;
                }
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
