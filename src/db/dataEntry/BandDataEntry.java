package db.dataEntry;

import db.DataEntry;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BandDataEntry implements DataEntry {
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

    public BandDataEntry(int bandID, String bandName, int foundationYear, int disbandYear) {
        this.bandID = bandID;
        this.bandName = bandName;
        this.disbandYear = disbandYear;
        this.foundationYear = foundationYear;
    }

    Map<String, Integer> fieldIndexMap = new HashMap<String, Integer>();
    String[] fields = new String[]{"bandID", "bandName", "foundationYear", "disbandYear"};


    public ResultSet parseFrom(ResultSet resultSet, String... fields) {
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
        } catch (SQLException e) {
            //TODO handle
        }
        return resultSet;
    }

    public BandDataEntry() {
        for (int i = 0; i < fields.length; i++)
            fieldIndexMap.put(fields[i], i);
    }

    @Override
    public String toSQL(String... fields) {
        if (fields.length == 0)
            fields = this.fields;
        StringBuilder sql = new StringBuilder();
        sql.append('(');
        for (int i = 0; i < fields.length; i++) {
            if (i > 0)
                sql.append(", ");
            sql.append(getField(fields[i]));
        }
        sql.append(')');
        return sql.toString();
    }
}
