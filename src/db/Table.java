package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Table {
    private Database db;
    private String name;
    private DataEntry entrySample;

    public Table(Database db, String name, DataEntry entrySample) {
        this.entrySample = entrySample;
        this.db = db;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void insert(List<DataEntry> entries, String... fields) {
        db.insert(this.name, entries, String.join(", ", fields));
        // TODO check data?
    }

    public List<DataEntry> select(String selectQuery, String condition, DataEntry sample, String... fields) {
        return db.select(this.name, selectQuery, condition, new DataEntryParser(sample, fields));
        // TODO check data?
    }

    public List<DataEntry> select(String condition, String... fields) {
        return db.select(this.name, String.join(", ", fields), condition, new DataEntryParser(entrySample, fields));
        // TODO check data?
    }

    public void delete(String condition) {
        db.delete(this.name, condition);
        //TODO ?? not sure
    }

    public List<DataEntry> search(String word, String searchField) throws SQLException {
        String query = String.format("Select * from %s where %s like ?;", name, searchField);
        PreparedStatement statement = db.prepare(query);
        statement.setString(1, "%" + word + "%");
        return db.executeSelect(statement, new DataEntryParser(entrySample, entrySample.fields));
    }

    public List<DataEntry> selectIdByName(String nameValue, String nameField, String idField) throws SQLException {
        String query = String.format("Select %s from %s where %s = ?;", idField, name, nameField);
        PreparedStatement statement = db.prepare(query);
        statement.setString(1, nameValue);
        return db.executeSelect(statement, new DataEntryParser(entrySample, idField));

    }

    public Table join(Table other, String on) {
        // TODO Implement?
        return null;
    }

}
