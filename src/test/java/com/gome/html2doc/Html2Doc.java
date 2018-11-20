package com.gome.html2doc;

import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by malong-ds on 2018/11/6.
 */
public class Html2Doc {
    public static void main(String[] args) throws Docx4JException {
        InputStream inputStream = Html2Doc.class.getClassLoader().getResourceAsStream("contract.html");
        // To docx, with content controls
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

        XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
        wordMLPackage.getMainDocumentPart().getContent().addAll(XHTMLImporter.convert(inputStream, null) );
//        String classPath = this.getClass().getClassLoader().c
        wordMLPackage.save(new java.io.File("E://sample.docx"));
    }

    @Test
    public void docx2Html() {

    }
}
