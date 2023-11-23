/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Form.SuatChieuJPanel;
import Helper.JDBCHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.SuatChieu;
import model.TimVe;
import raven.toast.Notifications;

/**
 *
 * @author 123tu
 */
public class SuatChieuDAO extends CinemaxDAO<SuatChieu, String> {

    final String INSERT_SQL = "INSERT INTO SuatChieu (SuatChieuID, PhongID, PhimID, ThoiGianBatDau, ThoiGianKetThuc, NhanVienID) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE SuatChieu SET PhongID=?, PhimID=?, ThoiGianBatDau=?, ThoiGianKetThuc=?, NhanVienID=? WHERE SuatChieuID=?";
    final String DELETE_SQL = "DELETE FROM SuatChieu WHERE SuatChieuID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM SuatChieu";
    final String SELECT_BY_ID_SQL = "SELECT * FROM SuatChieu WHERE SuatChieuID=?";
    SuatChieuJPanel sc;

    public static String PhimID, PhongID;

    public static String getPhimID() {
        return PhimID;
    }

    public static void setPhimID(String PhimID) {
        SuatChieuDAO.PhimID = PhimID;
    }

    public static String getPhongID() {
        return PhongID;
    }

    public static void setPhongID(String PhongID) {
        SuatChieuDAO.PhongID = PhongID;
    }

    @Override
    public void insert(SuatChieu entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaSC(), entity.getTenPC(), entity.getTenPhim(), entity.getThoiGianBD(), entity.getThoiGianKT(), entity.getTenNQL());
    }

    @Override
    public void update(SuatChieu entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getTenPC(), entity.getTenPhim(), entity.getThoiGianBD(), entity.getThoiGianKT(), entity.getTenNQL(), entity.getMaSC());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<SuatChieu> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public SuatChieu selectById(String id) {
        List<SuatChieu> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SuatChieu> selectBySql(String sql, Object... args) {
        List<SuatChieu> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                SuatChieu entity = new SuatChieu();
                entity.setMaSC(rs.getString("SuatChieuID"));
                entity.setTenPC(rs.getString("PhongID"));
                entity.setTenPhim(rs.getString("PhimID"));
                entity.setThoiGianBD(rs.getDate("ThoiGianBatDau"));
                entity.setThoiGianKT(rs.getDate("ThoiGianKetThuc"));
                entity.setTenNQL(rs.getString("NhanVienID"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void fillTable1(JTable tblSuatChieu) {
        DefaultTableModel model = (DefaultTableModel) tblSuatChieu.getModel();
        model.setRowCount(0);
        int stt = 1;
        try {
            String sql = "SELECT sc.SuatChieuID,p.TenPhim,pc.TenPhong,sc.ThoiGianBatDau,sc.ThoiGianKetThuc,pc.PhongID,p.PhimID,nv.NhanVienID FROM SuatChieu sc\n"
                    + "RIGHT JOIN Phim p ON sc.PhimID = p.PhimID\n"
                    + "join PhongChieu pc on sc.PhongID = pc.PhongID "
                    + "join NhanVien nv on nv.NhanVienID = sc.NhanVienID";
            ResultSet kq = JDBCHelper.query(sql);
            while (kq.next()) {
                String suatchieuid = kq.getString("SuatChieuID");
                String phongID = kq.getString("PhongID");
                String phimID = kq.getString("PhimID");
                String thoigianbatdau = kq.getString("ThoiGianBatDau");
                String thoigianketthuc = kq.getString("ThoiGianKetThuc");
                String nhanvienid = kq.getString("NhanVienID");
                Object[] data = {stt++, suatchieuid, phongID, phimID, thoigianbatdau, thoigianketthuc, nhanvienid};
                model.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fillPhim(JComboBox cboPhim) {
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

    public static void fillPhong(JComboBox cboPhong) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboPhong.getModel();
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

    public static void fillQuanLy(JComboBox cboQuanLy) {
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

//    public void insert1() {
//        try {
//            String sql = "insert into SuatChieu values(?,?,?,?,?,?)";
//            PreparedStatement st = JDBCHelper.prepareStatement(sql);
//            st.setString(1, sc.txtMaSC.getText());
//            st.setString(2, (String) sc.cboPhim.getSelectedItem());
//            st.setString(3, (String) sc.cboPhongChieu.getSelectedItem());
//            st.setString(4, sc.txtTGBatDau.getText());
//            st.setString(5, sc.txtTGKetThuc.getText());
//            st.setString(6, sc.txtTGKetThuc1.getText());
//            st.executeUpdate();
//            fillTable1(sc.tblSuatChieu);
//            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Thêm thành công !");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void delete1() {
//        try {
//            String sql = "delete * from SuatChieu where SuatChieuID = ?";
//            PreparedStatement st = Helper.JDBCHelper.prepareStatement(sql);
//            st.setString(1, sc.txtMaSC.getText());
//            st.executeUpdate();
//            fillTable1();
//            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Xóa thành công !");
//        } catch (Exception e) {
//        }
//    }
}
