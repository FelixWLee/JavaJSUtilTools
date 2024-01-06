package com.liping.nowcoder.string;

import java.util.Scanner;

/**
 * 描述
 * 计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
 * 输入描述：
 * 输入一行，代表要计算的字符串，非空，长度小于5000。
 * <p>
 * 输出描述：
 * 输出一个整数，表示输入字符串最后一个单词的长度。
 * <p>
 * 输入：
 * hello nowcoder
 * 输出：
 * 8
 * 说明：
 * 最后一个单词为nowcoder，长度为8
 *
 * @author LiPing
 * @create 2023-03-08-10:32
 */
public class GetLastStringLength {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String a = in.nextLine();
//            System.out.println(a);
            getLastStringLength(a);
        }

    }

    private static void getLastStringLength(String s) {

        if (!s.isEmpty()) {
            String[] arr = s.split(" ");
            System.out.println(arr[arr.length - 1].length());

        }


    }


}
