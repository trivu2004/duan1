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
public class Phim {
    private String maPhim;
    private String tenPhim;
    private String theLoai;
    private int thoiLuong;
    private String daoDien;
    private String dienVien;
    private String moTa;
    private Date ngayCongChieu;
    private Date ngayKetThuc;
    private int namSX;
    private String nuocSX;
    private String hinh;

    public Phim() {
    }

    public Phim(String maPhim, String tenPhim, String theLoai, int thoiLuong, String daoDien, String dienVien, String moTa, Date ngayCongChieu, Date ngayKetThuc, int namSX, String nuocSX, String hinh) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.daoDien = daoDien;
        this.dienVien = dienVien;
        this.moTa = moTa;
        this.ngayCongChieu = ngayCongChieu;
        this.ngayKetThuc = ngayKetThuc;
        this.namSX = namSX;
        this.nuocSX = nuocSX;
        this.hinh = hinh;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public String getDienVien() {
        return dienVien;
    }

    public void setDienVien(String dienVien) {
        this.dienVien = dienVien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayCongChieu() {
        return ngayCongChieu;
    }

    public void setNgayCongChieu(Date ngayCongChieu) {
        this.ngayCongChieu = ngayCongChieu;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getNamSX() {
        return namSX;
    }

    public void setNamSX(int namSX) {
        this.namSX = namSX;
    }

    public String getNuocSX() {
        return nuocSX;
    }

    public void setNuocSX(String nuocSX) {
        this.nuocSX = nuocSX;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
    
}
