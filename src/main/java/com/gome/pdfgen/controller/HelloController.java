package com.gome.pdfgen.controller;

import com.gome.pdfgen.util.Html2PdfUtil;
import com.gome.pdfgen.util.Xml2HtmlUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by malong-ds on 2018/10/16.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello() {
        return "hello world";
    }

    /**
     * xml转html
     * @return
     */
    @RequestMapping(value = "/xml2Html", method = RequestMethod.GET)
    public String xml2Html() {
        Xml2HtmlUtil xml2HtmlUtil = new Xml2HtmlUtil();
        xml2HtmlUtil.transferXml2Html("/home/contract.html");
        return "hello world";
    }

    /**
     * html转pdf
     * @return
     */
    @RequestMapping(value = "/html2Pdf", method = RequestMethod.GET)
    public String html2Pdf() {
        Html2PdfUtil html2PdfUtil = new Html2PdfUtil();
        html2PdfUtil.html2Pdf("/home");
        return "hello world";
    }
}
