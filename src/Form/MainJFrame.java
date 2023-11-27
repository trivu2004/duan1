package Form;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.PreparedStatement;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import raven.toast.Notifications;
import Helper.JDBCHelper;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import util.BCryptPasswordHashing;

/**
 *
 * @author TriDung
 */
public class MainJFrame extends javax.swing.JFrame {

    private static MainJFrame app;
    private static MainForm mainForm;
    private static LoginForm loginForm;
    private static QuenMatKhauJDialog quenMatKhauJDialog;
    public static String tenNhanVien;
    public static Image APP_ICON;
    public static String NhanVienID;
    public static String MatKhau;
    String file = "/image/Logo.png";
    private static TrangChuJPanel k;

    public MainJFrame() {
        initComponents();
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm();
        loginForm = new LoginForm();

        quenMatKhauJDialog = new QuenMatKhauJDialog(this, true);
        setContentPane(loginForm);
        Notifications.getInstance().setJFrame(this);
        mainForm.setMenuFull(false);
        APP_ICON = new ImageIcon(MainJFrame.class.getResource(file)).getImage();
        setIconImage(APP_ICON);
        setFocusable(true);
        requestFocus(true);
    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }

    public static boolean checknull() {
        if (loginForm.txtUser.getText().trim().equals("")) {
            return false;
        }
        if (loginForm.txtPass.getText().trim().equals("")) {
            return false;
        }
        return true;
    }

    public static void login() {
        if (checknull()) {
            try {
                String sql = "select * from NhanVien";
                PreparedStatement st = JDBCHelper.prepareStatement(sql);
                ResultSet kq = st.executeQuery();
                while (kq.next()) {
                    NhanVienID = kq.getString("NhanVienID");
                    MatKhau = kq.getString("MatKhau");
                    tenNhanVien = kq.getString("TenNhanVien");
                    // Mã hóa mật khẩu
                    if (BCryptPasswordHashing.verifyPassword(loginForm.txtPass.getText().trim(), MatKhau) & NhanVienID.equals(loginForm.txtUser.getText())) {
                        FlatAnimatedLafChange.showSnapshot();
                        app.setContentPane(app.mainForm);
                        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
                        setSelectedMenu(0, 0);
                        app.mainForm.hideMenu();
                        if (kq.getString(6).equals("0")) {
                            mainForm.master.lblNhanVien.setVisible(false);
                            mainForm.master.lblThongKe.setVisible(false);
                        }
                        SwingUtilities.updateComponentTreeUI(app.mainForm);
                        FlatAnimatedLafChange.hideSnapshotWithAnimation();
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chào mừng! " + NhanVienID);
                        return;
                    }
                }
<<<<<<< HEAD

                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sai tài khoản hoặc mật khẩu !");

=======
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sai tài khoản hoặc mật khẩu !");
>>>>>>> 763b61dad899fb54b649f11bb73dc2d1ac06396d
            } catch (Exception e) {
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng điền đầy đủ thông tin!");
            return;
        }
    }

    public static void logout() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.loginForm);
        app.loginForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.loginForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void setSelectedMenu(int index, int subIndex) {
        app.mainForm.setSelectedMenu(index, subIndex);
    }

    public static void moQuenMatKhau() {
        quenMatKhauJDialog.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            logout();
        }
    }//GEN-LAST:event_formKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            app = new MainJFrame();
//              app.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            app.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
