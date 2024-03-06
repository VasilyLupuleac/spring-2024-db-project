package db;

import db.dataEntry.BandDataEntry;
import db.dataEntry.SongDataEntry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class BandTable extends Table {
    public static String nameLabel = "BandName";
    public static String idLabel = "BandID";
    public static String foundationLabel = "FoundationDate";
    public static String disbandLabel = "DisbandDate";

    public BandTable(Database db) {
        super(db, "Band", new BandDataEntry());
    }

    public boolean addBand(String bandName, int foundationYear, int disbandYear) throws SQLException {
        if (disbandYear != 0 && disbandYear < foundationYear)
            return false;
        String query = insertQuery(nameLabel, foundationLabel, disbandLabel);
        PreparedStatement statement = db.prepare(query);
        statement.setString(1, bandName);
        if (foundationYear == 0)
            statement.setNull(2, Types.INTEGER);
        else
            statement.setInt(2, foundationYear);
        if (disbandYear == 0)
            statement.setNull(3, Types.INTEGER);
        else
            statement.setInt(3, disbandYear);
        db.execute(statement);
        return true;
    }

}
