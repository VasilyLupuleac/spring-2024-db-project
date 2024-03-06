package db;

import db.dataEntry.BandDataEntry;
import db.dataEntry.SongDataEntry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BandTable extends Table {

    public BandTable(Database db) {
        super(db, "Band", new BandDataEntry());
    }
}
