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

    static boolean checktrangthai(LocalTime startTime, LocalTime endTime) {
        LocalTime currentTime = LocalTime.now();

        return !currentTime.isBefore(startTime) && !currentTime.isAfter(endTime);
    }

    static boolean checktrangthai1(int timestart, int timeend) {
        LocalTime dau = LocalTime.of(timestart, 0);
        LocalTime cuoi = LocalTime.of(timestart, 0);
        System.out.println(checktrangthai(dau, cuoi));
        if (checktrangthai(dau, cuoi)) {
            return true;
        } else {
            System.out.println("Form.NewClass.checktrangthai1()");
            return false;
        }
    }

    public static void main(String[] args) {
        checktrangthai1(21,23);
    }
}
