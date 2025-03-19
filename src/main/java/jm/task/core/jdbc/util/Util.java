package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {

    static final String URL = "jdbc:mysql://localhost:3302/mydatabase";
    static final String USERNAME = "myuser";
    static final String PASSWORD = "mypassword";

    public static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Util() {
    }

    public static Connection getMyConnection() {

        try {
            loadDriver();
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
