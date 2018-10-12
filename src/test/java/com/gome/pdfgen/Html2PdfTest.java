package com.gome.pdfgen;

import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

/**
 * Created by malong-ds on 2018/10/11.
 */
public class Html2PdfTest {

    /**
     * html转pdf格式
     */
    @Test
    public void html2PdfTest() {
        OutputStream os = null;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("test.xhtml");
        //流转为字符串
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        try {
            for (int n; (n = inputStream.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
            String html = out.toString();
            String outputFile = "e:/a.pdf";
            os = new FileOutputStream(outputFile);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            ITextFontResolver fontResolver = renderer.getFontResolver();
            // 添加字体支持,路径可以自身项目的实际情况设置，我这里是本地项目，而且为了方便测试，就写成固定的了
            // 实际项目中，可以获取改字体所在真实的服务器的路径,这个方法是本地地址和网络地址都支持的
            // 这里面添加的是宋体
            fontResolver.addFont("simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.layout();
            renderer.createPDF(os);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
