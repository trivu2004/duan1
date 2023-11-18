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
import util.BCryptPasswordHashing;

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
        JDBCHelper.update(INSERT_SQL, entity.getMaNV(), entity.getTenNV(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getEmail(), entity.isChucVu(), BCryptPasswordHashing.hashPassword(entity.getMatKhau()));
    }

    @Override
    public void update(NhanVien entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getTenNV(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getEmail(), entity.isChucVu(), BCryptPasswordHashing.hashPassword(entity.getMatKhau()), entity.getMaNV());
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
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("NhanVienID"));
                nv.setTenNV(rs.getString("TenNhanVien"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getBoolean("ChucVu"));
                nv.setMatKhau(rs.getString("MatKhau"));
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
