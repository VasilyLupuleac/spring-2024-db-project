package db;

import java.sql.ResultSet;
import java.util.List;

public interface SQLParser {
    public DataEntry parseOne(ResultSet resultSet);
    public List<DataEntry> parseAll(ResultSet resultSet);
}

