package Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ReportUtil implements ITestListener {

    String filePath = "Report.pdf";
    static Document doc;
    Font para_font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
    Font error_font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.RED);
    Font skip_font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.YELLOW);
    Font pass_font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.GREEN);

    @Override
    public void onStart(ITestContext arg0) {
        doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(filePath));
            doc.open();
            doc.addCreationDate();
            doc.addHeader("page header", "Test Report");
            doc.add(new Chunk("Test Run Starting : " + arg0.getName()));
            doc.add(new Paragraph("Start Time:" + arg0.getStartDate(),para_font));
        } catch (FileNotFoundException | DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult arg0) {
        try {
            doc.add(new Paragraph(new Chunk("Test Case : " + arg0.getName(), para_font)));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        System.out.println("Test Pass : " + arg0.getName());
        try {
            doc.add(new Paragraph(new Chunk("Test Passed", pass_font)));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        System.out.println("Test Failed : " + arg0.getName());
        try {
            doc.add(new Paragraph(new Chunk("Test Failed", error_font)));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        System.out.println("Test Skipped : " + arg0.getName());
        try {
            doc.add(new Paragraph(new Chunk("Test Skipped", skip_font)));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext arg0) {
        try {
            doc.add(new Chunk("Test Run completed : " + arg0.getName()));
            doc.add(new Paragraph("Test End Time :" + arg0.getEndDate(),para_font));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        doc.close();
    }
}