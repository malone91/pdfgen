package com.gome.pdfgen.controller;

import org.apache.commons.lang.StringUtils;

/**
 * Created by malong-ds on 2018/10/18.
 */
public class TestDiff {
    public static void main(String[] args) {
        String difference = StringUtils.difference("java", "Java");
        System.out.println(difference);
    }
}
