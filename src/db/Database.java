package db;

import java.sql.*;

public class Database {
    private Connection dbConnection;
    public Database(String name, String host, String port) {
        // TODO open connection
    }
    public void insert(Table table, DataEntry[] entries) {
        // TODO
    }
    public DataEntry[] select(Table table, String query) {
        return null;
    }
}
