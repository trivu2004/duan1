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

    public TimVe() {
    }
    private String thoiGianChieu;

    public TimVe(String maSuatChieu, String tenPhim, String tenPhong, String thoiGianChieu) {
        this.maSuatChieu = maSuatChieu;
        this.tenPhim = tenPhim;
        this.tenPhong = tenPhong;
        this.thoiGianChieu = thoiGianChieu;
    }
}
