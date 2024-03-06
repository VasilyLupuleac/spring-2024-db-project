package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AlbumTable extends Table {
    public static String nameLabel = "Title";
    public static String idLabel = "AlbumID";
    public static String yearLabel = "ReleaseYear";
    public static String pictureLabel = "CoverURL";
    public static String bandLabel = "BandID";

    public AlbumTable(Database db) {
        super(db, "Album");
    }

    public void addAlbum(String title, int bandID, int releaseYear, String coverURL) throws SQLException {
        if (coverURL.isEmpty())
            coverURL = "https://gospelmusic.io/build/images/default-album.png";
        String query = insertQuery(nameLabel, yearLabel, pictureLabel, bandLabel);
        PreparedStatement statement = db.prepare(query);
        statement.setString(1, title);
        if (releaseYear == 0)
            statement.setNull(2, Types.INTEGER);
        else
            statement.setInt(2, releaseYear);
        statement.setString(3, coverURL);
        statement.setInt(4, bandID);
        db.execute(statement);
    }

}
