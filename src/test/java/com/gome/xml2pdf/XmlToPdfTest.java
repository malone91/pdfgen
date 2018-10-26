package com.gome.xml2pdf;

import com.gome.pdfgen.util.Xml2HtmlStringUtil;
import com.gome.util.PdfUtil;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import java.io.StringWriter;

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
        engine.evaluate(context, stringWriter, "", htmlString.toString());
        PdfUtil.html2Pdf(stringWriter.toString(), "E:/templatePdf.pdf");
    }

}
