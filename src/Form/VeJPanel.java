package Form;

import DAO.PhimDAO;
import DAO.PhongChieuDAO;
import DAO.SuatChieuDAO;
import DAO.VeDAO;
import Form.DatVeJPanel;
import Form.MainJFrame;
import Form.TrangChuJPanel;
import Helper.JDBCHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.Phim;
import model.PhongChieu;
import model.SuatChieu;
import model.TimVe;
import raven.toast.Notifications;

/**
 *
 * @author Tri Dung
 */
public class VeJPanel extends javax.swing.JPanel {

    /**
     * Creates new form VẹPanel
     */
    public VeDAO daoVe = new VeDAO();
    public SuatChieuDAO daoSuatChieu = new SuatChieuDAO();
    public PhimDAO daoPhim = new PhimDAO();
    public PhongChieuDAO daoPhongChieu = new PhongChieuDAO();
    public String TenPhim = "";
    public String TenPhong = "";
    public String ThoiGianChieu = "";
    public String MaSuatChieu = "";

    public String ngayHienTai;

    public String getTenPhim() {
        return TenPhim;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public String getThoiGianChieu() {
        return ThoiGianChieu;
    }

    public String getMaSuatChieu() {
        return MaSuatChieu;
    }

    public VeJPanel() {
        initComponents();
        Date now = new Date();
        SimpleDateFormat NgayMuaFormat = new SimpleDateFormat("YYYY-MM-dd");
        ngayHienTai = NgayMuaFormat.format(now);
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                String text = sdf.format(now);
                lblDongHo.setText(text);
            }
        }).start();
        LoadingJPanel loadingJPanel = new LoadingJPanel();
        long startTime = System.currentTimeMillis();

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadingJPanel.setVisible(true);
                return null;
            }

            @Override
            protected void done() {
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                // Được gọi khi công việc lâu dài hoàn thành
                loadingJPanel.setVisible(false);
                System.out.println("Data loading completed in " + elapsedTime + " milliseconds.\n");
            }
        };
        worker.execute();
        btnDatVe.setEnabled(false);
        fillCboThoiGian();
        fillCboPhim();
        fillCboPhongChieu();
        cboPhim.setSelectedIndex(-1);
        cboPhongChieu.setSelectedIndex(-1);
        cboThoiGian.setSelectedIndex(-1);
        fillToTable();
    }

    void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblSuatChieu.getModel();
        model.setRowCount(0);
        List<TimVe> list = new ArrayList<>();
        try {
            if (cboPhim.getSelectedIndex() == -1) {
                list = daoVe.inTatCaVe(ngayHienTai);
            } else {
                list = daoVe.findTicket(String.valueOf(cboThoiGian.getSelectedItem()), String.valueOf(cboPhongChieu.getSelectedItem()), String.valueOf(cboPhim.getSelectedItem()));
            }
            for (TimVe timVe : list) {
                Object[] row = {
                    tblSuatChieu.getRowCount() + 1,
                    timVe.getMaSuatChieu(),
                    timVe.getTenPhim(),
                    timVe.getTenPhong(),
                    timVe.getThoiGianChieu(),};
                model.addRow(row);
            }

        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        } finally {
            JDBCHelper.closeConnection();
        }
    }

    void fillCboThoiGian() {
        try {
            List<String> list = daoSuatChieu.fillChkThoiGianBatDau();
            for (String string : list) {
                cboThoiGian.addItem(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        lblTrangChu = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuatChieu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cboPhim = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboPhongChieu = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnDatVe = new javax.swing.JButton();
        cboThoiGian = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        lblTrangChu.setBackground(new java.awt.Color(0, 0, 0));
        lblTrangChu.setForeground(new java.awt.Color(255, 51, 51));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Wide Latin", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CINEMAX");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        lblDongHo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(255, 255, 255));
        lblDongHo.setText("00:00:00 AM");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Chọn Suất Chiếu:");

        javax.swing.GroupLayout lblTrangChuLayout = new javax.swing.GroupLayout(lblTrangChu);
        lblTrangChu.setLayout(lblTrangChuLayout);
        lblTrangChuLayout.setHorizontalGroup(
            lblTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDongHo)
                .addContainerGap())
            .addGroup(lblTrangChuLayout.createSequentialGroup()
                .addGap(459, 459, 459)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lblTrangChuLayout.setVerticalGroup(
            lblTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblTrangChuLayout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lblTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDongHo)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        tblSuatChieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSuatChieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Suất Chiếu", "Tên Phim", "Tên Phòng", "Thời Gian Chiếu"
            }
        )
        {
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ ô nào
                return false;
            }
        }
    );
    tblSuatChieu.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblSuatChieuMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblSuatChieu);

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
    jLabel2.setText("Chọn Phim:");

    cboPhim.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    cboPhim.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            cboPhimItemStateChanged(evt);
        }
    });

    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
    jLabel11.setText("Chọn Phòng Chiếu:");

    cboPhongChieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    cboPhongChieu.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            cboPhongChieuItemStateChanged(evt);
        }
    });

    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
    jLabel4.setText("Chọn Thời Gian:");

    btnDatVe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    btnDatVe.setText("Đặt Vé");
    btnDatVe.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDatVeActionPerformed(evt);
        }
    });

    cboThoiGian.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    cboThoiGian.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            cboThoiGianItemStateChanged(evt);
        }
    });

    jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jButton1.setText("Danh Sách Vé");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(lblTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(jLabel11)
                        .addComponent(cboPhongChieu, 0, 455, Short.MAX_VALUE)
                        .addComponent(cboThoiGian, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(60, 60, 60)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(cboPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(layout.createSequentialGroup()
            .addGap(555, 555, 555)
            .addComponent(btnDatVe, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(16, 16, 16))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(lblTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2)
                .addComponent(jLabel4))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(cboPhim)
                .addComponent(cboThoiGian))
            .addGap(18, 18, 18)
            .addComponent(jLabel11)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnDatVe)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(51, 51, 51))
    );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDatVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatVeActionPerformed
        int selcetedRow = tblSuatChieu.getSelectedRow();
        if (selcetedRow != -1) {

        }
        MainJFrame.showForm(new DatVeJPanel(this));
    }//GEN-LAST:event_btnDatVeActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        MainJFrame.showForm(new TrangChuJPanel());
    }//GEN-LAST:event_jLabel9MouseClicked

    private void cboThoiGianItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThoiGianItemStateChanged
        // TODO add your handling code here:
        fillToTable();
    }//GEN-LAST:event_cboThoiGianItemStateChanged

    private void cboPhongChieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhongChieuItemStateChanged
        // TODO add your handling code here:
        fillToTable();
    }//GEN-LAST:event_cboPhongChieuItemStateChanged

    private void cboPhimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhimItemStateChanged
        // TODO add your handling code here:
        fillToTable();
    }//GEN-LAST:event_cboPhimItemStateChanged

    private void tblSuatChieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuatChieuMouseClicked
        // TODO add your handling code here:
        int index = tblSuatChieu.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 1) {
            btnDatVe.setEnabled(true);
            MaSuatChieu = (String) tblSuatChieu.getValueAt(index, 1);
            TenPhim = (String) tblSuatChieu.getValueAt(index, 2);
            TenPhong = (String) tblSuatChieu.getValueAt(index, 3);
            ThoiGianChieu = (String) tblSuatChieu.getValueAt(index, 4);
        }
        if (evt.getClickCount() == 2) {
            if (index >= 0) {
                MainJFrame.showForm(new DatVeJPanel(this));
            }
        }
    }//GEN-LAST:event_tblSuatChieuMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MainJFrame.showForm(new DanhSachVeJPanel());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatVe;
    private javax.swing.JComboBox<String> cboPhim;
    private javax.swing.JComboBox<String> cboPhongChieu;
    private javax.swing.JComboBox<String> cboThoiGian;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JPanel lblTrangChu;
    private javax.swing.JTable tblSuatChieu;
    // End of variables declaration//GEN-END:variables
}
