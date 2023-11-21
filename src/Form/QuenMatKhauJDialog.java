package Form;

import Helper.JDBCHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author Tri Dung
 */
public class QuenMatKhauJDialog extends javax.swing.JDialog {

    private String sendotp = "";

    /**
     * Creates new form NewJDialog
     */
    public QuenMatKhauJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    boolean checkMail() {
        if (txtQuenMatKhau.getText().trim().equals("")) {
            return false;
        }
        return true;
    }

    boolean checkOTP() {
        if (txtQuenMatKhau.getText().trim().equals("")) {
            return false;
        }
        return true;
    }

    boolean checktontai() {
        try {
            String sql = "select * from NhanVien where Email = ?";
            PreparedStatement st = JDBCHelper.prepareStatement(sql);
            st.setString(1, txtQuenMatKhau.getText().trim());
            ResultSet kqcheckmail = st.executeQuery();
            if (kqcheckmail.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public String randomotp() {
        Random random = new Random();
        // Tạo số ngẫu nhiên trong khoảng từ 10000 đến 99999
        int soNgauNhien = random.nextInt(90000) + 10000;
        sendotp = soNgauNhien + "";
        return sendotp;
    }

    public String randommk() {
        Random random = new Random();

        // Ký tự bắt đầu và kết thúc trong bảng chữ cái
        char batDau = 'a';
        char ketThuc = 'z';

        // Tạo chuỗi ngẫu nhiên
        StringBuilder chuoiNgauNhien = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char kyTuNgauNhien = (char) (random.nextInt(ketThuc - batDau + 1) + batDau);
            chuoiNgauNhien.append(kyTuNgauNhien);
        }
        // In chuỗi ngẫu nhiên
        return chuoiNgauNhien.toString();
    }

    void quenMatKhau() {
        if (btnQuenMatKhau.getText().equals("Gửi OTP")) {
            if (checkMail()) {
                if (checktontai()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chúng tôi giây lát ");
                    final String from = "thuantqps31471@fpt.edu.vn";
                    final String pass = "yipcomzrudwbstkj";

                    Properties pr = new Properties();
                    pr.put("mail.smtp.host", "smtp.gmail.com");
                    pr.put("mail.smtp.post", "587");
                    pr.put("mail.smtp.auth", "true");
                    pr.put("mail.smtp.starttls.enable", "true");

                    // create authenticator : dang nhap gmail;
                    Authenticator auth = new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            // TODO Auto-generated method stub
                            return new PasswordAuthentication(from, pass);
                        }
                    };
                    // phien lam viec;
                    Session s = Session.getInstance(pr, auth);
                    // nguoi nhan : to;
                    final String to = txtQuenMatKhau.getText().trim();
                    // tao 1 tin nhan;
                    MimeMessage msg = new MimeMessage(s);
                    try {
                        // content: noi dung , texthtml : duoc gui code html va test , utf8;
                        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                        // nguoi gui;
                        msg.setFrom(from);
                        // nguoi nhan;
                        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
                        msg.setSentDate(new Date());

                        MimeMultipart mimeMultipart = new MimeMultipart();

//                    MimeBodyPart bodyPart = new MimeBodyPart();
//                    bodyPart.attachFile(new File(viewsend.txtfile.getText()));
                        MimeBodyPart bodyPart1 = new MimeBodyPart();
                        bodyPart1.setContent(randomotp(), "text/HTML; charset=UTF-8");

//                    mimeMultipart.addBodyPart(bodyPart);
                        mimeMultipart.addBodyPart(bodyPart1);

                        msg.setContent(mimeMultipart);

                        Transport.send(msg);

                        btnQuenMatKhau.setText("Gửi lại mật khẩu");
                        lblQuenMatKhau.setText("Mã OTP");
                        txtQuenMatKhau.setText("");
                        return;
                    } catch (Exception eee) {
                        eee.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng email");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập email");
                return;
            }
        }

        if (btnQuenMatKhau.getText().equals("Gửi lại mật khẩu")) {
            System.out.println("Form.QuenMatKhauJDialog.quenMatKhau()");
//            if (checkOTP()) {
//                if (txtQuenMatKhau.getText().trim().equals(sendotp)) {
//                    final String from = "thuantqps31471@fpt.edu.vn";
//                    final String pass = "yipcomzrudwbstkj";
//
//                    Properties pr = new Properties();
//                    pr.put("mail.smtp.host", "smtp.gmail.com");
//                    pr.put("mail.smtp.post", "587");
//                    pr.put("mail.smtp.auth", "true");
//                    pr.put("mail.smtp.starttls.enable", "true");
//
//                    // create authenticator : dang nhap gmail;
//                    Authenticator auth = new Authenticator() {
//                        @Override
//                        protected PasswordAuthentication getPasswordAuthentication() {
//                            // TODO Auto-generated method stub
//                            return new PasswordAuthentication(from, pass);
//                        }
//                    };
//                    // phien lam viec;
//                    Session s = Session.getInstance(pr, auth);
//                    // nguoi nhan : to;
//                    final String to = txtQuenMatKhau.getText().trim();
//                    // tao 1 tin nhan;
//                    MimeMessage msg = new MimeMessage(s);
//                    try {
//                        // content: noi dung , texthtml : duoc gui code html va test , utf8;
//                        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//                        // nguoi gui;
//                        msg.setFrom(from);
//                        // nguoi nhan;
//                        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
//                        msg.setSentDate(new Date());
//
//                        MimeMultipart mimeMultipart = new MimeMultipart();
//
////                    MimeBodyPart bodyPart = new MimeBodyPart();
////                    bodyPart.attachFile(new File(viewsend.txtfile.getText()));
//                        MimeBodyPart bodyPart1 = new MimeBodyPart();
//                        bodyPart1.setContent(randommk(), "text/HTML; charset=UTF-8");
//
////                    mimeMultipart.addBodyPart(bodyPart);
//                        mimeMultipart.addBodyPart(bodyPart1);
//
//                        msg.setContent(mimeMultipart);
//
//                        Transport.send(msg);
//                        JOptionPane.showMessageDialog(this, "Chúng tôi đã cấp lại mk mới vui lòng đăng nhập mà đổi mật khẩu theo ý bạn !");
//                        dispose();
//                    } catch (Exception eee) {
//                        eee.printStackTrace();
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(this, "OTP không hợp lệ !");
//                    return;
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã OTP");
//                return;
//            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblQuenMatKhau = new javax.swing.JLabel();
        txtQuenMatKhau = new javax.swing.JTextField();
        btnQuenMatKhau = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("CINEMAX");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nhập Email đã đăng ký với tài khoản nhân  viên và nhấn vào nút cấp lại mật khẩu. "); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Chúng tôi sẽ gửi OTP qua Email của bạn!");

        lblQuenMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQuenMatKhau.setText("Email");

        btnQuenMatKhau.setText("Gửi OTP");
        btnQuenMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuenMatKhauActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Quay lại trang đăng nhập?");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("tại đây!");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtQuenMatKhau)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuenMatKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnQuenMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lblQuenMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQuenMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQuenMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        btnQuenMatKhau.setText("Gửi OTP");
        lblQuenMatKhau.setText("Email");
        dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btnQuenMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuenMatKhauActionPerformed
        // TODO add your handling code here:
        quenMatKhau();
    }//GEN-LAST:event_btnQuenMatKhauActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuenMatKhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblQuenMatKhau;
    private javax.swing.JTextField txtQuenMatKhau;
    // End of variables declaration//GEN-END:variables
}
