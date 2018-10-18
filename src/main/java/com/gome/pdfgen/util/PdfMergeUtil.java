package com.gome.pdfgen.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;

/**
 *
 * @author malong-ds
 * @date 2018/10/17
 */
public class PdfMergeUtil {
    /**
     *
     * @param files
     * @param targetPath
     * @return
     */
    public static boolean mergePdfFiles(String[] files, String targetPath) {
        boolean retValue = false;
        Document document = null;
        try {
            document = new Document(new PdfReader(files[0]).getPageSize(1));
            FileOutputStream fileOutputStream = new FileOutputStream(targetPath);
            PdfCopy copy = new PdfCopy(document, fileOutputStream);
            document.open();
            for (int i = 0; i < files.length; i++) {
                PdfReader reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return retValue;
    }
}
