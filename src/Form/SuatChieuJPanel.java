/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form;

import DAO.SuatChieuDAO;
import Helper.JDBCHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.Phim;
import model.SuatChieu;
import raven.toast.Notifications;

public class SuatChieuJPanel extends javax.swing.JPanel {

    SuatChieuDAO dao = new SuatChieuDAO();
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    int row;

    public SuatChieuJPanel(SuatChieuDAO dao) {
        initComponents();
        fillTable();
        fillPhim();
        fillPhong();
        fillQuanLy();
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                String text = sdf.format(now);
                lblDongHo.setText(text);
            }
        }).start();
    }

    public void fillPhim() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboPhim.getModel();
        try {
            String sql = "select distinct PhimID from Phim";
            ResultSet kq = JDBCHelper.query(sql);
            while (kq.next()) {
                model.addElement(kq.getString("PhimID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillPhong() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboPhongChieu.getModel();
        try {
            String sql = "select distinct PhongID from PhongChieu";
            ResultSet kq = JDBCHelper.query(sql);
            while (kq.next()) {
                model.addElement(kq.getString("PhongID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillQuanLy() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboQuanLy.getModel();
        try {
            String sql = "select distinct NhanVienID from NhanVien";
            ResultSet kq = JDBCHelper.query(sql);
            while (kq.next()) {
                model.addElement(kq.getString("NhanVienID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblSuatChieu.getModel();
        model.setRowCount(0);
        int stt = 1;
        try {
            List<SuatChieu> list = dao.selectAll();
            for (SuatChieu sc : list) {
                Object[] row = {stt,
                    sc.getMaSC(),
                    sc.getTenPC(),
                    sc.getTenPhim(),
                    sc.getThoiGianBD(),
                    sc.getThoiGianKT(),
                    sc.getTenNQL()
                };
                model.addRow(row);
                stt++;
            }
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Lỗi truy vấn dữ liệu !");
            e.printStackTrace();
        }
    }

    public void setForm(SuatChieu sc) {//Vị trí lên form
        txtMaSC.setText(sc.getMaSC());
        cboPhongChieu.setSelectedItem(sc.getTenPC());
        cboPhim.setSelectedItem(sc.getTenPhim());
        txtTGBatDau.setText(String.valueOf(sc.getThoiGianBD()));
        txtTGKetThuc.setText(String.valueOf(sc.getThoiGianKT()));
        cboQuanLy.setSelectedItem(sc.getTenNQL());

    }

    void updateStatus() {
        boolean edit = this.row >= 0;
        txtMaSC.setEnabled(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
    }

    public void cleanForm() {
        setForm(new SuatChieu());
        txtMaSC.setText("");
        txtTGBatDau.setText("");
        txtTGKetThuc.setText("");
        cboPhongChieu.setSelectedIndex(-1);
        cboPhim.setSelectedIndex(-1);
        cboQuanLy.setSelectedIndex(-1);
        row = -1;
        updateStatus();
    }

    public SuatChieu getForm() throws ParseException {
        SuatChieu sc = new SuatChieu();
        sc.setMaSC(txtMaSC.getText());
        sc.setTenPC((String) cboPhongChieu.getSelectedItem());
        sc.setTenPhim((String) cboPhim.getSelectedItem());
        sc.setThoiGianBD(date.parse(txtTGBatDau.getText()));
        sc.setThoiGianKT(date.parse(txtTGKetThuc.getText()));
        sc.setTenNQL((String) cboQuanLy.getSelectedItem());
        return sc;
    }

    public void insert() throws ParseException {
        SuatChieu sc = getForm();
        try {
            dao.insert(sc);
            fillTable();
            cleanForm();
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Thêm Thành Công!");
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Thêm Thất Bại!");
            e.printStackTrace();
        }

    }

    public void update() throws ParseException {
        SuatChieu sc = getForm();
        try {
            dao.update(sc);
            fillTable();
            cleanForm();
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sửa Thành Công!");
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sửa Thất Bại!");
            e.printStackTrace();
        }
    }

    public void delete() throws ParseException {
        SuatChieu sc = getForm();
        try {
            dao.delete(txtMaSC.getText());
            fillTable();
            cleanForm();
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Xóa Thành Công!");
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sửa Thất Bại!");
            e.printStackTrace();
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

        jPanel2 = new javax.swing.JPanel();
        lblTrangChu = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuatChieu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTGBatDau = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMaSC = new javax.swing.JTextField();
        cboPhim = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboQuanLy = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtTGKetThuc = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cboPhongChieu = new javax.swing.JComboBox<>();
        lblPhim = new javax.swing.JLabel();
        lblPhongChieu = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 51, 51));

        lblTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setFont(new java.awt.Font("Wide Latin", 1, 36)); // NOI18N
        lblTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setText("CINEMAX");
        lblTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseClicked(evt);
            }
        });

        lblDongHo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(255, 255, 255));
        lblDongHo.setText("00:00:00 AM");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Thông Tin Suất Chiếu:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDongHo)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(459, 459, 459)
                .addComponent(lblTrangChu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(lblTrangChu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDongHo)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Thời Gian Bắt Đầu:");

        tblSuatChieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Suất Chiếu", "Phòng Chiếu", "Tên Phim", "Thời Gian Bắt Đầu", "Thời Gian Kết Thúc", "Người Quản Lý"
            }
        ));
        tblSuatChieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSuatChieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSuatChieu);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel1.setText("Mã Suất Chiếu:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Chọn Phim:");

        cboPhim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhimItemStateChanged(evt);
            }
        });
        cboPhim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboPhimMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel11.setText("Chọn Phòng Chiếu:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel12.setText("Thời Gian Kết Thúc:");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel13.setText("Người Quản Lý:");

        cboPhongChieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhongChieuItemStateChanged(evt);
            }
        });

        lblPhim.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        lblPhongChieu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPhim))
                            .addComponent(txtTGBatDau)
                            .addComponent(txtMaSC)
                            .addComponent(cboPhim, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPhongChieu))
                            .addComponent(jLabel12)
                            .addComponent(txtTGKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboPhongChieu, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaSC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblPhim))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lblPhongChieu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addGap(15, 15, 15)
                        .addComponent(txtTGBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTGKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cboQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnXoa)
                            .addComponent(btnSua)
                            .addComponent(btnMoi)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            insert();
        } catch (ParseException ex) {
            Logger.getLogger(SuatChieuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        MainJFrame.showForm(new TrangChuJPanel());
    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            // TODO add your handling code here:
            delete();
        } catch (ParseException ex) {
            Logger.getLogger(SuatChieuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void cboPhimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboPhimMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPhimMouseClicked

    private void tblSuatChieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuatChieuMouseClicked
        int index = tblSuatChieu.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 1) {
            btnThem.setEnabled(false);
            String masc = (String) tblSuatChieu.getValueAt(index, 1);
            String Tenpc = (String) tblSuatChieu.getValueAt(index, 2);
            String Tenphim = (String) tblSuatChieu.getValueAt(index, 3);
            Date Thoigianbatdau = (Date) tblSuatChieu.getValueAt(index, 4);
            Date Thoigianketthuc = (Date) tblSuatChieu.getValueAt(index, 5);
            String nguoiquanly = (String) tblSuatChieu.getValueAt(index, 6);

            txtMaSC.setText(masc);
            cboPhim.setSelectedItem(Tenphim);
            cboPhongChieu.setSelectedItem(Tenpc);
            txtTGBatDau.setText(String.valueOf(Thoigianbatdau));
            txtTGKetThuc.setText(String.valueOf(Thoigianketthuc));
            cboQuanLy.setSelectedItem(nguoiquanly);
        }
    }//GEN-LAST:event_tblSuatChieuMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            // TODO add your handling code here:
            update();
        } catch (ParseException ex) {
            Logger.getLogger(SuatChieuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void cboPhimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhimItemStateChanged
        // TODO add your handling code here:
        try {
            String sql = "select TenPhim from Phim\n"
                    + "where PhimID = ? ";
            PreparedStatement st = JDBCHelper.prepareStatement(sql);
            st.setString(1, (String) cboPhim.getSelectedItem());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lblPhim.setText(rs.getString("TenPhim"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboPhimItemStateChanged

    private void cboPhongChieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhongChieuItemStateChanged
        // TODO add your handling code here:
        try {
            String sql = "select TenPhong from PhongChieu\n"
                    + "where PhongID = ? ";
            PreparedStatement st = JDBCHelper.prepareStatement(sql);
            st.setString(1, (String) cboPhongChieu.getSelectedItem());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lblPhongChieu.setText(rs.getString("TenPhong"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboPhongChieuItemStateChanged

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    public javax.swing.JComboBox<String> cboPhim;
    public javax.swing.JComboBox<String> cboPhongChieu;
    public javax.swing.JComboBox<String> cboQuanLy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblPhim;
    private javax.swing.JLabel lblPhongChieu;
    private javax.swing.JLabel lblTrangChu;
    public javax.swing.JTable tblSuatChieu;
    public javax.swing.JTextField txtMaSC;
    public javax.swing.JTextField txtTGBatDau;
    public javax.swing.JTextField txtTGKetThuc;
    // End of variables declaration//GEN-END:variables
}
