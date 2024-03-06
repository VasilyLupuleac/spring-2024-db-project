package db;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection dbConnection = null;

    public Database (String name, String host, String port, String username, String password) throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;
        Class.forName("org.postgresql.Driver");


        dbConnection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected successfully");
        // TODO change


    }


    // Unsafe! Only for testing
    public ResultSet executeRaw (String query) {
        try {
            Statement statement = dbConnection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public PreparedStatement prepare (String query) throws SQLException {
        return dbConnection.prepareStatement(query);
    }

    public ResultSet executeSelect (PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    public void execute (PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.execute();
    }

    public boolean close () {
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

    public ResultSet transaction(SplitStatement... statements) {
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


            return preparedStatement.executeQuery();


        } catch (SQLException e) {
            //TODO handle
            return null;
        }

    }

    public void delete (String tableName, String condition) {
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
