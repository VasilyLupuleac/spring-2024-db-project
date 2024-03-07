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
    private PreparedStatement searchStatementTopRated;
    private PreparedStatement searchStatementMostReviewed;

    public SongTable(Database db, AlbumTable albums, BandTable bands) {
        super(db, "Song");
        this.albumTable = albums;
        this.bandTable = bands;
        try {
            initializeSearchStatements();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void initializeSearchStatements() throws SQLException {
        StringBuilder query = new StringBuilder();
        //query.append("drop view if exists SearchView;\n");
        //query.append("create view SearchView as\n");
        query.append(String.format("select %s.%s as title, %s.%s as songID, %s as genre, %s.%s as albumID, ", name, nameLabel, name, idLabel, genreLabel, name, albumLabel));
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
        query.append(String.format("and %s like ?\n", genreLabel));
        //query.append("select * from SearchView;\n");
        searchStatementTopRated = db.prepare(query.toString() + "order by rating desc;");
        searchStatementMostReviewed = db.prepare(query.toString() + "order by reviews desc;"); // Descending due to results representation
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

    public ResultSet songSearchTopRated(String song, String album, String band, String genre) throws SQLException {
        searchStatementTopRated.setString(1, "%" + song + "%");
        searchStatementTopRated.setString(2, "%" + album + "%");
        searchStatementTopRated.setString(3, "%" + band + "%");
        searchStatementTopRated.setString(4, "%" + genre + "%");
        return db.executeSelect(searchStatementTopRated);
    }

    public ResultSet songSearchMostReviewed(String song, String album, String band, String genre) throws SQLException {
        searchStatementMostReviewed.setString(1, "%" + song + "%");
        searchStatementMostReviewed.setString(2, "%" + album + "%");
        searchStatementMostReviewed.setString(3, "%" + band + "%");
        searchStatementMostReviewed.setString(4, "%" + genre + "%");
        return db.executeSelect(searchStatementMostReviewed);
    }
}
