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
public class SuatChieuDAO extends CinemaxDAO<SuatChieu, String>{
    final String INSERT_SQL = "INSERT INTO SuatChieu (SuatChieuID, PhongID, PhimID, ThoiGianBatDau, ThoiGianKetThuc, NhanVienID) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE SuatChieu SET PhongID=?, PhimID=?, ThoiGianBatDau=?, ThoiGianKetThuc=?, NhanVienID=? WHERE SuatChieuID=?";
    final String DELETE_SQL = "DELETE FROM SuatChieu WHERE SuatChieuID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM SuatChieu";
    final String SELECT_BY_ID_SQL = "SELECT * FROM SuatChieu WHERE SuatChieuID=?";        
   
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
                entity.setMaSC(rs.getString("MaSC"));
                entity.setTenPC(rs.getString("TenPC"));
                entity.setTenPhim(rs.getString("Phim"));
                entity.setThoiGianBD(rs.getDate("ThoiGianBD"));
                entity.setThoiGianKT(rs.getDate("ThoiGianKT"));
                entity.setTenNQL(rs.getString("TenNQL"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
