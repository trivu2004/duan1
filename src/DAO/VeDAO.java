/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Ve;

/**
 *
 * @author 123tu
 */
public class VeDAO extends CinemaxDAO<Ve, String> {

    final String INSERT_SQL = "INSERT INTO Ve (VeID, SuatChieuID, Ghe, GiaVe, NgayMua, LoaiVe) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Ve SET SuatChieuID=?, Ghe=?, GiaVe=?, NgayMua=?, LoaiVe=? WHERE VeID=?";
    final String DELETE_SQL = "DELETE FROM Ve WHERE VeID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM Ve";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Ve WHERE VeID=?";

    @Override
    public void insert(Ve entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaVe(), entity.getMaSC(), entity.getGhe(), entity.getGiaVe(), entity.getNgayMua(), entity.getLoaiVe());
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
                entity.setMaVe(rs.getString("MaVe"));
                entity.setMaSC(rs.getString("MaSC"));
                entity.setGhe(rs.getString("Ghe"));
                entity.setGiaVe(rs.getDouble("GiaVe"));
                entity.setNgayMua(rs.getDate("NgayMua"));
                entity.setLoaiVe(rs.getString("LoaiVe"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
