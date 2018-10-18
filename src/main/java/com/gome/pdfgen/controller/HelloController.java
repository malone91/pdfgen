package com.gome.pdfgen.controller;

import com.gome.pdfgen.util.Html2PdfUtil;
import com.gome.pdfgen.util.PdfHeaderFooterUtil;
import com.gome.pdfgen.util.PdfMergeUtil;
import com.gome.pdfgen.util.Xml2HtmlUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        Xml2HtmlUtil.transferXml2Html("xml2html/contract.xml", "xml2html/contract.xsl", "E:/contract.html");
        return "hello world";
    }

    /**
     * html转pdf
     * @return
     */
    @RequestMapping(value = "/html2Pdf", method = RequestMethod.GET)
    public String html2Pdf() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("contract_id", "合同编号");
        map.put("companyName", "公司名称");
        map.put("userCode", "1110000111555555");
        map.put("projectCode", "159848787861513");
        //页眉 页脚
        map.put("left-head", "代销净价合同");
        map.put("right-head", "国美零售");
        Html2PdfUtil.html2Pdf("contract.html", "E:", "target.pdf", "国美集团", map);
        return "hello world";
    }

    /**
     * 合并pdf
     * @return
     */
    @RequestMapping(value = "/mergePdf", method = RequestMethod.GET)
    public String mergePdf() {
        String[] files = { "e:\\1.pdf", "e:\\2.pdf","e:\\3.pdf", "e:\\4.pdf" };
        String targetPath = "e:\\temp.pdf";
        PdfMergeUtil.mergePdfFiles(files, targetPath);
        //单独给合并的文件添加页眉 页脚
        try {
            PdfHeaderFooterUtil.setPageHeaderAndFooter(targetPath, "E:/page.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello world";
    }
}
