package com.gome.pdfgen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 *
 * @author malong-ds
 * @date 2018/10/22
 */
@Data
public class Document {

    @Id
    private String documentId;

    private String xmlDocContent;
}
