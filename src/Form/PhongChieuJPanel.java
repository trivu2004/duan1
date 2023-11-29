package Form;

import Helper.JDBCHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.swing.Timer;

public class PhongChieuJPanel extends javax.swing.JPanel {

    public PhongChieuJPanel() {
        initComponents();
        lbltrangthai001.setText("Trống");
        lbltrangthai002.setText("Trống");
        lbltrangthai003.setText("Trống");
        lbltrangthai004.setText("Trống");
        lbltrangthai005.setText("Trống");
        lbltrangthai006.setText("Trống");
        fill();
        fillghe();

        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                String text = sdf.format(now);
                lblDongHo.setText(text);
            }
        }).start();
    }

    boolean checktrangthai(LocalTime startTime, LocalTime endTime) {
        LocalTime currentTime = LocalTime.now();

        return !currentTime.isBefore(startTime) && !currentTime.isAfter(endTime);
    }

    boolean checktrangthai1(int timestart, int timeend) {
        LocalTime dau = LocalTime.of(timestart, 0);
        LocalTime cuoi = LocalTime.of(timeend, 0);

        if (checktrangthai(dau, cuoi)) {
            return true;
        } else {
            return false;
        }
    }

    void fillghe() {
        try {
            String sql = "select sc.PhongID from SuatChieu as sc join Ve as v on sc.SuatChieuID = v.SuatChieuID where v.NgayMua = ?";
            PreparedStatement st = JDBCHelper.prepareStatement(sql);
            LocalDate hnay = LocalDate.now();
            st.setString(1, hnay + "");
            ResultSet kq = st.executeQuery();
            while (kq.next()) {

                if (kq.getString("PhongID").equals("P001")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select sc.CaChieu, v.Ghe from SuatChieu as sc join Ve as v on sc.SuatChieuID = v.SuatChieuID where NgayMua = ? and sc.PhongID = ?");
                    st1.setString(1, hnay + "");
                    st1.setString(2, "P001");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString(1).equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                String dsghesd1 = kq1.getString(2);
                                String[] myArray1 = dsghesd1.split(",");
                                lblghetrong001.setText((56 - myArray1.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                String dsghesd2 = kq1.getString(2);
                                String[] myArray2 = dsghesd2.split(",");
                                lblghetrong001.setText((56 - myArray2.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                String dsghesd3 = kq1.getString(2);
                                String[] myArray3 = dsghesd3.split(",");
                                lblghetrong001.setText((56 - myArray3.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                String dsghesd4 = kq1.getString(2);
                                String[] myArray4 = dsghesd4.split(",");
                                lblghetrong001.setText((56 - myArray4.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                String dsghesd5 = kq1.getString(2);
                                String[] myArray5 = dsghesd5.split(",");
                                lblghetrong001.setText((56 - myArray5.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                String dsghesd6 = kq1.getString(2);
                                String[] myArray6 = dsghesd6.split(",");
                                lblghetrong001.setText((56 - myArray6.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                String dsghesd7 = kq1.getString(2);
                                String[] myArray7 = dsghesd7.split(",");
                                lblghetrong001.setText((56 - myArray7.length) + "");
                            }
                        }

                    }
                }

                if (kq.getString("PhongID").equals("P002")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select sc.CaChieu, v.Ghe from SuatChieu as sc join Ve as v on sc.SuatChieuID = v.SuatChieuID where NgayMua = ? and sc.PhongID = ?");
                    st1.setString(1, hnay + "");
                    st1.setString(2, "P002");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString(1).equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                String dsghesd1 = kq1.getString(2);
                                String[] myArray1 = dsghesd1.split(",");
                                lblghetrong002.setText((56 - myArray1.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                String dsghesd2 = kq1.getString(2);
                                String[] myArray2 = dsghesd2.split(",");
                                lblghetrong002.setText((56 - myArray2.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                String dsghesd3 = kq1.getString(2);
                                String[] myArray3 = dsghesd3.split(",");
                                lblghetrong002.setText((56 - myArray3.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                String dsghesd4 = kq1.getString(2);
                                String[] myArray4 = dsghesd4.split(",");
                                lblghetrong002.setText((56 - myArray4.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                String dsghesd5 = kq1.getString(2);
                                String[] myArray5 = dsghesd5.split(",");
                                lblghetrong002.setText((56 - myArray5.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                String dsghesd6 = kq1.getString(2);
                                String[] myArray6 = dsghesd6.split(",");
                                lblghetrong002.setText((56 - myArray6.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                String dsghesd7 = kq1.getString(2);
                                String[] myArray7 = dsghesd7.split(",");
                                lblghetrong002.setText((56 - myArray7.length) + "");
                            }
                        }

                    }
                }

                if (kq.getString("PhongID").equals("P003")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select sc.CaChieu, v.Ghe from SuatChieu as sc join Ve as v on sc.SuatChieuID = v.SuatChieuID where NgayMua = ? and sc.PhongID = ?");
                    st1.setString(1, hnay + "");
                    st1.setString(2, "P003");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString(1).equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                String dsghesd1 = kq1.getString(2);
                                String[] myArray1 = dsghesd1.split(",");
                                lblghetrong003.setText((56 - myArray1.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                String dsghesd2 = kq1.getString(2);
                                String[] myArray2 = dsghesd2.split(",");
                                lblghetrong003.setText((56 - myArray2.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                String dsghesd3 = kq1.getString(2);
                                String[] myArray3 = dsghesd3.split(",");
                                lblghetrong003.setText((56 - myArray3.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                String dsghesd4 = kq1.getString(2);
                                String[] myArray4 = dsghesd4.split(",");
                                lblghetrong003.setText((56 - myArray4.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                String dsghesd5 = kq1.getString(2);
                                String[] myArray5 = dsghesd5.split(",");
                                lblghetrong003.setText((56 - myArray5.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                String dsghesd6 = kq1.getString(2);
                                String[] myArray6 = dsghesd6.split(",");
                                lblghetrong003.setText((56 - myArray6.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                String dsghesd7 = kq1.getString(2);
                                String[] myArray7 = dsghesd7.split(",");
                                lblghetrong003.setText((56 - myArray7.length) + "");
                            }
                        }

                    }
                }

                if (kq.getString("PhongID").equals("P004")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select sc.CaChieu, v.Ghe from SuatChieu as sc join Ve as v on sc.SuatChieuID = v.SuatChieuID where NgayMua = ? and sc.PhongID = ?");
                    st1.setString(1, hnay + "");
                    st1.setString(2, "P004");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString(1).equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                String dsghesd1 = kq1.getString(2);
                                String[] myArray1 = dsghesd1.split(",");
                                lblghetrong004.setText((56 - myArray1.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                String dsghesd2 = kq1.getString(2);
                                String[] myArray2 = dsghesd2.split(",");
                                lblghetrong004.setText((56 - myArray2.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                String dsghesd3 = kq1.getString(2);
                                String[] myArray3 = dsghesd3.split(",");
                                lblghetrong004.setText((56 - myArray3.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                String dsghesd4 = kq1.getString(2);
                                String[] myArray4 = dsghesd4.split(",");
                                lblghetrong004.setText((56 - myArray4.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                String dsghesd5 = kq1.getString(2);
                                String[] myArray5 = dsghesd5.split(",");
                                lblghetrong004.setText((56 - myArray5.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                String dsghesd6 = kq1.getString(2);
                                String[] myArray6 = dsghesd6.split(",");
                                lblghetrong004.setText((56 - myArray6.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                String dsghesd7 = kq1.getString(2);
                                String[] myArray7 = dsghesd7.split(",");
                                lblghetrong004.setText((56 - myArray7.length) + "");
                            }
                        }

                    }
                }

                if (kq.getString("PhongID").equals("P005")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select sc.CaChieu, v.Ghe from SuatChieu as sc join Ve as v on sc.SuatChieuID = v.SuatChieuID where NgayMua = ? and sc.PhongID = ?");
                    st1.setString(1, hnay + "");
                    st1.setString(2, "P005");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString(1).equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                String dsghesd1 = kq1.getString(2);
                                String[] myArray1 = dsghesd1.split(",");
                                lblghetrong005.setText((56 - myArray1.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                String dsghesd2 = kq1.getString(2);
                                String[] myArray2 = dsghesd2.split(",");
                                lblghetrong005.setText((56 - myArray2.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                String dsghesd3 = kq1.getString(2);
                                String[] myArray3 = dsghesd3.split(",");
                                lblghetrong005.setText((56 - myArray3.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                String dsghesd4 = kq1.getString(2);
                                String[] myArray4 = dsghesd4.split(",");
                                lblghetrong005.setText((56 - myArray4.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                String dsghesd5 = kq1.getString(2);
                                String[] myArray5 = dsghesd5.split(",");
                                lblghetrong005.setText((56 - myArray5.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                String dsghesd6 = kq1.getString(2);
                                String[] myArray6 = dsghesd6.split(",");
                                lblghetrong005.setText((56 - myArray6.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                String dsghesd7 = kq1.getString(2);
                                String[] myArray7 = dsghesd7.split(",");
                                lblghetrong005.setText((56 - myArray7.length) + "");
                            }
                        }

                    }
                }

                if (kq.getString("PhongID").equals("P006")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select sc.CaChieu, v.Ghe from SuatChieu as sc join Ve as v on sc.SuatChieuID = v.SuatChieuID where NgayMua = ? and sc.PhongID = ?");
                    st1.setString(1, hnay + "");
                    st1.setString(2, "P006");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString(1).equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                String dsghesd1 = kq1.getString(2);
                                String[] myArray1 = dsghesd1.split(",");
                                lblghetrong006.setText((56 - myArray1.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                String dsghesd2 = kq1.getString(2);
                                String[] myArray2 = dsghesd2.split(",");
                                lblghetrong006.setText((56 - myArray2.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                String dsghesd3 = kq1.getString(2);
                                String[] myArray3 = dsghesd3.split(",");
                                lblghetrong006.setText((56 - myArray3.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                String dsghesd4 = kq1.getString(2);
                                String[] myArray4 = dsghesd4.split(",");
                                lblghetrong006.setText((56 - myArray4.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                String dsghesd5 = kq1.getString(2);
                                String[] myArray5 = dsghesd5.split(",");
                                lblghetrong006.setText((56 - myArray5.length) + "");
                            }
                        }

                        if (kq1.getString(1).equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                String dsghesd6 = kq1.getString(2);
                                String[] myArray6 = dsghesd6.split(",");
                                lblghetrong006.setText((56 - myArray6.length) + "");
                            }
                        }
                        if (kq1.getString(1).equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                String dsghesd7 = kq1.getString(2);
                                String[] myArray7 = dsghesd7.split(",");
                                lblghetrong006.setText((56 - myArray7.length) + "");
                            }
                        }

                    }
                }

            }
        } catch (Exception e) {
        }
    }

    void fill() {
        try {
            String sql = "select PhongID from SuatChieu where NgayTaoXuat = ?";
            PreparedStatement st = JDBCHelper.prepareStatement(sql);
            LocalDate hnay = LocalDate.now();
            st.setString(1, hnay + "");
            ResultSet kq = st.executeQuery();
            while (kq.next()) {
                if (kq.getString("PhongID").equals("P001")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select CaChieu from SuatChieu where PhongID = ? and NgayTaoXuat = ?");
                    st1.setString(1, "P001");
                    st1.setString(2, hnay + "");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString("CaChieu").equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                lbltrangthai001.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                lbltrangthai001.setText("Đang Chiếu");
                            }
                        }

                        if (kq1.getString("CaChieu").equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                lbltrangthai001.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                lbltrangthai001.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                lbltrangthai001.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                lbltrangthai001.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                lbltrangthai001.setText("Đang Chiếu");
                            }
                        }
                    }
                }

                if (kq.getString("PhongID").equals("P002")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select CaChieu from SuatChieu where PhongID = ? and NgayTaoXuat = ?");
                    st1.setString(1, "P002");
                    st1.setString(2, hnay + "");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString("CaChieu").equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                lbltrangthai002.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                lbltrangthai002.setText("Đang Chiếu");
                            }
                        }

                        if (kq1.getString("CaChieu").equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                lbltrangthai002.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                lbltrangthai002.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                lbltrangthai002.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                lbltrangthai002.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                lbltrangthai002.setText("Đang Chiếu");
                            }
                        }
                    }
                }

                if (kq.getString("PhongID").equals("P003")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select CaChieu from SuatChieu where PhongID = ? and NgayTaoXuat = ?");
                    st1.setString(1, "P003");
                    st1.setString(2, hnay + "");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString("CaChieu").equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                lbltrangthai003.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                lbltrangthai003.setText("Đang Chiếu");
                            }
                        }

                        if (kq1.getString("CaChieu").equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                lbltrangthai003.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                lbltrangthai003.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                lbltrangthai003.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                lbltrangthai003.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                lbltrangthai003.setText("Đang Chiếu");
                            }
                        }
                    }
                }

                if (kq.getString("PhongID").equals("P004")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select CaChieu from SuatChieu where PhongID = ? and NgayTaoXuat = ?");
                    st1.setString(1, "P004");
                    st1.setString(2, hnay + "");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString("CaChieu").equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                lbltrangthai004.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                lbltrangthai004.setText("Đang Chiếu");
                            }
                        }

                        if (kq1.getString("CaChieu").equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                lbltrangthai004.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                lbltrangthai004.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                lbltrangthai004.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                lbltrangthai004.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                lbltrangthai004.setText("Đang Chiếu");
                            }
                        }
                    }
                }

                if (kq.getString("PhongID").equals("P005")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select CaChieu from SuatChieu where PhongID = ? and NgayTaoXuat = ?");
                    st1.setString(1, "P005");
                    st1.setString(2, hnay + "");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString("CaChieu").equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                lbltrangthai005.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                lbltrangthai005.setText("Đang Chiếu");
                            }
                        }

                        if (kq1.getString("CaChieu").equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                lbltrangthai005.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                lbltrangthai005.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                lbltrangthai005.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                lbltrangthai005.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                lbltrangthai005.setText("Đang Chiếu");
                            }
                        }
                    }
                }

                if (kq.getString("PhongID").equals("P006")) {
                    PreparedStatement st1 = JDBCHelper.prepareStatement("select CaChieu from SuatChieu where PhongID = ? and NgayTaoXuat = ?");
                    st1.setString(1, "P006");
                    st1.setString(2, hnay + "");
                    ResultSet kq1 = st1.executeQuery();
                    while (kq1.next()) {
                        if (kq1.getString("CaChieu").equals("Suất 1 (7h-9h)")) {
                            if (checktrangthai1(7, 9)) {
                                lbltrangthai006.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 2 (9h-11h)")) {
                            if (checktrangthai1(9, 11)) {
                                lbltrangthai006.setText("Đang Chiếu");
                            }
                        }

                        if (kq1.getString("CaChieu").equals("Suất 3 (13h-15h)")) {
                            if (checktrangthai1(13, 15)) {
                                lbltrangthai006.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 4 (15h-17h)")) {
                            if (checktrangthai1(15, 17)) {
                                lbltrangthai006.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 5 (17h-19h)")) {
                            if (checktrangthai1(17, 19)) {
                                lbltrangthai006.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 6 (19h-21h)")) {
                            if (checktrangthai1(19, 21)) {
                                lbltrangthai006.setText("Đang Chiếu");
                            }
                        }
                        if (kq1.getString("CaChieu").equals("Suất 7 (21h-23h)")) {
                            if (checktrangthai1(21, 23)) {
                                lbltrangthai006.setText("Đang Chiếu");
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel2 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTrangChu = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblghetrong001 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbltrangthai001 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblghetrong004 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lbltrangthai004 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblghetrong002 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbltrangthai002 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblghetrong005 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbltrangthai005 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblghetrong003 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbltrangthai003 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblghetrong006 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbltrangthai006 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 51, 51));

        lblTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setFont(new java.awt.Font("Wide Latin", 1, 36)); // NOI18N
        lblTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setText("CINEMAX");
        lblTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseClicked(evt);
            }
        });

        lblDongHo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(255, 255, 255));
        lblDongHo.setText("00:00:00 AM");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Thông Tin Phòng Chiếu:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDongHo)
                .addGap(14, 14, 14))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(459, 459, 459)
                .addComponent(lblTrangChu)
                .addContainerGap(490, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(lblTrangChu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDongHo)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("PC001");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Số lượng ghế còn trống: ");

        lblghetrong001.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblghetrong001.setForeground(new java.awt.Color(0, 255, 0));
        lblghetrong001.setText("56");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Trạng thái:");

        lbltrangthai001.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbltrangthai001.setForeground(new java.awt.Color(255, 0, 0));
        lbltrangthai001.setText("Đang chiếu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltrangthai001))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblghetrong001))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblghetrong001))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbltrangthai001))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel27.setText("PC004");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel28.setText("Số lượng ghế còn trống: ");

        lblghetrong004.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblghetrong004.setForeground(new java.awt.Color(0, 255, 0));
        lblghetrong004.setText("56");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel30.setText("Trạng thái:");

        lbltrangthai004.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbltrangthai004.setForeground(new java.awt.Color(255, 0, 0));
        lbltrangthai004.setText("Đang chiếu");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltrangthai004))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblghetrong004))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel27)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lblghetrong004))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(lbltrangthai004))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setText("PC002");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Số lượng ghế còn trống: ");

        lblghetrong002.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblghetrong002.setForeground(new java.awt.Color(0, 255, 0));
        lblghetrong002.setText("56");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Trạng thái:");

        lbltrangthai002.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbltrangthai002.setForeground(new java.awt.Color(255, 0, 0));
        lbltrangthai002.setText("Đang chiếu");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltrangthai002))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblghetrong002)))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(130, 130, 130))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblghetrong002))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbltrangthai002))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel22.setText("PC005");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel23.setText("Số lượng ghế còn trống: ");

        lblghetrong005.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblghetrong005.setForeground(new java.awt.Color(0, 255, 0));
        lblghetrong005.setText("56");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setText("Trạng thái:");

        lbltrangthai005.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbltrangthai005.setForeground(new java.awt.Color(255, 0, 0));
        lbltrangthai005.setText("Đang chiếu");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltrangthai005))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblghetrong005)))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(118, 118, 118))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblghetrong005))
                .addGap(38, 38, 38)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(lbltrangthai005))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel12.setText("PC003");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("Số lượng ghế còn trống: ");

        lblghetrong003.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblghetrong003.setForeground(new java.awt.Color(0, 255, 0));
        lblghetrong003.setText("00");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setText("Trạng thái:");

        lbltrangthai003.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbltrangthai003.setForeground(new java.awt.Color(255, 0, 0));
        lbltrangthai003.setText("Đang chiếu");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltrangthai003))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblghetrong003)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(118, 118, 118))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblghetrong003))
                .addGap(38, 38, 38)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbltrangthai003))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel17.setText("PC006");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setText("Số lượng ghế còn trống: ");

        lblghetrong006.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblghetrong006.setForeground(new java.awt.Color(0, 255, 0));
        lblghetrong006.setText("56");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setText("Trạng thái:");

        lbltrangthai006.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbltrangthai006.setForeground(new java.awt.Color(255, 0, 0));
        lbltrangthai006.setText("Đang chiếu");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltrangthai006))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblghetrong006)))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(118, 118, 118))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblghetrong006))
                .addGap(38, 38, 38)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lbltrangthai006))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(70, 70, 70)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        MainJFrame.showForm(new TrangChuJPanel());
    }//GEN-LAST:event_lblTrangChuMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel lblghetrong001;
    private javax.swing.JLabel lblghetrong002;
    private javax.swing.JLabel lblghetrong003;
    private javax.swing.JLabel lblghetrong004;
    private javax.swing.JLabel lblghetrong005;
    private javax.swing.JLabel lblghetrong006;
    private javax.swing.JLabel lbltrangthai001;
    private javax.swing.JLabel lbltrangthai002;
    private javax.swing.JLabel lbltrangthai003;
    private javax.swing.JLabel lbltrangthai004;
    private javax.swing.JLabel lbltrangthai005;
    private javax.swing.JLabel lbltrangthai006;
    private javax.swing.JPanel panel2;
    // End of variables declaration//GEN-END:variables
}
