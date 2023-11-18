/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Phim;
import model.TimVe;

/**
 *
 * @author 123tu
 */
public class PhimDAO extends CinemaxDAO<Phim, String> {

    final String INSERT_SQL = "INSERT INTO Phim (PhimID, TenPhim, DaoDien, DienVienChinh, ThoiLuong, TheLoai, NgayCongChieu, NgayKetThuc, Hinh, MoTa, QuocGia, NamSanXuat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Phim SET TenPhim=?, DaoDien=?, DienVienChinh=?, ThoiLuong=?, TheLoai=?, NgayCongChieu=?, NgayKetThuc=?, Hinh=?, MoTa=?, QuocGia=?, NamSanXuat=? WHERE PhimID=?";
    final String DELETE_SQL = "DELETE FROM Phim WHERE PhimID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM Phim";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Phim WHERE PhimID=?";

    @Override
    public void insert(Phim entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaPhim(), entity.getTenPhim(), entity.getDaoDien(), entity.getDienVien(), entity.getThoiLuong(), entity.getTheLoai(), entity.getNgayCongChieu(), entity.getNgayKetThuc(), entity.getHinh(), entity.getMoTa(), entity.getNuocSX(), entity.getNamSX());
    }

    @Override
    public void update(Phim entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getTenPhim(), entity.getDaoDien(), entity.getDienVien(), entity.getThoiLuong(), entity.getTheLoai(), entity.getNgayCongChieu(), entity.getNgayKetThuc(), entity.getHinh(), entity.getMoTa(), entity.getNuocSX(), entity.getNamSX(), entity.getMaPhim());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<Phim> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Phim selectById(String id) {
        List<Phim> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Phim> selectBySql(String sql, Object... args) {
        List<Phim> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                Phim entity = new Phim();
                entity.setMaPhim(rs.getString("PhimID"));
                entity.setTenPhim(rs.getString("TenPhim"));
                entity.setDaoDien(rs.getString("DaoDien"));
                entity.setDienVien(rs.getString("DienVienChinh"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setTheLoai(rs.getString("TheLoai"));
                entity.setNgayCongChieu(rs.getDate("NgayCongChieu"));
                entity.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                entity.setHinh(rs.getString("Hinh"));
                entity.setMoTa(rs.getString("MoTa"));
                entity.setNuocSX(rs.getString("QuocGia"));
                entity.setNamSX(rs.getInt("NamSanXuat"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<TimVe> FintTicket(String ThoiGian, String PhongChieu, String Phim) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
