package com.gome.pdfgen.controller;

import com.gome.pdfgen.model.Document;
import com.gome.pdfgen.model.Template;
import com.gome.pdfgen.service.DocumentService;
import com.gome.pdfgen.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author malong-ds
 * @date 2018/10/22
 */
@RestController
@RequestMapping(value = "/mongo")
public class MongoTestController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private DocumentService documentService;

    /**
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findTemAll", method = RequestMethod.GET)
    public List<Template> findTemAll() {
        List<Template> templateList = templateService.findAll();
        return templateList;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/addTemplate", method = RequestMethod.GET)
    public String addTemplate() {
        Template template = new Template();
        template.setXmlContent("我是谁，我从哪里来，我要里去！");
        templateService.saveTemplate(template);
        System.out.println(template.getTemplateId());
        return "insert success";
    }

    @ResponseBody
    @RequestMapping(value = "/findDocAll", method = RequestMethod.GET)
    public List<Document> findDocAll() {
        List<Document> documentList = documentService.findAll();
        return documentList;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/addDocument", method = RequestMethod.GET)
    public String addDocument() {
        Document document = new Document();
        document.setXmlDocContent("我是谁，我从哪里来，文档！");
        documentService.saveDocument(document);
        System.out.println(document.getDocumentId());
        return "insert success";
    }
}
