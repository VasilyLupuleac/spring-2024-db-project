package db;

import java.util.List;

class Table {
    private Database db;
    private String name;
    private SQLParser parser;

    public Table(Database db, String name, SQLParser parser) {
        this.db = db;
        this.name = name;
        this.parser = parser;
    }

    public String getName() {
        return name;
    }

    public void insert(List<DataEntry> entries, String fieldOrder) {
        db.insert(this, entries, fieldOrder);
        // TODO check data?
    }

    public List<DataEntry> select(String selectQuery, String condition) {
        return db.select(this, selectQuery, condition, parser);
        // TODO check data?
    }

    public void delete(String condition) {
        db.delete(this, condition);
        //TODO ?? not sure
    }

    public Table join(Table other, String on) {
        // TODO Change parser
        return new Table(this.db, this.name + " join " + other.name + on, parser);
    }

}
