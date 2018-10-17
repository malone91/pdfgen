package com.gome.mergepdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by malong-ds on 2018/10/17.
 */
public class PdfAddNumber extends PdfPageEventHelper {
    public PdfTemplate tpl;
    public BaseFont bf;

    public static void main(String[] args) throws IOException {

        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("e:/1.pdf"));
            writer.setPageEvent(new PdfAddNumber());

            BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            document.open();

            Paragraph title = new Paragraph("测试内容。。。。", new Font(bfChinese, 15));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
        } catch (Exception de) {
            de.printStackTrace();
        }
        document.close();
    }



    public void onOpenDocument(PdfWriter writer, Document document) {
        try {
            tpl = writer.getDirectContent().createTemplate(100, 100);
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public void onEndPage(PdfWriter writer, Document document) {
        // 在每页结束的时候把“第x页”信息写道模版指定位置
        PdfContentByte cb = writer.getDirectContent();
        cb.saveState();
        String text = "第" + writer.getPageNumber() + "页,共";
        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix(460, 786);// 定位“第x页,共” 在具体的页面调试时候需要更改这xy的坐标
        cb.showText(text);
        cb.endText();
        cb.addTemplate(tpl, 492, 786);// 定位“y页” 在具体的页面调试时候需要更改这xy的坐标

        cb.saveState();
        cb.stroke();
        cb.restoreState();
        cb.closePath();// sanityCheck();
    }

    public void onCloseDocument(PdfWriter writer, Document document) {
        // 关闭document的时候获取总页数，并把总页数按模版写道之前预留的位置
        tpl.beginText();
        tpl.setFontAndSize(bf, 8);
        tpl.showText(Integer.toString(writer.getPageNumber() - 1) + "页");
        tpl.endText();
        tpl.closePath();// sanityCheck();
    }
}
