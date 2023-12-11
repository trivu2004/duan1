package model;

/**
 *
 * @author Tri Dung
 */
public class TimVe {
    private String maSuatChieu;
    private String tenPhim;
    private String tenPhong;

    public String getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(String maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getThoiGianChieu() {
        return thoiGianChieu;
    }

    public void setThoiGianChieu(String thoiGianChieu) {
        this.thoiGianChieu = thoiGianChieu;
    }

    public String getCaChieu() {
        return caChieu;
    }

    public void setCaChieu(String caChieu) {
        this.caChieu = caChieu;
    }

    public TimVe() {
    }
    private String thoiGianChieu;
    private String caChieu;

    public TimVe(String maSuatChieu, String tenPhim, String tenPhong, String thoiGianChieu, String caChieu) {
        this.maSuatChieu = maSuatChieu;
        this.tenPhim = tenPhim;
        this.tenPhong = tenPhong;
        this.thoiGianChieu = thoiGianChieu;
        this.caChieu = caChieu;
    }
}
