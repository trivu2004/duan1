/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author 123tu
 */
public class Ve {

    private String maVe;
    private String loaiVe;
    private double giaVe;
    private String ghe;
    private Date ngayMua;
    private String maSC;

    public Ve() {
    }

    public Ve(String maVe, String loaiVe, double giaVe, String ghe, Date ngayMua, String maSC) {
        this.maVe = maVe;
        this.loaiVe = loaiVe;
        this.giaVe = giaVe;
        this.ghe = ghe;
        this.ngayMua = ngayMua;
        this.maSC = maSC;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(String loaiVe) {
        this.loaiVe = loaiVe;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    public String getGhe() {
        return ghe;
    }

    public void setGhe(String ghe) {
        this.ghe = ghe;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getMaSC() {
        return maSC;
    }

    public void setMaSC(String maSC) {
        this.maSC = maSC;
    }

}
