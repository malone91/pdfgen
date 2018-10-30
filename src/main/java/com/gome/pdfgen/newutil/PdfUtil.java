package com.gome.pdfgen.newutil;

import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.swing.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.*;
import java.util.Map;


@Slf4j
public final class PdfUtil {

    /**
     * return htmlString from xmlString and xsl file
     * @param xmlContent
     * @param xslPath
     * @return
     */
    public static String getHtmlContentByXsl(String xmlContent, String xslPath) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlContent.getBytes());
        Source source = new StreamSource(byteArrayInputStream);
        InputStream xslStream = null;
        //创建xsl输入流
        xslStream = PdfUtil.class.getClassLoader().getResourceAsStream(xslPath);
        Source template = new StreamSource(xslStream);
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(template);
            StringWriter sw = new StringWriter();
            Result resulted = new StreamResult(sw);
            transformer.setOutputProperty("encoding", "UTF-8");
            transformer.transform(source, resulted);
            return sw != null ? sw.toString() : null;
        } catch (TransformerException e) {
            log.error("Caught Exception ", e);
        } finally {
            if (xslStream != null) {
                try {
                    xslStream.close();
                } catch (IOException e) {
                    log.error("Caught Exception ", e);
                }
            }
        }
        return null;
    }

    /**
     * return pdf byte[] from html string
     * 根据html返回pdf字节数组
     * @param htmlContent
     * @return
     * @throws Exception
     */
    public static byte[] getPdfByteFromHtml(String htmlContent) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ITextRenderer textRenderer = new ITextRenderer();
        ITextFontResolver fontResolver = textRenderer.getFontResolver();
        textRenderer.setDocumentFromString(htmlContent);
        try {
            fontResolver.addFont("simsun.ttc", PdfName.IdentityH.getValue(), BaseFont.NOT_EMBEDDED);
            textRenderer.layout();
            textRenderer.createPDF(byteArrayOutputStream);
        } catch (DocumentException e) {
            log.error("Caught Exception ", e);
        } catch (IOException e) {
            log.error("Caught Exception ", e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * return string from htmlString and map(need to optimizing this data structure)
     * @param htmlString
     * @return
     */
    public static String getStringAfterFillingData(String htmlString, Map<String, String> map) {
        VelocityContext context = new VelocityContext();
        context.put("contract_id", "abc");
        VelocityEngine engine = new VelocityEngine();
        engine.init();
        StringWriter stringWriter = new StringWriter();
        engine.evaluate(context, stringWriter, "", htmlString);
        return stringWriter.toString();
    }

    /**
     * return pdf bytes from some htmlString array
     * @param htmlSourceArray
     * @return
     */
    public static byte[] getPdfBytesByHtmlArray(String[] htmlSourceArray) {
        if (htmlSourceArray == null) {return  null;}
        PDFMergerUtility mergerUtility = new PDFMergerUtility();
        for (String htmlContent : htmlSourceArray) {
            byte[] pdfByte = getPdfByteFromHtml(htmlContent);
            mergerUtility.addSource(new ByteArrayInputStream(pdfByte));
        }
        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        mergerUtility.setDestinationStream(outputStream);
        try {
            mergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
            return outputStream.toByteArray();
        } catch (IOException e) {
            log.error("Caught Exception ", e);
        }
        return null;
    }

    /**
     * add water mark or footer on pdf bytes
     * @param pdfByte
     * @param waterMarkName
     * @param footerAddNecessary
     * @return
     */
    public static byte[] addWaterMarkOrFooter(byte[] pdfByte, String waterMarkName, boolean footerAddNecessary) {
        PdfStamper pdfStamper = null;
        PdfReader pdfReader = null;
        try {
            //read file
            pdfReader = new PdfReader(pdfByte);
            ByteArrayOutputStream finalOutStream = new ByteArrayOutputStream();
            pdfStamper = new PdfStamper(pdfReader, finalOutStream);
            int interval = -5;
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.3f);
            gs.setStrokeOpacity(0.4f);

            JLabel label = new JLabel();
            FontMetrics metrics;
            int textH = 0;
            int textW = 0;
            label.setText(waterMarkName);
            metrics = label.getFontMetrics(label.getFont());
            textH = metrics.getHeight();
            textW = metrics.stringWidth(label.getText());
            PdfContentByte printPlace = null;
            PdfContentByte footer = null;
            com.itextpdf.text.Rectangle pageRect = null;
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            int totalPage = pdfReader.getNumberOfPages();
            for (int i=1; i<= totalPage; i++) {
                //set water mark
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
                //set footer
                if (footerAddNecessary) {
                    footer = pdfStamper.getUnderContent(i);
                    footer.beginText();
                    footer.setFontAndSize(base, 12);
                    footer.setTextMatrix(200, 200);
                    //左边距、下边距
                    footer.showTextAligned(Element.CCITT_ENDOFBLOCK, i + "\t" + totalPage, 300,20,0);
                    footer.endText();
                }
            }
            return finalOutStream.toByteArray();
        } catch (IOException e) {
            log.error("Caught Exception ", e);
        } catch (com.itextpdf.text.DocumentException e) {
            log.error("Caught Exception ", e);
        } finally {
            if (pdfStamper != null) {
                try {
                    pdfStamper.close();
                } catch (com.itextpdf.text.DocumentException e) {
                    log.error("Caught Exception ", e);
                } catch (IOException e) {
                    log.error("Caught Exception ", e);
                }
            }
            if (pdfReader != null) {
                pdfReader.close();
            }
        }
        return  null;
    }

    /**
     * generate pdf file from byte
     * @param pdfByte
     * @param filePath
     * @param fileName
     */
    public static void touchPdf(byte[] pdfByte, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath + File.separator + fileName);
            bos = new BufferedOutputStream(fos);
            bos.write(pdfByte);
        } catch (IOException e) {
            log.error("Caught Exception ", e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.error("Caught Exception ", e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
