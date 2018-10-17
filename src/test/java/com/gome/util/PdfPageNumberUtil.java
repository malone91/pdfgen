package com.gome.util;

import com.itextpdf.html2pdf.attach.impl.layout.PageCountType;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.lowagie.text.Element;

import java.io.FileOutputStream;

/**
 * Created by malong-ds on 2018/10/17.
 */
public class PdfPageNumberUtil {

    public static void setPageHeaderAndFooter(String source, String target) throws Exception {
        //读取文件
        PdfReader pdfReader = new PdfReader(source);
        //生成文件
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(target));
        int totalPage = pdfReader.getNumberOfPages();
        for (int i=1; i<= totalPage; i++) {
            PdfContentByte content = pdfStamper.getUnderContent(i);
            //添加文字
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            content.beginText();
            content.setFontAndSize(base, 12);
            content.setTextMatrix(200, 200);
            content.showTextAligned(Element.ALIGN_TOP,"左页眉",25,770,0);//左边距、下边距
            content.showTextAligned(Element.ALIGN_TOP,"右页眉",425,770,0);//左边距、下边距
            content.showTextAligned(Element.CCITT_ENDOFBLOCK,"第   " + i + "  页\t共   " + totalPage + "页",420,20,0);//左边距、下边距
            content.endText();
        }
        pdfStamper.close();
    }
}
