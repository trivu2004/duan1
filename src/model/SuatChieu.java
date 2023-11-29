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
    private String cachieu;
    private String tenNQL;
    private String ngaytao;

    public SuatChieu() {
    }

    public SuatChieu(String maSC, String tenPhim, String tenPC, String cachieu, String tenNQL, String ngaytao) {
        this.maSC = maSC;
        this.tenPhim = tenPhim;
        this.tenPC = tenPC;
        this.cachieu = cachieu;
        this.tenNQL = tenNQL;
        this.ngaytao = ngaytao;
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

    public String getCachieu() {
        return cachieu;
    }

    public void setCachieu(String cachieu) {
        this.cachieu = cachieu;
    }

    public String getTenNQL() {
        return tenNQL;
    }

    public void setTenNQL(String tenNQL) {
        this.tenNQL = tenNQL;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

}
