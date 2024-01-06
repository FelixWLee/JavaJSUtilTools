package com.liping.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author LiPing
 * @create 2023-03-09-20:05
 */
public class Test1123 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String numStr = in.nextLine();
        int line = Integer.parseInt(numStr);

        int num = 0;

        Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < line; i++) {
            String str = in.nextLine();
            String input[] = str.split(" ");
            String s1 = input[0];
            if ("IN".equals(s1)) {
                num++;
                int p = Integer.parseInt(input[1]);
                int pro = Integer.parseInt(input[2]);
                TreeMap<Integer, Integer> integerIntegerMap = map.get(p);
                if (integerIntegerMap == null) {
                    integerIntegerMap = new TreeMap<>();
                }
                integerIntegerMap.put(pro, num);
                map.put(p, integerIntegerMap);
            }
            if ("OUT".equals(s1)) {
                int p = Integer.parseInt(input[1]);
                TreeMap<Integer, Integer> treeMap = map.get(p);
                if (treeMap.size() == 0) {
                   System.out.println("NULL");
                   return;
                }
                Integer key = treeMap.lastEntry().getValue();
                System.out.println(key);
                System.out.println("\r\n");
                treeMap.remove(treeMap.lastEntry().getKey());
            }
        }
    }



}
