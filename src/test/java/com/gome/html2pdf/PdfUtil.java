package com.gome.html2pdf;

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

            String agreementBody = htmlContent;
            agreementBody = agreementBody.replace("&nbsp;", " ");
            agreementBody = agreementBody.replace("&ndash;", "–");
            agreementBody = agreementBody.replace("&mdash;", "—");
            agreementBody = agreementBody.replace("&lsquo;", "‘"); // left single quotation mark
            agreementBody = agreementBody.replace("&rsquo;", "’"); // right single quotation mark
            agreementBody = agreementBody.replace("&sbquo;", "‚"); // single low-9 quotation mark
            agreementBody = agreementBody.replace("&ldquo;", "“"); // left double quotation mark
            agreementBody = agreementBody.replace("&rdquo;", "”"); // right double quotation mark
            agreementBody = agreementBody.replace("&bdquo;", "„"); // double low-9 quotation mark
            agreementBody = agreementBody.replace("&prime;", "′"); // minutes
            agreementBody = agreementBody.replace("&Prime;", "″"); // seconds
            agreementBody = agreementBody.replace("&lsaquo;", "‹"); // single left angle quotation
            agreementBody = agreementBody.replace("&rsaquo;", "›"); // single right angle quotation
            agreementBody = agreementBody.replace("&oline;", "‾"); // overline

            textRenderer.setDocumentFromString(agreementBody);
            fontResolver.addFont("simsun.ttc", PdfName.IdentityH.getValue(), BaseFont.NOT_EMBEDDED);
            textRenderer.layout();
            textRenderer.createPDF(fileStream);
        }
    }

}
