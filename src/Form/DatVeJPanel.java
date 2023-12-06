/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form;

import DAO.VeDAO;
import Helper.JDBCHelper;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.Timer;
import model.Ve;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import raven.toast.Notifications;
import util.BCryptPasswordHashing;
import static util.BCryptPasswordHashing.logger;

/**
 *
 * @author 123tu
 */
public class DatVeJPanel extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form DatVeJPanel
     */
    VeDAO daoVE = new VeDAO();
    VeJPanel veJPanel;
    String thoiGianChieu;
    String tenPhong;
    String tenPhim;
    static String maSuatChieu;
    Color mauMacDinh = new Color(89, 86, 86);
    Color mauDangChon = new Color(255, 255, 0);
    Color mauDaChon = new Color(255, 51, 51);
    Color mauChuMacDinh = new Color(187, 187, 187);
    Color mauChuDangChon = new Color(0, 0, 0);
    String LoaiVe;
    String ngayMua;
    int VeCount = 0;
    int VeThuongCount = 0;
    int VeVipCount = 0;
    int VeCoupleCount = 0;
    int GiaVeThuong = 0;
    int GiaVeVIP = 0;
    int GiaVeCouple = 0;
    int GheThuongCount = 24;
    int GheVipCount = 24;
    int GheCoupleCount = 8;
    int GiaVeMacDinh = 50000;
    int GiaVe = 0;
    private Set<String> uniqueSeatTypes = new HashSet<>();
    HashSet<String> ArrayGhe = new HashSet<>();
    public static final Logger logger = Logger.getLogger(DatVeJPanel.class);

    public DatVeJPanel(VeJPanel veJPanel) {
        initComponents();
        PropertyConfigurator.configure("src\\Log\\log4j.properties");
        logger.info("Người dùng đã mởi form đặt vé");
        this.veJPanel = veJPanel;

        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                String text = sdf.format(now);
                SimpleDateFormat NgayMuaFormat = new SimpleDateFormat("YYYY-MM-dd");
                ngayMua = NgayMuaFormat.format(now);
                lblDongHo.setText(text);
                txtNgayMua.setText(ngayMua);
            }
        }).start();
        this.veJPanel = veJPanel;
        tenPhim = veJPanel.getTenPhim();
        tenPhong = veJPanel.getTenPhong();
        thoiGianChieu = veJPanel.getThoiGianChieu();
        maSuatChieu = veJPanel.getMaSuatChieu();
        lblVeDaChon.setText("");
        fillData();
        setGheDaChon();
        addActionListener();
    }

    void fillData() {
        txtMaVe.setText(String.valueOf(Integer.valueOf(daoVE.findVeID()) + 1));
        txtTenPhim.setText(tenPhim);
        txtTenPhong.setText(tenPhong);
        txtThoiGianChieu.setText(thoiGianChieu);
    }

    void doiMauGhe(JButton button) {
        if (button.getBackground().equals(mauDangChon)) {
            button.setBackground(mauMacDinh);
            button.setForeground(mauChuMacDinh);
            VeCount--;
            lblVeDaChon.setText("Đã chọn:" + VeCount + " ghế");
            return;
        }
        if (button.getBackground().equals(mauMacDinh)) {
            button.setBackground(mauDangChon);
            button.setForeground(mauChuDangChon);
            VeCount++;
            lblVeDaChon.setText("Đã chọn:" + VeCount + " ghế");
            return;
        }
    }

    void addActionListener() {
        String[] buttonNames = {
            "GheA01", "GheA02", "GheA03", "GheA04", "GheA05", "GheA06", "GheA07", "GheA08",
            "GheB01", "GheB02", "GheB03", "GheB04", "GheB05", "GheB06", "GheB07", "GheB08",
            "GheC01", "GheC02", "GheC03", "GheC04", "GheC05", "GheC06", "GheC07", "GheC08",
            "GheD01", "GheD02", "GheD03", "GheD04", "GheD05", "GheD06", "GheD07", "GheD08",
            "GheE01", "GheE02", "GheE03", "GheE04", "GheE05", "GheE06", "GheE07", "GheE08",
            "GheF01", "GheF02", "GheF03", "GheF04", "GheF05", "GheF06", "GheF07", "GheF08",
            "GheH01", "GheH02", "GheH03", "GheH04", "GheH05", "GheH06", "GheH07", "GheH08",};

        for (String buttonName : buttonNames) {
            JButton button = getButtonByName(buttonName);
            if (button != null) {
                button.addActionListener(this);
                button.setText(buttonName.substring(3));
            }
        }
    }

    private JButton getButtonByName(String buttonName) {
        try {
            return (JButton) getClass().getDeclaredField("btn" + buttonName).get(this);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private JButton getButtonByName2(String buttonName) {
        try {
            return (JButton) getClass().getDeclaredField("btnGhe" + buttonName).get(this);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    void setVeCouple() {
        int i = 0;
        VeCoupleCount = 0;
        String[] buttonNames = {"GheH01", "GheH02", "GheH03", "GheH04", "GheH05", "GheH06", "GheH07", "GheH08"};
        for (String buttonName : buttonNames) {
            JButton button = getButtonByName(buttonName);
            if (button != null && button.getBackground().equals(mauDangChon)) {
                uniqueSeatTypes.add("Couple");
                VeCoupleCount++;
                ArrayGhe.add(buttonName.substring(3));
            } else if (button.getBackground().equals(mauMacDinh)) {
                i++;
                ArrayGhe.remove(buttonName.substring(3));
                if (i == GheCoupleCount) {
                    uniqueSeatTypes.remove("Couple");
                }
            }
        }
        GiaVeCouple = 0;
        GiaVeCouple += VeCoupleCount * GiaVeMacDinh * 2;
        updateTxtLoaiVe();
    }

    void setVeVIP() {
        int i = 0;
        VeVipCount = 0;
        String[] buttonNames = {
            "GheD01", "GheD02", "GheD03", "GheD04", "GheD05", "GheD06", "GheD07", "GheD08",
            "GheE01", "GheE02", "GheE03", "GheE04", "GheE05", "GheE06", "GheE07", "GheE08",
            "GheF01", "GheF02", "GheF03", "GheF04", "GheF05", "GheF06", "GheF07", "GheF08",};

        for (String buttonName : buttonNames) {
            JButton button = getButtonByName(buttonName);
            if (button != null && button.getBackground().equals(mauDangChon)) {
                uniqueSeatTypes.add("VIP");
                VeVipCount++;
                ArrayGhe.add(buttonName.substring(3));
            } else if (button.getBackground().equals(mauMacDinh)) {
                i++;
                ArrayGhe.remove(buttonName.substring(3));
                if (i == GheVipCount) {
                    uniqueSeatTypes.remove("VIP");
                }
            }
        }
        GiaVeVIP = 0;
        GiaVeVIP += VeVipCount * GiaVeMacDinh * 2;
        updateTxtLoaiVe();
    }

    void setVeThuong() {
        int i = 0;
        VeThuongCount = 0;
        String[] buttonNames = {
            "GheA01", "GheA02", "GheA03", "GheA04", "GheA05", "GheA06", "GheA07", "GheA08",
            "GheB01", "GheB02", "GheB03", "GheB04", "GheB05", "GheB06", "GheB07", "GheB08",
            "GheC01", "GheC02", "GheC03", "GheC04", "GheC05", "GheC06", "GheC07", "GheC08",};

        for (String buttonName : buttonNames) {
            JButton button = getButtonByName(buttonName);
            if (button != null && button.getBackground().equals(mauDangChon)) {
                uniqueSeatTypes.add("Thường");
                VeThuongCount++;
                ArrayGhe.add(buttonName.substring(3));
            } else if (button.getBackground().equals(mauMacDinh)) {
                i++;
                ArrayGhe.remove(buttonName.substring(3));
                if (i == GheThuongCount) {
                    uniqueSeatTypes.remove("Thường");
                }
            }
            GiaVeThuong = 0;
            GiaVeThuong += VeThuongCount * GiaVeMacDinh;
            updateTxtLoaiVe();
        }
    }

    void updateTxtLoaiVe() {
        GiaVe = GiaVeThuong + GiaVeVIP + GiaVeCouple;
        txtGiaVe1.setText(GiaVe + "");
        LoaiVe = String.join(", ", uniqueSeatTypes);
        txtLoaiVe.setText(LoaiVe);
    }

    void setGheDaChon() {
        String gheDaChon = null;
        String[] GheThuong = {
            "A01", "A02", "A03", "A04", "A05", "A06", "A07", "A08",
            "B01", "B02", "B03", "B04", "B05", "B06", "B07", "B08",
            "C01", "C02", "C03", "C04", "C05", "C06", "C07", "C08",};

        String[] GheVIP = {
            "D01", "D02", "D03", "D04", "D05", "D06", "D07", "D08",
            "E01", "E02", "E03", "E04", "E05", "E06", "E07", "E08",
            "F01", "F02", "F03", "F04", "F05", "F06", "F07", "F08",};

        String[] GheCouple = {
            "H01", "H02", "H03", "H04", "H05", "H06", "H07", "H08",};
        try {
            List<String> list = daoVE.timGhe(maSuatChieu);
            StringBuilder danhSachGheDaChon = new StringBuilder();

            for (String ve : list) {
                gheDaChon = ve.toString();
                String OutString = gheDaChon.replace("[", "").replace("]", "");
                String[] arr = OutString.split(",");
                for (String string : arr) {
                    string = string.trim();
                    doiMauGheDaChon(getButtonByName2(string));
                    danhSachGheDaChon.append(string).append(", ");

                    for (String gheThuong : GheThuong) {
                        if (string.equals(gheThuong)) {
                            GheThuongCount--;
                        }
                    }

                    for (String gheVIP : GheVIP) {
                        if (string.equals(gheVIP)) {
                            GheVipCount--;
                        }
                    }

                    for (String gheCouple : GheCouple) {
                        if (string.equals(gheCouple)) {
                            GheCoupleCount--;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            JDBCHelper.closeConnection();
        }
    }

    void DatVe() {
        Ve entity = new Ve();
        entity.setMaSC(maSuatChieu);
        entity.setGhe(ArrayGhe + "");
        entity.setGiaVe(GiaVe);
        entity.setNgayMua(ngayMua);
        entity.setLoaiVe(txtLoaiVe.getText());
        try {
            daoVE.insert(entity);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Đặt vé thành công!");
            fillForm();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            JDBCHelper.closeConnection();
        }
    }

    void fillForm() {
        fillData();
        txtLoaiVe.setText("");
        txtGiaVe1.setText("0");
        lblVeDaChon.setText("");
        setGheDaChon();
    }

    void doiMauGheDaChon(JButton button) {
        button.setBackground(mauDaChon);
        button.addActionListener(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "A01":
                doiMauGhe(btnGheA01);
                setVeThuong();
                break;
            case "A02":
                doiMauGhe(btnGheA02);
                setVeThuong();
                break;
            case "A03":
                doiMauGhe(btnGheA03);
                setVeThuong();
                break;
            case "A04":
                doiMauGhe(btnGheA04);
                setVeThuong();
                break;
            case "A05":
                doiMauGhe(btnGheA05);
                setVeThuong();
                break;
            case "A06":
                doiMauGhe(btnGheA06);
                setVeThuong();
                break;
            case "A07":
                doiMauGhe(btnGheA07);
                setVeThuong();
                break;
            case "A08":
                doiMauGhe(btnGheA08);
                setVeThuong();
                break;
            case "B01":
                doiMauGhe(btnGheB01);
                setVeThuong();
                break;
            case "B02":
                doiMauGhe(btnGheB02);
                setVeThuong();
                break;
            case "B03":
                doiMauGhe(btnGheB03);
                setVeThuong();
                break;
            case "B04":
                doiMauGhe(btnGheB04);
                setVeThuong();
                break;
            case "B05":
                doiMauGhe(btnGheB05);
                setVeThuong();
                break;
            case "B06":
                doiMauGhe(btnGheB06);
                setVeThuong();
                break;
            case "B07":
                doiMauGhe(btnGheB07);
                setVeThuong();
                break;
            case "B08":
                doiMauGhe(btnGheB08);
                setVeThuong();
                break;
            case "C01":
                doiMauGhe(btnGheC01);
                setVeThuong();
                break;
            case "C02":
                doiMauGhe(btnGheC02);
                setVeThuong();
                break;
            case "C03":
                doiMauGhe(btnGheC03);
                setVeThuong();
                break;
            case "C04":
                doiMauGhe(btnGheC04);
                setVeThuong();
                break;
            case "C05":
                doiMauGhe(btnGheC05);
                setVeThuong();
                break;
            case "C06":
                doiMauGhe(btnGheC06);
                setVeThuong();
                break;
            case "C07":
                doiMauGhe(btnGheC07);
                setVeThuong();
                break;
            case "C08":
                doiMauGhe(btnGheC08);
                setVeThuong();
                break;
            case "D01":
                doiMauGhe(btnGheD01);
                setVeVIP();
                break;
            case "D02":
                doiMauGhe(btnGheD02);
                setVeVIP();
                break;
            case "D03":
                doiMauGhe(btnGheD03);
                setVeVIP();
                break;
            case "D04":
                doiMauGhe(btnGheD04);
                setVeVIP();
                break;
            case "D05":
                doiMauGhe(btnGheD05);
                setVeVIP();
                break;
            case "D06":
                doiMauGhe(btnGheD06);
                setVeVIP();
                break;
            case "D07":
                doiMauGhe(btnGheD07);
                setVeVIP();
                break;
            case "D08":
                doiMauGhe(btnGheD08);
                setVeVIP();
                break;
            case "E01":
                doiMauGhe(btnGheE01);
                setVeVIP();
                break;
            case "E02":
                doiMauGhe(btnGheE02);
                setVeVIP();
                break;
            case "E03":
                doiMauGhe(btnGheE03);
                setVeVIP();
                break;
            case "E04":
                doiMauGhe(btnGheE04);
                setVeVIP();
                break;
            case "E05":
                doiMauGhe(btnGheE05);
                setVeVIP();
                break;
            case "E06":
                doiMauGhe(btnGheE06);
                setVeVIP();
                break;
            case "E07":
                doiMauGhe(btnGheE07);
                setVeVIP();
                break;
            case "E08":
                doiMauGhe(btnGheE08);
                setVeVIP();
                break;
            case "F01":
                doiMauGhe(btnGheF01);
                setVeVIP();
                break;
            case "F02":
                doiMauGhe(btnGheF02);
                setVeVIP();
                break;
            case "F03":
                doiMauGhe(btnGheF03);
                setVeVIP();
                break;
            case "F04":
                doiMauGhe(btnGheF04);
                setVeVIP();
                break;
            case "F05":
                doiMauGhe(btnGheF05);
                setVeVIP();
                break;
            case "F06":
                doiMauGhe(btnGheF06);
                setVeVIP();
                break;
            case "F07":
                doiMauGhe(btnGheF07);
                setVeVIP();
                break;
            case "F08":
                doiMauGhe(btnGheF08);
                setVeVIP();
                break;
            case "H01":
                doiMauGhe(btnGheH01);
                doiMauGhe(btnGheH02);
                setVeCouple();
                break;
            case "H02":
                doiMauGhe(btnGheH02);
                doiMauGhe(btnGheH01);
                setVeCouple();
                break;
            case "H03":
                doiMauGhe(btnGheH03);
                doiMauGhe(btnGheH04);
                setVeCouple();
                break;
            case "H04":
                doiMauGhe(btnGheH04);
                doiMauGhe(btnGheH03);
                setVeCouple();
                break;
            case "H05":
                doiMauGhe(btnGheH05);
                doiMauGhe(btnGheH06);
                setVeCouple();
                break;
            case "H06":
                doiMauGhe(btnGheH06);
                doiMauGhe(btnGheH05);
                setVeCouple();
                break;
            case "H07":
                doiMauGhe(btnGheH07);
                doiMauGhe(btnGheH08);
                setVeCouple();
                break;
            case "H08":
                doiMauGhe(btnGheH08);
                doiMauGhe(btnGheH07);
                setVeCouple();
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTrangChu = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtLoaiVe = new javax.swing.JTextField();
        txtNgayMua = new javax.swing.JTextField();
        txtTenPhong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTenPhim = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtThoiGianChieu = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnGheA01 = new javax.swing.JButton();
        btnGheA02 = new javax.swing.JButton();
        btnGheA03 = new javax.swing.JButton();
        btnGheA04 = new javax.swing.JButton();
        btnGheA05 = new javax.swing.JButton();
        btnGheA06 = new javax.swing.JButton();
        btnGheB01 = new javax.swing.JButton();
        btnGheC01 = new javax.swing.JButton();
        btnGheD01 = new javax.swing.JButton();
        btnGheE01 = new javax.swing.JButton();
        btnGheF01 = new javax.swing.JButton();
        btnGheH01 = new javax.swing.JButton();
        btnGheA07 = new javax.swing.JButton();
        btnGheA08 = new javax.swing.JButton();
        btnGheB02 = new javax.swing.JButton();
        btnGheB03 = new javax.swing.JButton();
        btnGheB04 = new javax.swing.JButton();
        btnGheB05 = new javax.swing.JButton();
        btnGheB06 = new javax.swing.JButton();
        btnGheB07 = new javax.swing.JButton();
        btnGheB08 = new javax.swing.JButton();
        btnGheC02 = new javax.swing.JButton();
        btnGheC03 = new javax.swing.JButton();
        btnGheC04 = new javax.swing.JButton();
        btnGheC05 = new javax.swing.JButton();
        btnGheC06 = new javax.swing.JButton();
        btnGheC07 = new javax.swing.JButton();
        btnGheC08 = new javax.swing.JButton();
        btnGheD04 = new javax.swing.JButton();
        btnGheD05 = new javax.swing.JButton();
        btnGheD06 = new javax.swing.JButton();
        btnGheD07 = new javax.swing.JButton();
        btnGheD08 = new javax.swing.JButton();
        btnGheD02 = new javax.swing.JButton();
        btnGheD03 = new javax.swing.JButton();
        btnGheE04 = new javax.swing.JButton();
        btnGheE05 = new javax.swing.JButton();
        btnGheE06 = new javax.swing.JButton();
        btnGheE07 = new javax.swing.JButton();
        btnGheE08 = new javax.swing.JButton();
        btnGheE02 = new javax.swing.JButton();
        btnGheE03 = new javax.swing.JButton();
        btnGheF04 = new javax.swing.JButton();
        btnGheF05 = new javax.swing.JButton();
        btnGheF06 = new javax.swing.JButton();
        btnGheF07 = new javax.swing.JButton();
        btnGheF08 = new javax.swing.JButton();
        btnGheF02 = new javax.swing.JButton();
        btnGheF03 = new javax.swing.JButton();
        btnGheH04 = new javax.swing.JButton();
        btnGheH05 = new javax.swing.JButton();
        btnGheH06 = new javax.swing.JButton();
        btnGheH07 = new javax.swing.JButton();
        btnGheH08 = new javax.swing.JButton();
        btnGheH02 = new javax.swing.JButton();
        btnGheH03 = new javax.swing.JButton();
        lblVeDaChon = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSuatChieu = new javax.swing.JButton();
        btnDanhSach = new javax.swing.JButton();
        txtMaVe = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtGiaVe1 = new javax.swing.JTextField();

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
        jLabel10.setText("Đặt Vé:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDongHo)
                .addGap(20, 20, 20))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(459, 459, 459)
                .addComponent(lblTrangChu)
                .addContainerGap(496, Short.MAX_VALUE))
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Giá Vé:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel11.setText("Ngày (Mua/Đặt):");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Chọn Loại Vé:");

        txtLoaiVe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtLoaiVe.setEnabled(false);

        txtNgayMua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNgayMua.setEnabled(false);

        txtTenPhong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenPhong.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel13.setText("Tên Phim:");

        txtTenPhim.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenPhim.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel14.setText("Tên Phòng:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel15.setText("Thời Gian Chiếu:");

        txtThoiGianChieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtThoiGianChieu.setEnabled(false);

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel13.setBackground(new java.awt.Color(51, 153, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Màn Chiếu");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(347, 347, 347))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("Lối Vào =>");

        jPanel5.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Đang Chọn");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Đã Chọn");

        jPanel6.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        btnGheA01.setText("A-0");
        btnGheA01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGheA01MouseClicked(evt);
            }
        });

        btnGheA02.setText("A-0");

        btnGheA03.setText("A-0");

        btnGheA04.setText("A-0");

        btnGheA05.setText("A-0");

        btnGheA06.setText("A-0");

        btnGheB01.setText("A-0");

        btnGheC01.setText("A-0");

        btnGheD01.setText("A-0");

        btnGheE01.setText("A-0");

        btnGheF01.setText("A-0");

        btnGheH01.setText("A-0");

        btnGheA07.setText("A-0");

        btnGheA08.setText("A-0");

        btnGheB02.setText("A-0");

        btnGheB03.setText("A-0");

        btnGheB04.setText("A-0");

        btnGheB05.setText("A-0");

        btnGheB06.setText("A-0");

        btnGheB07.setText("A-0");

        btnGheB08.setText("A-0");

        btnGheC02.setText("A-0");

        btnGheC03.setText("A-0");

        btnGheC04.setText("A-0");

        btnGheC05.setText("A-0");

        btnGheC06.setText("A-0");

        btnGheC07.setText("A-0");

        btnGheC08.setText("A-0");

        btnGheD04.setText("A-0");

        btnGheD05.setText("A-0");

        btnGheD06.setText("A-0");

        btnGheD07.setText("A-0");

        btnGheD08.setText("A-0");

        btnGheD02.setText("A-0");

        btnGheD03.setText("A-0");

        btnGheE04.setText("A-0");

        btnGheE05.setText("A-0");

        btnGheE06.setText("A-0");

        btnGheE07.setText("A-0");

        btnGheE08.setText("A-0");

        btnGheE02.setText("A-0");

        btnGheE03.setText("A-0");

        btnGheF04.setText("A-0");

        btnGheF05.setText("A-0");

        btnGheF06.setText("A-0");

        btnGheF07.setText("A-0");

        btnGheF08.setText("A-0");

        btnGheF02.setText("A-0");

        btnGheF03.setText("A-0");

        btnGheH04.setText("A-0");

        btnGheH05.setText("A-0");

        btnGheH06.setText("A-0");

        btnGheH07.setText("A-0");

        btnGheH08.setText("A-0");

        btnGheH02.setText("A-0");

        btnGheH03.setText("A-0");

        lblVeDaChon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblVeDaChon.setText("jLabel1");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)))
                            .addComponent(jLabel5)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblVeDaChon, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(btnGheH01, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheH02, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheH03, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheH04, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheH05, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheH06, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheH07, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheH08, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(btnGheE01, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheE02, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheE03, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheE04, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheE05, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheE06, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheE07, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheE08, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(btnGheD01, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheD02, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheD03, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheD04, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheD05, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheD06, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheD07, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheD08, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(btnGheC01, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheC02, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheC03, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheC04, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheC05, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheC06, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheC07, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheC08, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(btnGheF01, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheF02, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheF03, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheF04, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheF05, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheF06, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheF07, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGheF08, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addComponent(btnGheA01, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheA02, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheA03, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheA04, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheA05, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheA06, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheA07, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheA08, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addComponent(btnGheB01, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheB02, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheB03, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheB04, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheB05, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheB06, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheB07, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnGheB08, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel5)))
                .addGap(4, 4, 4)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGheA01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheA02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheA03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheA04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheA05, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheA06, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheA07, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheA08, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGheB01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheB02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheB03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheB04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheB05, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheB06, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheB07, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGheB08, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGheC01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheC02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheC03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheC04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheC05, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheC06, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheC07, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheC08, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGheD01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheD02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheD03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheD04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheD05, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheD06, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheD07, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheD08, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGheE01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheE02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheE03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheE04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheE05, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheE06, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheE07, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheE08, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGheF01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheF02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheF03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheF04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheF05, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheF06, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheF07, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheF08, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGheH01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheH02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheH03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheH04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheH05, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheH06, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheH07, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGheH08, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel7)
                                .addGap(37, 37, 37)
                                .addComponent(lblVeDaChon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(342, 342, 342))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(304, 304, 304))))))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel6.setText("Chọn Ghế:");

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Đặt Vé");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSuatChieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSuatChieu.setText("Trở Lại");
        btnSuatChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuatChieuActionPerformed(evt);
            }
        });

        btnDanhSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDanhSach.setText("Xem Danh Sách Vé");
        btnDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachActionPerformed(evt);
            }
        });

        txtMaVe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMaVe.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel18.setText("Mã Vé:");

        txtGiaVe1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtGiaVe1.setText("0");
        txtGiaVe1.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11)
                    .addComponent(jLabel18)
                    .addComponent(txtThoiGianChieu, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(txtTenPhong)
                    .addComponent(txtTenPhim)
                    .addComponent(txtLoaiVe)
                    .addComponent(txtMaVe)
                    .addComponent(txtNgayMua)
                    .addComponent(btnSuatChieu)
                    .addComponent(txtGiaVe1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(711, 711, 711)
                        .addComponent(btnDanhSach))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtGiaVe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoiGianChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1305, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        MainJFrame.showForm(new TrangChuJPanel());
    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void btnSuatChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuatChieuActionPerformed
        MainJFrame.showForm(new VeJPanel());
    }//GEN-LAST:event_btnSuatChieuActionPerformed

    private void btnDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachActionPerformed
        MainJFrame.showForm(new DanhSachVeJPanel());
    }//GEN-LAST:event_btnDanhSachActionPerformed

    private void btnGheA01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGheA01MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGheA01MouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        DatVe();
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDanhSach;
    private javax.swing.JButton btnGheA01;
    private javax.swing.JButton btnGheA02;
    private javax.swing.JButton btnGheA03;
    private javax.swing.JButton btnGheA04;
    private javax.swing.JButton btnGheA05;
    private javax.swing.JButton btnGheA06;
    private javax.swing.JButton btnGheA07;
    private javax.swing.JButton btnGheA08;
    private javax.swing.JButton btnGheB01;
    private javax.swing.JButton btnGheB02;
    private javax.swing.JButton btnGheB03;
    private javax.swing.JButton btnGheB04;
    private javax.swing.JButton btnGheB05;
    private javax.swing.JButton btnGheB06;
    private javax.swing.JButton btnGheB07;
    private javax.swing.JButton btnGheB08;
    private javax.swing.JButton btnGheC01;
    private javax.swing.JButton btnGheC02;
    private javax.swing.JButton btnGheC03;
    private javax.swing.JButton btnGheC04;
    private javax.swing.JButton btnGheC05;
    private javax.swing.JButton btnGheC06;
    private javax.swing.JButton btnGheC07;
    private javax.swing.JButton btnGheC08;
    private javax.swing.JButton btnGheD01;
    private javax.swing.JButton btnGheD02;
    private javax.swing.JButton btnGheD03;
    private javax.swing.JButton btnGheD04;
    private javax.swing.JButton btnGheD05;
    private javax.swing.JButton btnGheD06;
    private javax.swing.JButton btnGheD07;
    private javax.swing.JButton btnGheD08;
    private javax.swing.JButton btnGheE01;
    private javax.swing.JButton btnGheE02;
    private javax.swing.JButton btnGheE03;
    private javax.swing.JButton btnGheE04;
    private javax.swing.JButton btnGheE05;
    private javax.swing.JButton btnGheE06;
    private javax.swing.JButton btnGheE07;
    private javax.swing.JButton btnGheE08;
    private javax.swing.JButton btnGheF01;
    private javax.swing.JButton btnGheF02;
    private javax.swing.JButton btnGheF03;
    private javax.swing.JButton btnGheF04;
    private javax.swing.JButton btnGheF05;
    private javax.swing.JButton btnGheF06;
    private javax.swing.JButton btnGheF07;
    private javax.swing.JButton btnGheF08;
    private javax.swing.JButton btnGheH01;
    private javax.swing.JButton btnGheH02;
    private javax.swing.JButton btnGheH03;
    private javax.swing.JButton btnGheH04;
    private javax.swing.JButton btnGheH05;
    private javax.swing.JButton btnGheH06;
    private javax.swing.JButton btnGheH07;
    private javax.swing.JButton btnGheH08;
    private javax.swing.JButton btnSuatChieu;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel lblVeDaChon;
    private javax.swing.JTextField txtGiaVe1;
    private javax.swing.JTextField txtLoaiVe;
    private javax.swing.JTextField txtMaVe;
    private javax.swing.JTextField txtNgayMua;
    private javax.swing.JTextField txtTenPhim;
    private javax.swing.JTextField txtTenPhong;
    private javax.swing.JTextField txtThoiGianChieu;
    // End of variables declaration//GEN-END:variables

}
