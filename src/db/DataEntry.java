package db;

public interface DataEntry {
    public String toSQL(String ... fields);
}
