package com.gome.util;

import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.text.pdf.BaseFont;
import org.apache.commons.io.FileUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

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
    public static void html2Pdf(String htmlContent, String filePath) throws Exception {
        FileUtils.touch(new File(filePath));
        try (OutputStream fileStream = new FileOutputStream(filePath)) {
            ITextRenderer textRenderer = new ITextRenderer();
            ITextFontResolver fontResolver = textRenderer.getFontResolver();
            textRenderer.setDocumentFromString(htmlContent);
            fontResolver.addFont("simsun.ttc", PdfName.IdentityH.getValue(), BaseFont.NOT_EMBEDDED);
            textRenderer.layout();
            textRenderer.createPDF(fileStream);
        }
    }

}
