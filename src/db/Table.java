package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Table {
    protected Database db;
    protected String name;

    public Table(Database db, String name) {
        this.db = db;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String insertQuery(String... fields) {
        String argsArray[] = new String[fields.length];
        Arrays.fill(argsArray, "?");
        return String.format("insert into %s(%s) values (%s);",
                name, String.join(", ", fields), String.join(", ", argsArray));
    }

    public void setById (int id, String idLabel, String value, String fieldLabel) throws SQLException {
        String query = String.format("update %s set %s = ? where %s = ?;", name, fieldLabel, idLabel);
        PreparedStatement statement = db.prepare(query);
        statement.setString(1, value);
        statement.setInt(2, id);
        db.execute(statement);
    }

    public void deleteById(int id, String idLabel) throws SQLException {
        String query = String.format("delete from %s where %s = ?;", name, idLabel);
        PreparedStatement statement = db.prepare(query);
        statement.setInt(1, id);
        db.execute(statement);
    }

    public ResultSet search(String word, String searchField) throws SQLException {
        String query = String.format("Select * from %s where %s like ?;", name, searchField);
        PreparedStatement statement = db.prepare(query);
        statement.setString(1, "%" + word + "%");
        return db.executeSelect(statement);
    }

    public ResultSet selectByName(String nameValue, String nameField) throws SQLException {
        String query = String.format("Select * from %s where %s = ?;", name, nameField);
        PreparedStatement statement = db.prepare(query);
        statement.setString(1, nameValue);
        return db.executeSelect(statement);
    }

    public ResultSet getById(int idValue, String idField) throws SQLException {
        String query = String.format("Select * from %s where %s = ?;", name, idField);
        PreparedStatement statement = db.prepare(query);
        statement.setInt(1, idValue);
        return db.executeSelect(statement);
    }

}
