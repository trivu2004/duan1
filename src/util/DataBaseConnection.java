package util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Tri Dung
 */
public class DataBaseConnection {

    public static DataBaseConnection instance;
    public Connection connection;

    public static DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public DataBaseConnection() {

    }

    public void connectToDatabase() throws SQLException {
        String server = "sql12.freesqldatabase.com";
        String port = "3306";
        String database = "sql12658501";
        String userName = "sql12658501";
        String password = "ap63uLZLNJ";
        connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
