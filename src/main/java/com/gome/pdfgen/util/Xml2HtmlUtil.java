package com.gome.pdfgen.util;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * XML依赖XML转为HTML文件，页眉页脚由工具类进行控制
 * @author malong-ds
 * @date 2018/10/16
 */
public final class Xml2HtmlUtil {

    /**
     * xml转html  for linux os
     */
    public static void transferXml2Html(String xmlPath, String xslPath, String target) {
        InputStream inputStream = null;
        InputStream stream = null;
        //创建xml文件输入流
        try {
            inputStream = Xml2HtmlUtil.class.getClassLoader().getResourceAsStream(xmlPath);
            Source source = new StreamSource(inputStream);
            //创建xsl输入流
            stream = Xml2HtmlUtil.class.getClassLoader().getResourceAsStream(xslPath);
            Source template = new StreamSource(stream);

            PrintStream stm = new PrintStream(new File(target));
            //输出到html
            //讲转换后的结果输出到 stm 中即 F:\123.html
            Result result=new StreamResult(stm);
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
}
