package util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Tri Dung
 */
public class BCryptPasswordHashing {

    public static final Logger logger = Logger.getLogger(BCryptPasswordHashing.class);

    public static String hashPassword(String password) {
        // Cấu hình log bằng Log4j (đảm bảo rằng bạn đã cài đặt thư viện và cấu hình log4j.properties)
        PropertyConfigurator.configure("src\\Log\\log4j.properties");

        // Tạo chuỗi salt ngẫu nhiên với độ dài salt là 14
        String salt = BCrypt.gensalt(14);

        // Mã hóa mật khẩu bằng salt
        String hashedPassword = BCrypt.hashpw(password, salt);

        // Ghi log thông báo đã mã hóa mật khẩu thành công
        logger.info("Đã mã hóa mật khẩu thành công");

        // Trả về mật khẩu đã mã hóa
        return hashedPassword;
    }

    public static boolean verifyPassword(String inputPassword, String hashedPassword) {
        // Kiểm tra mật khẩu nhập vào có khớp với mật khẩu đã mã hóa hay không
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }

//    public static void main(String[] args) {
//        while (true) {
//            System.out.println(hashPassword("1234"));;
//        }
//    }
}
