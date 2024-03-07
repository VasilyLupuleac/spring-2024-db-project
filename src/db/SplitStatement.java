package db;

import java.util.ArrayList;

public class SplitStatement {
    private String query;
    private ArrayList<String> args;

    public SplitStatement(String query, ArrayList<String> args) {
        this.query = query;
        this.args = args;
    }

    public String getQuery() {
        return query;
    }

    public ArrayList<String> getArgs() {
        return args;
    }
}
