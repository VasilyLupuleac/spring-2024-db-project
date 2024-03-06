package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SongTable extends Table {
    public String nameLabel = "Title";
    public String idLabel = "SongID";
    public String durationLabel = "Duration";
    public String genreLabel = "Genre";
    public String albumLabel = "AlbumID";
    public String orderLabel = "OrderNo";
    private AlbumTable albumTable;
    private BandTable bandTable;

    public SongTable(Database db, AlbumTable albums, BandTable bands) {
        super(db, "Song");
        this.albumTable = albums;
        this.bandTable = bands;
    }

    public void addSong(String title, int albumID, int duration, String genre, int orderNumber) throws SQLException {
        String query = insertQuery(nameLabel, durationLabel, genreLabel, albumLabel, orderLabel);
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

    public ResultSet songSearch(String song, String album, String band, String genre) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("begin transaction");
        query.append("create view SearchView as\n");
        query.append(String.format("select %s.%s as id, %s.%s as title, %s as genre,", name, nameLabel, genreLabel));
        query.append(String.format("%s.%s as album, %s as year", albumTable.name, albumTable.nameLabel, albumTable.yearLabel));
        query.append(String.format("%s.%s as band, ", bandTable.name, bandTable.nameLabel));
        query.append(String.format("(select avg(SongReview.Rating) where SongReview.BandID = %s.%s) as rating\n", name, nameLabel));
        query.append(String.format("from %s join %s ", name, albumTable.name));
        query.append(String.format("on %s.%s = %s.%s\n", name, albumLabel, albumTable.name, albumTable.nameLabel));
        query.append(String.format("join %s ", bandTable.name));
        query.append(String.format("on %s.%s = %s.%s\n", albumTable.name, albumTable.bandLabel, bandTable.name, bandTable.nameLabel));
        query.append(String.format("where %s.%s like ? ", albumTable.name, albumTable.nameLabel));
        query.append(String.format("and %s.%s like ? ", bandTable.name, bandTable.nameLabel));
        query.append(String.format("and %s like ?;\n", genreLabel));
        query.append("select * from SearchView;\n");
        query.append("commit;\n");
        PreparedStatement statement = db.prepare(query.toString());
        statement.setString(1, "%" + song + "%");
        statement.setString(2, "%" + album + "%");
        statement.setString(3, "%" + band + "%");
        statement.setString(4, "%" + genre + "%");
        return db.executeSelect(statement);
    }
}
