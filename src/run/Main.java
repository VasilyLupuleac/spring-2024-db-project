package run;

import db.*;

import ui.HomePage;
import ui.OpenConnectionPage;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static Database db;
    public static BandTable bands;
    public static AlbumTable albums;
    public static SongTable songs;

    public static void setDatabase (Database newDB) {
        db = newDB;
        bands = new BandTable(db);
        albums = new AlbumTable(db);
        songs = new SongTable(db, albums, bands);
    }

    public static void close() {
        db.close();
    }

    public static void main (String[] args) throws SQLException {
        OpenConnectionPage startPage = new OpenConnectionPage();
    }
}