package db;

import java.sql.*;
import java.util.Arrays;

public class Database {
    private Connection dbConnection = null;
    public Database(String name, String host, String port, String username, String password) {
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find the driver");
            return;
            // TODO handle
        }
        try {
            dbConnection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully");
            // TODO change
        }
        catch (SQLException e) {
            System.out.println("Cannot connect to the database");
            // TODO handle
        }

    }
    public boolean close() {
        if (dbConnection != null) {
            try {
                dbConnection.close();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }
    public void insert(Table table, DataEntry[] entries, String fieldOrder) {
        // TODO
    }
    public DataEntry[] select(Table table, String query) {
        return null;
    }
}
