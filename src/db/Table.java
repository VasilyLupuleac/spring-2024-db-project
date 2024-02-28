package db;

class Table {
    private Database db;
    private String name;
    public String getName;
    public Table(Database db, String name) {
        this.db = db;
        this.name = name;
    };
    public String getName() { return name; }
    public void insert(DataEntry[] entries, String fieldOrder) {
        db.insert(this, entries, fieldOrder);
        // TODO check data?
    }
    public DataEntry[] select(String selectQuery, String condition) {
        return db.select(this, selectQuery, condition);
        // TODO check data?
    }
    public Table join(Table other) {
        // TODO
        return this;
    }

}
