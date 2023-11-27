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
    private String tinhTrang;

    public PhongChieu() {
    }

    
    public PhongChieu(String maPC, String tinhTrang) {
        this.maPC = maPC;
        this.tinhTrang = tinhTrang;
    }

    public String getMaPC() {
        return maPC;
    }

    public void setMaPC(String maPC) {
        this.maPC = maPC;
    }


    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
 
    
}
