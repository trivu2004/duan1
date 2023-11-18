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
public class SuatChieu {
    private String maSC;
    private String tenPhim;
    private String tenPC;
    private Date thoiGianBD;
    private Date thoiGianKT;
    private String tenNQL;

    public SuatChieu() {
    }

    public SuatChieu(String maSC, String tenPhim, String tenPC, Date thoiGianBD, Date thoiGianKT, String tenNQL) {
        this.maSC = maSC;
        this.tenPhim = tenPhim;
        this.tenPC = tenPC;
        this.thoiGianBD = thoiGianBD;
        this.thoiGianKT = thoiGianKT;
        this.tenNQL = tenNQL;
    }

    public String getMaSC() {
        return maSC;
    }

    public void setMaSC(String maSC) {
        this.maSC = maSC;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getTenPC() {
        return tenPC;
    }

    public void setTenPC(String tenPC) {
        this.tenPC = tenPC;
    }

    public Date getThoiGianBD() {
        return thoiGianBD;
    }

    public void setThoiGianBD(Date thoiGianBD) {
        this.thoiGianBD = thoiGianBD;
    }

    public Date getThoiGianKT() {
        return thoiGianKT;
    }

    public void setThoiGianKT(Date thoiGianKT) {
        this.thoiGianKT = thoiGianKT;
    }

    public String getTenNQL() {
        return tenNQL;
    }

    public void setTenNQL(String tenNQL) {
        this.tenNQL = tenNQL;
    }
    
    
}
