package Form;

import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.CompressionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VeXemPhim {

    public void inVeXemPhimPDF(String tenTep, String maQRCode, String nhanVien, String tenPhim, String suatChieu, String phongChieu, String viTriGhe, double tongTien) {
        try {
            WriterProperties writerProperties = new WriterProperties();
            writerProperties.setCompressionLevel(CompressionConstants.BEST_COMPRESSION);

            PdfWriter pdfWriter = new PdfWriter(tenTep);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A6);
            Document document = new Document(pdfDocument);
            document.setMargins(0, 0, 0, 0);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            document.add(new Paragraph("CINEMAX QUAN 12").setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("VE XEM PHIM").setFontSize(14).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("--------------------------------------------------------"));
            document.add(new Paragraph("Thoi gian tao ve: " + dateFormat.format(new Date())));
            document.add(new Paragraph("Nhan Vien: " + nhanVien));
            document.add(new Paragraph("--------------------------------------------------------"));
            document.add(new Paragraph("Phim: " + tenPhim));
            document.add(new Paragraph("Suat: " + suatChieu));
            document.add(new Paragraph("Phong: " + phongChieu + "    " + "Ghe: " + viTriGhe));
            document.add(new Paragraph("==============================="));
            document.add(new Paragraph("Tong tien: " + tongTien + " VND").setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("--------------------------------------------------------"));
            BarcodeQRCode qrCode = new BarcodeQRCode(maQRCode);
            PdfFormXObject qrCodeObject = qrCode.createFormXObject(pdfDocument);
            Image image = new Image(qrCodeObject).setWidth(80).setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(image);

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VeXemPhim veXemPhim = new VeXemPhim();
        veXemPhim.inVeXemPhimPDF("ve_xem_phim.pdf", "VE001", "nv001", "sex  gay", "10am - 11am", "603", "f1", 10000);
    }
}
