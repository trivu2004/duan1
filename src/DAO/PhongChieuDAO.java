/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.PhongChieu;

/**
 *
 * @author 123tu
 */
public class PhongChieuDAO extends CinemaxDAO<PhongChieu, String>{
    final String INSERT_SQL = "INSERT INTO PhongChieu (PhongID, TenPhong, SoLuongGhe, TinhTrang) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE PhongChieu SET TenPhong=?, SoLuongGhe=?, TinhTrang=? WHERE PhongID=?";
    final String DELETE_SQL = "DELETE FROM PhongChieu WHERE PhongID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM PhongChieu";
    final String SELECT_BY_ID_SQL = "SELECT * FROM PhongChieu WHERE PhongID=?";        
   
    @Override
    public void insert(PhongChieu entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaPC(), entity.getTenPC(), entity.getSoLuongGhe(), entity.getTinhTrang());
    }

    @Override
    public void update(PhongChieu entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getTenPC(), entity.getSoLuongGhe(), entity.getTinhTrang(), entity.getMaPC());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<PhongChieu> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PhongChieu selectById(String id) {
        List<PhongChieu> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PhongChieu> selectBySql(String sql, Object... args) {
        List<PhongChieu> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                PhongChieu entity = new PhongChieu();
                entity.setMaPC(rs.getString("PhongID"));
                entity.setTenPC(rs.getString("TenPhong"));
                entity.setSoLuongGhe(rs.getInt("SoLuongGhe"));
                entity.setTinhTrang(rs.getString("TinhTrang"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
