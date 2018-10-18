package com.gome.pdfgen.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.lowagie.text.Element;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author malong-ds
 * @date 2018/10/17
 */
public class PdfHeaderFooterUtil {

    /**
     *
     * @param source
     * @param target
     * @throws Exception
     */
    public static void setPageHeaderAndFooter(String source, String target) {
        PdfStamper pdfStamper = null;
        try {
            //读取文件
            PdfReader pdfReader = new PdfReader(source);
            //生成文件
            pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(target));
            int totalPage = pdfReader.getNumberOfPages();
            for (int i=1; i<= totalPage; i++) {
                PdfContentByte content = pdfStamper.getUnderContent(i);
                //添加文字
                BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
                content.beginText();
                content.setFontAndSize(base, 12);
                content.setTextMatrix(200, 200);
                //左边距、下边距
                content.showTextAligned(Element.ALIGN_TOP,"左页眉",25,770,0);
                content.showTextAligned(Element.ALIGN_TOP,"右页眉",425,770,0);//左边距、下边距
                //左边距、下边距
                content.showTextAligned(Element.CCITT_ENDOFBLOCK,"第   " + i + "  页\t共   " + totalPage + "页",420,20,0);
                content.endText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pdfStamper != null) {
                    pdfStamper.close();
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
