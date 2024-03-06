import db.*;

import ui.HomePage;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static Database db;
    public static BandTable bands;
    public static AlbumTable albums;

    public static void main(String[] args) throws SQLException {
        System.out.println("Chicen toy :3 :3 :3");
        HomePage home = new HomePage();
        home.main(args);
        try {
            FileReader file = new FileReader("config.txt");
            BufferedReader reader = new BufferedReader(file);
            String name = reader.readLine();
            String host = reader.readLine();
            String port = reader.readLine();
            String username = reader.readLine();
            String password = reader.readLine();

            reader.close();
            file.close();
            db = new Database(name, host, port, username, password);
            bands = new BandTable(db);
            ResultSet result = bands.search("eat", bands.nameLabel);
            while (result.next()) {
                System.out.println(result.getString(bands.nameLabel));
            }

            // bands.addBand("Yes", 1968, -1);
            result = bands.search("Yes", bands.nameLabel);
            while (result.next()) {
                System.out.println(result.getInt(bands.foundationLabel));
            }

            db.close();
                  } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}