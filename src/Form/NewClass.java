package Form;

import Helper.JDBCHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Tri Dung
 */
public class NewClass {

    public static void main(String[] args) {
        String dsghe ="[H001]";
        int soluongghedadung = 0;
        for (int i = 0; i < dsghe.length(); i++) {
            if (dsghe.charAt(i) == ',') {
                soluongghedadung++;
            }
        }
        System.out.println(soluongghedadung);
        
    }
}
