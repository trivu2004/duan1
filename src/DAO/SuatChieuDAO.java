/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.SuatChieu;

/**
 *
 * @author 123tu
 */
public class SuatChieuDAO extends CinemaxDAO<SuatChieu, String> {

    final String INSERT_SQL = "INSERT INTO SuatChieu (SuatChieuID, PhongID, PhimID, NhanVienID , CaChieu ,NgayTaoXuat) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE SuatChieu SET PhongID=?, PhimID=?, NhanVienID=?, CaChieu = ? , NgayTaoXuat = ? WHERE SuatChieuID=?";
    final String DELETE_SQL = "DELETE FROM SuatChieu WHERE SuatChieuID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM SuatChieu";
    final String SELECT_BY_ID_SQL = "SELECT * FROM SuatChieu WHERE SuatChieuID=?";
    final String tutang = "SELECT SuatChieuID\n"
            + "FROM SuatChieu\n"
            + "ORDER BY SuatChieuID DESC\n"
            + "LIMIT 1";

    final String SELECT_THOIGIAN = "select * from SuatChieu\n"
            + "where NgayTaoXuat >= ?\n"
            + "GROUP BY NgayTaoXuat\n"
            + "ASC";
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

    public String tutang() {
        String masc = "";
        try {
            ResultSet rs = JDBCHelper.query(tutang);
            while (rs.next()) {
                masc = rs.getString("SuatChieuID");
            }
        } catch (Exception e) {
        }
        return masc;
    }

    @Override
    public void insert(SuatChieu entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaSC(), entity.getTenPC(), entity.getTenPhim(), entity.getTenNQL(), entity.getCachieu(), entity.getNgaytao());
    }

    @Override
    public void update(SuatChieu entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getTenPC(), entity.getTenPhim(), entity.getTenNQL(), entity.getCachieu(), entity.getNgaytao(), entity.getMaSC());
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
                entity.setTenNQL(rs.getString("NhanVienID"));
                entity.setCachieu(rs.getString("CaChieu"));
                entity.setNgaytao(rs.getDate("NgayTaoXuat"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<SuatChieu> inThoiGian(String date) {
        List<SuatChieu> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(SELECT_THOIGIAN, date);
            while (rs.next()) {
                SuatChieu entity = new SuatChieu();
                entity.setMaSC(rs.getString("SuatChieuID"));
                entity.setTenPC(rs.getString("PhongID"));
                entity.setTenPhim(rs.getString("PhimID"));
                entity.setTenNQL(rs.getString("NhanVienID"));
                entity.setCachieu(rs.getString("CaChieu"));
                entity.setNgaytao(rs.getDate("NgayTaoXuat"));
                list.add(entity);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
