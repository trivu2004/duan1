/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form;

import DAO.PhimDAO;
import Helper.DateHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.Phim;
import raven.toast.Notifications;
import util.XImange;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 *
 * @author 123tu
 */
public class PhimJPanel extends javax.swing.JPanel {

    DefaultTableModel model;
    PhimDAO dao = new PhimDAO();
    int row;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    JFileChooser fileChooser = new JFileChooser("src\\image");

    public PhimJPanel() {
        initComponents();
        fillTable();
        updateStatus();

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

    public void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImange.save(file);
            ImageIcon icon = XImange.read(file.getName());
            lblHinh.setIcon(icon);
            lblHinh.setToolTipText(file.getName());
        }
    }

    public void fillTable() {
        model = (DefaultTableModel) tblPhim.getModel();
        model.setRowCount(0);
        try {
            List<Phim> list = dao.selectAll();
            for (Phim p : list) {
                String TheLoai = String.valueOf(p.getTheLoai());
                Object[] row = {
                    tblPhim.getRowCount() + 1,
                    p.getMaPhim(),
                    p.getTenPhim(),
                    TheLoai,
                    p.getThoiLuong() + " phút",
                    p.getDaoDien(),
                    p.getDienVien(),
                    p.getMoTa(),
                    DateHelper.toString(p.getNgayCongChieu()),
                    DateHelper.toString(p.getNgayKetThuc()),
                    p.getNamSX(),
                    p.getNuocSX(),
                    p.getHinh(),
                    p.getGiaBQ()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    void edit() {
        try {
            String MaPhim = (String) tblPhim.getValueAt(this.row, 1);
            Phim model = dao.selectById(MaPhim);
            if (model != null) {
                setForm(model);
                updateStatus();
            }
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Lỗi truy vấn dữ liệu!");
        }
    }

    void updateStatus() {
        boolean edit = this.row >= 0;
        txtMaPhim.setEnabled(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
    }

    public void setForm(Phim p) {//Vị trí lên form
        txtMaPhim.setText(p.getMaPhim());
        txtTenPhim.setText(p.getTenPhim());
        txtThoiLuong.setText(String.valueOf(p.getThoiLuong()));
        txtDaoDien.setText(p.getDaoDien());
        txtDienVien.setText(p.getDienVien());
        txtMoTa.setText(p.getMoTa());
        txtNgayCC.setDate(p.getNgayCongChieu());
        txtNgayKT.setDate(p.getNgayKetThuc());
        txtNamSX.setText(String.valueOf(p.getNamSX()));
        txtNuocSX.setText(p.getNuocSX());
        String hinh = p.getHinh();
        if (hinh != null && !hinh.equals("")) {
            lblHinh.setIcon(null);
            lblHinh.setIcon(XImange.read(hinh));
            lblHinh.setToolTipText(hinh);
        }
        txtGiaBanQuyen.setText(String.valueOf(p.getGiaBQ()));
    }

    public Phim getForm() throws ParseException {//Khi Nhập dữ liệu sẽ nhập lên bảng
        Phim p = new Phim();
        p.setMaPhim(txtMaPhim.getText());
        p.setTenPhim(txtTenPhim.getText());
        String s = "";
        if (chkCaNhac.isSelected()) {
            s = "Ca Nhạc," + s;
        }
        if (chkChinhKich.isSelected()) {
            s = "Chính Kịch," + s;
        }
        if (chkGiaĐinh.isSelected()) {
            s = "Gia Đình," + s;
        }
        if (chkHaiHuoc.isSelected()) {
            s = "Hài Hước," + s;
        }
        if (chkHanhĐong.isSelected()) {
            s = "Hành Động," + s;
        }
        if (chkHoatHinh.isSelected()) {
            s = "Hoạt Hình," + s;
        }
        if (chkKhoaHocVienTuong.isSelected()) {
            s = "Khoa Học Viễn Tưởng, " + s;
        }
        if (chkKinhDi.isSelected()) {
            s = "Kinh Dị," + s;
        }
        if (chkLichSu.isSelected()) {
            s = "Lịch Sử," + s;
        }
        if (chkPhieuLuu.isSelected()) {
            s = "Phiêu Lưu," + s;
        }
        if (chkSuThi.isSelected()) {
            s = "Sử Thi," + s;
        }
        if (chkTaiLieu.isSelected()) {
            s = "Tài Liệu," + s;
        }
        if (chkTinhCam.isSelected()) {
            s = "Tình Cảm," + s;
        }
        if (chkTrinhTham.isSelected()) {
            s = "Trinh Thám," + s;
        }
        s = s.trim();
        if (s.endsWith(",")) {
            s = s.substring(0, s.length() - 1);
        }
        p.setTheLoai(s);
        p.setThoiLuong(Integer.parseInt(txtThoiLuong.getText()));
        p.setDaoDien(txtDaoDien.getText());
        p.setDienVien(txtDienVien.getText());
        p.setMoTa(txtMoTa.getText());
        p.setNgayCongChieu(txtNgayCC.getDate());
        p.setNgayKetThuc(txtNgayKT.getDate());
        p.setNamSX(Integer.parseInt(txtNamSX.getText()));
        p.setNuocSX(txtNuocSX.getText());
        if (lblHinh == null || lblHinh.getToolTipText() == null || lblHinh.getToolTipText().isEmpty()) {
            p.setHinh("No Avatar");
        } else {
            p.setHinh(lblHinh.getToolTipText());
        }
        p.setGiaBQ(Integer.parseInt(txtGiaBanQuyen.getText()));
        return p;
    }

    public void cleanForm() {
        setForm(new Phim());
        txtThoiLuong.setText("");
        txtNamSX.setText("");
        txtGiaBanQuyen.setText("");
        txtNgayCC.setDate(null);
        txtNgayKT.setDate(null);
        row = -1;
        updateStatus();
    }

    public void insert() throws ParseException {
        Phim p = getForm();
        try {
            dao.insert(p);
            fillTable();
            cleanForm();
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Thêm Thành Công");
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Thêm Thất Bại");
            e.printStackTrace();
        }
    }

    public void update() throws ParseException {
        Phim pc = getForm();
        try {
            dao.update(pc);
            fillTable();
            cleanForm();
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Cập nhập thành Công");
        } catch (Exception e) {
        }
    }

    public void del() {
        if (txtMaPhim.getText().equals("")) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chưa nhập dữ liệu vào để xóa");
        } else {
            String maP = txtMaPhim.getText();
            try {
                dao.delete(maP);
                fillTable();
                cleanForm();
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Xóa thành Công");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkValidate() {
        if (txtMaPhim.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống Mã phim!");
            txtMaPhim.requestFocus();
            return false;
        }
        if (txtMaPhim.getText().length() > 10) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Mã phim không thể quá 10 ký tự!");
            txtMaPhim.requestFocus();
            return false;
        }
        if (txtTenPhim.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống Tên phim!");
            txtTenPhim.requestFocus();
            return false;
        }
        if (!chkCaNhac.isSelected() && !chkChinhKich.isSelected() && !chkGiaĐinh.isSelected() && !chkHaiHuoc.isSelected() && !chkHanhĐong.isSelected() && !chkHoatHinh.isSelected() && !chkKhoaHocVienTuong.isSelected() && !chkKinhDi.isSelected() && !chkLichSu.isSelected() && !chkPhieuLuu.isSelected() && !chkSuThi.isSelected() && !chkTaiLieu.isSelected() && !chkTinhCam.isSelected() && !chkTrinhTham.isSelected()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chưa chọn Thể loại phim!");
            return false;
        }
        if (txtThoiLuong.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống Thời lượng phim!");
            txtThoiLuong.requestFocus();
            return false;
        }
        try {
            int thoiluong = Integer.parseInt(txtThoiLuong.getText());
            if (thoiluong < 0) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Thời lượng không nhập số âm!");
                txtThoiLuong.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Thời lượng nhập số!");
            return false;
        }

        if (txtDaoDien.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống tên Đạo diễn phim!");
            txtDaoDien.requestFocus();
            return false;
        }
        if (txtDienVien.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống tên Diễn viên phim!");
            txtDienVien.requestFocus();
            return false;
        }
        if (txtMoTa.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống Mô tả phim!");
            txtMoTa.requestFocus();
            return false;
        }
        if (txtNgayCC.getDate() == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống Ngày công chiếu phim!");
            txtNgayCC.requestFocus();
            return false;
        }
        if (txtNgayKT.getDate() == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống Ngày kết thúc phim!");
            txtNgayKT.requestFocus();
            return false;
        }
        Date ngayCC = txtNgayCC.getDate();
        Date ngayKT = txtNgayKT.getDate();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(ngayCC);
        c2.setTime(ngayKT);
        int songay = (int) ((c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000));
        if (songay < 30) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Ngày kết thúc phải sau Ngày công chiếu ít nhất 30 ngày!");
            return false;
        }
        if (txtNamSX.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống Năm sản xuất phim!");
            txtNamSX.requestFocus();
            return false;
        }
        try {
            int namsx = Integer.parseInt(txtNamSX.getText());
            if (namsx < 0) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Năm sản xuất không nhập số âm!");
                txtNamSX.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Năm sản xuất nhập số!");
            return false;
        }

        if (txtNuocSX.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống Nước sản xuất phim!");
            txtNuocSX.requestFocus();
            return false;
        }
        if (txtGiaBanQuyen.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Không để trống Giá bản quyền phim!");
            txtGiaBanQuyen.requestFocus();
            return false;
        }
        try {
            int giaBQ = Integer.parseInt(txtGiaBanQuyen.getText());
            if (giaBQ < 0) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Giá bản quyền không nhập số âm!");
                txtGiaBanQuyen.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Giá bản quyền nhập số!");
            return false;
        }
        return true;
    }

    void setLoaiPhim() {
        int rowSelected = tblPhim.getSelectedRow();
        String loaiPhim = (String) tblPhim.getValueAt(rowSelected, 3);
        String[] arr = loaiPhim.split(",");
        for (String string : arr) {
            setVarible(string).setSelected(true);
        }
    }

    JCheckBox setVarible(String string) {
        try {
            string = removeDiacritics(string);
            string = string.replaceAll("\\s+", "");
            return (JCheckBox) getClass().getDeclaredField("chk" + string.trim()).get(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String removeDiacritics(String input) {
        // Sử dụng Normalizer.normalize để chuẩn hóa chuỗi về Unicode
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

        // Sử dụng biểu thức chính quy để loại bỏ các ký tự không mong muốn (không phải chữ cái)
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(normalized).replaceAll("");

        return result;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtThoiLuong = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMaPhim = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenPhim = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhim = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDienVien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTrangChu = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        chkHanhĐong = new javax.swing.JCheckBox();
        chkKinhDi = new javax.swing.JCheckBox();
        chkHaiHuoc = new javax.swing.JCheckBox();
        chkTinhCam = new javax.swing.JCheckBox();
        chkChinhKich = new javax.swing.JCheckBox();
        chkTaiLieu = new javax.swing.JCheckBox();
        chkLichSu = new javax.swing.JCheckBox();
        chkHoatHinh = new javax.swing.JCheckBox();
        chkPhieuLuu = new javax.swing.JCheckBox();
        chkSuThi = new javax.swing.JCheckBox();
        chkTrinhTham = new javax.swing.JCheckBox();
        chkKhoaHocVienTuong = new javax.swing.JCheckBox();
        chkCaNhac = new javax.swing.JCheckBox();
        chkGiaĐinh = new javax.swing.JCheckBox();
        txtDaoDien = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNamSX = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNuocSX = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jpnAnh = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        txtNgayCC = new com.toedter.calendar.JDateChooser();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtGiaBanQuyen = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Mã Phim:");

        txtThoiLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Tên Phim:");

        txtMaPhim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Thể Loại:");

        txtTenPhim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Thời Lượng:");

        tblPhim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Phim", "Tên Phim", "Thể Loại", "Thời Lượng", "Đạo Diễn", "Diễn Viên Chính", "Mô Tả", "Ngày Công Chiếu", "Ngày Kết Thúc", "Năm Sản Xuất", "Nước Sản Xuất", "Hình", "Giá Bản Quyền"
            }
        ));
        tblPhim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPhimMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhim);

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setText("Xóa ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Đạo Diễn:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Diễn Viên Chính:");

        txtDienVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Ngày Công Chiếu:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Mô Tả:");

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
        jLabel10.setText("Thông Tin Phim:");

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
                .addContainerGap(481, Short.MAX_VALUE))
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

        chkHanhĐong.setText("Hành Động");

        chkKinhDi.setText("Kinh Dị");

        chkHaiHuoc.setText("Hài Hước");

        chkTinhCam.setText("Tình Cảm");

        chkChinhKich.setText("Chính Kịch");

        chkTaiLieu.setText("Tài Liệu");

        chkLichSu.setText("Lịch Sử");

        chkHoatHinh.setText("Hoạt Hình");

        chkPhieuLuu.setText("Phiêu Lưu");

        chkSuThi.setText("Sử Thi");

        chkTrinhTham.setText("Trinh Thám");

        chkKhoaHocVienTuong.setText("Khoa Học Viễn Tưởng");

        chkCaNhac.setText("Ca Nhạc");

        chkGiaĐinh.setText("Gia Đình");

        txtDaoDien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Ngày Kết Thúc:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Năm Sản Xuất:");

        txtNamSX.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Nước Sản Xuất:");

        txtNuocSX.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Hình:");

        jpnAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51)));
        jpnAnh.setForeground(new java.awt.Color(255, 255, 255));

        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHinhMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jpnAnhLayout = new javax.swing.GroupLayout(jpnAnh);
        jpnAnh.setLayout(jpnAnhLayout);
        jpnAnhLayout.setHorizontalGroup(
            jpnAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
        );
        jpnAnhLayout.setVerticalGroup(
            jpnAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtNgayCC.setForeground(new java.awt.Color(255, 51, 51));
        txtNgayCC.setDateFormatString("dd/MM/yyyy");
        txtNgayCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNgayKT.setForeground(new java.awt.Color(255, 51, 51));
        txtNgayKT.setDateFormatString("dd/MM/yyyy");
        txtNgayKT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Giá Bản Quyền:");

        txtGiaBanQuyen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(342, 342, 342)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(chkHoatHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chkHaiHuoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(chkHanhĐong))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(chkPhieuLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chkTrinhTham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(chkTinhCam, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chkGiaĐinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(chkKinhDi, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chkChinhKich, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkKhoaHocVienTuong)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(chkTaiLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chkLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkSuThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chkCaNhac, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDaoDien, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtDienVien, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(txtNgayCC, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNamSX)
                                    .addComponent(txtNuocSX)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jpnAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(txtGiaBanQuyen, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDaoDien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkHanhĐong)
                                    .addComponent(chkKinhDi)
                                    .addComponent(chkChinhKich)
                                    .addComponent(chkLichSu)
                                    .addComponent(chkCaNhac))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkHaiHuoc)
                                    .addComponent(chkTinhCam)
                                    .addComponent(chkTaiLieu)
                                    .addComponent(chkSuThi)
                                    .addComponent(chkGiaĐinh))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkHoatHinh)
                                    .addComponent(chkPhieuLuu)
                                    .addComponent(chkTrinhTham)
                                    .addComponent(chkKhoaHocVienTuong)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtThoiLuong)
                            .addComponent(txtNgayCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDienVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(32, 32, 32))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(txtNuocSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpnAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtGiaBanQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMoi)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnSua)
                        .addComponent(btnXoa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        MainJFrame.showForm(new TrangChuJPanel());
    }//GEN-LAST:event_lblTrangChuMouseClicked


    private void tblPhimMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhimMousePressed
        if (evt.getClickCount() == 2) {
            this.row = tblPhim.rowAtPoint(evt.getPoint());
            setLoaiPhim();
            edit();
        }
    }//GEN-LAST:event_tblPhimMousePressed

    private void lblAnhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMousePressed

    }//GEN-LAST:event_lblAnhMousePressed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            if (checkValidate()) {
                update();
            }
        } catch (ParseException ex) {
            Logger.getLogger(PhimJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        del();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        cleanForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            if (checkValidate()) {
                insert();
            }
        } catch (ParseException ex) {
            Logger.getLogger(PhimJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void lblHinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMousePressed
        if (evt.getClickCount() == 2) {
            chonAnh();
        }
    }//GEN-LAST:event_lblHinhMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JCheckBox chkCaNhac;
    private javax.swing.JCheckBox chkChinhKich;
    private javax.swing.JCheckBox chkGiaĐinh;
    private javax.swing.JCheckBox chkHaiHuoc;
    private javax.swing.JCheckBox chkHanhĐong;
    private javax.swing.JCheckBox chkHoatHinh;
    private javax.swing.JCheckBox chkKhoaHocVienTuong;
    private javax.swing.JCheckBox chkKinhDi;
    private javax.swing.JCheckBox chkLichSu;
    private javax.swing.JCheckBox chkPhieuLuu;
    private javax.swing.JCheckBox chkSuThi;
    private javax.swing.JCheckBox chkTaiLieu;
    private javax.swing.JCheckBox chkTinhCam;
    private javax.swing.JCheckBox chkTrinhTham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpnAnh;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JTable tblPhim;
    private javax.swing.JTextField txtDaoDien;
    private javax.swing.JTextField txtDienVien;
    private javax.swing.JTextField txtGiaBanQuyen;
    private javax.swing.JTextField txtMaPhim;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNamSX;
    private com.toedter.calendar.JDateChooser txtNgayCC;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtNuocSX;
    private javax.swing.JTextField txtTenPhim;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
