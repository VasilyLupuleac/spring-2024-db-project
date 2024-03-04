package db;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public abstract class DataEntry {
    protected Map<String, Integer> fieldIndexMap;
    String[] fields;
    public abstract boolean parseFrom(ResultSet resultSet, String... fields);
    public abstract String getField(String label);

    protected void initializeFieldMap() {
        fieldIndexMap = new HashMap<String, Integer>();
        for (int i = 0; i < fields.length; i++)
            fieldIndexMap.put(fields[i], i);
    }

    public String toSQL(String... fields) {
        if (fields.length == 0)
            fields = this.fields;
        StringBuilder sql = new StringBuilder();
        sql.append('(');
        for (int i = 0; i < fields.length; i++) {
            if (i > 0)
                sql.append(", ");
            sql.append(getField(fields[i]));
        }
        sql.append(')');
        return sql.toString();
    }
}
