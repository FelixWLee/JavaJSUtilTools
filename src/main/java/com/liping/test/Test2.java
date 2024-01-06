package com.liping.test;

import java.util.*;

/**
 * @author LiPing
 * @create 2022-05-10-21:14
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别


        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a1 = in.nextLine();
            String a2 = in.nextLine();
            String[] arr1 = a1.split("-");
            String[] arr2 = a2.split("-");

            Map<Integer, Integer> map = new HashMap<>();
            deal(arr1, map);
            deal(arr2, map);
            List<Integer> list  = new ArrayList<>();

            for (Integer integer : map.keySet()) {
                Integer i = map.get(integer);
                if (i == 4) {
                    list.add(integer);
                }
            }
            if(list.size()==0){
                System.out.println("3-4-5-6-7-8-9-10-J-Q-K-A");
                return;
            }

            int max = 0;
            int start = 3;
            for (int i = 0; i < list.size(); i++) {
                int sub = 0;
                if(i == list.size()-1){
                    sub = 15-list.get(i);
                }else{
                    sub =  list.get(i+1) -list.get(i);
                }
               if(sub>=max) {
                   max = sub;
                   start = list.get(i);
               }
            }

            if(max<=5){
                System.out.print("NO-CHAIN");
                return;
            }
            for (int i = start+1; i < max+start; i++) {
                String s = "";
                if(i == 11){
                    s = "J";
                } else if(i == 12){
                    s = "Q";
                }else if(i == 13){
                    s = "K";
                }else if(i == 14){
                    s = "A";
                }else {
                    s = String.valueOf(i);
                }

                if(i==max+start-1){
                    System.out.print(s);
                }else{
                    System.out.print(s+"-");
                }
            }
        }
    }

    private static void deal(String[] arr2, Map<Integer, Integer> map) {
        for (int i = 0; i < arr2.length; i++) {
            int temp = -1;
            if (arr2[i].equals("J")) {
                temp = 11;
            } else if (arr2[i].equals("Q")) {
                temp = 12;
            } else if (arr2[i].equals("K")) {
                temp = 13;
            } else if (arr2[i].equals("A")) {
                temp = 14;
            } else {
                temp = Integer.parseInt(arr2[i]);
            }

            if (map.containsKey(temp)) {
                map.put(temp, map.get(temp) + 1);
            } else {
                map.put(temp, 1);
            }
        }
    }


}
