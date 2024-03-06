import db.*;
import db.dataEntry.BandDataEntry;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Chicen toy :3 :3 :3");
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
            Database db = new Database(name, host, port, username, password);
            Table band = new Table(db, "Band", new BandDataEntry());
            List<DataEntry> result = band.search("eat", "BandName");
            for (DataEntry entry : result)
                System.out.println(((BandDataEntry) entry).bandName);

            db.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}