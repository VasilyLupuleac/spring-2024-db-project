package db;

public class Main {
    public static void main(String[] args) {
        System.out.println("Chicen toy");
        Database db = new Database("postgres", "localhost", "5433", "postgres", "123");
    }
}