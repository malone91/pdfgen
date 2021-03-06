package com.gome.html2pdf;

import com.gome.util.PdfUtil;
import com.gome.util.WaterMarkUtil;
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
        String vm = "contract.html";

        //设置生成pdf文件所在的文件夹位置，若没有，则自动生成
        File destPathFile = new File("E:/");
        if (!destPathFile.exists()) {
            destPathFile.mkdirs();
        }
        //目标文件,即生成的pdf文件的路径，文件名称自己取
        String destFilePath = destPathFile + File.separator + "templatePdf.pdf";

        VelocityContext context = new VelocityContext();
//        context.put("contract_id", "20181025");
//        context.put("category", "categoryValue");
//        context.put("brand", "brandValue");
//        context.put("gross_profit", "gross_profit_value");
//        context.put("po_comp", "po_comp_value");
//        context.put("po_change_days", "po_change_days_value");
//        context.put("term_days", "term_days_value");
//        context.put("loan_settle_option", "loan_settle_option_value");
//        context.put("free_interest_bank_months", "free_interest_bank_months_value");
//        context.put("free_interest_com_months", "free_interest_com_months_value");
//        context.put("price_cut_days", "price_cut_days_value");
//        context.put("promotion_days", "promotion_days_value");
//        context.put("price_cut_option", "price_cut_option_value");
//        context.put("terms_other", "terms_other_value");
//        context.put("sign_location", "sign_location_value");
//        context.put("con_date_begin", "con_date_begin_value");
//        context.put("con_date_end", "con_date_end_value");
        //页眉 页脚
        context.put("contract_id", "代销净h价合同");
        context.put("contract_id", "abc");
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(vm, "UTF-8", context, stringWriter);
        try {
            PdfUtil.html2Pdf(stringWriter.toString(), destFilePath);
            //添加水印
            WaterMarkUtil.waterMark("E:/templatePdf.pdf", "E:/templatePdfWater.pdf", "国美集团");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
