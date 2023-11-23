package model;

/**
 *
 * @author Tri Dung
 */
public class BieuDoDoanhThu {
    String tenPhim;
    int tongDoanhThu;

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public int getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(int tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public BieuDoDoanhThu(String tenPhim, int tongDoanhThu) {
        this.tenPhim = tenPhim;
        this.tongDoanhThu = tongDoanhThu;
    }

    public BieuDoDoanhThu() {
    }
}
