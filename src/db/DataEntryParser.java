package db;

import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

public class DataEntryParser implements SQLParser {
    DataEntry entrySample;
    String[] fields;

    public DataEntryParser(DataEntry entrySample, String... fields) {
        this.entrySample = entrySample;
        this.fields = fields;
    }

    @Override
    public DataEntry parseOne(ResultSet resultSet) {
        DataEntry newEntry = entrySample.newInstance();
        if (!newEntry.parseFrom(resultSet, fields)) {
            return null;
        }
        return newEntry;
    }

    @Override
    public List<DataEntry> parseAll(ResultSet resultSet) {
        DataEntry nextEntry = parseOne(resultSet);
        Vector<DataEntry> entries = new Vector<DataEntry>();
        while (nextEntry != null) {
            entries.add(nextEntry);
            nextEntry = parseOne(resultSet);
        }
        return entries;
    }
}
