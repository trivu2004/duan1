/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;

/**
 *
 * @author 123tu
 */
public class NhanVienDAO extends CinemaxDAO<NhanVien, String>{
    final String INSERT_SQL = "INSERT INTO NhanVien (NhanVienID, TenNhanVien, GioiTinh, NgaySinh, Email, ChucVu, MatKhau) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE NhanVien SET TenNhanVien=?, GioiTinh=?, NgaySinh=?, Email=?, ChucVu=?, MatKhau=? WHERE NhanVienID=?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE NhanVienID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE NhanVienID=?";        
   
    @Override
    public void insert(NhanVien entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaNV(), entity.getTenNV(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getEmail(), entity.isChucVu(), entity.getMatKhau());
    }

    @Override
    public void update(NhanVien entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getTenNV(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getEmail(), entity.isChucVu(), entity.getMatKhau(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setTenNV(rs.getString("TenNV"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setEmail(rs.getString("Email"));
                entity.setChucVu(rs.getBoolean("ChucVu"));
                entity.setMatKhau(rs.getString("MatKhau"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
