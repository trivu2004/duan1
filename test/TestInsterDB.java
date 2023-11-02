
import Form.DataBaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Tri Dung
 */
public class TestInsterDB {

    public static void main(String[] args) {
        String MaNV = "NV005";
        String Ho = "Nguyen";
        String Ten = "B";
        int Tuoi = 20;
        try {
            DataBaseConnection.getInstance().connectToDatabase();
            String sql = "insert into NhanVien(MaNV,Ho,Ten,Tuoi) values (?,?,?,?)";
            PreparedStatement ps = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, MaNV);
            ps.setString(2, Ho);
            ps.setString(3, Ten);
            ps.setInt(4, Tuoi);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
