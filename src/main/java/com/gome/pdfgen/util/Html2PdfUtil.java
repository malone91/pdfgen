package com.gome.pdfgen.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.StringWriter;
import java.util.Properties;

/**
 *
 * @author malong-ds
 * @date 2018/10/16
 */
public class Html2PdfUtil {

    public void html2Pdf(String destPathFile) {
        //初始化参数
        Properties properties=new Properties();
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //实例化一个VelocityEngine对象
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        String vm = "contract.html";

        //设置生成pdf文件所在的文件夹位置，若没有，则自动生成
//        File destPathFile = new File("E:/");
//        if (!destPathFile.exists()) {
//            destPathFile.mkdirs();
//        }
        //目标文件,即生成的pdf文件的路径，文件名称自己取
        String destFilePath = destPathFile + File.separator + "templatePdf.pdf";

        VelocityContext context = new VelocityContext();
        context.put("contract_id", "合同编号");
        context.put("companyName", "公司名称");
        context.put("userCode", "1110000111555555");
        context.put("projectCode", "159848787861513");
        //页眉 页脚
        context.put("left-head", "代销净价合同");
        context.put("right-head", "国美零售");
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(vm, "UTF-8", context, stringWriter);
        try {
            PdfGenUtil.html2Pdf(stringWriter.toString(), destFilePath);
            //添加水印
            WaterMarkUtil.waterMark("/home/templatePdf.pdf", "/home/templatePdfWater.pdf", "国美集团");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
