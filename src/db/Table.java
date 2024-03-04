package db;

import java.util.List;

class Table {
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

    public void insert(List<DataEntry> entries, String ... fields) {
        db.insert(this, entries, String.join(", ", fields));
        // TODO check data?
    }

    public List<DataEntry> select(String condition, String ... fields) {
        return db.select(this, String.join(", ", fields), condition, new DataEntryParser(entrySample, fields));
        // TODO check data?
    }

    public void delete(String condition) {
        db.delete(this, condition);
        //TODO ?? not sure
    }

    public Table join(Table other, String on) {
        // TODO Implement?
        return null;
    }

}
