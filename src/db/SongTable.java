package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SongTable extends Table {
    public String titleLabel = "Title";
    public String idLabel = "SongID";
    public String durationLabel = "Duration";
    public String genreLabel = "Genre";
    public String albumLabel = "AlbumID";
    public String orderLabel = "OrderNo";

    public SongTable(Database db) {
        super(db, "Song");
    }

    public void addSong(String title, int albumID, int duration, String genre, int orderNumber) throws SQLException {
        String query = insertQuery(titleLabel, durationLabel, genreLabel, albumLabel, orderLabel);
        PreparedStatement statement = db.prepare(query);
        statement.setString(1, title);
        if (duration == 0)
            statement.setNull(2, Types.INTEGER);
        else
            statement.setInt(2, duration);
        statement.setString(3, genre);
        statement.setInt(4, albumID);
        if (orderNumber == 0)
            statement.setNull(5, Types.INTEGER);
        else
            statement.setInt(5, orderNumber);
        db.execute(statement);

    }
}
