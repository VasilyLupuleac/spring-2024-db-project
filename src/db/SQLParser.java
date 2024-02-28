package db;

import java.sql.ResultSet;

public interface SQLParser {
    public DataEntry[] parseAll(ResultSet resultSet);
    public DataEntry parseOne(ResultSet resultSet);
}

