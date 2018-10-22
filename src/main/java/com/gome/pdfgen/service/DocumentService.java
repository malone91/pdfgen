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
}
