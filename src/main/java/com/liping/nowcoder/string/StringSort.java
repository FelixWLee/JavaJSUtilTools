package com.liping.nowcoder.string;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/5190a1db6f4f4ddb92fd9c365c944584?tpId=37&tqId=21249&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D3%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=3&judgeStatus=undefined&tags=&title=
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 *
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 *
 * 如，输入： Type 输出： epTy
 *
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 *
 * 如，输入： BabA 输出： aABb
 *
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 *
 * 如，输入： By?e 输出： Be?y
 *
 * 输入描述：
 * 输入字符串
 * 输出描述：
 * 输出字符串
 *
 * @author LiPing
 * @create 2023-03-08-10:32
 */
public class StringSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        in.close();
        stringSort(s);

    }

    private static void stringSort(String s) {
        // 先过滤出英文字母
        List<Character> characterList = new ArrayList<>();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (Character.isLetter(c)) {
                characterList.add(c);
            }
        }

        // 再用英文字母排序
        characterList.sort(Comparator.comparingInt(Character::toUpperCase));

        // 最后判断是否为非字母，进行填充
        StringBuffer sb = new StringBuffer();
        for (int i = 0, j = 0; i < s.length(); i++) {

            if (Character.isLetter(charArray[i])) {
                sb.append(characterList.get(j++));
            } else {
                sb.append(charArray[i]);
            }
        }
        System.out.println(sb.toString());
    }


}
