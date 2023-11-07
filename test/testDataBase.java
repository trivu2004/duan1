

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
            String sql = "select * from NhanVien";
            PreparedStatement p = DataBaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {                
                System.out.println(rs.getString("MaNV"));
                System.out.println(rs.getString("Ho"));
                System.out.println(rs.getString("Ten"));
                System.out.println(rs.getString("Tuoi"));
            }
            p.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
