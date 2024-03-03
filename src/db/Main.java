package db;

import java.io.*;

public class Main {
    public static void main(String[] args) {
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
            db.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }



    }
}