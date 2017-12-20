package com.liping.test;

import java.io.File;
import java.io.IOException;

/**
 * 测试主类
 *
 * @author LiPing
 * @create 2017-12-19-9:17
 */
public class Test {

    public static void main(String[] args) {
        try {

            File file = new File("C://Program Files (x86)//Tesseract-OCR//shouye.png");
            String recognizeText = new OCRHelper().recognizeText(file);
            System.out.print(recognizeText + "\t");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

