package com.gome.util;

import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.text.pdf.BaseFont;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

/**
 * 需要更换core-render的jar包
 * Created by malong-ds on 2018/10/11.
 */
public final class PdfUtil {

    /**
     * html转为pdf
     * @param htmlContent
     * @param filePath
     * @throws Exception
     */
    public static Document html2Pdf(String htmlContent, String filePath) throws Exception {
        FileUtils.touch(new File(filePath));
        try (OutputStream fileStream = new FileOutputStream(filePath)) {
            ITextRenderer textRenderer = new ITextRenderer();
            ITextFontResolver fontResolver = textRenderer.getFontResolver();
            textRenderer.setDocumentFromString(htmlContent);
            fontResolver.addFont("simsun.ttc", PdfName.IdentityH.getValue(), BaseFont.NOT_EMBEDDED);
            textRenderer.layout();
            textRenderer.createPDF(fileStream);
            //获取生成的pdf文件的流
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(htmlContent.getBytes()));
            return document;
        }
    }

    /**
     * 根据html返回pdf字节数组
     * @param htmlContent
     * @return
     * @throws Exception
     */
    public static byte[] getPdfStreamFromHtml(String htmlContent) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ITextRenderer textRenderer = new ITextRenderer();
        ITextFontResolver fontResolver = textRenderer.getFontResolver();
        textRenderer.setDocumentFromString(htmlContent);
        fontResolver.addFont("simsun.ttc", PdfName.IdentityH.getValue(), BaseFont.NOT_EMBEDDED);
        textRenderer.layout();
        textRenderer.createPDF(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
