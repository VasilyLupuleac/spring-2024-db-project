package db;

class Table {
    private Database db;
    private String name;

    public Table(Database db, String name) {
        this.db = db;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void insert(DataEntry[] entries, String fieldOrder) {
        db.insert(this, entries, fieldOrder);
        // TODO check data?
    }

    public DataEntry[] select(String selectQuery, String condition, SQLParser parser) {
        return db.select(this, selectQuery, condition, parser);
        // TODO check data?
    }

    public void delete(String condition) {
        db.delete(this, condition);
        //TODO ?? not sure
    }

    public Table join(Table other) {
        // TODO do we even need this? Also implement
        return this;
    }

}
