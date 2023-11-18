/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 123tu
 */
public class PhongChieu {
    private String maPC;
    private String tenPC;
    private int soLuongGhe;
    private String tinhTrang;

    public PhongChieu() {
    }

    
    public PhongChieu(String maPC, String tenPC, int soLuongGhe, String tinhTrang) {
        this.maPC = maPC;
        this.tenPC = tenPC;
        this.soLuongGhe = soLuongGhe;
        this.tinhTrang = tinhTrang;
    }

    public String getMaPC() {
        return maPC;
    }

    public void setMaPC(String maPC) {
        this.maPC = maPC;
    }

    public String getTenPC() {
        return tenPC;
    }

    public void setTenPC(String tenPC) {
        this.tenPC = tenPC;
    }

    public int getSoLuongGhe() {
        return soLuongGhe;
    }

    public void setSoLuongGhe(int soLuongGhe) {
        this.soLuongGhe = soLuongGhe;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    
}
