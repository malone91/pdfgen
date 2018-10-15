package com.gome.freemarkrer;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by malong-ds on 2018/10/15.
 */
public class UtilTest {

    @Test
    public void generatePdf() throws FileNotFoundException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("contract", "合同的的");
        FileOutputStream out = new FileOutputStream(new File("E:/freemarker.pdf"));

        PDFTemplateUtil pdfUtil = new PDFTemplateUtil();
        pdfUtil.createPDF(data, out);
    }
}
