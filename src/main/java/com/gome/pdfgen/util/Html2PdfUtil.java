package com.gome.pdfgen.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * HTML转为PDF工具咧
 * @author malong-ds
 * @date 2018/10/16
 */
public class Html2PdfUtil {

    public static void html2Pdf(String htmlSource, String diskPath, String targetFileName, String waterMark, Map<String, String> map) {
        //初始化参数
        Properties properties = new Properties();
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //实例化一个VelocityEngine对象
        VelocityEngine velocityEngine = new VelocityEngine(properties);

        //设置生成pdf文件所在的文件夹位置，若没有，则自动生成
//        File destPathFile = new File("E:/");
//        if (!destPathFile.exists()) {
//            destPathFile.mkdirs();
//        }

        //目标文件,即生成的pdf文件的路径，文件名称自己取
        String destFilePath = diskPath + File.separator + targetFileName;

        VelocityContext context = new VelocityContext();
        context.put("contract_id", map.get("contract_id"));
        context.put("companyName", map.get("companyName"));
        context.put("userCode", map.get("userCode"));
        context.put("projectCode", map.get("projectCode"));
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(htmlSource, "UTF-8", context, stringWriter);
        try {
            PdfGenUtil.html2Pdf(stringWriter.toString(), destFilePath);
            //添加水印
            WaterMarkUtil.waterMark(destFilePath, "E:/water.pdf", waterMark);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
