package com.gome.pdfgen.service.impl;

import com.gome.pdfgen.model.Template;
import com.gome.pdfgen.repository.TemplateRepository;
import com.gome.pdfgen.service.TemplateService;
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
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Template> findAll() {
        return mongoTemplate.findAll(Template.class);
    }

    @Override
    public void saveTemplate(Template template) {
        templateRepository.save(template);
    }
}
