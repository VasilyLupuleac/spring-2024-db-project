package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        } catch (SQLException e) {
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

    public List<DataEntry> transaction(SQLParser parser, SplitStatement... statements) {
        // TODO implement split statements
        StringBuilder query = new StringBuilder();
        query.append("begin transaction;\n");
        ArrayList<String> args = new ArrayList<>();
        for (SplitStatement st : statements) {
            query.append(st.getQuery());
            args.addAll(st.getArgs());
        }
        query.append("commit;");
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query.toString());
            for (int i = 0; i < args.size(); i++)
                preparedStatement.setString(i + 1, args.get(i));


            ResultSet resultSet = preparedStatement.executeQuery();
            return parser.parseAll(resultSet);

        } catch (SQLException e) {
            //TODO handle
            return null;
        }

    }

    public void insert(String tableName, List<DataEntry> entries, String fieldOrder) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append("?(?)\n");
        query.append(" VALUES (");
        int numEntries = entries.size();
        String[] placeholders = new String[numEntries];
        Arrays.fill(placeholders, "? ");
        query.append(String.join(", ", placeholders));
        query.append(");");
        try {
            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement(query.toString());
            preparedStatement.setString(1, tableName);
            preparedStatement.setString(2, fieldOrder);
            for (int i = 0; i < numEntries; i++)
                preparedStatement.setString(i + 3, entries.get(i).toSQL());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            //TODO handle
            System.out.println("Couldn't insert into " + tableName);
        }
    }

    public List<DataEntry> select(String tableName, String selectQuery, String condition, SQLParser parser) {
        StringBuilder query = new StringBuilder();
        query.append("select ?\n from ?\n");
        query.append(condition.isEmpty() ? ";" : "where ?;");
        try {
            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement(query.toString());
            preparedStatement.setString(1, selectQuery);
            preparedStatement.setString(2, tableName);
            if (!condition.isEmpty())
                preparedStatement.setString(3, condition);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parser.parseAll(resultSet);
        } catch (SQLException e) {
            //TODO handle
            System.out.println("Couldn't get the data from " + tableName);
            return null;
        }
    }

    public void delete(String tableName, String condition) {
        String queryString = "delete from ?\n" + (condition.isEmpty() ? ";" : "where ?;");
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(queryString);
            preparedStatement.setString(1, tableName);
            if (!condition.isEmpty())
                preparedStatement.setString(2, condition);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            //TODO handle
            System.out.println("Couldn't delete from " + tableName);
        }
    }
    // TODO implement update
}
