package db.dataEntry;

import db.DataEntry;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class BandDataEntry extends DataEntry {
    public int bandID;
    public String bandName;
    public int foundationYear;
    public int disbandYear;

    public BandDataEntry newInstance() {
        return new BandDataEntry();
    }

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


    public BandDataEntry() {
        this.fields = new String[]{"BandID", "BandName", "FoundationDate", "DisbandDate"};
        fieldIndexMap = new HashMap<String, Integer>();
        for (int i = 0; i < fields.length; i++)
            fieldIndexMap.put(fields[i], i);
    }

    public BandDataEntry(int bandID, String bandName, int foundationYear, int disbandYear) {
        this.fields = new String[]{"BandID", "BandName", "FoundationDate", "DisbandDate"};
        initializeFieldMap();
        this.bandID = bandID;
        this.bandName = bandName;
        this.disbandYear = disbandYear;
        this.foundationYear = foundationYear;
    }



    public boolean parseFrom(ResultSet resultSet, String... fields) {
        try {
            resultSet.next();
            for (int i = 1; i <= fields.length; i++) {
                switch (fieldIndexMap.get(fields[i - 1])) {
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
