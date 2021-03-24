package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "12345";

    public static Connection util() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
