package Form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tri Dung
 */
public class testSQL {

    public static void main(String[] args) {
        // Thông tin kết nối MySQL
        
        // Vào https://www.phpmyadmin.co/index.php đăng nhập để chỉnh sửa được database
        String host = "sql12.freesqldatabase.com";
        String port = "3306";
        String Database = "sql12658501";
        String username = "sql12658501";
        String password = "ap63uLZLNJ";

        String url = "jdbc:mysql://" + host + ":" + port + "/" + Database;

        // Thử kết nối
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            // Thêm code thực hiện các thao tác với cơ sở dữ liệu ở đây
            Statement st = connection.createStatement();
            String sql = "Select * from NhanVien";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("MaNV"));
                System.out.println(rs.getString("Ho"));
                System.out.println(rs.getString("Ten"));
                System.out.println(rs.getString("Tuoi"));
            }
            connection.close(); // Đừng quên đóng kết nối sau khi sử dụng xong
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối MySQL: " + e.getMessage());
        }
    }
}
