package com.gome.xml2pdf;

import com.gome.pdfgen.util.Xml2HtmlStringUtil;
import com.gome.util.PdfUtil;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by malong-ds on 2018/10/26.
 */
public class XmlToPdfTest {

    @Test
    public void generatePdf() throws Exception {
        String htmlString = Xml2HtmlStringUtil.transferXml2Html("xml2html/1026.xml", "xml2html/1026.xsl");
//        String htmlString = Xml2HtmlStringUtil.transferXmlString2Html("xml2html/1026.xsl");
        VelocityContext context = new VelocityContext();
        context.put("contract_id", "abc");
        VelocityEngine engine = new VelocityEngine();
        engine.init();
        StringWriter stringWriter = new StringWriter();
//        String doubleFile = htmlString + htmlString;
        engine.evaluate(context, stringWriter, "", htmlString);
        //第一种方法
//        PdfUtil.html2Pdf(stringWriter.toString(), "E:/templatePdf.pdf");
        //第二种方法，采用字节的形式
        byte[] pdfByte = PdfUtil.getPdfStreamFromHtml(stringWriter.toString());
        PDFMergerUtility mergerUtility = new PDFMergerUtility();
        for (int i=0; i< 2; i++) {
            mergerUtility.addSource(new ByteArrayInputStream(pdfByte));
        }
        //可以设置为流
        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        mergerUtility.setDestinationStream(outputStream);
        mergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        //读取文件
        PdfReader pdfReader = new PdfReader(outputStream.toByteArray());
        //处理页脚 和 水印
        ByteArrayOutputStream finalOutStream = new ByteArrayOutputStream();
        PdfStamper pdfStamper = new PdfStamper(pdfReader, finalOutStream);
        int interval = -5;
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.3f);
        gs.setStrokeOpacity(0.4f);

        JLabel label = new JLabel();
        FontMetrics metrics;
        int textH = 0;
        int textW = 0;
        String waterMarkName = "水印测试";
        label.setText(waterMarkName);
        metrics = label.getFontMetrics(label.getFont());
        textH = metrics.getHeight();
        textW = metrics.stringWidth(label.getText());
        int totalPage = pdfReader.getNumberOfPages();
        PdfContentByte printPlace = null;
        PdfContentByte footer = null;
        Rectangle pageRect = null;
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        for (int i=1; i<= totalPage; i++) {
            //设置页脚
            footer = pdfStamper.getUnderContent(i);
            footer.beginText();
            footer.setFontAndSize(base, 9);
            footer.setTextMatrix(200, 200);
            //左边距、下边距
            footer.showTextAligned(Element.CCITT_ENDOFBLOCK, i + "/" + totalPage, 300, 20, 0);
            footer.endText();

            //设置水印
            pageRect = pdfReader.getPageSizeWithRotation(i);
            printPlace = pdfStamper.getOverContent(i);
            printPlace.saveState();
            printPlace.setGState(gs);
            printPlace.beginText();
            printPlace.setFontAndSize(base, 20);
            // 水印文字成30度角倾斜
            for (int height = interval + textH; height < pageRect.getHeight();
                 height = height + textH*3) {
                for (int width = interval + textW; width < pageRect.getWidth() + textW;
                     width = width + textW*2) {
                    printPlace.showTextAligned(com.itextpdf.text.Element.ALIGN_RIGHT
                            , waterMarkName, width - textW,
                            height - textH, 30);
                }
            }
            printPlace.endText();
        }
        pdfStamper.close();
        //生成文件
        PdfUtil.getFile(finalOutStream.toByteArray(), "E:",  "merge.pdf");
    }

}
