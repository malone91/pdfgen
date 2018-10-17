package com.gome.mergepdf;




import com.gome.util.PdfPageNumberUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;

/**
 * 地址 ： https://www.cnblogs.com/pocketbook/p/6427579.html
 * Created by malong-ds on 2018/10/17.
 */
public class PdfMergeTest {

    public static void main(String[] args) {
        String[] files = { "e:\\1.pdf", "e:\\2.pdf","e:\\3.pdf", "e:\\4.pdf" };
        String savepath = "e:\\temp.pdf";
        mergePdfFiles(files, savepath);
    }

    /**
     *
     * @param files
     * @param newfile
     * @return
     */
    public static boolean mergePdfFiles(String[] files, String newfile) {
        boolean retValue = false;
        Document document = null;
        try {
            document = new Document(new PdfReader(files[0]).getPageSize(1));
            FileOutputStream fileOutputStream = new FileOutputStream(newfile);
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
            document.close();
        }
        try {
            PdfPageNumberUtil.setPageHeaderAndFooter(newfile, "E:/page.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retValue;
    }
}
