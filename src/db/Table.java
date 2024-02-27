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
    public void insert(DataEntry[] entries) {
        db.insert(this, entries);
        // TODO check data?
    }
    public DataEntry[] select(String queryString) {
        return db.select(this, queryString);
        // TODO check data?
    }
    public Table join(Table other) {
        // TODO
        return this;
    }

}