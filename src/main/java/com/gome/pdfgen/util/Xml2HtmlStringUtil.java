package com.gome.pdfgen.util;


import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * XML依赖XML转为HTML文件，页眉页脚由工具类进行控制
 * @author malong-ds
 * @date 2018/10/16
 */
public final class Xml2HtmlStringUtil {

    /**
     * 获取xml和xsl生成的字符串
     * @return
     */
    public static String transferXml2Html(String xmlPath, String xslPath) {
        // 实例化 DocumentBuilderFactory 对象
        InputStream inputStream = null;
        InputStream stream = null;
        try {
            inputStream = Xml2HtmlStringUtil.class.getClassLoader().getResourceAsStream(xmlPath);
            Source source = new StreamSource(inputStream);
            //创建xsl输入流
            stream = Xml2HtmlStringUtil.class.getClassLoader().getResourceAsStream(xslPath);
            Source template = new StreamSource(stream);
            Transformer transformer = TransformerFactory.newInstance().newTransformer(template);
            StringWriter sw = new StringWriter();
            Result resulted = new StreamResult(sw);
            transformer.setOutputProperty("encoding", "UTF-8");
            transformer.transform(source, resulted);
            return sw != null ? sw.toString() : null;
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
        return null;
    }

}
