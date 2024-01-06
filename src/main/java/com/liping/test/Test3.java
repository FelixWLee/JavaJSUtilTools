package com.liping.test;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author LiPing
 * @create 2023-03-03-20:59
 *
 * Int[] a = {1, 3, 5, 7}
 * Int[] b = {2, 40, 60, 90}
 *
 * Int findK(int[] a, int[] b, int K) {
 * // 返回第K小的值
 * }
 */
public class Test3 {

    public static void main(String[] args){
//        int[] a = {1, 3, 5, 7};
//        int[] b = {2, 40, 60, 90};
//        findK(a, b, 5);


//        String a1 = "acv";
//        boolean subsequence = isSubsequence("acf", "abcdv");
//        System.out.println(subsequence);

        System.out.println(reversePrefix("abcdefd", 'd'));
    }

    /**
     * 输入：word = "abcdefd", ch = "d"
     * 输出："dcbaefd"
     * 解释："d" 第一次出现在下标 3 。 
     * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "dcbaefd" 。
     */
    public static String reversePrefix(String word, char ch) {

//        int i = 0;
//        while( i < word.length()) {
//
//            if (ch==word.charAt(i)) {
//                break;
//            }
//            i++;
//        }
//
//        if (i == word.length()) {
//            return word;
//        } else {
//            String substring = word.substring(0, i + 1);
//            String otherString = word.substring(i + 1);
//            StringBuffer sb = new StringBuffer(substring);
//            return sb.reverse().toString() + otherString;
//        }

        int index = word.indexOf(ch);
        if (index > 0) {
            char[] arr = word.toCharArray();
            int left = 0;
            int right = index;
            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                left++;
                right--;
            }
            word = new String(arr);
        }
        return word;
    }

    public static boolean isSubsequence(String s, String t) {

        char[] s1 = new char[s.length()];
        s.getChars(0, s.length(), s1, 0);

        char[] t1 = new char[t.length()];
        t.getChars(0, t.length(), t1, 0);
        int k = 0;
        for(int i = 0; i < s1.length; i++) {

            boolean isExit = false;

            for(int j = k; j < t1.length; j = k) {

                if (!isExit) {
                    if(s1[i] != t1[j]) {
                        k++;
                    } else {
                        isExit = true;
                        k = ++j;
                    }
                } else {break;}

            }

            if(!isExit) {
                return false;
            }
        }

        return true;
    }



    public static void findK(int[] a, int[] b, int K) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int j : a) {
            set.add(j);
        }

        for (int j : b) {
            set.add(j);
        }

        if (!set.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>(set);
            System.out.println(list.get(K-1));
        }

    }
}
