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
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append("?(?)");
        query.append(" VALUES (");
        int numEntries = entries.length;
        String[] placeholders = new String[numEntries];
        Arrays.fill(placeholders, "? ");
        query.append(String.join(", ", placeholders));
        query.append(");");
        try {
            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement(query.toString());
            preparedStatement.setString(1, table.getName());
            preparedStatement.setString(2, fieldOrder);
            for (int i = 0; i < numEntries; i++)
                preparedStatement.setString(i + 3, entries[i].toSQL());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            //TODO handle
            System.out.println("Couldn't insert into " + table.getName());
        }
    }

    public DataEntry[] select(Table table, String selectQuery, String condition, SQLParser parser) {
        StringBuilder query = new StringBuilder();
        query.append("select ? from ?");
        if (!condition.isEmpty())
            query.append("where ?");
        query.append(';');
        try {
            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement(query.toString());
            preparedStatement.setString(1, selectQuery);
            preparedStatement.setString(2, table.getName());
            if (!condition.isEmpty())
                preparedStatement.setString(3, condition);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parser.parseAll(resultSet);
        } catch (SQLException e) {
            //TODO handle
            System.out.println("Couldn't insert into " + table.getName());
            return null;
        }
    }
}
