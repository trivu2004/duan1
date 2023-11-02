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
        PropertyConfigurator.configure("src\\Log\\log4j.properties");
        String salt = BCrypt.gensalt(12);
        String hashedPassword = BCrypt.hashpw(password, salt);
        logger.info("Đã mã hóa mật khẩu thành công");
        return hashedPassword;
    }

    public static boolean verifyPassword(String inputPassword, String hashedPassword) {
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(hashPassword("1234"));;
        }
    }
}
