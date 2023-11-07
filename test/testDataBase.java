

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
            String sql = "select * from PhongChieu";
            PreparedStatement p = DataBaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {                
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
            }
            p.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
