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
    private PreparedStatement searchStatement;

    public SongTable(Database db, AlbumTable albums, BandTable bands) {
        super(db, "Song");
        this.albumTable = albums;
        this.bandTable = bands;
        try {
            initializeSearchStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void initializeSearchStatement() throws SQLException {
        StringBuilder query = new StringBuilder();
        //query.append("drop view if exists SearchView;\n");
        //query.append("create view SearchView as\n");
        query.append(String.format("select %s.%s as title, %s as genre,", name, nameLabel,  genreLabel));
        query.append(String.format("%s.%s as album, %s as year, %s as url, ", albumTable.name, albumTable.nameLabel, albumTable.yearLabel, albumTable.pictureLabel));
        query.append(String.format("%s.%s as band, ", bandTable.name, bandTable.nameLabel));
        query.append(String.format("(select avg(SongReview.Rating) as rating from SongReview where SongReview.SongID = %s.%s), ", name, idLabel));
        query.append(String.format("(select count(*) as reviews from SongReview where SongReview.SongID = %s.%s)\n", name, idLabel));
        query.append(String.format("from %s join %s ", name, albumTable.name));
        query.append(String.format("on %s.%s = %s.%s\n", name, albumLabel, albumTable.name, albumTable.idLabel));
        query.append(String.format("join %s ", bandTable.name));
        query.append(String.format("on %s.%s = %s.%s\n", albumTable.name, albumTable.bandLabel, bandTable.name, bandTable.idLabel));
        query.append(String.format("where %s.%s like ? ", name, nameLabel));
        query.append(String.format("and %s.%s like ? ", albumTable.name, albumTable.nameLabel));
        query.append(String.format("and %s.%s like ? ", bandTable.name, bandTable.nameLabel));
        query.append(String.format("and %s like ?;\n", genreLabel));
        //query.append("select * from SearchView;\n");
        searchStatement = db.prepare(query.toString());
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
        searchStatement.setString(1, "%" + song + "%");
        searchStatement.setString(2, "%" + album + "%");
        searchStatement.setString(3, "%" + band + "%");
        searchStatement.setString(4, "%" + genre + "%");
        return db.executeSelect(searchStatement);
    }
}
