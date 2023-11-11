/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author USER
 */
public class SuatChieu {
    String SuatChieuID,PhongID,PhimID;
    Date ThoiGianBatDau , ThoiGianKetThuc;

    public String getSuatChieuID() {
        return SuatChieuID;
    }

    public void setSuatChieuID(String SuatChieuID) {
        this.SuatChieuID = SuatChieuID;
    }

    public String getPhongID() {
        return PhongID;
    }

    public SuatChieu() {
    }

    public void setPhongID(String PhongID) {
        this.PhongID = PhongID;
    }

    public String getPhimID() {
        return PhimID;
    }

    public void setPhimID(String PhimID) {
        this.PhimID = PhimID;
    }

    public Date getThoiGianBatDau() {
        return ThoiGianBatDau;
    }

    public void setThoiGianBatDau(Date ThoiGianBatDau) {
        this.ThoiGianBatDau = ThoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return ThoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date ThoiGianKetThuc) {
        this.ThoiGianKetThuc = ThoiGianKetThuc;
    }

    public SuatChieu(String SuatChieuID, String PhongID, String PhimID, Date ThoiGianBatDau, Date ThoiGianKetThuc) {
        this.SuatChieuID = SuatChieuID;
        this.PhongID = PhongID;
        this.PhimID = PhimID;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.ThoiGianKetThuc = ThoiGianKetThuc;
    }
    
}
