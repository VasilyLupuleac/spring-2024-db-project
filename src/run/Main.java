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
        System.out.println("Chicen toy :3 :3 :3");
        OpenConnectionPage startPage = new OpenConnectionPage();
        /*ResultSet result = bands.search("eat", bands.nameLabel);
        while (result.next()) {
            System.out.println(result.getString(bands.nameLabel));
        }

        // bands.addBand("Yes", 1968, -1);
        result = bands.search("Yes", bands.nameLabel);
        while (result.next()) {
            System.out.println(result.getInt(bands.foundationLabel));
        }*/


    }
}