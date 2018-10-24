package com.gome.pdfgen.service;

import com.gome.pdfgen.model.Document;
import com.gome.pdfgen.model.Template;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author malong-ds
 * @date 2018/10/22
 */
@Repository
public interface DocumentService {

    void saveDocument(Document document);

    List<Document> findAll();

    /**
     * 场景1：选择合同模板后点击生成文档，后台业务操作根据模板id生成文档，设置文档的模板id并存入mongo中，并返回生成文档的documentId
     * 存xml
     * @param templateId
     * @return
     */
    String createDocument(String templateId);

    /**
     * 场景2：填写表单信息，点击提交后将填充的信息，将填写的数据与文档的blank整合，生成合同contract，并且返回生成合同的contractId
     * 此场景不涉及文档模板的修改，只是merge data to document to generate contract
     * 修改xml
     * @param documentId
     * @return
     */
    String fillDocumentFormData(String documentId);


}
