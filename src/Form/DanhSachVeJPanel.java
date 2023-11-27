/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form;

import DAO.PhimDAO;
import DAO.PhongChieuDAO;
import DAO.SuatChieuDAO;
import DAO.VeDAO;
import Helper.JDBCHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.DanhSachVe;
import model.Phim;
import model.PhongChieu;
import model.SuatChieu;
import model.TimVe;
import raven.toast.Notifications;

/**
 *
 * @author 123tu
 */
public class DanhSachVeJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DanhSachVeJPanel
     */
    VeDAO dao = new VeDAO();
    public SuatChieuDAO daoSuatChieu = new SuatChieuDAO();
    public PhimDAO daoPhim = new PhimDAO();
    public PhongChieuDAO daoPhongChieu = new PhongChieuDAO();

    public DanhSachVeJPanel() {
        initComponents();

        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                String text = sdf.format(now);
                lblDongHo.setText(text);
            }
        }).start();
        fillCboPhim();
        fillCboPhongChieu();
        fillCboThoiGianChieu();
        cboLoaiVe.setSelectedIndex(-1);
        cboPhim.setSelectedIndex(-1);
        cboPhongChieu.setSelectedIndex(-1);
        cboThoiGianChieu.setSelectedIndex(-1);
        fillToTable();
    }

    void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        List<DanhSachVe> list = new ArrayList<>();
        try {
            if (cboLoaiVe.getSelectedIndex() == -1) {
                list = dao.inTatCaDanhSachVe();
            } else {
                list = dao.inDanhSachVe(String.valueOf(cboLoaiVe.getSelectedItem()), String.valueOf(cboPhim.getSelectedItem()), String.valueOf(cboPhongChieu.getSelectedItem()), String.valueOf(cboThoiGianChieu.getSelectedItem()));
            }
            for (DanhSachVe danhSach : list) {
                Object[] row = {
                    jTable1.getRowCount() + 1,
                    danhSach.getVeID(),
                    danhSach.getTenPhim(),
                    danhSach.getTenPhong(),
                    danhSach.getThoiGianBatDau(),
                    danhSach.getGhe(),
                    danhSach.getLoaiVe(),
                    danhSach.getGiaVe(),
                    danhSach.getNgayMua()};
                model.addRow(row);
            }
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        } finally {
            JDBCHelper.closeConnection();
        }
    }

    void fillCboPhim() {
        try {
            List<Phim> list = daoPhim.selectAll();
            for (Phim phim : list) {
                cboPhim.addItem(phim.getTenPhim() + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCHelper.closeConnection();
        }
    }

    void fillCboPhongChieu() {
        try {
            List<PhongChieu> list = daoPhongChieu.selectAll();
            for (PhongChieu phongChieu : list) {
                cboPhongChieu.addItem(phongChieu.getMaPC() + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCHelper.closeConnection();
        }
    }

    void fillCboThoiGianChieu() {
        try {
            List<SuatChieu> list = daoSuatChieu.selectAll();
            for (SuatChieu suatChieu : list) {
                cboThoiGianChieu.addItem(suatChieu.getNgaytao() + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCHelper.closeConnection();
        }
    }

    void inVe() {
        VeXemPhim veXemPhim = new VeXemPhim();
        int selcetedRow = jTable1.getSelectedRow();
        String tenFile = (String) jTable1.getValueAt(selcetedRow, 1);
        String tenNhanVien = MainJFrame.tenNhanVien;
        String tenPhim = (String) jTable1.getValueAt(selcetedRow, 2);
        String phongChieu = (String) jTable1.getValueAt(selcetedRow, 3);
        String suatChieu = DatVeJPanel.maSuatChieu;
        String viTriGhe = (String) jTable1.getValueAt(selcetedRow, 5);
        double tongTien = Double.valueOf(String.valueOf(jTable1.getValueAt(selcetedRow, 7)));
        veXemPhim.inVeXemPhimPDF(tenFile, tenNhanVien, tenPhim, suatChieu, phongChieu, viTriGhe, tongTien);
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "In Vé Thành Công!");
    }

    void xoaVe() {
        int selcetedRow = jTable1.getSelectedRow();
        try {
            if (selcetedRow != -1) {
                dao.delete((String) jTable1.getValueAt(selcetedRow, 1));
                fillToTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCHelper.closeConnection();
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
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cboLoaiVe = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cboPhim = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cboPhongChieu = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cboThoiGianChieu = new javax.swing.JComboBox<>();
        btnSuatChieu = new javax.swing.JButton();
        btnThemVe = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

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

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Danh Sách Vé:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
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
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Vé", "Tên Phim", "Tên Phòng", "Thời Gian Chiếu", "Ghế", "Loại Vé", "Giá Vé", "Ngày (Mua/Đặt)", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Tìm Theo Loại Vé:");

        cboLoaiVe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cboLoaiVe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thường", "VIP", "Couple" }));
        cboLoaiVe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiVeItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("Tìm Theo Phim:");

        cboPhim.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cboPhim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhimItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel6.setText("Tìm Theo phòng Chiếu:");

        cboPhongChieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel7.setText("Tìm Theo Thời Gian Chiếu:");

        cboThoiGianChieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cboThoiGianChieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboThoiGianChieuItemStateChanged(evt);
            }
        });

        btnSuatChieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSuatChieu.setText("Cập Nhật");
        btnSuatChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuatChieuActionPerformed(evt);
            }
        });

        btnThemVe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemVe.setText("Thêm Vé");
        btnThemVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVeActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("In Vé");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboThoiGianChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThemVe, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(850, 850, 850)
                                .addComponent(btnSuatChieu))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboThoiGianChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSuatChieu)
                        .addComponent(btnThemVe)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        MainJFrame.showForm(new TrangChuJPanel());
    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void btnThemVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVeActionPerformed
        MainJFrame.showForm(new VeJPanel());
    }//GEN-LAST:event_btnThemVeActionPerformed

    private void cboLoaiVeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiVeItemStateChanged
        // TODO add your handling code here:
        fillToTable();
    }//GEN-LAST:event_cboLoaiVeItemStateChanged

    private void cboPhimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhimItemStateChanged
        // TODO add your handling code here:     
        fillToTable();
    }//GEN-LAST:event_cboPhimItemStateChanged

    private void cboThoiGianChieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThoiGianChieuItemStateChanged
        // TODO add your handling code here:
        fillToTable();
    }//GEN-LAST:event_cboThoiGianChieuItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        inVe();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSuatChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuatChieuActionPerformed
        // TODO add your handling code here:
        xoaVe();
    }//GEN-LAST:event_btnSuatChieuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuatChieu;
    private javax.swing.JButton btnThemVe;
    private javax.swing.JComboBox<String> cboLoaiVe;
    private javax.swing.JComboBox<String> cboPhim;
    private javax.swing.JComboBox<String> cboPhongChieu;
    private javax.swing.JComboBox<String> cboThoiGianChieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblTrangChu;
    // End of variables declaration//GEN-END:variables
}
