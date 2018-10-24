package com.gome.pdfgen.service.impl;

import com.gome.pdfgen.model.Document;
import com.gome.pdfgen.repository.DocumentRepository;
import com.gome.pdfgen.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author malong-ds
 * @date 2018/10/22
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveDocument(Document document) {

    }

    @Override
    public List<Document> findAll() {
        return null;
    }

    @Override
    public String createDocument(String templateId) {
        return null;
    }

    @Override
    public String fillDocumentFormData(String documentId) {
        return null;
    }
}
