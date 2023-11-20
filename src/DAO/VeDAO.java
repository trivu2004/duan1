/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DanhSachVe;
import model.TimVe;
import model.Ve;

/**
 *
 * @author 123tu
 */
public class VeDAO extends CinemaxDAO<Ve, String> {
    
    final String INSERT_SQL = "INSERT INTO Ve (SuatChieuID, Ghe, GiaVe, NgayMua, LoaiVe) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Ve SET SuatChieuID=?, Ghe=?, GiaVe=?, NgayMua=?, LoaiVe=? WHERE VeID=?";
    final String DELETE_SQL = "DELETE FROM Ve WHERE VeID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM Ve";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Ve WHERE VeID=?";
    final String SELECT_TICKET = "SELECT SuatChieu.SuatChieuID, Phim.TenPhim, PhongChieu.TenPhong, ThoiGianBatDau "
            + "FROM SuatChieu "
            + "JOIN Phim ON SuatChieu.PhimID = Phim.PhimID "
            + "JOIN PhongChieu ON SuatChieu.PhongID = PhongChieu.PhongID "
            + "WHERE Phim.TenPhim = ? AND PhongChieu.TenPhong = ? AND ThoiGianBatDau like ?"
            + "group by SuatChieu.SuatChieuID";
    
    final String SELECT_VEID = "select VeID from Ve\n"
            + "ORDER BY VeID\n"
            + "DESC\n"
            + "LIMIT ?";
    
    final String SELECT_DANHSACHVE = "select VeID,TenPhim,TenPhong,ThoiGianBatDau,Ghe,LoaiVe,GiaVe,NgayMua from SuatChieu\n"
            + "Join Ve\n"
            + "on SuatChieu.SuatChieuID = Ve.SuatChieuID\n"
            + "JOIN Phim\n"
            + "on SuatChieu.PhimID = Phim.PhimID\n"
            + "Join PhongChieu\n"
            + "on SuatChieu.PhongID = PhongChieu.PhongID\n"
            + "where LoaiVe like ? and TenPhim = ? and TenPhong = ? and ThoiGianBatDau like ?";
    
    final String SELECT_TIMGHE = "select Ghe from Ve\n"
            + "inner join SuatChieu SC\n"
            + "on Ve.SuatChieuID = SC.SuatChieuID\n"
            + "where SC.SuatChieuID = ?";
    
    @Override
    public void insert(Ve entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaSC(), entity.getGhe(), entity.getGiaVe(), entity.getNgayMua(), entity.getLoaiVe());
    }
    
    @Override
    public void update(Ve entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getMaSC(), entity.getGhe(), entity.getGiaVe(), entity.getNgayMua(), entity.getLoaiVe(), entity.getMaVe());
    }
    
    @Override
    public void delete(String id) {
        JDBCHelper.update(DELETE_SQL, id);
    }
    
    @Override
    public List<Ve> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }
    
    @Override
    public Ve selectById(String id) {
        List<Ve> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    @Override
    public List<Ve> selectBySql(String sql, Object... args) {
        List<Ve> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                Ve entity = new Ve();
                entity.setMaVe(rs.getString("VeID"));
                entity.setMaSC(rs.getString("SuatChieuID"));
                entity.setGhe(rs.getString("Ghe"));
                entity.setGiaVe(rs.getDouble("GiaVe"));
                entity.setNgayMua(rs.getString("NgayMua"));
                entity.setLoaiVe(rs.getString("LoaiVe"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<TimVe> findTicket(String ThoiGian, String PhongChieu, String Phim) {
        List<TimVe> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(SELECT_TICKET, Phim, PhongChieu, ThoiGian + "%");
            while (rs.next()) {
                TimVe entity = new TimVe();
                entity.setMaSuatChieu(rs.getString("SuatChieuID"));
                entity.setTenPhim(rs.getString("TenPhim"));
                entity.setTenPhong(rs.getString("TenPhong"));
                entity.setThoiGianChieu(rs.getString("ThoiGianBatDau"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String findVeID() {
        Object veID = "";
        try {
            veID = JDBCHelper.value(SELECT_VEID, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veID + "";
    }
    
    public List<DanhSachVe> inDanhSachVe(String loaiVe, String tenPhim, String tenPhong, String thoiGianBatDau) {
        List<DanhSachVe> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(SELECT_DANHSACHVE, "%" + loaiVe + "%", tenPhim, tenPhong, thoiGianBatDau + "%");
            while (rs.next()) {
                DanhSachVe entity = new DanhSachVe();
                entity.setGhe(rs.getString("Ghe"));
                entity.setGiaVe(rs.getString("GiaVe"));
                entity.setLoaiVe(rs.getString("LoaiVe"));
                entity.setNgayMua(rs.getString("NgayMua"));
                entity.setTenPhim(rs.getString("TenPhim"));
                entity.setTenPhong(rs.getString("TenPhong"));
                entity.setThoiGianBatDau(rs.getString("ThoiGianBatDau"));
                entity.setVeID(rs.getString("VeID"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<String> timGhe(String suatChieuID) {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(SELECT_TIMGHE, suatChieuID);
            while (rs.next()) {                
                list.add(rs.getString("Ghe"));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
