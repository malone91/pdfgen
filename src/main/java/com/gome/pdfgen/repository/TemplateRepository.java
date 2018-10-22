package com.gome.pdfgen.repository;

import com.gome.pdfgen.model.Template;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author malong-ds
 * @date 2018/10/22
 */
public interface TemplateRepository extends MongoRepository<Template, String> {

}
