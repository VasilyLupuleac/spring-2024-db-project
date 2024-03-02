package db.dataEntry;

import db.DataEntry;
import db.SQLParser;
import db.parser.BandParser;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class BandDataEntry implements DataEntry {
    public int bandID;
    public String bandName;
    public int foundationYear;
    public int disbandYear;

    /* enum Field {
        BAND_ID,
        BAND_NAME,
        FOUNDATION_YEAR,
        DISBAND_YEAR;
    }
    Map<Field, SQLParser> fieldParsers = new HashMap<Field, SQLParser>();


    public SQLParser makeParser(Field ... fields) {
        class Parser implements SQLParser {
            @Override
            public DataEntry[] parseAll(ResultSet resultSet) {
                while(resultSet.next())
                // TODO
            }
        }
    } */

    public BandDataEntry(int bandID, String bandName, int foundationYear, int disbandYear) {
        this.bandID = bandID;
        this.bandName = bandName;
        this.disbandYear = disbandYear;
        this.foundationYear = foundationYear;
    }

    @Override
    public String toSQL() {
        return "(" + String.join(", ", new String[]{Integer.toString(bandID),
                bandName, Integer.toString(foundationYear), Integer.toString(disbandYear)}) + ")";
    }
}
