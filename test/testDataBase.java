

import util.DataBaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Tri Dung
 */
public class testDataBase {

    public static void main(String[] args) {
        try {
            DataBaseConnection.getInstance().connectToDatabase();
            String sql = "select * from NhanVien";
            PreparedStatement p = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {                
                System.out.println(rs.getString("NhanVienID"));
            }
            p.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
