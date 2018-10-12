package com.gome.pdfgen;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by malong-ds on 2018/10/11.
 */
public class PdfGenerationTest {

    private static final String DESTINATION = "e:/a.pdf";

    /**
     * 生成PDF文件
     */
    @Test
    public void pdfGenerationTest() {
        Document document = null;
        try {
            PdfWriter writer = new PdfWriter(DESTINATION);
            PdfDocument pdf = new PdfDocument(writer);
            document = new Document(pdf);
            document.add(new Paragraph("gome"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    /**
     * PDF添加表格
     */
    @Test
    public void pdfTableGenerationTest() {
        Document doc = null;
        try {
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DESTINATION));
            //构建文档对象
            doc = new Document(pdfDoc);
            //中文字体
            PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
            //构建表格以100%的宽度
            Table table = new Table(new float[]{2,4,4}).setWidth(UnitValue.createPercentValue(100));
            //向表格添加内容
            Cell cell1=new Cell().add(new Paragraph("表格1")).setFont(sysFont);
            Cell cell2=new Cell().add(new Paragraph("表格2")).setFont(sysFont);
            Cell cell3=new Cell().add(new Paragraph("表格3")).setFont(sysFont);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            //将表格添加入文档并页面居中
            doc.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
    }

    /**
     * 生成复杂文字
     */
    @Test
    public void pdfComplexGenerationTest() {
        Document document = null;
        try {
            PdfWriter writer = new PdfWriter(DESTINATION);
            PdfDocument pdf = new PdfDocument(writer);
            document = new Document(pdf);

            PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            document.add(new Paragraph("The text is: ").setFont(font));
            List list = new List()
                    .setSymbolIndent(12)
                    .setListSymbol("\u2022")
                    .setFont(font);
            list.add(new ListItem("Never gonna give you up"))
                    .add(new ListItem("Never gonna let you down"))
                    .add(new ListItem("Never gonna run around and desert you"))
                    .add(new ListItem("Never gonna make you cry"))
                    .add(new ListItem("Never gonna say goodbye"))
                    .add(new ListItem("Never gonna tell a lie and hurt you"));
            document.add(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }


}
