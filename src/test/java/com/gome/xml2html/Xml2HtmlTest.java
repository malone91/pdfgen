package com.gome.xml2html;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by malong-ds on 2018/10/16.
 */
public class Xml2HtmlTest {

    @Test
    public void transferXml2Html() {
        InputStream inputStream = null;
        InputStream stream = null;
        //创建xml文件输入流
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream("xml2html/wordnew.xml");
            Source source = new StreamSource(inputStream);
            //创建xsl输入流
            stream = this.getClass().getClassLoader().getResourceAsStream("xml2html/word.xsl");
            Source template = new StreamSource(stream);

            PrintStream stm = new PrintStream(new File("E:/test.xml"));
            //输出到html
            Result result = new StreamResult(stm);
            //根据XSL文件创建准个转换对象
            Transformer transformer= TransformerFactory.newInstance().newTransformer(template);
            //处理xml进行交换
            //创建文件转换对象
            transformer.transform(source, result);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFreemarker() throws IOException, TemplateException {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("contract_id", "201826323");
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(this.getClass(), "");  //FTL文件所存在的位置
        Template t = configuration.getTemplate("template.ftl"); //文件名
        File outFile = new File("E:/a.doc");  //导出文档的存放位置
        Writer out = null;
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        t.process(dataMap, out);
    }
}
