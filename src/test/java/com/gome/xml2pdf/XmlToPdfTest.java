package com.gome.xml2pdf;

import com.gome.pdfgen.util.Xml2HtmlStringUtil;
import com.gome.util.PdfUtil;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.lowagie.text.Element;
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
        VelocityContext context = new VelocityContext();
        context.put("contract_id", "代销净h价合同");
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
        int totalPage = pdfReader.getNumberOfPages();
        PdfContentByte contentByte = null;
        Rectangle pageRect = null;
        int interval = -5;
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.3f);
        gs.setStrokeOpacity(0.4f);
        int total = pdfReader.getNumberOfPages() + 1;

        JLabel label = new JLabel();
        FontMetrics metrics;
        int textH = 0;
        int textW = 0;
        String waterMarkName = "水印测试";
        label.setText(waterMarkName);
        metrics = label.getFontMetrics(label.getFont());
        textH = metrics.getHeight();
        textW = metrics.stringWidth(label.getText());
        for (int i=1; i<= totalPage; i++) {
            //设置页脚
            PdfContentByte content = pdfStamper.getUnderContent(i);
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            content.beginText();
            content.setFontAndSize(base, 12);
            content.setTextMatrix(200, 200);
            content.showTextAligned(Element.CCITT_ENDOFBLOCK,"第   " + i + "  页\t共   " + totalPage + "页",300,20,0);//左边距、下边距
            content.endText();
            //设置水印
            pageRect = pdfReader.getPageSizeWithRotation(i);
            contentByte = pdfStamper.getOverContent(i);
            contentByte.saveState();
            contentByte.setGState(gs);
            contentByte.beginText();
            contentByte.setFontAndSize(base, 20);

            // 水印文字成30度角倾斜
            for (int height = interval + textH; height < pageRect.getHeight();
                 height = height + textH*3) {
                for (int width = interval + textW; width < pageRect.getWidth() + textW;
                     width = width + textW*2) {
                    contentByte.showTextAligned(com.itextpdf.text.Element.ALIGN_RIGHT
                            , waterMarkName, width - textW,
                            height - textH, 30);
                }
            }
            // 添加水印文字
            contentByte.endText();
        }
        pdfStamper.close();
        //生成文件
        PdfUtil.getFile(finalOutStream.toByteArray(), "E:",  "merge.pdf");
    }

}
