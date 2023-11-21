package Helper;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Tri Dung
 */
public class JDBCHelper {
    private static Connection connection;

    public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
        DriverManager.registerDriver(new Driver());
        String server = "sql12.freesqldatabase.com";
        String port = "3306";
        String database = "sql12658501";
        String userName = "sql12658501";
        String password = "ap63uLZLNJ";

        connection = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);
        } else {
            pstmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            try {
                stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet query(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = query(sql, args);
            if (rs.next()) {
                Object result = rs.getObject(1);
                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
