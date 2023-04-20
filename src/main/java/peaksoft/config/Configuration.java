package peaksoft.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class Configuration {
    private static final String url = "jdbc:postgresql://localhost:5432/java9";
    private static final String userName = "postgres";
    private static final String password = "postgres";


    public static Connection connectionTODatabase() throws SQLException {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(
                    url,
                    userName,
                    password
            );
            System.out.println("Successfully connected to database...");
        } catch (SQLDataException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}