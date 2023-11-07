package util;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tri Dung
 */
public class DataBaseConnection {

    public static Connection getConnection() {
        Connection c = null;
        try {
            DriverManager.registerDriver(new Driver());
            String server = "sql12.freesqldatabase.com";
            String port = "3306";
            String database = "sql12658501";
            String userName = "sql12658501";
            String password = "ap63uLZLNJ";
            c = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
        } catch (SQLException e) {
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
