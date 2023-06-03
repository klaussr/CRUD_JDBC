package semkin.util;

import java.sql.*;

public class JdbcUtils {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/alex";

    private static final String USER = "admin";
    private static final String PASSWORD = "1111";

    private static Connection connection;

    private static Connection getConnection() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Sorry");
                System.exit(1);
            }
        }
        return connection;
    }

    public static PreparedStatement preparedStatement(String sql) {
        try {
            return getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement preparedStatementWithKeys(String sql) {
        try {
            return getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
