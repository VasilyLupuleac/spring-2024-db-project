package db.dataEntry;

import db.DataEntry;

public class bmDataEntry extends DataEntry {
    String role;

    public bmDataEntry (String role) {
        this.role = role;
    }

    @Override
    public String toSQL() {
        return "(" + role + ")";
    }
}
