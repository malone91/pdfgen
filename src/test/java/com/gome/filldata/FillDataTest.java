package com.gome.filldata;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import java.io.File;
import java.io.StringWriter;
import java.util.Properties;

/**
 * 博客地址 https://blog.csdn.net/my_blankness/article/details/81362439
 * 创建PDF文件，包含文字和页眉 页脚 填充空值
 * Created by malong-ds on 2018/10/11.
 */
public final class FillDataTest {

    /**
     * 将html文件转为pdf格式并且添加水印
     */
    @Test
    public void fillDataTest() {
        //初始化参数
        Properties properties=new Properties();
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //实例化一个VelocityEngine对象
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        String vm = "filldata.xhtml";

        //设置生成pdf文件所在的文件夹位置，若没有，则自动生成
        File destPathFile = new File("E:/");
        if (!destPathFile.exists()) {
            destPathFile.mkdirs();
        }
        //目标文件,即生成的pdf文件的路径，文件名称自己取
        String destFilePath = destPathFile + File.separator + "templatePdf.pdf";

        VelocityContext context = new VelocityContext();
        context.put("companyName", "公司名称");
        context.put("userCode", "1110000111555555");
        context.put("projectCode", "159848787861513");
        context.put("left-head", "左页眉");
        context.put("right-head", "右页眉");
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(vm, "UTF-8", context, stringWriter);
        try {
            PdfUtil.html2Pdf(stringWriter.toString(), destFilePath);
            WaterMarkUtil.waterMark("E:/templatePdf.pdf", "E:/templatePdfWater.pdf", "国美集团");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
