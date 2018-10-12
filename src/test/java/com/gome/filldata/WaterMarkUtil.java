package com.gome.filldata;

import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;

/**
 * https://blog.csdn.net/duan1309/article/details/81232300
 * Created by malong-ds on 2018/10/12.
 */
public final class WaterMarkUtil {

    /**
     * 添加水印
     * @param inputFile
     * @param outputFile
     * @param waterMarkName
     */
    public static void waterMark(String inputFile, String outputFile, String waterMarkName) {
        int interval = -5;
        try {
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));

            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",   BaseFont.EMBEDDED);

            Rectangle pageRect = null;
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.3f);
            gs.setStrokeOpacity(0.4f);
            int total = reader.getNumberOfPages() + 1;

            JLabel label = new JLabel();
            FontMetrics metrics;
            int textH = 0;
            int textW = 0;
            label.setText(waterMarkName);
            metrics = label.getFontMetrics(label.getFont());
            textH = metrics.getHeight();
            textW = metrics.stringWidth(label.getText());

            PdfContentByte under;
            for (int i = 1; i < total; i++) {
                pageRect = reader.getPageSizeWithRotation(i);
                under = stamper.getOverContent(i);
                under.saveState();
                under.setGState(gs);
                under.beginText();
                under.setFontAndSize(base, 20);

                // 水印文字成30度角倾斜
                //你可以随心所欲的改你自己想要的角度
                for (int height = interval + textH; height < pageRect.getHeight();
                     height = height + textH*3) {
                    for (int width = interval + textW; width < pageRect.getWidth() + textW;
                         width = width + textW*2) {
                        under.showTextAligned(Element.ALIGN_LEFT
                                , waterMarkName, width - textW,
                                height - textH, 30);
                    }
                }
                // 添加水印文字
                under.endText();
            }
            //说三遍
            //一定不要忘记关闭流
            //一定不要忘记关闭流
            //一定不要忘记关闭流
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
