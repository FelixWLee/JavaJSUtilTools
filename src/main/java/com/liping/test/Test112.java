package com.liping.test;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author LiPing
 * @create 2023-03-09-20:05
 */
public class Test112 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] inArray = input.split(" ");
        int s = Integer.parseInt(inArray[0]);
        int t = Integer.parseInt(inArray[1]);
        int a = Integer.parseInt(inArray[2]);
        int b = Integer.parseInt(inArray[3]);

        TreeSet<Integer> treeSet = new TreeSet<Integer>();

        for (int aNum = 1; aNum <= 10000; aNum++) {
            for (int bNum = 1; bNum <= 10000; bNum++) {
                if (s + a * aNum + b * bNum == t) {
                    treeSet.add(aNum);
                }

                if (s + a * aNum - b * bNum == t) {
                    treeSet.add(aNum);
                }
                if (s - a * aNum + b * bNum == t) {
                    treeSet.add(aNum);
                }
                if (s - a * aNum + b * bNum == t) {
                    treeSet.add(aNum);
                }
            }

        }

        if (!treeSet.isEmpty()) {
            System.out.println(treeSet.first());
        }
    }



}
