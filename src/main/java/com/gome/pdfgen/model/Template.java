package com.gome.pdfgen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 *
 * @author malong-ds
 * @date 2018/10/22
 */
@Data
public class Template {

    @Id
    private String templateId;

    private String xmlContent;
}
